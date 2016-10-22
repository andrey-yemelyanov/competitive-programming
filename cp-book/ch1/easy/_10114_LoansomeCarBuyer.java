package helvidios.cp.ch1.easy;

import java.util.Locale;
import java.util.Scanner;

public class _10114_LoansomeCarBuyer {
	public static void main(String... args){
		String data = "30 500.0 15000.0 3\r\n" + 
				"0 .10\r\n" + 
				"1 .03\r\n" + 
				"3 .002\r\n" + 
				"12 500.0 9999.99 2\r\n" + 
				"0 .05\r\n" + 
				"2 .1\r\n" + 
				"60 2400.0 30000.0 3\r\n" + 
				"0 .2\r\n" + 
				"1 .05\r\n" + 
				"12 .025\r\n" + 
				"-99 0 17000 1\r\n"; 
		Scanner scanner = new Scanner(data);
		scanner.useLocale(Locale.US);
		while(scanner.hasNextInt()){
			int loanDuration = scanner.nextInt();
			if(loanDuration < 0) break;
			
			double downPayment = scanner.nextDouble();
			double loanAmount = scanner.nextDouble();
			
			int depreciationRecordCount = scanner.nextInt();
			double[] depreciationRates = new double[loanDuration + 1]; 
			while(depreciationRecordCount-- > 0){
				int month = scanner.nextInt();
				double depreciationRate = scanner.nextDouble();
				depreciationRates[month] = depreciationRate;
			}
			
			double actualRate = depreciationRates[0];
			for(int month = 1; month < depreciationRates.length; month++){
				if(depreciationRates[month] == 0)
					depreciationRates[month] = actualRate;
				else actualRate = depreciationRates[month];
			}
			
			int months = getNumberOfMonthsUntilProfitability(loanAmount, downPayment, depreciationRates);
			if(months == 1) System.out.printf("%1$s month\n", months);
			else System.out.printf("%1$s months\n", months);
		}
		scanner.close();
	}
	
	/**
	 * Calculates number of complete months before the borrower owes less than the asset is worth.
	 */
	private static int getNumberOfMonthsUntilProfitability(
			double loanAmount, double downPayment, double[] depreciationRates){
		double assetValue = loanAmount + downPayment;
		double monthlyPayment = loanAmount / (depreciationRates.length - 1);
		assetValue -= assetValue * depreciationRates[0]; // apply initial depreciation
		
		int currentMonth = 1;
		while (currentMonth < depreciationRates.length && loanAmount >= assetValue) {
			// make a monthly payment
			loanAmount -= monthlyPayment;
			// apply depreciation to asset
			assetValue -= assetValue * depreciationRates[currentMonth];
			currentMonth++;
		}
		
		return currentMonth - 1;
	}
}
