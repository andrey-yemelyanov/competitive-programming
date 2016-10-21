package helvidios.cp.ch3.completesearch;

import java.util.Arrays;
import java.util.Scanner;

public class _10125_Sumsets {
	public static void main(String... args){
		String data = "5\r\n" + 
				"2\r\n" + 
				"3\r\n" + 
				"5\r\n" + 
				"7\r\n" + 
				"12\r\n" + 
				"5\r\n" + 
				"2\r\n" + 
				"16\r\n" + 
				"64\r\n" + 
				"256\r\n" + 
				"1024\r\n" + 
				"0";
		String data2 = "5\r\n" + 
				"-8 4 10 11 14\n0";
		Scanner scanner = new Scanner(data2);
		while(true){
			int n = scanner.nextInt();
			if(n == 0) break;
			int[] set = new int[n];
			for(int i = 0; i < n; i++){
				set[i] = scanner.nextInt();
			}
			int d = findD(set);
			if(d == Integer.MIN_VALUE) System.out.println("no solution");
			else System.out.println(d);
		}
		scanner.close();
	}
	
	public static int findD(int[] set){
		Arrays.sort(set);
		for(int d = set.length - 1; d >= 0; d--){
			for(int c = 0; c < set.length; c++){
				if(c != d){
					for(int b = 0; b < set.length; b++){
						if(b != c && b != d){
							int a = set[d] - (set[c] + set[b]);
							int i = Arrays.binarySearch(set, a);
							if(i >= 0 && i != d && i != c && i != b){
								return set[d];
							}
						}
					}
				}
			}
		}
		return Integer.MIN_VALUE;
	}
}
