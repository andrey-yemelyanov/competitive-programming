package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _11110_Equidivisions{
	public static void main(String[] args){
		String data = "2\r\n1 2 2 1\r\n5\r\n1 1 1 2 1 3 3 2 2 2\r\n2 1 4 2 4 1 5 1 3 1\r\n4 5 5 2 5 3 5 5 5 4\r\n2 5 3 4 3 5 4 3 4 4\r\n5\r\n1 1 1 2 1 3 3 2 2 2\r\n2 1 3 1 4 1 5 1 4 2\r\n4 5 5 2 5 3 5 5 5 4\r\n2 4 1 4 3 5 4 3 4 4\r\n0\r\n";
		String data2 = "1\n";
		String data3 = "1\r\n2\r\n1 1\r\n2\r\n1 1 1 2\r\n2\r\n1 1 1 2 2 1\r\n2\r\n1 1 1 2 1 2\r\n3\r\n1 1 1 2 1 3\r\n2 1 2 2 2 3\r\n3\r\n1 1 1 2 1 3\r\n2 1 2 2 2 3\r\n3\r\n1 1 1 2 2 1\r\n3 3 3 2 2 3\r\n3\r\n1 1 1 2 1 3 1 1 1 2 1 3      \r\n2 1 2 2 2 3 2 1 2 2 2 3        \r\n11\r\n1 1 1 2 1 3 1 4 1 5 1 6 1 7 1 8 1 9 1 10 1 11 \r\n2 1 2 2 2 3 2 4 2 5 2 6 2 7 2 8 2 9 2 10 2 11 \r\n3 1 3 2 3 3 3 4 3 5 3 6 3 7 3 8 3 9 3 10 3 11 \r\n4 1 4 2 4 3 4 4 4 5 4 6 4 7 4 8 4 9 4 10 4 11 \r\n5 1 5 2 5 3 5 4 5 5 5 6 5 7 5 8 5 9 5 10 5 11 \r\n6 1 6 2 6 3 6 4 6 5 6 6 6 7 6 8 6 9 6 10 6 11 \r\n7 1 7 2 7 3 7 4 7 5 7 6 7 7 7 8 7 9 7 10 7 11 \r\n8 1 8 2 8 3 8 4 8 5 8 6 8 7 8 8 8 9 8 10 8 11 \r\n9 1 9 2 9 3 9 4 9 5 9 6 9 7 9 8 9 9 9 10 9 11 \r\n10 1 10 2 10 3 10 4 10 5 10 6 10 7 10 8 10 9 10 10 10 11 \r\n0\r\n";
		Scanner s = new Scanner(data3);
		while(s.hasNext()){
			int n = s.nextInt();
			if(n == 0) break;
			int[][] m = new int[n][n];
			s.nextLine();
			for(int id = 1; id < n; id++){
				String[] pairs = s.nextLine().split("\\s+");
				//System.out.println(Arrays.toString(pairs));
				for(int i = 0; i < pairs.length; i+=2){
					int r = Integer.parseInt(pairs[i]) - 1;
					int c = Integer.parseInt(pairs[i + 1]) - 1;
					m[r][c] = id;
				}
			}
			int nDivs = countDivs(m);
			if(nDivs == n) System.out.println("good");
			else System.out.println("wrong");
		}
	}

	static int countDivs(int[][] m){
		int nDivs = 0;
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[i].length; j++){
				if(m[i][j] != VISITED){
					floodfill(m, i, j, m[i][j]);
					nDivs++;
				}
			}
		}
		return nDivs;
	}

	static final int VISITED = -1;
	static final int[] dr = new int[] {-1, 0, 1, 0};
   	static final int[] dc = new int[] {0, 1, 0, -1};
   	static void floodfill(int[][] m, int row, int col, int color){
   		if(row < 0 || row >= m.length || col < 0 || col >= m[row].length) return;
   		if(m[row][col] != color) return;
   		m[row][col] = VISITED;
   		for(int d = 0; d < 4; d++){
   			floodfill(m, row + dr[d], col + dc[d], color);
   		}
   	}
}