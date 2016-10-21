package helvidios.cp.ch1.adhoc.reallifeeasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _10554_CaloriesFromFat {
	public static final int[] CALORIES = new int[]{9,4,4,4,7};
	
	public static void main(String... args){
		String data = "3g 10g 10% 0g 0g\r\n" + 
				"55% 100C 0% 0g 30g\r\n" + 
				"-\r\n" + 
				"25g 0g 0g 0g 0g\r\n" + 
				"-\r\n" + 
				"1g 15% 20% 30% 1C\r\n" + 
				"-\r\n" + 
				"-\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		String[] testCase = null;
		while ((testCase = readTestCase(scanner)).length != 0) {
			int[][] foodItems = new int[testCase.length][5];
			char[][] units = new char[testCase.length][5];
			for(int i = 0; i < testCase.length; i++){
				String[] tokens = testCase[i].split(" ");
				for(int j = 0; j < tokens.length; j++){
					foodItems[i][j] = Integer.parseInt(tokens[j].substring(0, tokens[j].length() - 1));
					units[i][j] = tokens[j].charAt(tokens[j].length() - 1);
				}
			}
			int amountFat = percentCaloriesFromFat(foodItems, units);
			System.out.println(amountFat + "%");
		}
			
		scanner.close();
	}

	private static String[] readTestCase(Scanner scanner){
		List<String> testCase = new ArrayList<String>();
		String line;
		while(!(line = scanner.nextLine()).equals("-")){
			testCase.add(line);
		}
		return testCase.toArray(new String[0]);
	}
	
	public static int percentCaloriesFromFat(int[][] foodItems, char[][] units){
		double totalFatCalories = 0;
		double totalCalories = 0;
		for(int i = 0; i < foodItems.length; i++){
			double totalCaloriesFoodItem = getTotalCalories(foodItems[i], units[i]);
			totalFatCalories += caloriesFromFat(foodItems[i], units[i], totalCaloriesFoodItem);
			totalCalories += totalCaloriesFoodItem;
		}
		return (int)Math.round((totalFatCalories / totalCalories) * 100);
	}
	
	public static double caloriesFromFat(int[] quantities, char[] units, double totalCalories){
		final int FAT = 0;
		if(units[FAT] == 'C') return quantities[FAT];
		if(units[FAT] == 'g') return calories(quantities[FAT], FAT);
		return (quantities[FAT] / 100.0) * totalCalories;
	}
	
	public static double getTotalCalories(int[] quantities, char[] units){
		double calories = 0;
		int percentage = 0;
		for(int i = 0; i < quantities.length; i++){
			if(units[i] == 'C') calories += quantities[i];
			else if(units[i] == 'g') calories += calories(quantities[i], i);
			else if(units[i] == '%') percentage += quantities[i];
		}
		
		double totalCalories = calories / (1 - (percentage / 100.0));
		return totalCalories;
	}
	
	static int calories(int quantity, int micronutrient){
		return CALORIES[micronutrient] * quantity;
	}
}
