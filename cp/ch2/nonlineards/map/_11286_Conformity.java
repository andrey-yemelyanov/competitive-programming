package helvidios.cp.ch2.nonlineards.map;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class _11286_Conformity {
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	
	public static void main(String... args) throws IOException{
		String data = "3\r\n" + 
				"100 101 102 103 488\r\n" + 
				"100 200 300 101 102\r\n" + 
				"103 102 101 488 100\r\n" + 
				"3\r\n" + 
				"200 202 204 206 208\r\n" + 
				"123 234 345 456 321\r\n" + 
				"100 200 300 400 444\r\n" + 
				"0\r\n" + 
				"\r\n" + 
				"";
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data.getBytes()));
		StringBuilder sb = new StringBuilder();
		while(true){
			int nFrosh = Integer.parseInt(reader.readLine());
			if(nFrosh == 0) break;
			int[][] courseCombinations = new int[nFrosh][5];
			for(int i = 0; i < courseCombinations.length; i++){
				String[] courses = reader.readLine().split(" ");
				for(int j = 0; j < 5; j++){
					courseCombinations[i][j] = Integer.parseInt(courses[j]);
				}
			}
			sb.append(popularCombinationScore(courseCombinations));
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	public static int popularCombinationScore(int[][] courseCombinations){
		String[] combinations = new String[courseCombinations.length];
		for(int i = 0; i < courseCombinations.length; i++){
			String combination = "";
			Arrays.sort(courseCombinations[i]);
			for(int j = 0; j < courseCombinations[i].length; j++){
				combination += courseCombinations[i][j];
			}
			combinations[i] = combination;
		}
		
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for(String combination : combinations){
			if(!map.containsKey(combination)){
				map.put(combination, 0);
			}
			map.put(combination, map.get(combination) + 1);
		}
		
		int maxOccurrence = 0;
		for(Entry<String, Integer> entry : map.entrySet()){
			if(entry.getValue() > maxOccurrence){
				maxOccurrence = entry.getValue();
			}
		}
		
		int count = 0;
		for(Entry<String, Integer> entry : map.entrySet()){
			if(entry.getValue() == maxOccurrence){
				count += entry.getValue();
			}
		}
		
		return maxOccurrence == 1 ? combinations.length : count;
	}
}
