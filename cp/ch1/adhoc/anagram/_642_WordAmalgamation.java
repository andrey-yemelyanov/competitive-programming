package helvidios.cp.ch1.adhoc.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class _642_WordAmalgamation {
	public static void main(String... args){
		String data = "tarp\r\n" + 
				"given\r\n" + 
				"score\r\n" + 
				"refund\r\n" + 
				"only\r\n" + 
				"trap\r\n" + 
				"work\r\n" + 
				"earn\r\n" + 
				"course\r\n" + 
				"pepper\r\n" + 
				"part\r\n" + 
				"XXXXXX\r\n" + 
				"resco\r\n" + 
				"nfudre\r\n" + 
				"aptr\r\n" + 
				"sett\r\n" + 
				"oresuc\r\n" + 
				"XXXXXX\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		List<String> dictionary = new ArrayList<String>();
		while(scanner.hasNext()){
			String word = scanner.next();
			if(word.equals("XXXXXX")) break;
			dictionary.add(word);
		}
		while (scanner.hasNext()) {
			String scrambledWord = scanner.next();
			if(scrambledWord.equals("XXXXXX")) break;
			List<String> anagrams = getAnagrams(dictionary, scrambledWord);
			if(anagrams.size() == 0){
				System.out.println("NOT A VALID WORD");
				System.out.println("******");
			}else{
				StringBuilder sb = new StringBuilder();
				for(String anagram : anagrams){
					sb.append(anagram);
					sb.append("\n");
				}
				System.out.print(sb.toString());
				System.out.println("******");
			}
		}
		scanner.close();
	}
	
	static List<String> getAnagrams(List<String> dictionary, String scrambledWord){
		Map<Integer, SortedSet<String>> anagrams = new HashMap<Integer, SortedSet<String>>();
		for(String word : dictionary){
			int key = key(word);
			if(!anagrams.containsKey(key)){
				SortedSet<String> set = new TreeSet<String>();
				anagrams.put(key, set);
			}
			anagrams.get(key).add(word);
		}
		
		int key = key(scrambledWord);
		List<String> words = new ArrayList<String>();
		if(anagrams.containsKey(key)){
			words.addAll(anagrams.get(key));
		}
		
		return words;
	}
	
	static int key(String phrase){
		return sort(phrase).hashCode();
	}
	
	static String sort(String word){
		char[] chars = word.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
}
