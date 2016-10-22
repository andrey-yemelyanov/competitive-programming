package helvidios.cp.ch2.nonlineards.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class _10132_FileFragmentation {
	public static void main(String... args){
		String data = "2\n\n" + 
				"011\r\n" + 
				"0111\r\n" + 
				"01110\r\n" + 
				"111\r\n" + 
				"0111\r\n" + 
				"10111\n\n"
				+ "011\r\n" + 
				"0111\r\n" + 
				"01110\r\n" + 
				"111\r\n" + 
				"0111\r\n" + 
				"10111";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		scanner.nextLine();
		scanner.nextLine();
		while(nTestCases-- > 0){
			List<String> fragments = new ArrayList<String>();
			String line = null;
			while(scanner.hasNext() && !(line = scanner.nextLine()).isEmpty()){
				fragments.add(line);
			}
			System.out.println(getOriginalBitPattern(fragments));
			if(nTestCases > 0) System.out.println();
		}
		scanner.close();
	}
	
	static int getTotalNumberOfBits(List<String> fragments){
		int count = 0;
		for(String fragment : fragments){
			count += fragment.length();
		}
		return count;
	}
	
	public static String getOriginalBitPattern(List<String> fragments){
		Map<String, Integer> occurrences = new TreeMap<String, Integer>();
		
		int fileLength = getTotalNumberOfBits(fragments) / (fragments.size() / 2); 
		for(int i = 0; i < fragments.size(); i++){
			for(int j = i + 1; j < fragments.size(); j++){
				String pattern1 = fragments.get(i) + fragments.get(j);
				if(pattern1.length() == fileLength){
					if(!occurrences.containsKey(pattern1)){
						occurrences.put(pattern1, 0);
					}
					occurrences.put(pattern1, occurrences.get(pattern1) + 1);
				}
				
				String pattern2 = fragments.get(j) + fragments.get(i);
				if(pattern2.length() == fileLength){
					if(!occurrences.containsKey(pattern2)){
						occurrences.put(pattern2, 0);
					}
					occurrences.put(pattern2, occurrences.get(pattern2) + 1);
				}
			}
		}
		
		int maxOccurrence = 0;
		String originalPattern = null;
		for(Entry<String, Integer> entry : occurrences.entrySet()){
			if(entry.getValue() > maxOccurrence){
				maxOccurrence = entry.getValue();
				originalPattern = entry.getKey();
			}
		}
		return originalPattern;
	}
}
