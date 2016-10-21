package helvidios.cp.ch1.adhoc.reallifeeasy;

import java.util.Scanner;

public class _10812_BeatTheSpread {
	public static void main(String... args){
		String data = "1\r\n" + 
				"43 12";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int nTestCases = scanner.nextInt();
			while (nTestCases-- > 0) {
				int sum = scanner.nextInt();
				int diff = scanner.nextInt();
				int[] scores = getScores(sum, diff);
				if(scores != null){
					System.out.println(scores[0] + " " + scores[1]);
				}else System.out.println("impossible");
			}
		}
		scanner.close();
	}
	
	public static int[] getScores(int s, int d){
		// score1 = sum - score2
		// sum - 2*score2 = diff
		// 2*score2 - sum = diff
		int score1, score2;
		
		if(((int)Math.abs(s - d)) % 2 == 0){
			score2 = (s - d) / 2;
			if(score2 >= 0){
				score1 = s - score2;
				if(score1 >= 0) return new int[]{Math.max(score1, score2), Math.min(score1, score2)};
			}
			
			score2 = (s + d) / 2;
			if(score2 >= 0){
				score1 = s - score2;
				if(score1 >= 0) return new int[]{Math.max(score1, score2), Math.min(score1, score2)};
			}
		}
		
		return null;
	}
}
