package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _11085_BackTo8Queens {
	public static void main(String... args){
		String data = "1 2 3 4 5 6 7 8\r\n" + 
				"1 1 1 1 1 1 1 1";
		Scanner scanner = new Scanner(data);
		int caseNum = 0;
		while(scanner.hasNext()){
			int[] originalQueenPositions = new int[8];
			for(int i = 0; i < originalQueenPositions.length; i++){
				originalQueenPositions[i] = scanner.nextInt() - 1;
			}
			System.out.println("Case " + (++caseNum) + ": " + findMinMoves(originalQueenPositions));
		}
		scanner.close();
	}
	
	static int findMinMoves(int[] originalQueenPositions){
		minMoves = Integer.MAX_VALUE;
		int[] queenPositions = new int[8];
		findMinMoves(originalQueenPositions, queenPositions, 0, 0);
		return minMoves;
	}
	
	static int minMoves;
	static void findMinMoves(int[] originalQueenPositions, int[] queenPositions, int col, int nMoves){
		if(col == 8){
			minMoves = Math.min(minMoves, nMoves);
			return;
		}
		for(int row = 0; row < 8; row++){
			if(!isUnderAttack(queenPositions, col, row)){
				queenPositions[col] = row;
				boolean queenMoved = row != originalQueenPositions[col];
				findMinMoves(originalQueenPositions, queenPositions, col + 1, queenMoved ? nMoves + 1 : nMoves);
			}
		}
	}
	
	static boolean isUnderAttack(int[] queenPositions, int col, int row){
		for(int i = 0; i < col; i++){
			if(row == queenPositions[i] || Math.abs(row - queenPositions[i]) == Math.abs(col - i)){
				return true;
			}
		}
		return false;
	}
}
