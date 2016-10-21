package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _11553_GridGame {
	public static void main(String... args){
		String data = "3\r\n" + 
				"2\r\n" + 
				"10 10\r\n" + 
				"-5 -5\r\n" + 
				"2\r\n" + 
				"10 -5\r\n" + 
				"10 -5\r\n" + 
				"2\r\n" + 
				"10 -5\r\n" + 
				"-5 10\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int n = scanner.nextInt();
			int[][] grid = new int[n][n];
			for(int i = 0; i < grid.length; i++){
				for(int j = 0; j < grid[0].length; j++){
					grid[i][j] = scanner.nextInt();
				}
			}
			System.out.println(solve(grid));
		}
		scanner.close();
	}
	
	public static int solve(int[][] grid){
		Integer[] ordering = new Integer[grid.length];
		for(int i = 0; i < ordering.length; i++){
			ordering[i] = i;
		}
		
		int maxPrize = Integer.MAX_VALUE;
		do{
			int sum = 0;
			for(int i = 0; i < ordering.length; i++){
				sum += grid[i][ordering[i]];
			}
			maxPrize = Math.min(maxPrize, sum);
		}while((ordering = nextPermutation(ordering)) != null);
		
		return maxPrize;
	}
	
	public static <E extends Comparable<E>> E[] nextPermutation(E[] permutation){
		// Find the largest index k such that a[k] < a[k + 1].
		int k = permutation.length - 2;
		for(;k >= 0 && permutation[k].compareTo(permutation[k + 1]) >= 0; k--);
		if(k < 0) return null;
		
		// Find the largest index l greater than k such that a[k] < a[l].
		int l = permutation.length - 1;
		for(;l > k && permutation[k].compareTo(permutation[l]) >= 0;l--);
		
		// Swap the value of a[k] with that of a[l].
		swap(permutation, k, l);
		
		// Reverse the sequence from a[k + 1] up to and including the final element a[n].
		int left = k + 1;
		int right = permutation.length - 1;
		while(left < right){
			swap(permutation, left++, right--);
		}
		
		return permutation;
	}
	
	static <E> void swap(E[] array, int i, int j){
		E temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
