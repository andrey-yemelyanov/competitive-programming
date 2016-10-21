package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _10452_Marcus {
	public static void main(String... args){
		String data = "2\r\n" + 
				"6 5\r\n" + 
				"PST#T\r\n" + 
				"BTJAS\r\n" + 
				"TYCVM\r\n" + 
				"YEHOF\r\n" + 
				"XIBKU\r\n" +  
				"N@RJB\r\n" + 
				"5 4\r\n" + 
				"JA#X\r\n" + 
				"JVBN\r\n" + 
				"XOHD\r\n" + 
				"DQEM\r\n" + 
				"T@IY";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int nRows = scanner.nextInt();
			int nCols = scanner.nextInt();
			char[][] cobblestone = new char[nRows][nCols];
			for(int i = 0; i < nRows; i++){
				char[] row = scanner.next().toCharArray();
				for(int j = 0; j < row.length; j++){
					cobblestone[i][j] = row[j];
				}
			}
			System.out.println(findPath(cobblestone));
		}
		scanner.close();
	}
	
	static final char DEST = '#';
	static final char ORIGIN = '@';
	static final String WORD = "IEHOVA";
	
	static String findPath(char[][] cobblestone){
		int col = 0;
		int row = cobblestone.length - 1;
		for(int i = 0; i < cobblestone[0].length; i++){
			if(cobblestone[row][i] == ORIGIN) col = i;
		}
		return go(cobblestone, row, col, "", -1);
	}
	
	static String go(char[][] cobblestone, int row, int col, String path, int letter){
		if(row < 0 || col < 0 || row == cobblestone.length || col == cobblestone[0].length) return null;
		if(cobblestone[row][col] == DEST) return path;
		if(letter >= WORD.length()) return null;
		if(letter >= 0 && cobblestone[row][col] != WORD.charAt(letter)) return null;
		String p = go(cobblestone, row - 1, col, path + "forth ", letter + 1);
		if(p == null) p = go(cobblestone, row, col - 1, path + "left ", letter + 1);
		if(p == null) p = go(cobblestone, row, col + 1, path + "right ", letter + 1);
		return p.trim();
	}
}
