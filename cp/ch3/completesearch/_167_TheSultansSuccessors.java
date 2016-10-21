package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _167_TheSultansSuccessors {
	public static void main(String... args){
		String data = "1\r\n" + 
				"1 2 3 4 5 6 7 8\r\n" + 
				"9 10 11 12 13 14 15 16\r\n" + 
				"17 18 19 20 21 22 23 24\r\n" + 
				"25 26 27 28 29 30 31 32\r\n" + 
				"33 34 35 36 37 38 39 40\r\n" + 
				"41 42 43 44 45 46 47 48\r\n" + 
				"48 50 51 52 53 54 55 56\r\n" + 
				"57 58 59 60 61 62 63 64\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nChessboards = scanner.nextInt();
		while(nChessboards-- > 0){
			int[][] chessboard = new int[8][8];
			for(int row = 0; row < 8; row++){
				for(int col = 0; col < 8; col++){
					chessboard[row][col] = scanner.nextInt();
				}
			}
			System.out.printf("%1$5d\n", bestScore(chessboard));
		}
		scanner.close();
	}
	
	public static int bestScore(int[][] chessboard){
		return backtrack(0, new int[8], chessboard);
	}
	
	static boolean validPlacement(int[] row, int r, int c){
		for(int col = 0; col < c; col++){
			if(row[col] == r || Math.abs(c - col) == Math.abs(r - row[col])) return false;
		}
		return true;
	}
	
	static int backtrack(int col, int[] row, int[][] chessboard){
		if(col == 8){
			return sum(chessboard, row);
		}
		int bestSum = 0;
		for(int r = 0; r < 8; r++){
			if(validPlacement(row, r, col)){
				row[col] = r;
				int sum = backtrack(col + 1, row, chessboard);
				bestSum = Math.max(bestSum, sum);
			}
		}
		return bestSum;
	}
	
	static int sum(int[][] chessboard, int[] row){
		int sum = 0;
		for(int i = 0; i < row.length; i++){
			sum += chessboard[row[i]][i];
		}
		return sum;
	}
}
