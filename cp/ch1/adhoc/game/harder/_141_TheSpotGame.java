package helvidios.cp.ch1.adhoc.game.harder;

import java.util.Scanner;

public class _141_TheSpotGame {
	static class Move{
		public int x;
		public int y;
		public boolean isAddition;
	}
	public static void main(String... args){
		String data = "2\r\n" + 
				"1 1 +\r\n" + 
				"2 2 +\r\n" + 
				"2 2 -\r\n" + 
				"1 2 +\r\n" + 
				"2\r\n" + 
				"1 1 +\r\n" + 
				"2 2 +\r\n" + 
				"1 2 +\r\n" + 
				"2 2 -\r\n" + 
				"0\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int boardSize = scanner.nextInt();
			if(boardSize == 0) break;
			Move[] moves = new Move[2 * boardSize];
			for(int i = 0; i < moves.length; i++){
				Move move = new Move();
				move.x = scanner.nextInt();
				move.y = scanner.nextInt();
				String op = scanner.next();
				move.isAddition = op.equals("+");
				moves[i] = move;
			}
			System.out.println(play(boardSize, moves));
		}
		scanner.close();
	}
	
	static String play(int boardSize, Move[] moves){
		boolean[][] board = new boolean[boardSize][boardSize];
		boolean[][][] boardHistory = new boolean[moves.length][][];
		for(int i = 0; i < moves.length; i++){
			Move move = moves[i];
			board[move.y - 1][move.x - 1] = move.isAddition;
			if(boardEmpty(board) 
				|| boardAlreadyOccurred(board, boardHistory) 
				|| boardAlreadyOccurred(rotateLeft(board), boardHistory) // -90 degrees
				|| boardAlreadyOccurred(rotateLeft(rotateLeft(rotateLeft(board))), boardHistory) // -270 degrees
				|| boardAlreadyOccurred(rotateLeft(rotateLeft(board)), boardHistory)){ // -180 degrees
				return String.format("Player %1$d wins on move %2$d", ((i + 1) % 2) + 1, i + 1);
			}
			boardHistory[i] = copyBoard(board);
		}
		return "Draw";
	}
	
	static boolean[][] copyBoard(boolean[][] board){
		boolean[][] newBoard = new boolean[board.length][board[0].length];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}
	
	public static boolean[][] rotateLeft(boolean[][] board){
		boolean[][] newBoard = new boolean[board.length][board[0].length];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				newBoard[i][j] = board[j][board.length - i - 1];
			}
		}
		return newBoard;
	}
	
	static boolean boardEmpty(boolean[][] board){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j]) return false;
			}
		}
		return true;
	}
	
	static boolean boardAlreadyOccurred(boolean[][] board, boolean[][][] boardHistory){
		for(boolean[][] previousBoard : boardHistory){
			if(previousBoard == null) return false;
			if(boardsIdentical(board, previousBoard)){
				return true;
			}
		}
		return false;
	}
	
	static boolean boardsIdentical(boolean[][] board1, boolean[][] board2){
		for(int i = 0; i < board1.length; i++){
			for(int j = 0; j < board1[0].length; j++){
				if(board1[i][j] != board2[i][j]){
					return false;
				}
			}
		}
		return true;
	}
}