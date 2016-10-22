package helvidios.cp.ch1.supereasy;

import java.util.Scanner;

public class _11547_Automatic_Answer {
	public static void main(String... args){
		String data = "2\r\n" + 
				"637\r\n" + 
				"-120";
		Scanner scanner = new Scanner(data);
		int testCaseCount = scanner.nextInt();
		while (testCaseCount-- > 0) {
			int n = scanner.nextInt();
			int result = Math.abs((((((n * 567) / 9) + 7492) * 235) / 47) - 798);
			System.out.println((result / 10) % 10);
		}
		scanner.close();
	}
}
