package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _10063_KnuthsPermutation {
	public static void main(String... args){
		String data = "abc\r\n" + 
				"bca\r\n" + 
				"dcba";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			String chars = scanner.next();
			List<String> permutations = permute(chars);
			StringBuilder out = new StringBuilder();
			for(int i = 0; i < permutations.size(); i++){
				out.append(permutations.get(i));
				out.append("\n");
			}
			System.out.print(out.toString());
			if(scanner.hasNext()) System.out.println();
		}
		scanner.close();
	}
	
	static List<String> permute(String chars){
		List<String> permutations = new ArrayList<String>();
		permute(chars, "", 0, permutations);
		return permutations;
	}
	
	static void permute(String chars, String permutation, int current, List<String> permutations){
		if(current == chars.length()){
			permutations.add(permutation);
			return;
		}
		for(int i = 0; i <= permutation.length(); i++){
			String newPermutation = permutation.substring(0, i)
									+ chars.charAt(current)
									+ permutation.substring(i);
			permute(chars, newPermutation, current + 1, permutations);
		}
	}
}
