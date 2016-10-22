package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _10660_CitizenAttentionOffices {
	public static final int GRID_SIZE = 5;
	public static void main(String... args){
		String data = "4\r\n" + 
				"1\r\n" + 
				"2 2 1\r\n" + 
				"4\r\n" + 
				"0 0 1\r\n" + 
				"4 4 1\r\n" + 
				"0 4 1\r\n" + 
				"4 0 1\r\n" + 
				"5\r\n" + 
				"0 0 1\r\n" + 
				"1 1 1\r\n" + 
				"2 2 1\r\n" + 
				"3 3 1\r\n" + 
				"4 4 1\r\n" + 
				"7\r\n" + 
				"4 2 2\r\n" + 
				"3 3 1\r\n" + 
				"2 4 3\r\n" + 
				"2 1 1\r\n" + 
				"1 3 4\r\n" + 
				"1 2 2\r\n" + 
				"1 0 1\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int[][] cityPlan = new int[GRID_SIZE][GRID_SIZE];
			int m = scanner.nextInt();
			while(m-- > 0){
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				int population = scanner.nextInt() * 1000;
				cityPlan[row][col] = population;
			}
			Integer[] optimalLocation = findOptimalOfficePlacement(cityPlan);
			System.out.println(printArray(optimalLocation));
		}
		scanner.close();
	}
	
	public static String printArray(Integer[] array){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < array.length; i++){
			builder.append(array[i]);
			if(i < array.length - 1) builder.append(" ");
		}
		return builder.toString();
	}
	
	public static int distance(
			int row1, 
			int col1, 
			int row2, 
			int col2, 
			int multiplier){
		int distance = Math.abs(row1 - row2) + Math.abs(col1 - col2);
		return distance * multiplier;
	}
	
	public static Integer[] findOptimalOfficePlacement(int[][] cityPlan){
		Integer[][] locations = generatePossibleOfficeLocations();
		int minDistanceSum = Integer.MAX_VALUE;
		Integer[] optimalLocation = null;
		for(Integer[] location : locations){
			int distanceSum = 0;
			for(int row = 0; row < cityPlan.length; row++){
				for(int col = 0; col < cityPlan[0].length; col++){
					int minDistance = Integer.MAX_VALUE;
					for(int l = 0; l < location.length; l++){
						int officeRow = location[l] / cityPlan.length;
						int officeCol = location[l] % cityPlan[0].length;
						int distance = distance(
								row, 
								col,
								officeRow,
								officeCol, 
								cityPlan[row][col]);
						if(distance < minDistance){
							minDistance = distance;
						}
					}
					distanceSum += minDistance;
				}
			}
			if(distanceSum < minDistanceSum){
				minDistanceSum = distanceSum;
				optimalLocation = location;
			}
		}
		return optimalLocation;
	}
	
	public static Integer[][] generatePossibleOfficeLocations(){
		final int gridSize = GRID_SIZE * GRID_SIZE;
		final int nOffices = GRID_SIZE;
		// generate 5 choose 25 combinations
		List<Integer[]> combinations = new ArrayList<Integer[]>();

		for(int a = 0; a < gridSize - nOffices + 1; a++){
			for(int b = a + 1; b < gridSize - nOffices + 2; b++){
				for(int c = b + 1; c < gridSize - nOffices + 3; c++){
					for(int d = c + 1; d < gridSize - nOffices + 4; d++){
						for(int e = d + 1; e < gridSize - nOffices + 5; e++){
							combinations.add(new Integer[]{a,b,c,d,e});
						}
					}
				}
			}
		}
		
		return combinations.toArray(new Integer[0][]);
	}
}
