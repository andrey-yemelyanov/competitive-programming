package helvidios.cp.ch1.easy;

import java.util.Scanner;

public class _11764_JumpingMario {
	public static void main(String... args){
		String data = "3\r\n" + 
				"\r\n" + 
				"8\r\n" + 
				"\r\n" + 
				"1 4 2 2 3 5 3 4\r\n" + 
				"\r\n" + 
				"1\r\n" + 
				"\r\n" + 
				"9\r\n" + 
				"\r\n" + 
				"5\r\n" + 
				"\r\n" + 
				"1 2 3 4 5\r\n"; 
		Scanner scanner = new Scanner(data);
		int testCaseCount = scanner.nextInt();
		int caseNum = 0;
		while(testCaseCount-- > 0){
			int wallCount = scanner.nextInt();
			int[] wallHeights = new int[wallCount];
			for(int wall = 0; wall < wallCount; wall++){
				wallHeights[wall] = scanner.nextInt();
			}
			
			int[] jumps = getJumpCount(wallHeights);
			System.out.printf("Case %1$d: %2$d %3$d\n", ++caseNum, jumps[0], jumps[1]);
		}
		scanner.close();
	}
	
	private static int[] getJumpCount(int[] wallHeights){
		int lowJumps = 0;
		int highJumps = 0;
		for(int wall = 1; wall < wallHeights.length; wall++){
			if(wallHeights[wall] > wallHeights[wall - 1])
				highJumps++;
			if(wallHeights[wall] < wallHeights[wall - 1])
				lowJumps++;
		}
		return new int[]{highJumps, lowJumps};
	}
}
