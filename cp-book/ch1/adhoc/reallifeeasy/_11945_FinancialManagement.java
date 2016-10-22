package helvidios.cp.ch1.adhoc.reallifeeasy;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class _11945_FinancialManagement {
	public static void main(String... args){
		String data = "1 \r\n" + 
				"100.00 \r\n" + 
				"489.12 \r\n" + 
				"12454.12 \r\n" + 
				"1234.10 \r\n" + 
				"823.05 \r\n" + 
				"109.20 \r\n" + 
				"5.27 \r\n" + 
				"1542.25 \r\n" + 
				"839.18 \r\n" + 
				"83.99 \r\n" + 
				"1295.01 \r\n" + 
				"1.75\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		scanner.useLocale(Locale.US);
		while(scanner.hasNext()){
			int nTestCases = scanner.nextInt();
			int index = 0;
			while(nTestCases-- > 0){
				int[] balance = new int[12];
				for(int i = 0; i < 12; i++){
					balance[i] = (int) (scanner.nextDouble() * 100);
				}
				int avg = (int) Math.round(getAverage(balance)); // avg in cents
				System.out.printf("%1$d %2$s\n", ++index, 
						DecimalFormat.getCurrencyInstance(Locale.US).format(avg / 100.0));
			}
		}
		scanner.close();
	}
	
	// amounts in cents
	public static double getAverage(int[] amounts){
		int sum = 0;
		for(int amount : amounts){
			sum += amount;
		}
		return (double)sum / amounts.length;
	}
}
