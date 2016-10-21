package helvidios.cp.ch1.adhoc.anagram;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _454_Anagrams {
	static class AnagramPair{
		public String word1;
		public String word2;
	}
	public static void main(String... args) throws NumberFormatException, IOException{
		String data = "2\r\n" + 
				"\r\n" + 
				"horse\r\n" +
				"carthorse\r\n" + 
				"horse cart\r\n" + 
				"i do not know u\r\n" + 
				"ok i now donut\r\n" + 
				"orchestra\r\n" + 
				"\r\n" + 
				"cat\n"+
				"tac\n";
		String data2 = "1 \r\n" + 
				"\r\n" + 
				"aaa \r\n" + 
				"aaa \r\n" + 
				"aaa";
		String data3 = "1\r\n" + 
				"\r\n" + 
				"erosh\r\n" + 
				"erosh\r\n" + 
				"horse\r\n" + 
				"horse\r\n" + 
				"ac bd\r\n" + 
				"a cbd\r\n" + 
				"q\r\n" + 
				"q\r\n" + 
				"[horse]'\r\n" + 
				"[e]rosh'\r\n" + 
				"iiiiiiiiiiiiij\r\n" + 
				"iiiiiiiiiiiiji\r\n" + 
				"orchestra\r\n" + 
				"pq\r\n" + 
				"qp\r\n" + 
				"carthorse\r\n" + 
				"erosh\r\n" + 
				"horsecart\r\n" + 
				"ok i now donut\r\n" + 
				"oknow uidot n\r\n" + 
				"i do not know u\r\n" + 
				"abdc\r\n" + 
				"kencti kecut\r\n" + 
				"kecut kencit";
		String data4 = "1\r\n" + 
				"\r\n" + 
				"ERosh\r\n" + 
				"eroSh\r\n" + 
				"HORse\r\n" + 
				"horse";
		String data5 = "3\r\n" + 
				"\r\n" + 
				"carthorse\r\n" + 
				"horse\r\n" + 
				"horse cart\r\n" + 
				"i do not know u\r\n" + 
				"ok i now donut\r\n" + 
				"orchestra\r\n" + 
				"\r\n" + 
				"aPPLE\r\n" + 
				"PPLEa\r\n" + 
				"LEaPP\r\n" + 
				"\r\n" + 
				"erosh\r\n" + 
				"erosh\r\n" + 
				"horse\r\n" + 
				"horse\r\n" + 
				"ac bd\r\n" + 
				"a cbd\r\n" + 
				"q\r\n" + 
				"q\r\n" + 
				"[horse]'\r\n" + 
				"[e]rosh'\r\n" + 
				"iiiiiiiiiiiiij\r\n" + 
				"iiiiiiiiiiiiji\r\n" + 
				"orchestra\r\n" + 
				"pq\r\n" + 
				"qp\r\n" + 
				"carthorse\r\n" + 
				"erosh\r\n" + 
				"horsecart\r\n" + 
				"ok i now donut\r\n" + 
				"oknow uidot n\r\n" + 
				"i do not know u\r\n" + 
				"abdc\r\n" + 
				"kencti kecut\r\n" + 
				"kecut kencit";
		String data10 = "4\r\n" + 
				"\r\n" + 
				"carthorse\r\n" + 
				"horse\r\n" + 
				"horse cart\r\n" + 
				"i do not know u\r\n" + 
				"ok i now donut\r\n" + 
				"orchestra\r\n" + 
				"\r\n" + 
				"carthorse\r\n" + 
				"horse\r\n" + 
				"horse cart\r\n" + 
				"i do not know u\r\n" + 
				"ok i now donut\r\n" + 
				"orchestra\r\n" + 
				"\r\n" + 
				"abc\r\n" + 
				"def\r\n" + 
				"\r\n" + 
				"cba\r\n" + 
				"abc\r\n" + 
				"abc";
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(data10.getBytes())));
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int nTestCases = Integer.parseInt(reader.readLine().trim());
		reader.readLine();
		while(nTestCases-- > 0){
			List<String> phrases = new ArrayList<String>();
			String line;
			while((line = reader.readLine()) != null && !line.isEmpty()){
				phrases.add(line);
			}
			
			Collections.sort(phrases);
			List<AnagramPair> pairs = getAnagramPairs(phrases);
			if(pairs.size() > 0){
				System.out.println(printAnagramPairs(pairs));
			}
			if(nTestCases >= 1) System.out.println();
		}
	}
	
	static String printAnagramPairs(List<AnagramPair> pairs){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < pairs.size(); i++){
			sb.append(String.format("%1$s = %2$s", pairs.get(i).word1, pairs.get(i).word2));
			if(i < pairs.size() - 1) sb.append("\n");
		}
		return sb.toString();
	}
	
	public static List<AnagramPair> getAnagramPairs(List<String> phrases){
		Map<Integer, List<String>> anagrams = new HashMap<Integer, List<String>>();
		for (String phrase : phrases) {
			int key = key(phrase);
			if(!anagrams.containsKey(key)){
				List<String> set = new ArrayList<String>();
				anagrams.put(key, set);
			}
			anagrams.get(key).add(phrase);
		}
		
		List<AnagramPair> pairs = new ArrayList<AnagramPair>();
		for(Map.Entry<Integer, List<String>> entry : anagrams.entrySet()){
			for(int i = 0; i < entry.getValue().size(); i++){
				for(int j = i + 1; j < entry.getValue().size(); j++){
					AnagramPair pair = new AnagramPair();
					pair.word1 = entry.getValue().get(i);
					pair.word2 = entry.getValue().get(j);
					pairs.add(pair);
				}
			}
		}
		
		Collections.sort(pairs, new Comparator<AnagramPair>() {
			@Override
			public int compare(AnagramPair o1, AnagramPair o2) {
				return o1.word1.compareTo(o2.word1);
			}
		});
		
		return pairs;
	}
	
	static int key(String phrase){
		return sort(phrase.replace(' ', '\0')).hashCode();
	}
	
	static String sort(String word){
		char[] chars = word.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
}
