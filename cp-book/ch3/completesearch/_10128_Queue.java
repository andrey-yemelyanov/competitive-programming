package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _10128_Queue {
	public static void main(String... args){
		String data = "3\r\n" + 
				"10 4 4\r\n" + 
				"11 3 1\r\n" + 
				"3 1 2";
		String data2 = "1\n13 3 3";
		String data3 = "1\n10 4 4";
		String data4 = "1\n11 3 3";
		String data5 = "1\n13 3 3";
		String data6 = "1\n13 1 4";
		String data7 = "1\n1 2 1";
		String data8 = "1\n2 4 6";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int n = scanner.nextInt();
			int p = scanner.nextInt();
			int r = scanner.nextInt();
			long nValidPermutations = countValidPermutations(n,p,r);
			System.out.println(nValidPermutations);
		}
		scanner.close();
	}
	
	static int countValidPermutations(int n, int p, int r){
		int[] array = new int[n];
		for(int i = 0; i < n; i++){
			array[i] = i + 1;
		}
		
		int count = 0;
		
		if(array.length == 1){
			if(p == 1 && r == 1) return 1;
			return 0;
		}
		
		if(p == 1 || r == 1){
			int[] subarray = new int[array.length - 1];
			for(int i = 0; i < subarray.length; i++){
				subarray[i] = array[i];
			}
			count = countValidPermutations(subarray, 0, 0, 0, findMax(subarray), p == 1 ? r - 1 : p - 1);
		}else{
			for(int i = p - 1; i <= array.length - r; i++){
				swap(array, i, array.length - 1);
				int[] leftArray = new int[i];
				for(int j = 0; j < leftArray.length; j++){
					leftArray[j] = array[j];
				}
				
				int[] rightArray = new int[array.length - i - 1];
				for(int j = 0; j < rightArray.length; j++){
					rightArray[j] = array[i + j + 1];
				}
				
				count += countValidPermutations(leftArray, 0, 0, 0, findMax(leftArray), p - 1)
						* countValidPermutations(rightArray, 0, 0, 0, findMax(rightArray), r - 1)
						* choose(array.length - 1, rightArray.length);
				swap(array, i, array.length - 1);
			}
		}
		
		return count;
	}
	
	static int choose(int n, int k){
		 /* N!
		 --------
		 (N-K)!K! */
		return factorial(n) / (factorial(n - k) * factorial(k));
	}
	
	static int countValidPermutations(
		int[] array, 
		int start, 
		int nPeopleSeen, 
		int highestSoFar,
		int highest,
		int maxPeopleSeen){
		if(start == array.length){
			return 1;
		}
		int nPermutations = 0;
		for(int i = start; i < array.length; i++){
			swap(array, i, start);
			
			int newHighestSoFar = highestSoFar;
			int newNPeopleSeen = nPeopleSeen;
			if(array[start] > highestSoFar){
				newHighestSoFar = array[start];
				newNPeopleSeen++;
			}
			
			int nPeopleHigherThanHighest = highest - newHighestSoFar;
			if(newNPeopleSeen == maxPeopleSeen && newHighestSoFar == highest){
				nPermutations += factorial(array.length - start - 1);
			}else if(newNPeopleSeen < maxPeopleSeen && nPeopleHigherThanHighest >= maxPeopleSeen - newNPeopleSeen){
				nPermutations += countValidPermutations(array, start + 1, newNPeopleSeen, newHighestSoFar, highest, maxPeopleSeen);
			}
			
			swap(array, i, start);
		}
		return nPermutations;
	}
	
	static int findMax(int[] array){
		int max = array[0];
		for(int i = 1; i < array.length; i++){
			max = Math.max(max, array[i]);
		}
		return max;
	}
	
	static int factorial(int n){
		int f = 1;
		for(int i = 1; i <= n; i++){
			f *= i;
		}
		return f;
	}
	
	static void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
