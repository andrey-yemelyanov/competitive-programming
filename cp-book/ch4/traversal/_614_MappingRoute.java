package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _614_MappingRoute {
    public static void main(String[] args) {
        String data = "2 3 1 1 1 3\r\n1 1 0\r\n0 0 0\r\n4 3 3 2 4 3\r\n0 3 0\r\n0 2 0\r\n0 3 0\r\n0 1 0\r\n0 0 0 0 0 0\r\n";
        Scanner scanner = new Scanner(data);
		int mazeNum = 1;
		while(scanner.hasNext()){
			int nRows = scanner.nextInt();
			int nCols = scanner.nextInt();
			int startRow = scanner.nextInt() - 1;
			int startCol = scanner.nextInt() - 1;
			int endRow = scanner.nextInt() - 1;
			int endCol = scanner.nextInt() - 1;
			if(nRows == 0 && nCols == 0) break;
			int[][] maze = new int[nRows][nCols];
			for(int i = 0; i < nRows; i++){
				for(int j = 0; j < nCols; j++){
					maze[i][j] = scanner.nextInt();
				}
			}
			int[][] solution = explore(startRow, startCol, endRow, endCol, maze);
			System.out.println("Maze " + mazeNum++);
			System.out.println();
			System.out.println(printMaze(solution, maze));
			System.out.println();System.out.println();
		}
        scanner.close();
    }
	
	static int[][] explore(int startRow, int startCol, int endRow, int endCol, int[][] maze){
		int[][] visited = new int[maze.length][maze[0].length];
		explore(startRow, startCol, endRow, endCol, 1, maze, visited);
		return visited;
	}
	
	static final int VISITED_NOT_ON_PATH = -1;
	static final int NOTVISITED = 0;
	static final int WALL_SOUTH = 2;
	static final int WALL_EAST = 1;
	static final int WALL_EAST_SOUTH = 3;
	static boolean moveValid(int fromRow, int fromCol, int toRow, int toCol, int[][] maze){
		if(toRow < 0 || toRow >= maze.length) return false;
		if(toCol < 0 || toCol >= maze[0].length) return false;
		if(fromRow > toRow && 
			(maze[toRow][fromCol] == WALL_SOUTH || 
				maze[toRow][fromCol] == WALL_EAST_SOUTH)) return false;
		if(toRow > fromRow && 
			(maze[fromRow][fromCol] == WALL_SOUTH ||
				maze[fromRow][fromCol] == WALL_EAST_SOUTH)) return false;
		if(fromCol > toCol &&
			(maze[fromRow][toCol] == WALL_EAST ||
				maze[fromRow][toCol] == WALL_EAST_SOUTH)) return false;
		if(toCol > fromCol &&
			(maze[fromRow][fromCol] == WALL_EAST ||
				maze[fromRow][fromCol] == WALL_EAST_SOUTH)) return false;
		return true;
	}
	
	static boolean explore(int row, int col, int endRow, int endCol, int seqNum, 
		int[][] maze, int[][] visited){
			visited[row][col] = seqNum;
			if(row == endRow && col == endCol) return true;
			
			// explore W
			int nextRow = row;
			int nextCol = col - 1;
			if(moveValid(row, col, nextRow, nextCol, maze) 
				&& visited[nextRow][nextCol] == NOTVISITED){
					if(explore(nextRow, nextCol, endRow, endCol, seqNum + 1, 
						maze, visited)) return true;
				}
				
			// explore N
			nextRow = row - 1;
			nextCol = col;
			if(moveValid(row, col, nextRow, nextCol, maze) 
				&& visited[nextRow][nextCol] == NOTVISITED){
					if(explore(nextRow, nextCol, endRow, endCol, seqNum + 1, 
						maze, visited)) return true;
				}
				
			// explore E
			nextRow = row;
			nextCol = col + 1;
			if(moveValid(row, col, nextRow, nextCol, maze) 
				&& visited[nextRow][nextCol] == NOTVISITED){
					if(explore(nextRow, nextCol, endRow, endCol, seqNum + 1, 
						maze, visited)) return true;
				}
			
			// explore S
			nextRow = row + 1;
			nextCol = col;
			if(moveValid(row, col, nextRow, nextCol, maze) 
				&& visited[nextRow][nextCol] == NOTVISITED){
					if(explore(nextRow, nextCol, endRow, endCol, seqNum + 1, 
						maze, visited)) return true;
				}
				
			visited[row][col] = VISITED_NOT_ON_PATH;
			return false;
		}
		
	static String printMaze(int[][] visited, int[][] maze){
		StringBuilder str = new StringBuilder();
		str.append("+");
		for(int j = 0; j < visited[0].length; j++){
			str.append("---+");
		}
		str.append("\n");
		for(int i = 0; i < visited.length; i++){
			for(int j = 0; j < visited[0].length; j++){
				if(j == 0) str.append("|");
				str.append(printCell(i, j, visited));
				if(j == visited[0].length - 1) str.append("|");
				else{
					if(maze[i][j] == WALL_EAST || maze[i][j] == WALL_EAST_SOUTH) str.append("|");
					else str.append(" ");
				}
			}
			str.append("\n");
			if(i < visited.length - 1){
				str.append("+");
				for(int j = 0; j < visited[0].length; j++){
					if(maze[i][j] == WALL_SOUTH || maze[i][j] == WALL_EAST_SOUTH) str.append("---+");
					else str.append("   +");
				}
				str.append("\n");
			}
		}
		str.append("+");
		for(int j = 0; j < visited[0].length; j++){
			str.append("---+");
		}
		return str.toString();
	}
	
	static String printCell(int row, int col, int[][] visited){
		if(visited[row][col] == VISITED_NOT_ON_PATH){
			return "???";
		}
		if(visited[row][col] > 0){
			return String.format("%3d", visited[row][col]);
		}
		return "   ";
	}
}