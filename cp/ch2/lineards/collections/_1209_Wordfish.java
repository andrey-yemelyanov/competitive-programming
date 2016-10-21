package helvidios.cp.ch2.lineards.collections;

import java.util.Scanner;

public class _1209_Wordfish {
	public static void main(String... args){
		String data = "WORDFISH";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			System.out.println(getPassword(scanner.next()));
		}
		scanner.close();
	}
	
	public static String getPassword(String username){
		String[] permutations = new String[21];
		String permutation = username;
		permutations[10] = permutation;
		
		for(int i = 9; i >= 0; i--){
			if(permutation != null) permutation = prevPermutation(permutation);
			permutations[i] = permutation;
		}
		
		permutation = username;
		for(int i = 11; i < permutations.length; i++){
			if(permutation != null) permutation = nextPermutation(permutation);
			permutations[i] = permutation;
		}
		
		int largestMinDist = 0;
		int index = -1;
		for(int i = 0; i < permutations.length; i++){
			if(permutations[i] != null){
				int distance = getMinAbsDistance(permutations[i]);
				if(distance > largestMinDist){
					largestMinDist = distance;
					index = i;
				}
			}
		}
		
		return permutations[index] + largestMinDist;
	}
	
	static int getMinAbsDistance(String word){
		int dist = Integer.MAX_VALUE;
		for(int i = 0; i <= word.length() - 2; i++){
			int d = Math.abs(word.charAt(i) - word.charAt(i + 1));
			if(d < dist) dist = d;
		}
		return dist;
	}
	
	public static String prevPermutation(String code){
		char[] chars = code.toCharArray();
		
		// Find the largest index k such that a[k] > a[k + 1].
		int k = chars.length - 2;
		for(;k >= 0 && chars[k] <= chars[k + 1]; k--);
		if(k < 0) return null;
				
		// Find the largest index l greater than k such that a[k] > a[l].
		int l = chars.length - 1;
		for(;l > k && chars[k] <= chars[l];l--);
		
		// Swap the value of a[k] with that of a[l].
		swap(chars, k, l);
		
		// Reverse the sequence from a[k + 1] up to and including the final element a[n].
		int left = k + 1;
		int right = chars.length - 1;
		while(left < right){
			swap(chars, left++, right--);
		}
		
		return new String(chars);
	}
	
	/*
	 * 1. Find the largest index k such that a[k] < a[k + 1]. 
	 * 		If no such index exists, the permutation is the last permutation.
	   2. Find the largest index l greater than k such that a[k] < a[l].
	   3. Swap the value of a[k] with that of a[l].
	   4. Reverse the sequence from a[k + 1] up to and including the final element a[n].
	 */
	public static String nextPermutation(String code){
		char[] chars = code.toCharArray();
		
		// Find the largest index k such that a[k] < a[k + 1].
		int k = chars.length - 2;
		for(;k >= 0 && chars[k] >= chars[k + 1]; k--);
		if(k < 0) return null;
		
		// Find the largest index l greater than k such that a[k] < a[l].
		int l = chars.length - 1;
		for(;l > k && chars[k] >= chars[l];l--);
		
		// Swap the value of a[k] with that of a[l].
		swap(chars, k, l);
		
		// Reverse the sequence from a[k + 1] up to and including the final element a[n].
		int left = k + 1;
		int right = chars.length - 1;
		while(left < right){
			swap(chars, left++, right--);
		}
		
		return new String(chars);
	}
	
	static void swap(char[] chars, int i, int j){
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}
}
