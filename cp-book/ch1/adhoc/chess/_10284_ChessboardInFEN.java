package helvidios.cp.ch1.adhoc.chess;

import java.util.Scanner;

public class _10284_ChessboardInFEN {
	static final char EMPTY = '0';
	public static void main(String... args){
		String data = "5k1r/2q3p1/p3p2p/1B3p1Q/n4P2/6P1/bbP2N1P/1K1RR3\r\n" + 
				"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			System.out.println(countNotAttackedEmptySquares(
					parseChessboard(scanner.next())));
		}
		scanner.close();
	}
	
	static char[][] parseChessboard(String fenConfiguration){
		char[][] chessboard = new char[8][8];
		String[] rows = fenConfiguration.split("/");
		for(int row = 0; row < rows.length; row++){
			int col = 0;
			for(int c = 0; c < rows[row].length(); c++){
				char current = rows[row].charAt(c);
				if(Character.isDigit(current)){
					int count = Character.getNumericValue(current);
					while(count-- > 0){
						chessboard[row][col++] = EMPTY;
					}
				}
				else chessboard[row][col++] = current;
			}
		}
		return chessboard;
	}
	
	static int countNotAttackedEmptySquares(char[][] chessboard){
		int count = 0;
		for(int row = 0; row < chessboard.length; row++){
			for(int col = 0; col < chessboard[0].length; col++){
				if(chessboard[row][col] == EMPTY && !isSquareUnderAttack(chessboard, row, col))
					count++;
			}
		}
		return count;
	}
	
	static boolean isSquareUnderAttack(char[][] chessboard, int row, int col){
		return isUnderAttackByBishop(chessboard, row, col) ||
				isUnderAttackByKing(chessboard, row, col) ||
				isUnderAttackByKnight(chessboard, row, col) ||
				isUnderAttackByPawn(chessboard, row, col) ||
				isUnderAttackByQueen(chessboard, row, col) ||
				isUnderAttackByRook(chessboard, row, col);
	}
	
	static char getChesspiece(char[][] chessboard, int row, int col){
		if(row >= 0 && row < chessboard.length){
			if(col >= 0 && col < chessboard[0].length){
				return chessboard[row][col];
			}
		}
		return EMPTY;
	}
	
	static boolean isUnderAttackByPawn(char[][] chessboard, int row, int col){
		return getChesspiece(chessboard, row-1, col-1) == 'p' 
				|| getChesspiece(chessboard, row-1, col+1) == 'p' 
				|| getChesspiece(chessboard, row+1, col-1) == 'P'
				|| getChesspiece(chessboard, row+1, col+1) == 'P';
	}
	
	static boolean isUnderAttackByKnight(char[][] chessboard, int row, int col){
		return Character.toUpperCase(getChesspiece(chessboard, row-2, col+1)) == 'N'
				|| Character.toUpperCase(getChesspiece(chessboard, row-2, col-1)) == 'N'
				|| Character.toUpperCase(getChesspiece(chessboard, row+1, col+2)) == 'N'
				|| Character.toUpperCase(getChesspiece(chessboard, row-1, col+2)) == 'N'
				|| Character.toUpperCase(getChesspiece(chessboard, row-1, col-2)) == 'N'
				|| Character.toUpperCase(getChesspiece(chessboard, row+1, col-2)) == 'N'
				|| Character.toUpperCase(getChesspiece(chessboard, row+2, col+1)) == 'N'
				|| Character.toUpperCase(getChesspiece(chessboard, row+2, col-1)) == 'N';
	}
	
	static boolean isUnderAttackByBishop(char[][] chessboard, int row, int col){
		return isUnderAttackAlongDiagonals(chessboard, row, col, 'B');
	}
	
	static boolean isUnderAttackByRook(char[][] chessboard, int row, int col){
		return isUnderAttackAlongVerticalAndHorizontalLines(chessboard, row, col, 'R');
	}
	
	static boolean isUnderAttackByQueen(char[][] chessboard, int row, int col){
		return isUnderAttackAlongDiagonals(chessboard, row, col, 'Q') ||
				isUnderAttackAlongVerticalAndHorizontalLines(chessboard, row, col, 'Q');
	}
	
	static boolean isUnderAttackByKing(char[][] chessboard, int row, int col){
		return Character.toUpperCase(getChesspiece(chessboard, row-1, col)) == 'K'
				|| Character.toUpperCase(getChesspiece(chessboard, row-1, col+1)) == 'K'
				|| Character.toUpperCase(getChesspiece(chessboard, row-1, col-1)) == 'K'
				|| Character.toUpperCase(getChesspiece(chessboard, row, col+1)) == 'K'
				|| Character.toUpperCase(getChesspiece(chessboard, row, col-1)) == 'K'
				|| Character.toUpperCase(getChesspiece(chessboard, row+1, col)) == 'K'
				|| Character.toUpperCase(getChesspiece(chessboard, row+1, col-1)) == 'K'
				|| Character.toUpperCase(getChesspiece(chessboard, row+1, col+1)) == 'K';
	}
	
	static boolean isUnderAttackAlongDiagonals(char[][] chessboard, int row, int col, char chesspiece){
		for(int left = col - 1, up = row - 1; left >= 0 && up >= 0; left--, up--){
			if(chessboard[up][left] != EMPTY && Character.toUpperCase(chessboard[up][left]) != chesspiece)
				break;
			if(checkSquare(chessboard, up, left, chesspiece))
				return true;
		}
		
		for(int left = col - 1, down = row + 1; left >= 0 && down < chessboard.length; left--, down++){
			if(chessboard[down][left] != EMPTY && Character.toUpperCase(chessboard[down][left]) != chesspiece)
				break;
			if(checkSquare(chessboard, down, left, chesspiece))
				return true;
		}
		
		for(int right = col + 1, up = row - 1; right < chessboard[0].length && up >= 0; right++, up--){
			if(chessboard[up][right] != EMPTY && Character.toUpperCase(chessboard[up][right]) != chesspiece)
				break;
			if(checkSquare(chessboard, up, right, chesspiece))
				return true;
		}
		
		for(int right = col + 1, down = row + 1; right < chessboard[0].length && down < chessboard.length; right++, down++){
			if(chessboard[down][right] != EMPTY && Character.toUpperCase(chessboard[down][right]) != chesspiece)
				break;
			if(checkSquare(chessboard, down, right, chesspiece))
				return true;
		}
		
		return false;
	}
	
	static boolean isUnderAttackAlongVerticalAndHorizontalLines(char[][] chessboard, int row, int col, char chesspiece){
		for(int left = col - 1; left >= 0; left--){
			if(chessboard[row][left] != EMPTY && Character.toUpperCase(chessboard[row][left]) != chesspiece)
				break;
			if(checkSquare(chessboard, row, left, chesspiece))
				return true;
		}
		
		for(int right = col + 1; right < chessboard[0].length; right++){
			if(chessboard[row][right] != EMPTY && Character.toUpperCase(chessboard[row][right]) != chesspiece)
				break;
			if(checkSquare(chessboard, row, right, chesspiece))
				return true;
		}
		
		for(int up = row - 1; up >= 0; up--){
			if(chessboard[up][col] != EMPTY && Character.toUpperCase(chessboard[up][col]) != chesspiece)
				break;
			if(checkSquare(chessboard, up, col, chesspiece))
				return true;
		}
		
		for(int down = row + 1; down < chessboard.length; down++){
			if(chessboard[down][col] != EMPTY && Character.toUpperCase(chessboard[down][col]) != chesspiece)
				break;
			if(checkSquare(chessboard, down, col, chesspiece))
				return true;
		}
		
		return false;
	}
	
	static boolean checkSquare(char[][] chessboard, int row, int col, char chesspiece){
		if(Character.toUpperCase(chessboard[row][col]) == chesspiece)
			return true;
		return false;
	}
}
