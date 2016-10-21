package helvidios.cp.ch2.lineards._2darraymanip;

import java.util.Scanner;

public class _10855_RotatedSquare {
	public static void main(String... args){
		String data = "4 2\r\n" + 
				"ABBA\r\n" + 
				"ABBB\r\n" + 
				"BAAA\r\n" + 
				"BABB\r\n" + 
				"AB\r\n" + 
				"BB\r\n" + 
				"6 2\r\n" + 
				"ABCDCD\r\n" + 
				"BCDCBD\r\n" + 
				"BACDDC\r\n" + 
				"DCBDCA\r\n" + 
				"DCBABD\r\n" + 
				"ABCDBA\r\n" + 
				"BC\r\n" + 
				"CD\r\n" + 
				"0 0\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(true){
			int N = scanner.nextInt();
			int n = scanner.nextInt();
			if(N == 0 && n == 0) break;
			char[][] largeSquare = new char[N][N];
			char[][] smallSquare = new char[n][n];
			for(int i = 0; i < N; i++){
				largeSquare[i] = scanner.next().toCharArray();
			}
			for(int i = 0; i < n; i++){
				smallSquare[i] = scanner.next().toCharArray();
			}
			int[] result = findOccurrences(largeSquare, smallSquare);
			System.out.println(result[0] + " " + result[1] + " " + result[2] + " " + result[3]);
		}
		scanner.close();
	}
	
	public static int[] findOccurrences(char[][] largeSquare, char[][] smallSquare){
		int nNoRotation = countOccurrences(largeSquare, smallSquare);
		int n90DegRotation = countOccurrences(largeSquare, rotateRight(smallSquare));
		int n180DegRotation = countOccurrences(largeSquare, rotateRight(rotateRight(smallSquare)));
		int n270DegRotation = countOccurrences(largeSquare, rotateRight(rotateRight(rotateRight(smallSquare))));
		return new int[]{nNoRotation, n90DegRotation, n180DegRotation, n270DegRotation};
	}
	
	public static int countOccurrences(char[][] largeSquare, char[][] smallSquare){
		int count = 0;
		for(int row = 0; row <= largeSquare.length - smallSquare.length; row++){
			for(int col = 0; col <= largeSquare[row].length - smallSquare[0].length; col++){
				boolean largeSquareContainsSmallSquare = true;
				for(int i = 0; i < smallSquare.length && largeSquareContainsSmallSquare; i++){
					for(int j = 0; j < smallSquare[i].length; j++){
						if(smallSquare[i][j] != largeSquare[row + i][col + j]) {
							largeSquareContainsSmallSquare = false;
							break;
						}
					}
				}
				if(largeSquareContainsSmallSquare) count++;
			}
		}
		return count;
	}
	
	public static char[][] rotateRight(char[][] matrix){
		char[][] rotated = new char[matrix.length][matrix[0].length];
		
		for(int row = 0; row < matrix.length; row++){
			for(int col = 0; col < matrix[row].length; col++){
				int destCol = matrix[row].length - 1 - row;
				int destRow = col;
				rotated[destRow][destCol] = matrix[row][col];
			}
		}
		
		return rotated;
	}
}
