package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _11953_Battleships{
	public static void main(String[] args){
		String data = "2\r\n4\r\nx...\r\n..x.\r\n@.@.\r\n....\r\n2\r\n..\r\nx.";
		Scanner s = new Scanner(data);
		int nTests = s.nextInt();
		for(int c = 1; c <= nTests; c++){
			char[][] grid = new char[s.nextInt()][];
			for(int i = 0; i < grid.length; i++){
				grid[i] = s.next().toCharArray();
			}
			System.out.printf("Case %d: %d\n", c, aliveShips(grid));
		}
	}

	static int aliveShips(char[][] grid){
		int nShipsAlive = 0;
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				if(grid[i][j] == HIT || grid[i][j] == SHIP){
					if(floodfill(grid, i, j)) nShipsAlive++;
				}
			}
		}
		return nShipsAlive;
	}

	static final char HIT = '@';
	static final char SHIP = 'x';
	static final char VISITED = '1';
	static int[] dr = new int[]{-1, 0, 1, 0};
	static int[] dc = new int[]{0, 1, 0, -1};
	static boolean floodfill(char[][] grid, int row, int col){
		if(row < 0 || row >= grid.length || col < 0 || col >= grid[row].length) return false;
   		if(grid[row][col] != HIT && grid[row][col] != SHIP) return false;
   		boolean shipAlive = grid[row][col] == SHIP;
   		grid[row][col] = VISITED;
   		for(int d = 0; d < 4; d++){
   			if(floodfill(grid, row + dr[d], col + dc[d])) shipAlive = true;
   		}
   		return shipAlive;
	}
}