package helvidios.cp.ch1;

import java.util.Scanner;

public class DieRollProbability {
	public static void main(String... args){
		String data = "";
		Scanner scanner = new Scanner(data);
		
		int nRolls = 10;
		double expectedValue = 3.5;
		double range = 0.5;
		System.out.printf("Probability that after %1$d die rolls the average will be within"
				+ " range %2$f(+-%3$f) = %4$f\n", nRolls, expectedValue, range, 
				countCombinationsForAverageInRange(nRolls, expectedValue, range) / 
				(double)countCombinations(nRolls));
		
		scanner.close();
	}
	
	private static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
	
	static int countCombinationsForAverageInRange(int nRolls, double average, double range){
		int count = 0;
		for(double i = average - range; i <= average + range; i += 0.1){
			count += countCombinationsForAverage(nRolls, round(i, 1));
		}
		return count;
	}
	
	static int countCombinations(int nRolls){
		return countCombinations(nRolls, 0);
	}
	
	static int countCombinations(int nRolls, int currentRoll){
		if(currentRoll == nRolls){
			return 1;
		}
		
		int count = 0;
		for(int i = 1; i <= 6; i++){
			count += countCombinations(nRolls, currentRoll + 1);
		}
		return count;
	}
	
	static int countCombinationsForAverage(int nRolls, double average){
		return countCombinationsForAverage(nRolls, 0, average, 0);
	}
	
	static int countCombinationsForAverage(int nRolls, int currentRoll, double average, int sum){
		if(currentRoll == nRolls){
			if(sum == (int)(nRolls * average)){
				return 1;
			}else return 0;
		}
		
		int count = 0;
		for(int i = 1; i <= 6; i++){
			count += countCombinationsForAverage(nRolls, currentRoll + 1, average, sum + i);
		}
		return count;
	}
}
