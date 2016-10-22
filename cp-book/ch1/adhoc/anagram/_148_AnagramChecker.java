package helvidios.cp.ch1.adhoc.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class _148_AnagramChecker {
	public static void main(String... args){
		String data = "ABC\r\n" + 
				"AND\r\n" + 
				"DEF\r\n" + 
				"DXZ\r\n" + 
				"K\r\n" + 
				"KX\r\n" + 
				"LJSRT\r\n" + 
				"LT\r\n" + 
				"PT\r\n" + 
				"PTYYWQ\r\n" + 
				"Y\r\n" + 
				"YWJSRQ\r\n" + 
				"ZD\r\n" + 
				"ZZXY\r\n" + 
				"# \r\n" + 
				"ZZXY ABC DEF\r\n" + 
				"SXZYTWQP KLJ YRTD\r\n" + 
				"ZZXY YWJSRQ PTYYWQ ZZXY\r\n" + 
				"#";
		String data2 = "E\r\n" + 
				"EE\r\n" + 
				"#\r\n" + 
				"EE\r\n" + 
				"E E\r\n" + 
				"E\r\n" + 
				"#";
		String data3 = "A \r\n" + 
				"A \r\n" + 
				"BBB \r\n" + 
				"# \r\n" + 
				"AA \r\n" + 
				"# ";
		String data4 = "A\r\n" + 
				"ABC\r\n" + 
				"ACB\r\n" + 
				"AND\r\n" + 
				"DEF\r\n" + 
				"DXZ\r\n" + 
				"E\r\n" + 
				"E\r\n" + 
				"EE\r\n" + 
				"K\r\n" + 
				"KX\r\n" + 
				"LJSRT\r\n" + 
				"LT\r\n" + 
				"PT\r\n" + 
				"PTYYWQ\r\n" + 
				"Y\r\n" + 
				"YWJSRQ\r\n" + 
				"ZD\r\n" + 
				"ZZXY\r\n" + 
				"#\r\n" + 
				"AA\r\n" + 
				"ZZXY ABC DEF\r\n" + 
				"SXZYTWQP KLJ YRTD\r\n" + 
				"ZZXY YWJSRQ PTYYWQ ZZXY\r\n" + 
				"LJSRT K PTYYWQ DXZ \r\n" + 
				"EE\r\n" + 
				"E E\r\n" + 
				"E\r\n" + 
				"ABC\r\n" + 
				"TL TP\r\n" + 
				"#\r\n" + 
				"";
				String data6 = "A \r\n" + 
				"B \r\n" + 
				"# \r\n" + 
				"AA \r\n" + 
				"#";
				String data7 = "A\r\n" + 
						"B\r\n" + 
						"C\r\n" + 
						"D\r\n" + 
						"E\r\n" + 
						"F\r\n" + 
						"G\r\n" + 
						"H\r\n" + 
						"I\r\n" + 
						"J\r\n" + 
						"K\r\n" + 
						"L\r\n" + 
						"M\r\n" + 
						"N\r\n" + 
						"O\r\n" + 
						"P\r\n" + 
						"Q\r\n" + 
						"R\r\n" + 
						"S\r\n" + 
						"T\r\n" + 
						"#\r\n" + 
						"ABCDEFGHIJKLMNOPQRST\r\n" + 
						"#";
		Scanner scanner = new Scanner(data);
		List<String> dictionary = new ArrayList<String>();
		while(scanner.hasNext()){
			String word = scanner.next();
			if(word.contains("#")) break;
			dictionary.add(word);
		}
		StringBuilder s = new StringBuilder();
		while(scanner.hasNext()){
			String phrase = scanner.nextLine();
			if(phrase.contains("#")) break;
			if(!phrase.trim().equals("")){
				List<String> anagrams = generateAnagrams(dictionary, phrase);
				if(anagrams.size() > 0){
					s.append(printAnagram(phrase, anagrams));
				}
			}
		}
		System.out.print(s.toString());
		scanner.close();
	}
	
	public static List<String> generateAnagrams(List<String> dictionary, String phrase){
		long startTime = System.currentTimeMillis();
		
		String[] sortedAnagram = phrase.split(" ");
		Arrays.sort(sortedAnagram);
		String originalWordAnagram = toString(Arrays.asList(sortedAnagram)); 
		
		phrase = phrase.replace(" ", "");
		HashSet<String> anagrams = new HashSet<String>();
		generateAnagrams(dictionary, phrase, "", new Stack<String>(), anagrams);
		anagrams.remove(originalWordAnagram);
		List<String> list = new ArrayList<String>(anagrams);
		Collections.sort(list);
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.printf("%1$f seconds.\n", elapsedTime / 1000.0);
		return list;
	}
	
	public static String printAnagram(String phrase, List<String> anagrams){
		StringBuilder s = new StringBuilder();
		for(String anagram : anagrams){
			s.append(String.format("%1$s = %2$s\n", phrase, anagram));
		}
		return s.toString();
	}
	
	public static void generateAnagrams(
			List<String> dictionary, String phrase, String word, Stack<String> currentAnagram, HashSet<String> anagrams){
		if(phrase.length() == 0 && word.length() == 0){
			System.out.println(toString(currentAnagram));
			anagrams.add(toString(currentAnagram));
		}
		
		int[] occurrences = new int[26];
		for(int i = 0; i < phrase.length(); i++){
			if(occurrences[phrase.charAt(i) - 'A'] == 0){ // make sure we have not already explored from this letter
				occurrences[phrase.charAt(i) - 'A'] = 1;
				String newWord = word + phrase.charAt(i);
				String newPhrase = phrase.substring(0,  i) + phrase.substring(i + 1);
				
				if(isInDictionary(dictionary, newWord)){
					currentAnagram.push(newWord);
					generateAnagrams(dictionary, newPhrase, "", currentAnagram, anagrams);
					if(!currentAnagram.empty()) currentAnagram.pop();
				}
				
				if(isPrefix(dictionary, newWord)){
					generateAnagrams(dictionary, newPhrase, newWord, currentAnagram, anagrams);
				}
			}
		}
	}
	
	static String toString(List<String> list){
		list = new ArrayList<String>(list);
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < list.size(); i++){
			sb.append(list.get(i));
			if(i < list.size() - 1)
				sb.append(" ");
		}
		return sb.toString();
	}
	
	public static boolean isPrefix(List<String> dictionary, String word){
		int pos = Collections.binarySearch(dictionary, word);
		if(pos >= 0){
			if(pos + 1 < dictionary.size()){
				String nextWord = dictionary.get(pos + 1);
		        return nextWord.startsWith(word);
			}
			return false;
		}
		pos = -(pos+1);
        if (pos == dictionary.size()) {
            return false;
        }
        String nextWord = dictionary.get(pos);
        return nextWord.startsWith(word);
	}
	
	public static boolean isInDictionary(List<String> dictionary, String word){
		int from = 0;
		int to = dictionary.size() - 1;
		while(from <= to){
			int mid = from + ((to - from) / 2);
			if(dictionary.get(mid).equals(word)) return true;
			if(dictionary.get(mid).compareTo(word) > 0){
				to = mid - 1;
			}else{
				from = mid + 1;
			}
		}
		return false;
	}
}
