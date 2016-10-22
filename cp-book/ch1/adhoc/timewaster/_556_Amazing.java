package helvidios.cp.ch1.adhoc.timewaster;

import java.util.Scanner;

public class _556_Amazing {
	public static final int CLOSED = -1;
	
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	
	public static void main(String... args){
		String data = "3 5\r\n" + 
				"01010\r\n" + 
				"01010\r\n" + 
				"00000\r\n" + 
				"0 0\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(true){
			int nRows = scanner.nextInt();
			int nCols = scanner.nextInt();
			if(nRows == 0 && nCols == 0) break;
			int[][] maze = new int[nRows][nCols];
			for(int row = 0; row < nRows; row++){
				String values = scanner.next();
				for(int col = 0; col < nCols; col++){
					int value = values.charAt(col) == '1' ? -1 : 0;
					maze[row][col] = value;
				}
			}
			int[] values = collectValues(maze);
			System.out.println(print(values));
		}
		
		scanner.close();
	}
	
	static String print(int[] values){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < values.length; i++){
			sb.append(String.format("%1$3d", values[i]));
		}
		return sb.toString();
	}
	
	public static int[] collectValues(int[][] maze){
		// traverse maze
		traverseMaze(maze);
		
		// analyze results
		int[] results = new int[5];
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[0].length; col++){
				if(maze[row][col] != CLOSED){
					int visitCount = maze[row][col];
					if(visitCount < results.length){
						results[visitCount]++;
					}
				}
			}
		}
		return results;
	}
	
	public static void traverseMaze(int[][] maze){
		int direction = RIGHT;
		int row = maze.length - 1;
		int col = 0;
		
		while(true){
			if(!hasWallOnRightSide(maze, row, col, direction)){
				direction = turnRight(direction);
				int[] newCoord = step(direction, row, col);
				row = newCoord[0];
				col = newCoord[1];
			}
			else if(stepPossible(maze, row, col, direction)){  
				int[] newCoord = step(direction, row, col);
				row = newCoord[0];
				col = newCoord[1];
			}
			else if(!hasWallOnLeftSide(maze, row, col, direction)){
				direction = turnLeft(direction);
				int[] newCoord = step(direction, row, col);
				row = newCoord[0];
				col = newCoord[1];
			}else{ // turn around
				direction = turnLeft(turnLeft(direction));
				int[] newCoord = step(direction, row, col);
				row = newCoord[0];
				col = newCoord[1];
			}
			maze[row][col]++;
			if(row == maze.length - 1 && col == 0) return;
		}
	}
	
	static int[] step(int direction, int row, int col){
		if(direction == UP) return new int[]{row - 1, col};
		if(direction == DOWN) return new int[]{row + 1, col};
		if(direction == LEFT) return new int[]{row, col - 1};
		return new int[]{row, col + 1};
	}
	
	static int turnRight(int direction){
		return (direction + 1) % 4;
	}
	
	static int turnLeft(int direction){
		if(direction == UP) return LEFT;
		return (direction - 1) % 4;
	}
	
	static boolean stepPossible(int[][] maze, int row, int col){
		return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] != CLOSED;
	}
	
	static boolean stepPossible(int[][] maze, int row, int col, int direction){
		int[] coord = step(direction, row, col);
		return stepPossible(maze, coord[0], coord[1]);
	}
	
	static boolean hasWallOnLeftSide(int[][] maze, int row, int col, int direction){
		if(direction == DOWN){
			return col + 1 >= maze[0].length || maze[row][col + 1] == CLOSED;
		}
		else if(direction == UP){
			return col - 1 < 0 || maze[row][col - 1] == CLOSED;
		}
		else if(direction == LEFT){
			return row + 1 >= maze.length || maze[row + 1][col] == CLOSED;
			
		}
		return row - 1 < 0 || maze[row - 1][col] == CLOSED; 
	}
	
	static boolean hasWallOnRightSide(int[][] maze, int row, int col, int direction){
		if(direction == UP){
			return col + 1 >= maze[0].length || maze[row][col + 1] == CLOSED;
		}
		else if(direction == DOWN){
			return col - 1 < 0 || maze[row][col - 1] == CLOSED;
		}
		else if(direction == LEFT){
			return row - 1 < 0 || maze[row - 1][col] == CLOSED;
		}
		return row + 1 >= maze.length || maze[row + 1][col] == CLOSED;
	}
}
