package helvidios.cp.ch1.adhoc.chess;

import java.util.Scanner;

public class _278_Chess {
	public static void main(String... args){
		String data = "2\r\n" + 
				"r 6 7\r\n" + 
				"k 8 8";
		Scanner scanner = new Scanner(data);
		int nProblems = scanner.nextInt();
		while(nProblems-- > 0){
			String chesspiece = scanner.next();
			int nRows = scanner.nextInt();
			int nCols = scanner.nextInt();
			System.out.println(getMax(chesspiece, nRows, nCols));
		}
		scanner.close();
	}
	
	static int getMax(String chesspiece, int nRows, int nCols){
		switch (chesspiece) {
			case "r":
				return countRooks(nRows, nCols);
			case "k":
				return countKnights(nRows, nCols);
			case "K":
				return countKings(nRows, nCols);
			default: // "Q"
				return countQueens(nRows, nCols);
		}
	}
	
	static int countKings(int nRows, int nCols){
		return (int) (Math.ceil((double)nRows / 2) * Math.ceil((double)nCols / 2));
	}
	
	static int countRooks(int nRows, int nCols){
		return Math.min(nRows, nCols);
	}
	
	static int countKnights(int nRows, int nCols){
		return (int) Math.ceil((nRows * nCols) / 2);
	}
	
	static int countQueens(int nRows, int nCols){
		return countRooks(nRows, nCols);		
	}
}
