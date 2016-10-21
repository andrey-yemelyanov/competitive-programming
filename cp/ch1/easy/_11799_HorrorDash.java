package helvidios.cp.ch1.easy;

import java.util.Scanner;

public class _11799_HorrorDash {
	public static void main(String... args){
		String data = "2 \r\n" + 
				"\r\n" + 
				"5 9 3 5 2 6 \r\n" + 
				"\r\n" + 
				"1 2\r\n"; 
		Scanner scanner = new Scanner(data);
		int testCaseCount = scanner.nextInt();
		int caseNum = 0;
		while(testCaseCount-- > 0){
			int studentCount = scanner.nextInt();
			int max = Integer.MIN_VALUE;
			while (studentCount-- > 0) {
				int speed = scanner.nextInt();
				if(speed > max) max = speed;
			}
			System.out.printf("Case %1$d: %2$d\n", ++caseNum, max);
		}
		scanner.close();
	}
}
