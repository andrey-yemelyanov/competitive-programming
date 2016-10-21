package helvidios.cp.ch3.greedy;

import java.util.*;

public class DynamicFrog{
	public static void main(String[] args){
		String data = "3\r\n1 10\r\nB-5\r\n1 10\r\nS-5\r\n2 10\r\nB-3 S-6";
		String data2 = "6\r\n6 50\r\nS-2 B-14 S-20 S-26 B-38 S-43\r\n8 50\r\nS-2 B-14 S-20 S-26 B-27 B-38 S-43 S-49\r\n14 50\r\nS-2 S-12 B-14 B-19 S-20 S-22 S-23 S-26 B-27 B-34 B-38 S-43 S-46 S-49\r\n16 50\r\nS-2 S-12 B-14 B-19 S-20 S-22 S-23 S-25 S-26 B-27 B-34 S-35 B-38 S-43 S-46 S-49\r\n12 50\r\nS-2 S-12 S-14 S-19 S-20 S-23 S-26 S-27 S-34 S-43 S-46 S-49\r\n15 50\r\nB-2 B-8 B-12 B-14 B-19 B-20 B-22 B-23 B-26 B-27 B-34 B-38 B-43 B-46 B-49";
		Scanner scanner = new Scanner(data2);
		int nTestCases = scanner.nextInt();
		for(int caseNum = 1; caseNum <= nTestCases; caseNum++){
			int n = scanner.nextInt(); int d = scanner.nextInt();
			boolean[] rocks = new boolean[n + 2];
			rocks[0] = true;
			rocks[rocks.length - 1] = true;
			int[] distance = new int[n + 2];
			distance[0] = 0;
			distance[distance.length - 1] = d;
			for(int i = 0; i < n; i++){
				String rock = scanner.next();
				if(rock.charAt(0) == 'B'){
					rocks[i + 1] = true;
				}
				distance[i + 1] = Integer.parseInt(rock.split("-")[1]);
			}
			System.out.printf("Case %1$d: %2$d\n", caseNum, getMaxLeapDistance(rocks, distance, d));
		}
	}
	
	static int getMaxLeapDistance(boolean[] rocks, int[] distance, int d){
		boolean[] floating = new boolean[rocks.length];
		for(int i = 0; i < floating.length; i++){
			floating[i] = true;
		}
		return getMaxLeapDistance(rocks, distance, floating, d);
	}
	
	static int getMaxLeapDistance(
		boolean[] rocks, 
		int[] distance, 
		boolean[] floating,
		int d){
			int maxLeapDistance = 0; int totalDistance = 0; int prevRock = -1;
			// leap from left bank to the right bank
			for(int i = 0; i < rocks.length; i++){
				if(rocks[i]){ // big rock
					prevRock = i;
					maxLeapDistance = Math.max(maxLeapDistance, distance[i] - totalDistance);
					totalDistance = distance[i];
				}else{ // small rock
					if(i - prevRock > 1 || prevRock == -1 || rocks[prevRock]){
						prevRock = i;
						floating[i] = false;
						maxLeapDistance = Math.max(maxLeapDistance, distance[i] - totalDistance);
						totalDistance = distance[i];
					}
				}
			}
			
			// leap from right bank to the left bank
			totalDistance = 0;
			for(int i = rocks.length - 1; i >= 0; i--){
				if(rocks[i] || floating[i]){
					if(!rocks[i]) floating[i] = false;
					maxLeapDistance = Math.max(maxLeapDistance, d - distance[i] - totalDistance);
					totalDistance = d - distance[i];
				}
			}
			
			return maxLeapDistance;
		}
}