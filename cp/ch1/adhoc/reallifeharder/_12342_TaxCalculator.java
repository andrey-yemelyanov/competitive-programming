package helvidios.cp.ch1.adhoc.reallifeharder;

import java.util.Scanner;

public class _12342_TaxCalculator {
	public static void main(String... args){
		String data = "4\r\n" + 
				"180001\r\n" + 
				"12345\r\n" + 
				"615000\n"
				+ "1000000000";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		int caseNum = 0;
		StringBuilder sb = new StringBuilder();
		while (nTestCases-- > 0) {
			int income = scanner.nextInt();
			sb.append("Case " + ++caseNum + ": " + calculateTax(income) + "\n");
		}
		System.out.print(sb.toString());
		scanner.close();
	}
	
	public static int calculateTax(int income){
		final int threshold1 = 180000;
		final int threshold2 = 300000;
		final int threshold3 = 400000;
		final int minTax = 2000;
		
		int totalTax = 0;
		income -= threshold1;
		if(income <= 0) return 0;
		
		if(income >= threshold2){
			income -= threshold2;
			totalTax += threshold2 * 0.1;
		}else{
			totalTax += (int)Math.ceil(income * 0.1);
			income = 0;
		}
		
		if(income >= threshold3){
			income -= threshold3;
			totalTax += threshold3 * 0.15;
		}else{
			totalTax += (int)Math.ceil(income * 0.15);
			income = 0;
		}
		
		if(income >= threshold2){
			income -= threshold2;
			totalTax += threshold2 * 0.2;
		}else{
			totalTax += (int)Math.ceil(income * 0.2);
			income = 0;
		}
		
		if(income > 0){
			totalTax += (int)Math.ceil(income * 0.25);
		}
		
		if(totalTax < minTax) totalTax = minTax;
		
		return totalTax;
	}
}
