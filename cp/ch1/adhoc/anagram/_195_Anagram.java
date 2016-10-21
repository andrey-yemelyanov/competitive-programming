package helvidios.cp.ch1.adhoc.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class _195_Anagram {
	public static void main(String... args){
		String data = "3\r\n" + 
				"aAb\r\n" + 
				"abc\r\n" + 
				"acba\r\n" + 
				"";
		/*String data4 = "1\n" + 
				"abAB";*/
		Scanner scanner = new Scanner(data);
		int nWords = scanner.nextInt();
		StringBuilder s = new StringBuilder();
		while (nWords-- > 0) {
			s.append(printAnagrams(generateAnagrams(scanner.next())));
			if(nWords >= 1) s.append("\n");
		}
		System.out.println(s.toString());
		scanner.close();
	}
	
	static String sort(String s){
	    Character[] ch=new Character[s.length()];
	    for (int i = 0; i < s.length(); i++) {
	        ch[i]=s.charAt(i);
	    }
	    Arrays.sort(ch, new Comparator<Character>() {
	        @Override
	        public int compare(Character o1, Character o2) {
	            if((Character.isUpperCase(o1) && Character.isLowerCase(o2)) || 
	            		(Character.isLowerCase(o1) && Character.isUpperCase(o2))){
	            	if(Character.toLowerCase(o1) != Character.toLowerCase(o2))
	            		return Character.compare(Character.toLowerCase(o1), Character.toLowerCase(o2));
	            }
	            return o1.compareTo(o2);
	        }
	    });

	    StringBuilder sb = new StringBuilder();
	    for(Character c : ch){
	    	sb.append(c);
	    }
	    return sb.toString();
	}
	
	public static String[] generateAnagrams(String word){
		List<String> anagrams = new ArrayList<String>();
		generateAnagrams(sort(word), "", anagrams);
		return anagrams.toArray(new String[0]);
	}
	
	static void generateAnagrams(String word, String currentAnagram, List<String> anagrams){
		if(word.equals("")){
			anagrams.add(currentAnagram);
			return;
		}
		
		Set<Character> occurrences = new HashSet<Character>();
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!occurrences.contains(c)){
				occurrences.add(c);
				generateAnagrams(word.substring(0, i) + word.substring(i + 1), 
						currentAnagram + c, anagrams);
			}
		}
	}
	
	public static String printAnagrams(String[] anagrams){
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < anagrams.length; i++){
			s.append(anagrams[i]);
			if(i < anagrams.length - 1){
				s.append("\n");
			}
		}
		return s.toString();
	}
}
