package helvidios.cp.ch2.nonlineards.map;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class _11572_UniqueSnowflakes {
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	public static void main(String... args) throws NumberFormatException, IOException{
		String data = "1\r\n" + 
				"5\r\n" + 
				"1\r\n" + 
				"2\r\n" + 
				"3\r\n" + 
				"2\r\n" + 
				"1";
		String data2 = "1\r\n" + 
				"5\r\n" + 
				"1\r\n" + 
				"2\r\n" + 
				"2\r\n" + 
				"2\r\n" + 
				"5";
		
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data2.getBytes()));
		int nTestCases = Integer.parseInt(reader.readLine());
		while(nTestCases-- > 0){
			int nSnowflakes = Integer.parseInt(reader.readLine());
			int[] snowflakes = new int[nSnowflakes];
			for(int i = 0; i < nSnowflakes; i++){
				snowflakes[i] = Integer.parseInt(reader.readLine());
			}
			System.out.println(countUniqueSnowflakes(snowflakes));
		}
	}
	
	public static int countUniqueSnowflakes(int[] snowflakes){
		Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
		int maxCount = 0;
		int from = 0;
		for(int i = 0; i < snowflakes.length; i++){
			if(!map.containsKey(snowflakes[i])){
				map.put(snowflakes[i], new ArrayList<Integer>());
			}
			List<Integer> occurrences = map.get(snowflakes[i]);
			if(!occurrences.isEmpty() && occurrences.get(occurrences.size() - 1) >= from){
				int uniqueCount = i - from;
				if(uniqueCount > maxCount) maxCount = uniqueCount;
				from = occurrences.get(occurrences.size() - 1) + 1;
			}
			else if(i == snowflakes.length - 1){
				int uniqueCount = i - from + 1;
				if(uniqueCount > maxCount) maxCount = uniqueCount;
			}
			occurrences.add(i);
		}
		return maxCount;
	}
}
