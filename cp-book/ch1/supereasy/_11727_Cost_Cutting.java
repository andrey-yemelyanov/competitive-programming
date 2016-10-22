package helvidios.cp.ch1.supereasy;

import java.util.Arrays;
import java.util.Scanner;

public class _11727_Cost_Cutting {
	public static void main(String... args){
		String data = "3\n"
				+ "1000 2000 3000\n"
				+ "3000 2500 1500\r\n" + 
				"1500 1200 1800\r\n";
		Scanner scanner = new Scanner(data);
		int testCaseCount = scanner.nextInt();
		int caseCount = 1;
		while (testCaseCount-- > 0) {
			int[] salaries = new int[3];
			salaries[0] = scanner.nextInt();
			salaries[1] = scanner.nextInt();
			salaries[2] = scanner.nextInt();
			Arrays.sort(salaries);
			System.out.printf("Case %1$d: %2$d\n", caseCount++, salaries[1]);
		}
		scanner.close();
	}
}
