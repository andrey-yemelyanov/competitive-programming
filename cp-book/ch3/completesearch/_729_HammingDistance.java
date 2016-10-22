package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _729_HammingDistance{
	public static void main(String[] args){
		String data = "2\n4 2\n4 2";
		Scanner scanner = new Scanner(data);
		int nDataSets = scanner.nextInt();
		while(nDataSets-- > 0){
			int n = scanner.nextInt();
			int h = scanner.nextInt();
			System.out.print(print(generate(h, n)));
			if(nDataSets > 0) System.out.println();
		}
		scanner.close();
	}

	static String print(List<String> patterns){
		StringBuilder out = new StringBuilder();
		for(String pattern : patterns){
			out.append(pattern);
			out.append("\n");
		}	
		return out.toString();
	}

	static List<String> generate(int maxBitsSet, int maxPatternLength){
		List<String> patterns = new ArrayList<String>();
		generate("", 0, maxBitsSet, maxPatternLength, patterns);
		return patterns;
	}

	static void generate(String pattern, int bitsSet, int maxBitsSet, int maxPatternLength, List<String> patterns){
		if(pattern.length() == maxPatternLength){
			if(bitsSet == maxBitsSet) patterns.add(pattern);
		}else{
			generate(pattern + "0", bitsSet, maxBitsSet, maxPatternLength, patterns);
			generate(pattern + "1", bitsSet + 1, maxBitsSet, maxPatternLength, patterns);
		}
	}
}