package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _00852_DecidingVctoryInGo{
   public static void main(String[] args){
		String data = "1\r\nOX..XXO..\r\nOXX.XOO..\r\nOOXX.XO.O\r\n.OOX.XOO.\r\n..OOXXXOO\r\n..OO.X.XO\r\n..OOXX.XX\r\n..OX.X...\r\n..OXX....";
		Scanner s = new Scanner(data);
		int nTests = s.nextInt();
		s.nextLine();
		while(nTests-- > 0){
			char[][] board = new char[9][];
			for(int i = 0; i < board.length; i++){
				board[i] = s.nextLine().toCharArray();
			}
			int[] score = getScore(board);
			System.out.printf("Black %d White %d\n", score[1], score[0]);
		}
   }
   
   static final char WHITE = 'O';
   static final char BLACK = 'X';
   static final char VISITED = 'V';
   static final char EMPTY = '.';
   static int count(char[][] board, char color){
		int count = 0;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(board[i][j] == color) count++;
			}
		}
		return count;
   }

   static final int[] dr = new int[] {-1, 0, 1, 0};
   static final int[] dc = new int[] {0, 1, 0, -1};
   static int floodfill(char[][] board, int row, int col, Set<Character> set){
   		if(row < 0 || row >= board.length || col < 0 || col >= board[row].length) return 0;
   		if(board[row][col] == VISITED) return 0;
   		if(board[row][col] == WHITE || board[row][col] == BLACK){
   			set.add(board[row][col]);
   			return 0;
   		}
   		board[row][col] = VISITED;
   		int count = 1;
   		for(int d = 0; d < 4; d++){
   			count += floodfill(board, row + dr[d], col + dc[d], set);
   		}
   		return count;
   }

   static int[] getScore(char[][] board){
   		int whiteScore = count(board, WHITE);
   		int blackScore = count(board, BLACK);
   		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(board[i][j] == EMPTY){
					Set<Character> set = new HashSet<>();
					int n = floodfill(board, i, j, set);
					if(set.size() == 1){
						if(set.toArray(new Character[0])[0] == WHITE){
							whiteScore += n;
						}else{
							blackScore += n;
						}
					}
				}
			}
		}
		return new int[] {whiteScore, blackScore};
   }
}