package helvidios.cp.ch1.medium;

import java.util.Scanner;

public class _11687_Digits {
	public static void main(String... args){
		String data = "42\r\n" + 
				"1\n"+
				"END\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			String token = scanner.next();
			if(token.equals("END")) break;
			
			String result = token;
			int x = 0;
			for(; !getNumDigits(result).equals(result); x++, result = getNumDigits(result));
			System.out.println(x + 1);
		}
		scanner.close();
	}
	
	private static String getNumDigits(String number){
		return Integer.toString(number.length());
	}
}
