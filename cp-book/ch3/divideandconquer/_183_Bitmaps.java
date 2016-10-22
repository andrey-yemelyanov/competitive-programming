package helvidios.cp.ch3.divideandconquer;

import java.util.*;

public class _183_Bitmaps {
	static final String empty = "";
	static final String one = "1";
	static final String zero = "0";
	static final String D = "D";
	public static void main(String... args){
		String data = "B 3 4\r\n" + 
				"001000011011\r\n" + 
				"D 2 3\r\n" + 
				"DD10111\r\n" + 
				"#";
		String data2 = "B 0 0\r\n" + 
				"B 16 16\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"B 16 16\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"B 16 16\r\n" + 
				"0000000011110000\r\n" + 
				"0000000011110000\r\n" + 
				"0000000011110000\r\n" + 
				"0000000011110000\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"B 16 16\r\n" + 
				"0000000011110011\r\n" + 
				"0000000011110011\r\n" + 
				"0000000011110000\r\n" + 
				"0000000011110000\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"B 16 16\r\n" + 
				"0000000011110010\r\n" + 
				"0000000011110011\r\n" + 
				"0000000011110000\r\n" + 
				"0000000011110000\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000011111111\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"0000000000000000\r\n" + 
				"B 1 1\r\n" + 
				"1\r\n" + 
				"B 2 2\r\n" + 
				"0001\r\n" + 
				"B 3 3\r\n" + 
				"001101010\r\n" + 
				"B 4 4\r\n" + 
				"0001110101010101\r\n" + 
				"B 5 5\r\n" + 
				"0010011101110100111110101\r\n" + 
				"B 1 10\r\n" + 
				"1001010101\r\n" + 
				"B 10 1\r\n" + 
				"0100011010\r\n" + 
				"B 2 20\r\n" + 
				"00111011101001101010\r\n" + 
				"11010001010101000010\r\n" + 
				"B 20 2\r\n" + 
				"00111000000010100000\r\n" + 
				"01011111000001010001\r\n" + 
				"D  16  16\r\n" + 
				"0\r\n" + 
				"D  16  16\r\n" + 
				"D0100\r\n" + 
				"D  16  16\r\n" + 
				"D0D101100\r\n" + 
				"D  16  16\r\n" + 
				"D0D1D01001100\r\n" + 
				"D  16  16\r\n" + 
				"D0D1D0D1011001100\r\n" + 
				"D   1   1\r\n" + 
				"1\r\n" + 
				"D   2   2\r\n" + 
				"D0001\r\n" + 
				"D   3   3\r\n" + 
				"DD00101D010\r\n" + 
				"D   4   4\r\n" + 
				"DD0011D0101D0101D0101\r\n" + 
				"D   5   5\r\n" + 
				"DDD0011110D0D0110DD011D101D1101\r\n" + 
				"D   1  10\r\n" + 
				"DDDD100D10DDD101D01\r\n" + 
				"D  10   1\r\n" + 
				"DDDD0100DD10D10\r\n" + 
				"D   2  20\r\n" + 
				"DDDD011DDD011D10DDDD1001DDD010D10DDD10D10DD01D01DDDD010D10D0D10\r\n" + 
				"D  20   2\r\n" + 
				"DDDDD0110DDD0110DDDD01000DDD01D100DD1D10DDD011D01\r\n" + 
				"#";
		Scanner scanner = new Scanner(data2);
		boolean formatRead = false;
		String format = "";
		while(scanner.hasNext()){
			if(!formatRead){
				format = scanner.next();
			}
			if(format.equals("#")) break;
			int nRows = scanner.nextInt();
			int nCols = scanner.nextInt();
			int[][] bitmap = new int[nRows][nCols];
			String encoding = ""; String line;
			String nextFormat = "";
			while(true){
				line = scanner.next();
				if(line.equals("B") || line.equals("D") || line.equals("#")){
					nextFormat = line;
					formatRead = true;
					break;
				}
				encoding += line;
			}
			if(format.equals("B")){
				for(int i = 0; i < nRows; i++){
					for(int j = 0; j < nCols; j++){
						bitmap[i][j] = Character.getNumericValue(encoding.charAt(i * nCols + j));
					}
				}
				String dEncoding = convertBToD(bitmap, 0, nRows - 1, 0, nCols - 1);
				System.out.println(String.format("D%1$4d%2$4d\n%3$s", nRows, nCols, printEncoding(dEncoding)));
			}else if(format.equals("D")){
				String bEncoding = convertDToB(encoding, nRows, nCols);
				System.out.println(String.format("B%1$4d%2$4d\n%3$s", nRows, nCols, printEncoding(bEncoding)));
			}
			format = nextFormat;
		}
		scanner.close();
	}
	
