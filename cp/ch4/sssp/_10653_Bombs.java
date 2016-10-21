package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;

public class _10653_Bombs{
	public static void main(String[] args){
		String data = "10 10\r\n9\r\n0 1 2\r\n1 1 2\r\n2 2 2 9\r\n3 2 1 7\r\n5 3 3 6 9\r\n6 4 0 1 2 7\r\n7 3 0 3 8\r\n8 2 7 9\r\n9 3 2 3 4\r\n0 0\r\n9 9\r\n0 0";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int nRows = s.nextInt(); int nCols = s.nextInt();
			if(nRows == 0 && nCols == 0) break;
			int[][] grid = new int[nRows][nCols];
			int nRowsWithBombs = s.nextInt();
			for(int i = 0; i < nRowsWithBombs; i++){
				int row = s.nextInt();
				int nColsWithBombs = s.nextInt();
				for(int j = 0; j < nColsWithBombs; j++){
					grid[row][s.nextInt()] = BOMB;
				}
			}
			int[] source = new int[] {s.nextInt(), s.nextInt()};
			int[] dest = new int[] {s.nextInt(), s.nextInt()};
			bfs(grid, toId(source[0], source[1], grid));
			System.out.println(dist[toId(dest[0], dest[1], grid)]);
		}
	}

	static final int INF = -1;
    static int[] dist;
    static void bfs(int[][] grid, int source){
        dist = new int[grid.length * grid[0].length];
        for(int i = 0; i < dist.length; i++){
            dist[i] = INF;
        }
        dist[source] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        while(!q.isEmpty()){
            int u = q.remove();
            for(int neighbor : validMoves(u, grid)){
                if(dist[neighbor] == INF){
                    dist[neighbor] = dist[u] + 1;
                    q.add(neighbor);
                }
            }
        }
    }

    static List<Integer> validMoves(int from, int[][] grid){
    	List<Integer> validMoves = new ArrayList<>();
    	int[] coord = toCoord(from, grid);
    	int row = coord[0]; int col = coord[1];
    	// up
    	if(coordValid(row - 1, col, grid)) validMoves.add(toId(row - 1, col, grid));
    	// right
    	if(coordValid(row, col + 1, grid)) validMoves.add(toId(row, col + 1, grid));
    	// down
    	if(coordValid(row + 1, col, grid)) validMoves.add(toId(row + 1, col, grid));
    	// left
    	if(coordValid(row, col - 1, grid)) validMoves.add(toId(row, col - 1, grid));
    	return validMoves;
    }

    static int[] toCoord(int id, int[][] grid){
    	int nCols = grid[0].length;
    	return new int[] {id / nCols, id % nCols}; 
    }

    static int toId(int row, int col, int[][] grid){
    	int nCols = grid[0].length;
    	return nCols * row + col; 
    }

    static final int BOMB = 1;
    static boolean coordValid(int row, int col, int[][] grid){
    	if(row < 0 || row >= grid.length) return false;
    	if(col < 0 || col >= grid[0].length) return false;
    	return grid[row][col] != BOMB;
    }
}