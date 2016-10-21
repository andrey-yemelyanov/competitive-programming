package helvidios.cp.ch1.adhoc.game.harder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _339_SameGameSimulation {
	public static final int CONNECTED = -2;
	public static final int EMPTY = -1;
	public static void main(String... args){
		String data = "3 5\r\n" + 
				"1 2 3 5 5\r\n" + 
				"2 2 3 5 1\r\n" + 
				"1 3 5 2 2\r\n" + 
				"3 5\r\n" + 
				"2 2\r\n" + 
				"1 2\r\n" + 
				"1 2\r\n" + 
				"1 1\r\n" + 
				"0 0\r\n" + 
				"\r\n" + 
				"3 5\r\n" + 
				"1 2 3 5 5\r\n" + 
				"2 2 3 5 1\r\n" + 
				"1 3 5 2 2\r\n" + 
				"2 2    1 2   1 4   1 2   \r\n" + 
				"99 99 0 0\r\n" + 
				"\r\n" + 
				"4 3\r\n" + 
				"1 4 4\r\n" + 
				"4 4 2\r\n" + 
				"1 2 3\r\n" + 
				"3 1 3\r\n" + 
				"1 2 1 1 1 3 1 1 0 0\r\n" + 
				"0 0\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int index = 0;
		while(scanner.hasNext()){
			int nRows = scanner.nextInt();
			int nCols = scanner.nextInt();
			if(nRows == 0 && nCols == 0) break;
			int[][] grid = new int[nRows][nCols];
			for(int row = nRows - 1; row >= 0; row--){
				for(int col = 0; col < nCols; col++){
					grid[row][col] = scanner.nextInt();
				}
			}
			List<int[]> removals = new ArrayList<int[]>();
			while(true){
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				if(row == 0 && col == 0) break;
				removals.add(new int[]{row,col});
			}
			playSameGame(grid, removals.toArray(new int[0][0]), ++index);
		}
		scanner.close();
	}
	
	public static void playSameGame(int[][] grid, int[][] removals, int index){
		play(grid, removals);
		if(index > 1) System.out.println();
		System.out.println("Grid " + index + ".");
		String indent = "    ";
		if(gridEmpty(grid)){
			System.out.println(indent + "Game Won");
		}else{
			System.out.println(printGrid(grid, indent));
		}
	}
	
	/**
	 * Plays the SameGame and returns the grid after applying a sequence of removals.
	 */
	public static void play(int[][] grid, int[][] removals){
		for (int[] removal : removals) {
			int row = grid.length - removal[0];
			int col = removal[1] - 1;
			if(markConnectedRegion(grid, row, col)){
				collapse(grid);
				if(gridEmpty(grid)) break;
			}
		}
	}
	
	public static String printGrid(int[][] grid, String indent){
		StringBuilder s = new StringBuilder();
		for(int row = 0; row < grid.length; row++){	
			s.append(indent);
			for(int col = 0; col < grid[0].length; col++){
				if(grid[row][col] == EMPTY){
					s.append(" ");
				}else{
					s.append(grid[row][col]);
				}
				if(col < grid[0].length - 1){
					s.append(" ");
				}
			}
			if(row < grid.length - 1)
				s.append("\n");
		}
		return s.toString();
	}
	
	/**
	 * Returns true if this grid is empty, i.e. contains no integers from 0 to 9.
	 */
	public static boolean gridEmpty(int[][] grid){
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				if(grid[row][col] != EMPTY){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Marks all cells (with flag value {@code CONNECTED=-2}) that can be reached by moving horizontally 
	 * (left or right) and/or vertically (up or down) from a given 
	 * cell specified by row and col, subject to the restriction 
	 * that all cells in the connected region must contain the same value.
	 * Ignores invalid removals i.e. non-existant cells or regions with fewer than two cells. 
	 * Returns true if connected region has been discovered.
	 */
	public static boolean markConnectedRegion(int[][] grid, int row, int col){
		if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length)
			return false;
		int cellVal = grid[row][col];
		int cellCount = markConnectedRegionRecursive(grid, row, col, 0, cellVal);
		// restore source cell value if connected region size is 1
		if(cellCount == 1) {
			grid[row][col] = cellVal;
			return false;
		}
		return true;
	}
	
	private static int markConnectedRegionRecursive(
			int[][] grid, int row, int col, int cellCount, int sourceCellVal){
		if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length)
			return cellCount;
		int cellVal = grid[row][col];
		if(cellVal == EMPTY) return cellCount;
		if(cellVal != sourceCellVal) return cellCount;
		
		cellCount++;
		grid[row][col] = CONNECTED;
		
		// explore up
		cellCount = markConnectedRegionRecursive(grid, row - 1, col, cellCount, sourceCellVal);
		// explore down
		cellCount = markConnectedRegionRecursive(grid, row + 1, col, cellCount, sourceCellVal);
		// explore left
		cellCount = markConnectedRegionRecursive(grid, row, col - 1, cellCount, sourceCellVal);
		// explore right
		cellCount = markConnectedRegionRecursive(grid, row, col + 1, cellCount, sourceCellVal);
		
		return cellCount;
	}
	
	/**
	 * Collapses the grid around the connected region.
	 * All the cells in the connected region are removed, and all cells above those that 
	 * were removed 'drop down' (toward the bottom of the grid). 
	 * When all the cells in a column have been removed, then columns to the 
	 * right of the removed column slide to the left.
	 */
	public static void collapse(int[][] grid){
		for(int col = 0; col < grid[0].length; col++){
			compressColumn(grid, col);
		}
		compressGrid(grid);
	}
	
	private static void compressGrid(int[][] grid){
		int insertAtCol = 0;
		int col = insertAtCol;
		while (col < grid[0].length){
			if(columnEmpty(grid, col)){
				while(col < grid[0].length && columnEmpty(grid, col))
					col++;
				if(col < grid[0].length)
					swapColumns(grid, insertAtCol++, col);
			}
			else{
				col++;
				insertAtCol++;
			}
		}
	}
	
	private static boolean columnEmpty(int[][] grid, int col){
		for(int row = 0; row < grid.length; row++){
			if(grid[row][col] != EMPTY){
				return false;
			}
		}
		return true;
	}
	
	private static void swapCells(int[][] grid, int row1, int col1, int row2, int col2){
		int temp = grid[row1][col1];
		grid[row1][col1] = grid[row2][col2];
		grid[row2][col2] = temp;
	}
	
	private static void swapColumns(int[][] grid, int col1, int col2){
		for(int row = 0; row < grid.length; row++){
			swapCells(grid, row, col1, row, col2);
		}
	}
	
	public static void compressColumn(int[][] grid, int col){
		int insertAtRow = grid.length - 1;
		int row = insertAtRow;
		while (row >= 0){
			if(grid[row][col] == CONNECTED){
				while(row >= 0 && grid[row][col] == CONNECTED)
					row--;
				if(row >= 0)
					swapCells(grid, insertAtRow--, col, row, col);
			}
			else{
				row--;
				insertAtRow--;
			}
		}
		
		// replace all CONNECTED flags with EMPTY flags
		for(int i = 0; i < grid.length; i++){
			if(grid[i][col] == CONNECTED){
				grid[i][col] = EMPTY;
			}
		}
	}
}