	static String printEncoding(String encoding){
		StringBuilder out = new StringBuilder();
		final int nCharsPerLine = 50; int charsConsumed = 0;
		while(charsConsumed < encoding.length()){
			charsConsumed++;
			out.append(encoding.charAt(charsConsumed - 1));
			if(charsConsumed < encoding.length() && charsConsumed % nCharsPerLine == 0) out.append("\n");
		}
		return out.toString();
	}
	
	static String convertDToB(String dEncoding, int nRows, int nCols){
		int[][] bitmap = new int[nRows][nCols];
		convertDToB(dEncoding, 0, bitmap, 0, nRows - 1, 0, nCols - 1);
		StringBuilder out = new StringBuilder();
		for(int i = 0; i < nRows; i++){
			for(int j = 0; j < nCols; j++){
				out.append(bitmap[i][j]);
			}
		}
		return out.toString();
	}
	
	static int convertDToB(
			String dEncoding,
			int currentChar,
			int[][] bitmap, 
			int startRow, 
			int endRow, 
			int startCol, 
			int endCol){
		if(endRow < startRow || endCol < startCol) return currentChar;
		if(currentChar >= dEncoding.length()) return currentChar;
		if(dEncoding.charAt(currentChar) == one.charAt(0)){
			for(int i = startRow; i <= endRow; i++){
				for(int j = startCol; j <= endCol; j++){
					bitmap[i][j] = Integer.parseInt(one);
				}
			}
			return currentChar + 1; // consume 1
		}
		if(dEncoding.charAt(currentChar) == zero.charAt(0)){
			for(int i = startRow; i <= endRow; i++){
				for(int j = startCol; j <= endCol; j++){
					bitmap[i][j] = Integer.parseInt(zero);
				}
			}
			return currentChar + 1; // consume 0
		}
		
		int midRow = startRow + (endRow - startRow) / 2; int midCol = startCol + (endCol - startCol) / 2;
		currentChar++; // consume D
		currentChar = convertDToB(dEncoding, currentChar, bitmap, startRow, midRow, startCol, midCol);
		currentChar = convertDToB(dEncoding, currentChar, bitmap, startRow, midRow, midCol + 1, endCol);
		currentChar = convertDToB(dEncoding, currentChar, bitmap, midRow + 1, endRow, startCol, midCol);
		currentChar = convertDToB(dEncoding, currentChar, bitmap, midRow + 1, endRow, midCol + 1, endCol);
		return currentChar;
	}
	
	static String convertBToD(int[][] bitmap, int startRow, int endRow, int startCol, int endCol){
		if(startRow == endRow && startCol == endCol) return Integer.toString(bitmap[startRow][startCol]);
		if(endRow < startRow || endCol < startCol) return empty;
		int midRow = startRow + (endRow - startRow) / 2; int midCol = startCol + (endCol - startCol) / 2;
		String upperLeft = convertBToD(bitmap, startRow, midRow, startCol, midCol);
		String upperRight = convertBToD(bitmap, startRow, midRow, midCol + 1, endCol);
		String lowerLeft = convertBToD(bitmap, midRow + 1, endRow, startCol, midCol);
		String lowerRight = convertBToD(bitmap, midRow + 1, endRow, midCol + 1, endCol);
		if(allOnes(new String[]{upperLeft, upperRight, lowerLeft, lowerRight})) return one;
		if(allZeroes(new String[]{upperLeft, upperRight, lowerLeft, lowerRight})) return zero;
		return D + upperLeft + upperRight + lowerLeft + lowerRight;
	}
	
	static boolean allOnes(String[] args){
		for(String s : args){
			if(!(s.equals(empty) || s.equals(one))){
				return false;
			}
		}
		return true;
	}
	
	static boolean allZeroes(String[] args){
		for(String s : args){
			if(!(s.equals(empty) || s.equals(zero))){
				return false;
			}
		}
		return true;
	}
}
