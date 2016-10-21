package helvidios.cp.ch1.easy;

import java.util.Scanner;

public class _12157_TariffPlan {
	public static void main(String... args){
		String data = "3\r\n" + 
				"2\r\n" + 
				"61 10\r\n" + 
				"3\r\n" + 
				"40 40 40\r\n" + 
				"2\r\n" + 
				"60 65";
		Scanner scanner = new Scanner(data);
		int testCaseCount = scanner.nextInt();
		int caseNum = 0;
		while(testCaseCount-- > 0){
			int[] calls = new int[scanner.nextInt()];
			for(int call = 0; call < calls.length; call++)
				calls[call] = scanner.nextInt();
			
			int mileCost = calculateMileCost(calls);
			int juiceCost = calculateJuiceCost(calls);
			
			if(mileCost == juiceCost)
				System.out.printf("Case %1$d: Mile Juice %2$d\n", ++caseNum, mileCost);
			else if(mileCost < juiceCost)
				System.out.printf("Case %1$d: Mile %2$d\n", ++caseNum, mileCost);
			else System.out.printf("Case %1$d: Juice %2$d\n", ++caseNum, juiceCost);
		}
		scanner.close();
	}
	
	private static int calculateMileCost(int[] calls){
		int cost = 0;
		for(int call : calls)
			cost += calculateMileCost(call);
		return cost;
	}
	
	private static int calculateJuiceCost(int[] calls){
		int cost = 0;
		for(int call : calls)
			cost += calculateJuiceCost(call);
		return cost;
	}
	
	private static int calculateMileCost(int callDuration){
		return 10 * ((callDuration / 30) + 1); 
	}
	
	private static int calculateJuiceCost(int callDuration){
		return 15 * ((callDuration / 60) + 1);
	}
}
