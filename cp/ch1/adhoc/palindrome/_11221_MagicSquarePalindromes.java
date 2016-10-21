package helvidios.cp.ch1.adhoc.palindrome;

import java.util.Scanner;

public class _11221_MagicSquarePalindromes {
	public static void main(String... args){
		String data = "3\r\n" + 
				"sator arepo tenet opera rotas\r\n" + 
				"this sentence is, quite clearly, not a magic square palindrome! but then again, you never know...\r\n" + 
				"muse sun, eve.s e(y)es even use sum.\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		for(int test = 1; test <= nTestCases; ){
			String line = scanner.nextLine();
			if(!line.equals("")){
				System.out.printf("Case #%1$d:\n", test);
				int dimension;
				if((dimension = getSquareDimension(line)) != 0){
					System.out.println(dimension);
				}else {
					System.out.println("No magic :(");
				}
				test++;
			}
		}
		scanner.close();
	}
	
	public static int getSquareDimension(String line){
		line = strip(line);
		if(!hasIntegralSquareRoot(line.length())) return 0;
		int dimension = (int)Math.sqrt(line.length());
		char[][] square = new char[dimension][dimension];
		for(int i = 0; i < square.length; i++){
			for(int j = 0; j < square[0].length; j++){
				square[i][j] = line.charAt(j + i * square.length);
			}
		}
		if(isMagicSquarePalindrome(square)) return dimension;
		return 0;
	}
	
	public static boolean hasIntegralSquareRoot(int n){
		int sqrt = (int)Math.sqrt(n);
		return sqrt * sqrt == n;
	}
	
	public static boolean isMagicSquarePalindrome(char[][] square){
		// Start from the (1,1) cell, move right until the end of the line and than proceed to the next line.
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < square.length; i++){
			for(int j = 0; j < square[0].length; j++){
				sb.append(square[i][j]);
			}
		}
		String line1 = sb.toString();
		sb.setLength(0);
		
		// Start from the (1,1) cell, move down until the end of the column and then proceed to the next column.
		for(int j = 0; j < square[0].length; j++){
			for(int i = 0; i < square.length; i++){
				sb.append(square[i][j]);
			}
		}
		String line2 = sb.toString();
		sb.setLength(0);
		
		// Start from the (K,K) cell, move left until the beginning of the line and then proceed to the previous line.
		for(int i = square.length - 1; i >= 0; i--){
			for(int j = square[0].length - 1; j >= 0; j--){
				sb.append(square[i][j]);
			}
		}
		String line3 = sb.toString();
		sb.setLength(0);
		
		// Start from the (K,K) cell, move up until the beginning of the column and then proceed to the previous column.
		for(int j = square[0].length - 1; j >= 0; j--){
			for(int i = square.length - 1; i >= 0; i--){
				sb.append(square[i][j]);
			}
		}
		String line4 = sb.toString();
		
		return line1.equals(line2) && line1.equals(line3) && line1.equals(line4);
	}
	
	public static String strip(String line){
		line = line.toUpperCase();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < line.length(); i++){
			if(Character.isLetter(line.charAt(i)))
				sb.append(line.charAt(i));
		}
		return sb.toString();
	}
}
