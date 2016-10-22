package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _11059_MaximumProduct {
	public static void main(String... args){
		String data = "3\r\n" + 
				"2 4 -3\r\n" + 
				"5\r\n" + 
				"2 5 -1 2 -1";
		String data2 = "3\n0 2 0\n";
		String data3 = "18\r\n" + 
				"10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10 10";
		String data4 = "2\r\n" + 
				"-1 -2\r\n" + 
				"\r\n" + 
				"6\r\n" + 
				"2 5 -1 2 -3 0";
		Scanner scanner = new Scanner(data4);
		int caseNum = 1;
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			int[] seq = new int[n];
			for(int i = 0; i < n; i++){
				seq[i] = scanner.nextInt();
			}
			System.out.printf("Case #%1$d: The maximum product is %2$d.\n\n", caseNum++, maxProduct(seq));
		}
		scanner.close();
	}
	
	public static long maxProduct(int[] seq){
		long maxProduct = Integer.MIN_VALUE;
		for(int start = 0; start < seq.length; start++){
			if(seq[start] > maxProduct) maxProduct = seq[start];
			for(int end = start + 1; end < seq.length; end++){
				long product = seq[start];
				for(int i = start + 1; i <= end; i++){
					product *= seq[i];
				}
				if(product > maxProduct) maxProduct = product;
			}
		}
		if(maxProduct < 0) maxProduct = 0;
		return maxProduct;
	}
}
