package helvidios.cp.ch1.medium;

import java.util.Scanner;

public class _10324_ZerosAndOnes {
	public static void main(String... args){
		String data = "0000011111\r\n" + 
				"3\r\n" + 
				"0 5\r\n" + 
				"4 2\r\n" + 
				"5 9\r\n" + 
				"01010101010101010101010101111111111111111111111111111111111110000000000000000\r\n" + 
				"5\r\n" + 
				"4 4\r\n" + 
				"25 60\r\n" + 
				"1 3\r\n" + 
				"62 76\r\n" + 
				"24 62\r\n" + 
				"1\r\n" + 
				"1\r\n" + 
				"0 0\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int counter = 0;
		while(scanner.hasNext()){
			String str = scanner.next();
			int queryCount = scanner.nextInt();
			System.out.printf("Case %1$s:\n", ++counter);
			for(int query = 0; query < queryCount; query++){
				int i = scanner.nextInt();
				int j = scanner.nextInt();
				int from = Math.min(i, j);
				int to = Math.max(i, j);
				
				if(allCharsSame(str, from, to))
					System.out.println("Yes");
				else System.out.println("No");
			}
		}
		scanner.close();
	}
	
	private static boolean allCharsSame(String str, int from, int to){
		for(int c = from + 1; c < str.length() && c <= to; c++){
			if(str.charAt(c) != str.charAt(c - 1))
				return false;
		}
		return true;
	}
}
