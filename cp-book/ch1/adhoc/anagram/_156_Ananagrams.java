package helvidios.cp.ch1.adhoc.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class _156_Ananagrams {
	public static void main(String... args){
		String data = "ladder came tape soon leader acme RIDE lone Dreis peat\r\n" + 
				" ScAlE orb  eye  Rides dealer  NotE derail LaCeS  drIed\r\n" + 
				"noel dire Disk mace Rob dries\r\n" + 
				"#\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		List<String> dictionary = new ArrayList<String>();
		while (scanner.hasNext()) {
			String word = scanner.next();
			if(word.equals("#")) break;
			dictionary.add(word);
		}
		List<String> ananagrams = getAnanagrams(dictionary);
		Collections.sort(ananagrams);
		for (String word : ananagrams) {
			System.out.println(word);
		}
		scanner.close();
	}
	
	public static List<String> getAnanagrams(List<String> dictionary){
		List<String> ananagrams = new ArrayList<String>();
		for(int i = 0; i < dictionary.size(); i++){
			String word = dictionary.get(i);
			boolean isAnanagram = true;
			for(int j = 0; j < dictionary.size(); j++){
				if(j != i){
					if(anagram(word, dictionary.get(j))){
						isAnanagram = false;
						break;
					}
				}
			}
			
			if(isAnanagram) ananagrams.add(word);
		}
		return ananagrams;
	}
	
	static boolean anagram(String word1, String word2){
		if(word1.length() != word2.length()) return false;
		if(word1.length() == 1) return false;
		
		char[] chars1 = word1.toLowerCase().toCharArray();
		Arrays.sort(chars1);
		String sortedWord1 = new String(chars1);
		
		char[] chars2 = word2.toLowerCase().toCharArray();
		Arrays.sort(chars2);
		String sortedWord2 = new String(chars2);
		
		return sortedWord1.equals(sortedWord2);
	}
}
