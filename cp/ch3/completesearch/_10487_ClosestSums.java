package helvidios.cp.ch3.completesearch;

import java.util.Arrays;
import java.util.Scanner;

public class _10487_ClosestSums {
	public static void main(String... args){
		String data = "5\r\n" + 
				"3\r\n" + 
				"12\r\n" + 
				"17\r\n" + 
				"33\r\n" + 
				"34\r\n" + 
				"3\r\n" + 
				"1\r\n" + 
				"51\r\n" + 
				"30\r\n" + 
				"3\r\n" + 
				"1\r\n" + 
				"2\r\n" + 
				"3\r\n" + 
				"3\r\n" + 
				"1\r\n" + 
				"2\r\n" + 
				"3\r\n" + 
				"3\r\n" + 
				"1\r\n" + 
				"2\r\n" + 
				"3\r\n" + 
				"3\r\n" + 
				"4\r\n" + 
				"5\r\n" + 
				"6\r\n" + 
				"0";
		String data2 = "5\r\n" + 
				"3\n 12\n 17\n 33\n 34\r\n" + 
				"3\r\n" + 
				"42\n 46\n 49\r\n" + 
				"5\r\n" + 
				"3\n 12\n 17\n 33\n 34\r\n" + 
				"3\r\n" + 
				"42\n 46\n 47\n0";
		String data3 = "2\r\n" + 
				"4\r\n" + 
				"4\r\n" + 
				"1\r\n" + 
				"8\r\n" + 
				"0";
		Scanner scanner = new Scanner(data3);
		StringBuilder out = new StringBuilder();
		int caseNum = 1;
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			if(n == 0) break;
			int[] set = new int[n];
			for(int i = 0; i < set.length; i++){
				set[i] = scanner.nextInt();
			}
			Arrays.sort(set);
			int m = scanner.nextInt();
			out.append(String.format("Case %1$d:\n", caseNum++));
			while(m-- > 0){
				int query = scanner.nextInt();
				int closestSum = findClosestSum(set, query);
				out.append(String.format("Closest sum to %1$d is %2$d.\n", 
						query, closestSum));
			}
		}
		System.out.print(out.toString());
		scanner.close();
	}
	
	public static int findClosestSum(int[] set, int query){
		int closestSum = Integer.MAX_VALUE;
		for(int i = 0; i < set.length; i++){
			int smallestDiff = Integer.MAX_VALUE;
			int bestSum = closestSum;
			for(int j = i + 1; j < set.length; j++){
				if(set[i] == set[j]) continue;
				int sum = set[i] + set[j];
				int diff = diff(sum, query);
				if(diff < smallestDiff){
					smallestDiff = diff;
					bestSum = sum;
				}else break;
			}
			if(diff(bestSum, query) < diff(closestSum, query)) closestSum = bestSum;
		}
		
		if(closestSum == Integer.MAX_VALUE) return set[0] + set[1];
		
		return closestSum;
	}
	
	private static int diff(int a, int b){
		return Math.abs(a - b);
	}
}
