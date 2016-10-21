package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _12406_HelpDexter {
	public static void main(String... args){
		String data = "3\r\n" + 
				"2 2\r\n" + 
				"2 1\r\n" + 
				"2 3";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		StringBuilder out = new StringBuilder();
		int caseNum = 0;
		while(nTestCases-- > 0){
			int p = scanner.nextInt();
			int q = scanner.nextInt();
			long[] result = solve(p, q);
			out.append("Case " + ++caseNum + ": ");
			if(result == null) out.append("impossible\n");
			else if(result[0] == result[1]) out.append(result[0] + "\n");
			else out.append(result[0] + " " + result[1] + "\n");
		}
		System.out.print(out.toString());
		scanner.close();
	}
	
	public static long[] solve(int p, int q){
		long exp = (long)Math.pow(2, q);
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		for(int i = 0; i < Math.pow(2, p); i++){
			long n = toLong(i, p);
			if(n % exp == 0){
				min = Math.min(min, n);
				max = Math.max(max, n);
			}
		}
		if(min == Long.MAX_VALUE && max == Long.MIN_VALUE) return null;
		return new long[]{min, max};
	}
	
	static long toLong(int n, int p){
		long l = 0;
		for(int i = 0; i < p; i++){
			if((n & (1 << i)) != 0){
				l = l * 10 + 1;
			}else l = l * 10 + 2;
		}
		return l;
	}
}
