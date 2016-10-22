package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _11094_Continents{
	public static void main(String[] args){
		String data = "5 5\r\nwwwww\r\nwwllw\r\nwwwww\r\nwllww\r\nwwwww\r\n1 3";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int nRows = s.nextInt();
			int nCols = s.nextInt();
			char[][] map = new char[nRows][nCols];
			for(int i = 0; i < nRows; i++){
				map[i] = s.next().toCharArray();
			}
			int row = s.nextInt();
			int col = s.nextInt();
			System.out.println(largestContinent(map, row, col));
		}
	}

	static int largestContinent(char[][] map, int row, int col){
		char land = map[row][col];
		char visited = ',';
		// mark king's continent as visited
		floodfill(map, row, col, land, visited);
		int largestContinent = 0;
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				if(map[i][j] == land){
					largestContinent = max(largestContinent, floodfill(map, i, j, land, visited));
				}
			}
		}
		return largestContinent;
	}

	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	static int floodfill(char[][] map, int row, int col, char color, char newColor){
		if(col < 0) col = map[0].length - 1; if(col >= map[0].length) col = 0;
		if(row < 0 || row >= map.length) return 0;
		if(map[row][col] != color) return 0;
		int size = 1;
		map[row][col] = newColor;
		for(int d = 0; d < 4; d++){
			size += floodfill(map, row + dr[d], col + dc[d], color, newColor);
		}
		return size;
	}
}