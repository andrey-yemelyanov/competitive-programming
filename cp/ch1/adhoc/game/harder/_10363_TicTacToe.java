package helvidios.cp.ch1.adhoc.game.harder;

import java.util.Scanner;

public class _10363_TicTacToe {
	public static final int X = 1;
	public static final int O = 2;
	public static void main(String... args){
		/*String data = "2\r\n" + 
				"X.O\r\n" + 
				"OO.\r\n" + 
				"XXX\r\n" + 
				"\r\n"+
				"O.X\r\n" + 
				"XX.\r\n" + 
				"OOO";
		String data2 = "3\r\n" + 
				"XXX\r\n" + 
				".X.\r\n" + 
				"OOO\r\n" + 
				"\r\n" + 
				"XXX\r\n" + 
				".X.\r\n" + 
				"OOO\r\n" + 
				"\r\n" + 
				"X.O\r\n" + 
				"...\r\n" + 
				"O.X";*/
		String data3 = "34\r\n" + 
				"XXX\r\n" + 
				"XOO\r\n" + 
				"XOO\r\n" + 
				"\r\n" + 
				"OXX\r\n" + 
				"XOO\r\n" + 
				"XXO\r\n" + 
				"\r\n" + 
				"X..\r\n" + 
				"X..\r\n" + 
				"X..\r\n" + 
				"\r\n" + 
				"X..\r\n" + 
				"X..\r\n" + 
				"O..\r\n" + 
				"\r\n" + 
				"X.X\r\n" + 
				"...\r\n" + 
				".X.\r\n" + 
				"\r\n" + 
				"O.O\r\n" + 
				"...\r\n" + 
				".O.\r\n" + 
				"\r\n" + 
				"X.O\r\n" + 
				"XO.\r\n" + 
				"XOX\r\n" + 
				"\r\n" + 
				"X.O\r\n" + 
				"XOO\r\n" + 
				"XXX\r\n" + 
				"\r\n" + 
				"OXO\r\n" + 
				"XXX\r\n" + 
				"OXO\r\n" + 
				"\r\n" + 
				"XXX\r\n" + 
				".O.\r\n" + 
				".O.\r\n" + 
				"\r\n" + 
				"OOO\r\n" + 
				".X.\r\n" + 
				".X.\r\n" + 
				"\r\n" + 
				"XOX\r\n" + 
				"OXO\r\n" + 
				"XOX\r\n" + 
				"\r\n" + 
				"XXX\r\n" + 
				"OOO\r\n" + 
				"X.O\r\n" + 
				"\r\n" + 
				"X.O\r\n" + 
				"..X\r\n" + 
				"X..\r\n" + 
				"\r\n" + 
				"X.X\r\n" + 
				".OO\r\n" + 
				"XOO\r\n" + 
				"\r\n" + 
				"X.X\r\n" + 
				"OO.\r\n" + 
				"X.X\r\n" + 
				"\r\n" + 
				"X.O\r\n" + 
				"O.X\r\n" + 
				"X.O\r\n" + 
				"\r\n" + 
				"X.O\r\n" + 
				"OO.\r\n" + 
				"XXX\r\n" + 
				"\r\n" + 
				"X.O\r\n" + 
				"O.X\r\n" + 
				"OOX\r\n" + 
				"\r\n" + 
				"X.O\r\n" + 
				"O..\r\n" + 
				"XXO\r\n" + 
				"\r\n" + 
				"X.O\r\n" + 
				"O.O\r\n" + 
				"X..\r\n" + 
				"\r\n" + 
				"...\r\n" + 
				"...\r\n" + 
				"...\r\n" + 
				"\r\n" + 
				"...\r\n" + 
				".X.\r\n" + 
				"...\r\n" + 
				"\r\n" + 
				"...\r\n" + 
				".O.\r\n" + 
				"...\r\n" + 
				"\r\n" + 
				"OO.\r\n" + 
				"XXO\r\n" + 
				"X.O\r\n" + 
				"\r\n" + 
				"XXX\r\n" + 
				"XXX\r\n" + 
				"XXX\r\n" + 
				"\r\n" + 
				"OOO\r\n" + 
				"OOO\r\n" + 
				"OOO\r\n" + 
				"\r\n" + 
				".O.\r\n" + 
				"XXX\r\n" + 
				".O.\r\n" + 
				"\r\n" + 
				".X.\r\n" + 
				"OOO\r\n" + 
				".X.\r\n" + 
				"\r\n" + 
				"X.O\r\n" + 
				".X.\r\n" + 
				"O.X\r\n" + 
				"\r\n" + 
				"OXO\r\n" + 
				"OXX\r\n" + 
				"X.O\r\n" + 
				"\r\n" + 
				"XXX\r\n" + 
				".OO\r\n" + 
				"OX.\r\n" + 
				"\r\n" + 
				"OOO\r\n" + 
				".XX\r\n" + 
				"X.X\r\n" + 
				"\r\n" + 
				"XO.\r\n" + 
				"XO.\r\n" + 
				"XOO";
		Scanner scanner = new Scanner(data3);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int[][] board = new int[3][3];
			for(int row = 0; row < board.length; row++){
				String rowLine = scanner.next();
				for(int i = 0; i < rowLine.length(); i++){
					char c = rowLine.charAt(i);
					switch (c) {
						case 'X':
							board[row][i] = X;
							break;
						case 'O':
							board[row][i] = O;
							break;
					}
				}
			}
			if(isBoardValid(board))
				System.out.println("yes");
			else System.out.println("no");
		}
		scanner.close();
	}
	
	public static boolean isBoardValid(int[][] board){
		int nX = countMoves(board, X);
		int nO = countMoves(board, O);
		if(nX - nO > 1) return false; 
		if(nO > nX) return false;
		boolean xWin = isPlayerWin(board, X);
		boolean oWin = isPlayerWin(board, O);
		if(xWin && oWin) return false;
		if(xWin && nX <= nO) return false;
		if(oWin && nO != nX) return false;
		return true;
	}
	
	public static int countMoves(int[][] board, int player){
		int count = 0;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] == player) count++;
			}
		}
		return count;
	}
	
	public static boolean isPlayerWin(int[][] board, int player){
		// check each row
		for(int row = 0; row < board.length; row++){
			if(allEqualTo(board[row], player)) return true;
		}
		
		// check each column
		for(int col = 0; col < board[0].length; col++){
			int[] column = new int[board.length];
			for(int row = 0; row < board.length; row++){
				column[row] = board[row][col];
			}
			if(allEqualTo(column, player)) return true;
		}
		
		// check diagonals
		int[] diagonal = new int[board.length];
		for(int i = 0; i < diagonal.length; i++){
			diagonal[i] = board[i][i];
		}
		if(allEqualTo(diagonal, player)) return true;
		
		diagonal = new int[board.length];
		for(int i = 0; i < diagonal.length; i++){
			diagonal[i] = board[board.length - 1 - i][i];
		}
		if(allEqualTo(diagonal, player)) return true;
		
		return false;
	}
	
	static boolean allEqualTo(int[] array, int element){
		for(int i = 0; i < array.length; i++){
			if(array[i] != element) return false;
		}
		return true;
	}
}
