package helvidios.cp.ch1.adhoc.palindrome;

import java.util.HashSet;
import java.util.Scanner;

public class _353_PeskyPalindromes {
	public static void main(String... args){
		String data = "a\n"
				+ "boy \r\n" + 
				"adam \r\n" + 
				"madam \r\n" + 
				"tot\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			String str = scanner.next();
			System.out.printf("The string '%1$s' contains %2$d palindromes.\n", 
					str, countUniquePalindromes(str));
		}
		scanner.close();
	}
	
	public static int countUniquePalindromes(String word){
		HashSet<String> palindromes = new HashSet<String>();
		for(int i = 0; i < word.length(); i++){
			for(int j = i; j < word.length(); j++){
				String candidate = word.substring(i, j + 1); 
				if(isPalindrome(word, i, j))
					palindromes.add(candidate);
			}
		}
		return palindromes.size();
	}
	
	public static boolean isPalindrome(String word, int from, int to){
		while(from <= to){
			if(word.charAt(from) != word.charAt(to)) return false;
			from++; to--;
		}
		return true;
	}
}
