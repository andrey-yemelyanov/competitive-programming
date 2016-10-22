package helvidios.cp.ch1.supereasy;

import java.util.Scanner;

public class _10550_Combination_Lock {
	private final static int FULL_CIRCLE = 360;
	private final static int MARK_COUNT = 40;
	
	public static void main(String... args){
		String data = "0 30 0 30\r\n" + 
				"5 35 5 35\r\n" + 
				"0 20 0 20\r\n" + 
				"7 27 7 27\r\n" + 
				"0 10 0 10\r\n" + 
				"9 19 9 19\r\n" + 
				"0 0 0 0";
		
		Scanner scanner = new Scanner(data);
		while (scanner.hasNextInt()) {
			int initPosition = scanner.nextInt();
			int comb1 = scanner.nextInt();
			int comb2 = scanner.nextInt();
			int comb3 = scanner.nextInt();
			
			if(initPosition == 0 && comb1 == 0 && comb2 == 0 && comb3 == 0) break;
			
			int degreesPerMark = FULL_CIRCLE / MARK_COUNT;
			// 1. turn the dial clockwise 2 full turns
			int totalDegrees = FULL_CIRCLE * 2; 
			// 2. stop at the first number of the combination
			totalDegrees += getMarkCountBetween(initPosition, comb1, false) * degreesPerMark;
			// 3. turn the dial counter-clockwise 1 full turn
			totalDegrees += FULL_CIRCLE;
			// 4. continue turning counter-clockwise until the 2nd number is reached
			totalDegrees += getMarkCountBetween(comb1, comb2, true) * degreesPerMark;
			// 5. turn the dial clockwise again until the 3rd number is reached
			totalDegrees += getMarkCountBetween(comb2, comb3, false) * degreesPerMark;
			
			System.out.println(totalDegrees);
		}
		scanner.close();
	}
	
	public static int getMarkCountBetween(int mark1, int mark2, boolean inClockwiseDirection){
		int markCountClockwise = 0;
		while (mark1 != mark2) {
			markCountClockwise++;
			mark1 = (mark1 + 1) % MARK_COUNT;
		}
		
		if(inClockwiseDirection){
			return markCountClockwise;
		}else{
			return MARK_COUNT - markCountClockwise;
		}
	}
}
