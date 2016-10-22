package helvidios.cp.ch1.adhoc.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/*
 * Algorithm borrowed from http://blog.tobygameac.com/2013/08/uva-148-anagram-checker.html.
 */
public class _148_AnagramChecker_Efficient {
	public static void main(String... args){
		String data2 = "ABC\r\n" + 
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
		Scanner scanner = new Scanner(data2);
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
				List<String> anagrams = generateAnagrams(phrase, dictionary);
				if(anagrams.size() > 0){
					s.append(printAnagram(phrase, anagrams));
				}
			}
		}
		System.out.print(s.toString());
		scanner.close();
	}
	
	public static String printAnagram(String phrase, List<String> anagrams){
		StringBuilder s = new StringBuilder();
		for(String anagram : anagrams){
			s.append(String.format("%1$s = %2$s\n", phrase, anagram));
		}
		return s.toString();
	}
	
	public static List<String> generateAnagrams(String phrase, List<String> dictionary){
		List<String> anagrams = new ArrayList<String>();
		
		String[] sortedAnagram = phrase.split(" ");
		Arrays.sort(sortedAnagram);
		String originalAnagram = toString(Arrays.asList(sortedAnagram)); 
		
		phrase = phrase.replace(" ", "");
		generateAnagrams(phrase.length(), 0, anagrams, new Stack<String>(), originalAnagram, dictionary, generateFrequencyTable(phrase));
		
		return anagrams;
	}
	
	public static int[] generateFrequencyTable(String phrase){
		int[] freq = new int[26];
		for(int i = 0; i < phrase.length(); i++){
			freq[phrase.charAt(i) - 'A']++;
		}
		return freq;
	}
	
	public static void generateAnagrams(
			int remaining, 
			int index, 
			List<String> anagrams, 
			Stack<String> currentAnagram,
			String originalAnagram,
			List<String> dictionary,
			int[] chars){
		if(remaining == 0){
			String anagram = toString(currentAnagram);
			if(!anagram.equals(originalAnagram)){
				anagrams.add(anagram);
			}
			return;
		}
		
		if(index >= dictionary.size()) return;
		
		boolean validWord = true;
		int[] charsCopy = copy(chars);
		for(int i = 0; i < dictionary.get(index).length(); i++){
			int charIndex = dictionary.get(index).charAt(i) - 'A';
			charsCopy[charIndex]--;
			validWord = (charsCopy[charIndex] >= 0);
			if(!validWord) break;
		}
		
		if(validWord){
			currentAnagram.push(dictionary.get(index));
			generateAnagrams(remaining - dictionary.get(index).length(), index + 1, anagrams, currentAnagram, originalAnagram, dictionary, charsCopy);
			currentAnagram.pop();
		}
		
		generateAnagrams(remaining, index + 1, anagrams, currentAnagram, originalAnagram, dictionary, chars);
	}
	
	static int[] copy(int[] array){
		int[] copy = new int[array.length];
		for(int i = 0; i < array.length; i++){
			copy[i] = array[i];
		}
		return copy;
	}
	
	static String toString(List<String> anagram){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < anagram.size(); i++){
			sb.append(anagram.get(i));
			if(i < anagram.size() - 1)
				sb.append(" ");
		}
		return sb.toString();
	}
}
