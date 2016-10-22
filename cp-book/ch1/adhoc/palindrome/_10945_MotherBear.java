package helvidios.cp.ch1.adhoc.palindrome;

import java.util.Scanner;

public class _10945_MotherBear {
	public static void main(String... args){
		String data = "Madam, Im adam!\r\n" + 
				"Roma tibi subito motibus ibit amor.\r\n" + 
				"Me so hungry!\r\n" + 
				"Si nummi immunis\r\n" + 
				"AAAAAaaa\n"+
				"DONE\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			if(line.equals("DONE")) break;
			if(isPalindrome(line))
				System.out.println("You won't be eaten!");
			else System.out.println("Uh oh..");
		}
		scanner.close();
	}
	
	public static boolean isPalindrome(String str){
		str = str.toUpperCase();
		for(int from = 0, to = str.length() - 1; from <= to;){
			if(!validChar(str.charAt(from))){
				from++;
				continue;
			}
			
			if(!validChar(str.charAt(to))){
				to--;
				continue;
			}
			
			if(str.charAt(from) != str.charAt(to))
				return false;
			
			from++;to--;
		}
		return true;
	}
	
	static boolean validChar(char c){
		return (c != '.' && c != ',' && c != '!' && c != '?' && c != ' ');
	}
}
