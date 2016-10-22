package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;
import java.io.*;

public class _00929_NumberMaze{
	private static class Cell{
		public int row;
		public int col;
		public int cost;
		public Cell(int row, int col, int cost){
			this.row = row;
			this.col = col;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException{
		String data = "2\r\n4\r\n5\r\n0 3 1 2 9\r\n7 3 4 9 9\r\n1 7 5 5 3\r\n2 3 4 2 5\r\n1\r\n6\r\n0 1 2 3 4 5";
		String data2 = "8\r\n1\r\n1\r\n9\r\n2\r\n2\r\n0 0\r\n0 0\r\n20\r\n20\r\n1 4 5 7 8 9 7 5 3 4 6 7 4 3 6 8 4 3 5 9\r\n2 4 0 9 7 5 4 6 7 8 4 3 1 4 6 7 8 9 6 5\r\n1 2 3 4 6 6 8 9 9 0 0 0 0 0 3 4 5 4 3 1\r\n1 4 5 7 8 9 7 5 3 4 6 7 4 3 6 8 4 3 5 9\r\n2 4 0 9 7 5 4 6 7 8 4 3 1 4 6 7 8 9 6 5\r\n1 2 3 4 5 6 7 8 9 9 0 6 4 3 2 4 5 6 7 8\r\n8 5 9 2 4 5 6 7 8 9 2 1 2 3 4 5 6 8 9 0\r\n9 9 9 0 0 0 0 0 1 2 3 4 6 7 8 9 0 5 4 3\r\n1 2 3 4 5 6 7 8 9 9 0 6 4 3 2 4 5 6 7 8\r\n1 4 5 7 8 9 7 5 3 4 6 7 4 3 6 8 4 3 5 9\r\n1 3 5 7 8 9 2 2 2 2 3 4 4 4 4 5 6 7 8 9\r\n0 9 9 8 7 6 9 9 9 9 9 9 9 0 0 0 0 0 9 9\r\n1 2 3 4 5 6 7 8 9 9 0 6 4 3 2 4 5 6 7 8\r\n8 5 9 2 4 5 6 7 8 9 2 1 2 3 4 5 6 8 9 0\r\n1 2 3 4 6 6 8 9 9 0 0 0 0 0 3 4 5 4 3 1\r\n1 4 5 7 8 9 7 5 3 4 6 7 4 3 6 8 4 3 5 9\r\n1 2 3 4 5 6 7 8 9 5 3 2 4 5 6 7 8 9 9 6\r\n1 2 3 4 5 6 7 8 9 9 0 6 4 3 2 4 5 6 7 8\r\n0 0 0 0 0 0 1 1 1 1 2 3 3 3 3 3 4 4 4 4\r\n3 3 3 1 1 1 1 1 3 4 5 6 7 8 9 0 5 4 3 1\r\n22\r\n20\r\n1 4 5 7 8 9 7 5 3 4 6 7 4 3 6 8 4 3 5 9\r\n2 4 0 9 7 5 4 6 7 8 4 3 1 4 6 7 8 9 6 5\r\n1 2 3 4 6 6 8 9 9 0 0 0 0 0 3 4 5 4 3 1\r\n1 4 5 7 8 9 7 5 3 4 6 7 4 3 6 8 4 3 5 9\r\n2 4 0 9 7 5 4 6 7 8 4 2 2 2 2 2 8 9 6 5\r\n1 2 3 4 5 6 7 8 9 9 0 6 4 3 2 4 0 6 7 8\r\n1 5 9 2 4 5 6 7 8 9 2 1 2 3 4 5 6 8 9 0\r\n9 9 9 0 0 0 0 0 1 2 3 4 6 7 8 9 0 5 4 3\r\n1 2 3 4 5 6 7 8 9 9 0 6 4 0 0 0 5 6 7 8\r\n1 4 5 7 8 9 7 5 3 4 6 7 4 0 0 0 4 3 5 9\r\n1 3 5 7 8 9 2 2 0 2 3 4 4 0 0 0 6 4 8 9\r\n0 9 9 8 7 6 9 9 9 9 9 9 9 0 0 0 2 0 9 9\r\n1 2 3 4 5 6 7 8 9 9 0 6 4 3 2 4 5 6 7 8\r\n8 5 9 2 4 5 6 7 8 9 2 1 2 3 4 5 6 8 9 0\r\n1 2 3 4 6 6 8 9 0 0 0 0 0 0 3 4 5 4 3 1\r\n1 4 5 7 8 9 7 5 3 4 6 7 4 3 6 8 4 3 5 9\r\n1 2 3 4 5 6 7 8 9 5 3 2 4 5 6 7 8 9 9 6\r\n1 2 3 4 5 6 7 8 9 9 0 6 4 3 2 4 5 6 7 8\r\n0 0 0 0 0 0 1 1 1 1 2 3 3 3 3 3 4 4 4 4\r\n3 3 3 1 1 1 1 1 3 4 5 6 7 8 9 0 5 4 3 1\r\n8 5 9 2 4 5 6 7 8 9 2 1 2 3 4 5 6 8 9 0\r\n1 2 3 4 6 6 8 9 9 0 0 0 0 0 3 4 5 4 3 1\r\n33\r\n16\r\n4 6 9 7 6 0 3 4 6 0 8 5 1 2 1 8\r\n9 6 0 3 8 7 1 3 3 4 1 2 9 5 5 3\r\n4 4 3 0 6 6 6 2 9 6 7 2 1 0 1 0\r\n6 1 3 6 0 4 9 5 1 3 7 0 8 2 5 4\r\n8 8 6 4 7 4 6 6 1 5 8 2 5 1 2 3\r\n4 7 0 4 2 1 9 5 4 8 5 5 2 0 9 2\r\n9 8 6 6 2 4 4 5 1 4 9 8 6 3 2 0\r\n1 4 7 5 5 8 0 0 7 5 5 1 7 6 4 8\r\n4 2 6 9 7 0 4 0 5 4 9 3 7 3 3 0\r\n9 2 5 6 1 5 8 0 2 3 1 0 2 5 0 6\r\n8 7 7 7 9 2 9 6 8 0 9 7 3 5 8 4\r\n7 3 1 0 1 9 0 3 5 2 5 7 9 8 5 9\r\n5 3 8 6 7 8 3 5 8 2 2 4 9 2 8 7\r\n6 1 9 9 1 0 2 8 4 0 7 5 8 2 5 5\r\n7 3 1 4 3 4 1 4 9 4 0 8 8 8 7 4\r\n2 7 5 5 9 0 3 3 0 0 8 0 4 5 5 2\r\n1 8 8 6 5 0 0 6 6 0 6 4 1 4 1 3\r\n3 6 8 2 6 3 7 8 3 7 8 7 3 5 1 6\r\n6 0 2 1 2 3 7 8 5 3 4 6 9 5 1 2\r\n4 9 6 2 4 3 1 7 3 1 7 6 9 8 4 5\r\n0 6 8 2 1 7 2 7 2 7 3 2 4 5 6 8\r\n6 5 3 1 8 6 0 1 7 9 9 8 8 3 5 0\r\n2 3 3 3 2 7 0 5 4 6 7 1 3 5 1 1\r\n0 4 4 1 0 5 4 0 4 4 8 4 9 4 7 3\r\n9 2 7 2 9 9 7 6 7 6 7 0 1 8 4 2\r\n3 8 5 3 5 9 5 2 5 4 8 7 0 5 0 1\r\n7 9 3 9 1 2 5 8 8 2 1 2 2 5 6 5\r\n5 1 1 1 0 6 5 6 2 3 3 2 1 5 4 8\r\n5 9 9 6 2 4 4 2 8 7 4 1 2 0 8 8\r\n3 9 1 4 8 6 2 0 1 5 5 2 0 9 3 7\r\n0 2 3 2 7 0 7 7 7 1 8 0 4 7 0 7\r\n8 3 3 6 1 5 9 2 2 4 5 5 5 8 2 7\r\n2 6 2 1 8 9 9 5 0 7 7 6 6 9 4 5\r\n8\r\n17\r\n1 4 2 3 2 2 1 6 8 5 7 6 1 8 9 2 7\r\n9 5 4 3 1 2 3 3 4 1 1 3 8 7 4 2 7\r\n7 9 3 1 9 8 6 5 0 2 8 6 0 2 4 8 6\r\n5 0 9 0 0 6 1 3 8 9 3 4 4 6 0 6 6\r\n1 8 4 9 6 3 7 8 8 2 9 1 3 5 9 8 4\r\n0 7 6 3 6 1 5 4 2 0 9 7 3 7 2 6 0\r\n1 6 5 7 5 4 1 2 0 0 1 4 6 0 7 1 7\r\n7 7 7 3 3 5 9 9 8 1 8 2 6 6 0 3 8\r\n12\r\n17\r\n4 0 6 1 8 9 8 4 1 4 3 9 8 8 0 8 7\r\n7 8 3 8 3 7 1 0 7 3 4 9 6 5 1 0 9\r\n9 6 8 3 4 8 4 9 9 2 5 5 3 3 3 7 4\r\n3 8 0 8 8 0 6 8 1 9 8 9 7 2 2 8 2\r\n8 9 0 7 8 1 5 8 6 1 2 4 2 5 8 6 2\r\n6 5 3 9 2 4 6 1 8 2 1 1 9 7 6 2 9\r\n5 2 0 0 3 9 1 8 1 9 5 3 2 5 2 5 8\r\n6 7 7 2 2 9 4 1 9 6 9 8 2 5 5 4 9\r\n1 2 5 0 8 3 9 3 9 6 7 9 9 7 6 9 3\r\n5 7 6 6 5 8 2 5 4 4 1 6 1 6 3 3 5\r\n5 3 2 8 2 5 3 6 1 8 6 2 1 4 6 2 9\r\n1 5 0 3 6 4 9 2 9 3 4 4 0 5 9 6 3\r\n14\r\n16\r\n2 8 0 7 2 2 5 9 1 0 8 5 0 7 9 0\r\n5 3 4 1 0 4 8 5 9 2 5 4 1 3 9 5\r\n8 2 7 9 6 1 7 7 1 9 0 3 4 1 7 5\r\n3 3 2 4 1 2 9 0 8 7 4 5 6 8 0 7\r\n7 4 3 1 3 6 3 0 1 9 0 4 2 9 3 1\r\n4 8 2 0 5 5 9 3 2 8 7 4 8 1 4 3\r\n5 5 2 6 8 9 2 9 5 9 4 5 0 5 8 8\r\n0 1 3 2 2 2 7 0 3 1 3 3 7 0 9 5\r\n5 8 3 7 8 3 7 9 3 9 1 8 2 8 0 1\r\n8 4 8 6 0 9 3 0 0 8 7 6 3 5 6 5\r\n8 3 9 4 8 3 9 7 6 4 5 8 6 1 5 5\r\n1 9 6 4 5 4 2 5 3 2 6 8 2 6 6 3\r\n9 4 5 6 2 5 3 5 6 6 1 4 4 6 2 6\r\n8 2 5 5 1 0 1 5 1 7 6 0 0 2 4 3\r\n";
		String data3 = "1\r\n4\r\n5\r\n0 9 9 9 9\r\n0 9 0 0 0\r\n0 0 0 9 0\r\n9 9 9 9 0";
		String data4 = "1\r\n33\r\n16\r\n4 6 9 7 6 0 3 4 6 0 8 5 1 2 1 8\r\n9 6 0 3 8 7 1 3 3 4 1 2 9 5 5 3\r\n4 4 3 0 6 6 6 2 9 6 7 2 1 0 1 0\r\n6 1 3 6 0 4 9 5 1 3 7 0 8 2 5 4\r\n8 8 6 4 7 4 6 6 1 5 8 2 5 1 2 3\r\n4 7 0 4 2 1 9 5 4 8 5 5 2 0 9 2\r\n9 8 6 6 2 4 4 5 1 4 9 8 6 3 2 0\r\n1 4 7 5 5 8 0 0 7 5 5 1 7 6 4 8\r\n4 2 6 9 7 0 4 0 5 4 9 3 7 3 3 0\r\n9 2 5 6 1 5 8 0 2 3 1 0 2 5 0 6\r\n8 7 7 7 9 2 9 6 8 0 9 7 3 5 8 4\r\n7 3 1 0 1 9 0 3 5 2 5 7 9 8 5 9\r\n5 3 8 6 7 8 3 5 8 2 2 4 9 2 8 7\r\n6 1 9 9 1 0 2 8 4 0 7 5 8 2 5 5\r\n7 3 1 4 3 4 1 4 9 4 0 8 8 8 7 4\r\n2 7 5 5 9 0 3 3 0 0 8 0 4 5 5 2\r\n1 8 8 6 5 0 0 6 6 0 6 4 1 4 1 3\r\n3 6 8 2 6 3 7 8 3 7 8 7 3 5 1 6\r\n6 0 2 1 2 3 7 8 5 3 4 6 9 5 1 2\r\n4 9 6 2 4 3 1 7 3 1 7 6 9 8 4 5\r\n0 6 8 2 1 7 2 7 2 7 3 2 4 5 6 8\r\n6 5 3 1 8 6 0 1 7 9 9 8 8 3 5 0\r\n2 3 3 3 2 7 0 5 4 6 7 1 3 5 1 1\r\n0 4 4 1 0 5 4 0 4 4 8 4 9 4 7 3\r\n9 2 7 2 9 9 7 6 7 6 7 0 1 8 4 2\r\n3 8 5 3 5 9 5 2 5 4 8 7 0 5 0 1\r\n7 9 3 9 1 2 5 8 8 2 1 2 2 5 6 5\r\n5 1 1 1 0 6 5 6 2 3 3 2 1 5 4 8\r\n5 9 9 6 2 4 4 2 8 7 4 1 2 0 8 8\r\n3 9 1 4 8 6 2 0 1 5 5 2 0 9 3 7\r\n0 2 3 2 7 0 7 7 7 1 8 0 4 7 0 7\r\n8 3 3 6 1 5 9 2 2 4 5 5 5 8 2 7\r\n2 6 2 1 8 9 9 5 0 7 7 6 6 9 4 5";
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader reader = new BufferedReader(new StringReader(data4));
		String line;
		int nTests = Integer.parseInt(reader.readLine());
		while(nTests-- > 0){
			int n = Integer.parseInt(reader.readLine());
			int m = Integer.parseInt(reader.readLine());
			int[][] cost = new int[n][m];
			for(int i = 0; i < cost.length; i++){
				String[] row = reader.readLine().split("\\s+");
				for(int j = 0; j < cost[i].length; j++){
					cost[i][j] = Integer.parseInt(row[j]);
				}
			}
			dijkstra(cost);
			System.out.println(dist[n - 1][m - 1] + cost[0][0]);
		}
	}

	static int[] DR = {-1, 0, +1, 0}, DC = {0, -1, 0, +1};
	static final int INF = Integer.MAX_VALUE;
	static int[][] dist;
	static void dijkstra(int[][] cost){
		dist = new int[cost.length][cost[0].length];
		for(int i = 0; i < dist.length; i++){
			for(int j = 0; j < dist[i].length; j++){
				dist[i][j] = INF;
			}
		}
		dist[0][0] = 0;
		PriorityQueue<Cell> pq = new PriorityQueue<Cell>(10, new Comparator<Cell>(){
			@Override
			public int compare(Cell c1, Cell c2){
				return Integer.compare(c1.cost, c2.cost);
			}
		});
		pq.add(new Cell(0, 0, 0));
		while(!pq.isEmpty()){
			Cell cell = pq.remove();
			if(cell.cost > dist[cell.row][cell.col]) continue;
			for(int i = 0; i < 4; i++){
				int nextRow = cell.row + DR[i]; int nextCol = cell.col + DC[i];
				if(nextRow < 0 || nextRow >= dist.length || nextCol < 0 || nextCol >= dist[0].length) continue;
				if(dist[cell.row][cell.col] + cost[nextRow][nextCol] < dist[nextRow][nextCol]){
					dist[nextRow][nextCol] = dist[cell.row][cell.col] + cost[nextRow][nextCol];
					pq.add(new Cell(nextRow, nextCol, dist[nextRow][nextCol]));
				}
			}
		}
	}
}