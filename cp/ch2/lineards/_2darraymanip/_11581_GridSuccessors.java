package helvidios.cp.ch2.lineards._2darraymanip;

import java.util.Scanner;

public class _11581_GridSuccessors {
	public static void main(String... args){
		String data = "3\r\n" + 
				"111\r\n" + 
				"100\r\n" + 
				"001\r\n" + 
				"101\r\n" + 
				"000\r\n" + 
				"101\r\n" + 
				"000\r\n" + 
				"000\r\n" + 
				"000";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int[][] grid = new int[3][3];
			for(int i = 0; i < 3; i++){
				char[] row = scanner.next().toCharArray();
				for(int j = 0; j < 3; j++){
					grid[i][j] = Character.getNumericValue(row[j]);
				}
			}
			System.out.println(solve(grid));
		}
		scanner.close();
	}
	
	public static int solve(int[][] grid){
		int index = -1;
		while(!allZeroes(grid)){
			index++;
			grid = f(grid);
		}
		return index;
	}
	
	static boolean allZeroes(int[][] grid){
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				if(grid[row][col] != 0) return false;
			}
		}
		return true;
	}
	
	public static int[][] f(int[][] grid){
		int[][] transformed = new int[grid.length][grid[0].length];
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				transformed[row][col] = sumAdjacentCells(grid, row, col) % 2;
			}
		}
		return transformed;
	}
	
	static int sumAdjacentCells(int[][] grid, int row, int col){
		int sum = 0;
		if(row > 0) sum += grid[row - 1][col];
		if(col > 0) sum += grid[row][col - 1];
		if(row < grid.length - 1) sum += grid[row + 1][col];
		if(col < grid[0].length - 1) sum += grid[row][col + 1];
		return sum;
	}
}
