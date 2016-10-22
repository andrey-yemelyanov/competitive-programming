package helvidios.cp.ch1.easy;

import java.util.Scanner;

public class _11559_EventPlanning {
	public static void main(String... args){
		String data = "3 1000 2 3\r\n" + 
				"200\r\n" + 
				"0 2 2\r\n" + 
				"300\r\n" + 
				"27 3 20\r\n" + 
				"5 2000 2 4\r\n" + 
				"300\r\n" + 
				"4 3 0 4\r\n" + 
				"450\r\n" + 
				"7 8 0 13";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int participantCount = scanner.nextInt();
			int budget = scanner.nextInt();
			int hotelCount = scanner.nextInt();
			int weekCount = scanner.nextInt();
			int[] hotelPrices = new int[hotelCount];
			int[][] availableBeds = new int[hotelCount][weekCount];
			for(int hotel = 0; hotel < hotelCount; hotel++){
				hotelPrices[hotel] = scanner.nextInt();
				for(int week = 0; week < weekCount; week++)
					availableBeds[hotel][week] = scanner.nextInt();
			}
			
			int minCost = getMinCost(participantCount, budget, hotelPrices, availableBeds);
			if(minCost == Integer.MAX_VALUE) System.out.println("stay home");
			else System.out.println(minCost);
		}
		scanner.close();
	}
	
	private static int getMinCost(
			int participantCount, 
			int budget, 
			int[] hotelPrices, 
			int[][] availableBeds){
		int minCost = Integer.MAX_VALUE;
		for(int hotel = 0; hotel < hotelPrices.length; hotel++){
			// make sure staying at this hotel is within the budget
			int totalCost = hotelPrices[hotel] * participantCount;
			if(totalCost > budget) continue;
			
			// make sure this hotel has available beds for all participants during at least one week
			for(int week = 0; week < availableBeds[hotel].length; week++){
				if(availableBeds[hotel][week] >= participantCount){
					if(totalCost < minCost) minCost = totalCost;
				}
			}
		}
		
		return minCost;
	}
}
