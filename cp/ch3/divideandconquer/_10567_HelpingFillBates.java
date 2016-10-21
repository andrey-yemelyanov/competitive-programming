package helvidios.cp.ch3.divideandconquer;

import java.util.*;

public class _10567_HelpingFillBates{
	static final int nStates = 52;
	static final int notFound = -1;
	public static void main(String[] args){
		String data = "aaaaaaaaaaaaaabbbbbbbbbdddddddddddccccccccccccc\r\n3\r\naaaaaaaaaaaaaaaaaaa\r\naaaaaaaaaaabbbbbbbbbbbc\r\nabccc\r\n";
		String data2 = "aaaaaaaaaaaaaabbbbbbbbbdddddddddddccccccccccccc\r\n1\r\naaaaaaaaaaaaaaaaaaa";
		Scanner scanner = new Scanner(data);
		String candidates = scanner.next();
		List<List<Integer>> list = processCandidates(candidates);
		int nQueries = scanner.nextInt();
		while(nQueries-- > 0){
			String query = scanner.next();
			int[] match = findMatch(query, list);
			if(match == null) System.out.println("Not matched");
			else System.out.println("Matched " + match[0] + " " + match[1]);
		}
	}

	static int lowerBound(int n, List<Integer> list){
		int i = Collections.binarySearch(list, n);
		if(i >= 0) return i;
		int insertionPoint = - i - 1;
		if(insertionPoint == list.size()) return notFound;
		return insertionPoint;
	}

	static int[] findMatch(String query, List<List<Integer>> list){
		int largestSerialNumber = Integer.MIN_VALUE;
		int from = Integer.MIN_VALUE, to = Integer.MIN_VALUE;
		for(int i = 0; i < query.length(); i++){
			int charCode = getCharCode(query.charAt(i));
			int index = lowerBound(largestSerialNumber + 1, list.get(charCode));
			if(index == notFound) return null;
			largestSerialNumber = list.get(charCode).get(index);
			if(i == 0) from = largestSerialNumber;
			else if(i == query.length() - 1) to = largestSerialNumber;
		}		
		return new int[]{from, to};
	}

	static List<List<Integer>> processCandidates(String candidates){
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		for(int i = 0; i < nStates; i++){
			list.add(new ArrayList<Integer>());
		}

		for(int i = 0; i < candidates.length(); i++){
			list.get(getCharCode(candidates.charAt(i))).add(i);
		}

		return list;
	}

	static int getCharCode(char c){
		return Character.isUpperCase(c) 
			? c - 'A' 
			: c - 'a' + 26;
	}
}