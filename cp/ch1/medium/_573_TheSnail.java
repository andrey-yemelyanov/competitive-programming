package helvidios.cp.ch1.medium;

import java.util.Scanner;

public class _573_TheSnail {
	private static class Result{
		public int daysCount;
		public boolean snailLeftTheWell;
		
		public Result(int daysCount, boolean snailLeftTheWell){
			this.daysCount = daysCount;
			this.snailLeftTheWell = snailLeftTheWell;
		}
	}
	public static void main(String... args){
		String data = "6 3 1 10\r\n" + 
				"10 2 1 50\r\n" + 
				"50 5 3 14\r\n" + 
				"50 6 4 1\r\n" + 
				"50 6 3 1\r\n" + 
				"1 1 1 1\r\n" + 
				"0 0 0 0\r\n"; 
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int wellHeight = scanner.nextInt();
			int dailyClimbDistance = scanner.nextInt();
			int slideDownDistance = scanner.nextInt();
			int fatigueFactor = scanner.nextInt();
			if(wellHeight == 0) break;
			
			Result r = getDaysRequired(
					wellHeight, 
					dailyClimbDistance, 
					slideDownDistance, 
					fatigueFactor);
			if(!r.snailLeftTheWell) System.out.printf("failure on day %1$d\n", r.daysCount);
			else System.out.printf("success on day %1$d\n", r.daysCount);
		}
		scanner.close();
	}
	
	private static Result getDaysRequired(
			int wellHeight, 
			double dailyClimbDistance,
			int slideDownDistance,
			int fatigueFactor){
		int daysCount = 0;
		double snailHeight = 0;
		double distanceLostToFatigue = dailyClimbDistance * (fatigueFactor / 100.0);
		
		while(true){
			daysCount++; // new day
			
			// Climb up during the day.
			// The snail never climbs a negative distance. 
			// If the fatigue factor drops the snail's climbing 
			// distance below zero, the snail does not climb at all that day.
			if(dailyClimbDistance > 0){
				snailHeight += dailyClimbDistance;
				dailyClimbDistance -= distanceLostToFatigue;
				if(snailHeight > wellHeight) break;
			}
			
			// slide down during the night
			snailHeight -= slideDownDistance;
			if(snailHeight < 0) break;
		}
		
		if(snailHeight < 0)
			return new Result(daysCount, false);
		else return new Result(daysCount, true);
	}
}
