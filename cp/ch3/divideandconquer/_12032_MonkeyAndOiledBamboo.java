package helvidios.cp.ch3.divideandconquer;

import java.util.*;

public class _12032_MonkeyAndOiledBamboo {
	public static void main(String... args){
		String data = "2\r\n" + 
				"5\r\n" + 
				"1 6 7 11 13\r\n" + 
				"4\r\n" + 
				"3 9 10 14";
		String data2 = "1\r\n" + 
				"4\r\n" + 
				"3 4 6 7 ";
		String data3 = "1\n1\n10";
		Scanner scanner = new Scanner(data3);
		int nTestCases = scanner.nextInt();
		for(int caseNum = 1; caseNum <= nTestCases; caseNum++){
			int n = scanner.nextInt();
			int[] rungs = new int[n];
			for(int i = 0; i < n; i++){
				rungs[i] = scanner.nextInt();
			}
			int ans = findK(rungs);
			System.out.printf("Case %1$d: %2$d\n", caseNum, ans);
		}
		scanner.close();
	}
	
	static int findK(int[] rungs){
		double lower = 0; double upper = rungs[rungs.length - 1]; double mid = 0.0; int ans = 0;
		final double epsilon = 1e-9;
		while(Math.abs(upper - lower) > epsilon){
			mid = (upper + lower) / 2.0;
			if(canReachTopRung(rungs, (int)Math.ceil(mid))){
				upper = mid;
				ans = (int)Math.ceil(mid);
			}else{
				lower = mid;
			}
		}
		return ans;
	}
	
	static boolean canReachTopRung(int[] rungs, int k){
		for(int i = 0; i < rungs.length; i++){
			int heightDiff = i == 0 ? rungs[i] : rungs[i] - rungs[i - 1];
			if(heightDiff > k) return false;
			if(heightDiff == k) k--;
		}
		return true;
	}
}
