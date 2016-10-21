package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _11961_DNA {
	public static void main(String... args){
		String data = "1\r\n" + 
				"3 1\r\n" + 
				"AAA";
		Scanner scanner = new Scanner(data);
		int nTests = scanner.nextInt();
		while(nTests-- > 0){
			scanner.nextInt();
			int requiredSubsetSize = scanner.nextInt();
			String dnaString = scanner.next();
			SortedSet<String> mutations = getMutations(dnaString, requiredSubsetSize);
			System.out.println(mutations.size());
			System.out.println(toString(mutations));
		}
		scanner.close();
	}
	
	static String toString(SortedSet<String> mutations){
		StringBuilder str = new StringBuilder();
		List<String> list = new ArrayList<>(mutations);
		for(int i = 0; i < list.size(); i++){
			str.append(list.get(i));
			if(i < list.size() - 1) str.append("\n");
		}
		return str.toString();
	}
	
	static SortedSet<String> getMutations(String dnaString, int requiredSubsetSize){
		SortedSet<String> mutations = new TreeSet<String>();
		List<Integer> set = new ArrayList<Integer>();
		for(int i = 0; i < dnaString.length(); i++){
			set.add(i);
		}
		subsets(dnaString, mutations, set, new ArrayList<Integer>(), 0, requiredSubsetSize);
		return mutations;
	}
	
	static void subsets(
			String dnaString, 
			SortedSet<String> mutations, 
			List<Integer> set, 
			List<Integer> subset, 
			int current,
			int requiredSubsetSize){
		if(current == set.size()){
			if(subset.size() == requiredSubsetSize){ 
				mutations.addAll(generateMutations(dnaString, subset));
			}
			return;
		}
		subset.add(set.get(current));
		subsets(dnaString, mutations, set, subset, current + 1, requiredSubsetSize);
		subset.remove(subset.size() - 1);
		subsets(dnaString, mutations, set, subset, current + 1, requiredSubsetSize);
	}
	
	static List<String> generateMutations(String dnaString, List<Integer> subset){
		List<String> mutations = new ArrayList<String>();
		generateMutations(mutations, dnaString, subset, new char[subset.size()], 0);
		return mutations;
	}
	
	static char[] nucleotides = new char[]{'A','C','G','T'};
	static void generateMutations(
			List<String> mutations, 
			String dnaString, 
			List<Integer> subset, 
			char[] mutation,
			int current){
		if(current == subset.size()){
			char[] dnaStringChars = dnaString.toCharArray();
			for(int i = 0; i < subset.size(); i++){
				dnaStringChars[subset.get(i)] = mutation[i];
			}
			mutations.add(new String(dnaStringChars));
			return;
		}
		for(int i = 0; i < nucleotides.length; i++){
			mutation[current] = nucleotides[i];
			generateMutations(mutations, dnaString, subset, mutation, current + 1);
		}
	}
}
