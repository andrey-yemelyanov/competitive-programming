package helvidios.cp.ch2.lineards.collections;

import java.util.Scanner;

public class _146_IDCodes {
	public static void main(String... args){
		String data = "abaacb\r\n" + 
				"cbbaa\r\n" + 
				"#\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(true){
			String code = scanner.next();
			if(code.equals("#")) break;
			String perm = nextPermutation(code);
			if(perm == null) System.out.println("No Successor");
			else System.out.println(perm);
		}
		scanner.close();
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
