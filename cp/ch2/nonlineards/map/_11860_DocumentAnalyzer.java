package helvidios.cp.ch2.nonlineards.map;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _11860_DocumentAnalyzer {
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	public static void main(String... args) throws NumberFormatException, IOException{
		String data = "3\r\n" + 
				"1. a case is a case,\r\n" + 
				"2. case is not a case~\r\n" + 
				"END\r\n" + 
				"a b c d e\r\n" + 
				"END\r\n" + 
				"a@#$a^%a a a\r\n" + 
				"b b----b b++12b\r\n" + 
				"END";
		String data2 = "3\r\n" + 
				"you work in a leading software development company. as you are great in coding, most of the critical\r\n" + 
				"tasks are allotted for you. you like the challenge and you feel excited to solve those problems.\r\n" + 
				"recently your company is developing a project named document analyzer. in this project you\r\n" + 
				"are assigned a task; of course a critical task. the task is that you are given a document consisting\r\n" + 
				"of lowercase letters, numbers and punctuations. you have to analyze the document and separate the\r\n" + 
				"words first. words are consecutive sequences of lower case letters. after listing the words, in the order\r\n" + 
				"same as they occurred in the document, you have to number them from 1, 2, …, n. after that you have\r\n" + 
				"to find the range p and q (p  q) such that all kinds of words occur between p and q (inclusive). if\r\n" + 
				"there are multiple such solutions you have to find the one where the difference of p and q is smallest. if\r\n" + 
				"still there is a tie, then find the solution where p is smallest.\r\n" + 
				"since you do have other works to do, you have to solve this task within 5 hours.\n"+
				"END\r\n" + 
				"a b c d e\r\n" + 
				"END\r\n" + 
				"a@#$a^%a a a\r\n" + 
				"b b----b b++12b\r\n" + 
				"END";
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data2.getBytes()));
		int nDocuments = Integer.parseInt(reader.readLine());
		for(int i = 1; i <= nDocuments; i++){
			String line;
			StringBuilder sb = new StringBuilder();
			while(!(line = reader.readLine()).equals("END")){
				sb.append(line);
				sb.append("\n");
			}
			int[] interval = getInterval(getWords(sb.toString()));
			System.out.printf("Document %1$d: %2$d %3$d\n", i, interval[0] + 1, interval[1] + 1);
		}
	}
	
	public static String[] getWords(String text){
		Pattern pattern = Pattern.compile("[a-z]+");
		Matcher matcher = pattern.matcher(text);
		List<String> words = new ArrayList<String>();
		while(matcher.find()){
			words.add(matcher.group());
		}
		return words.toArray(new String[0]);
	}
	
	static int countDistinctWords(String[] words){
		return new TreeSet<String>(Arrays.asList(words)).size();
	}
	
	public static int[] getInterval(String[] words){
		Map<String, Integer> map = new TreeMap<String, Integer>();
		int nUniqueWords = countDistinctWords(words);
		int p = 0; int q = 0; int bestP = 0; int bestQ = 0;
		int minIntervalLength = Integer.MAX_VALUE;
		
		while(true){
			if(map.size() < nUniqueWords && q == words.length) break;
			if(map.size() == nUniqueWords){
				int intervalLength = q - p;
				if(intervalLength < minIntervalLength){
					minIntervalLength = intervalLength;
					bestQ = q - 1;
					bestP = p;
				}

				if(map.get(words[p]) == 1){
					map.remove(words[p]);
				}else{
					map.put(words[p], map.get(words[p]) - 1);
				}
				p++;
			}
			else{
				if(!map.containsKey(words[q])){
					map.put(words[q], 0);
				}
				map.put(words[q], map.get(words[q]) + 1);
				q++;
			}
		}
		
		return new int[]{bestP, bestQ};
	}
}
