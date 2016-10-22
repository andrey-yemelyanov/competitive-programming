package helvidios.cp.ch3.greedy;

import java.util.*;

public class _12210_MatchmakingProblem {
	public static void main(String... args){
		String data = "4 4\r\n" + 
				"26\r\n" + 
				"25\r\n" + 
				"2\r\n" + 
				"21\r\n" + 
				"35\r\n" + 
				"25\r\n" + 
				"23\r\n" + 
				"24\r\n" + 
				"1 2\r\n" + 
				"20\r\n" + 
				"30\r\n" + 
				"40\r\n" + 
				"4 2\r\n" + 
				"5\r\n" + 
				"5\r\n" + 
				"10\r\n" + 
				"15\r\n" + 
				"20\r\n" + 
				"18\r\n" + 
				"0 0";
		Scanner scanner = new Scanner(data);
		int caseNum = 1;
		while(scanner.hasNext()){
			int b = scanner.nextInt();
			int s = scanner.nextInt();
			if(b == 0 && s == 0) break;
			int[] bachelors = new int[b];
			int[] spinsters = new int[s];
			for(int i = 0; i < b; i++){
				bachelors[i] = scanner.nextInt();
			}
			for(int i = 0; i < s; i++){
				spinsters[i] = scanner.nextInt();
			}
			int[] ans = match(bachelors, spinsters);
			if(ans[0] == 0){
				System.out.printf("Case %1$d: 0\n", caseNum++);
			}else{
				System.out.printf("Case %1$d: %2$d %3$d\n", caseNum++, ans[0], ans[1]);
			}
			
		}
		scanner.close();
	}
	
	static final int notFound = -1;
	static int findMatchingSpinster(int bachelorAge, int[] spinsters, boolean[] singleSpinsters){
		int i = Arrays.binarySearch(spinsters, bachelorAge);
		if(i < 0) i = -i - 1;
		if(i == spinsters.length) i = spinsters.length - 1;
		int left = i; int right = i + 1;
		while(left >= 0 && !singleSpinsters[left]) left--;
		while(right < singleSpinsters.length && !singleSpinsters[right]) right++;
		if(left < 0 && right >= singleSpinsters.length) return notFound;
		if(left < 0) return right;
		if(right >= singleSpinsters.length) return left;
		int diffLeft = Math.abs(bachelorAge - spinsters[left]);
		int diffRight = Math.abs(bachelorAge - spinsters[right]);
		return diffLeft < diffRight ? left : right;
	}
	
	static int[] match(int[] bachelors, int[] spinsters){
		Arrays.sort(bachelors);
		Arrays.sort(spinsters);
		
		boolean[] singleSpinsters = new boolean[spinsters.length];
		for(int i = 0; i < spinsters.length; i++){
			singleSpinsters[i] = true;
		}
		
		for(int i = bachelors.length - 1; i >= 0; i--){
			int matchingSpinster = findMatchingSpinster(bachelors[i], spinsters, singleSpinsters);
			if(matchingSpinster == notFound) return new int[]{i + 1, bachelors[0]};
			singleSpinsters[matchingSpinster] = false;
		}
		
		return new int[]{0,0};
	}
}
