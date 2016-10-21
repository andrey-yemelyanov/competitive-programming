package helvidios.cp.ch1.easy;

import java.util.Scanner;

public class _11679_SubPrime {
	public static void main(String... args){
		String data = "3 3\r\n" + 
				"1 1 1\r\n" + 
				"1 2 1\r\n" + 
				"2 3 2\r\n" + 
				"3 1 3\r\n" + 
				"3 3\r\n" + 
				"1 1 1\r\n" + 
				"1 2 1\r\n" + 
				"2 3 2\r\n" + 
				"3 1 4\r\n" + 
				"3 3\r\n" + 
				"1 1 1\r\n" + 
				"1 2 2\r\n" + 
				"2 3 2\r\n" + 
				"3 1 2\r\n" + 
				"0 0";
		Scanner scanner = new Scanner(data);
		while (scanner.hasNext()) {
			int bankCount = scanner.nextInt();
			int loanCount = scanner.nextInt();
			if(bankCount == 0 && loanCount == 0) break;
			
			int[] reserves = new int[bankCount];
			for(int bank = 0; bank < bankCount; bank++){
				reserves[bank] = scanner.nextInt();
			}
			
			int[][] loans = new int[loanCount][3];
			for(int loan = 0; loan < loanCount; loan++){
				loans[loan][0] = scanner.nextInt() - 1; // debtor bank
				loans[loan][1] = scanner.nextInt() - 1; // creditor bank
				loans[loan][2] = scanner.nextInt(); // loan value
			}
			
			if(isBailoutNeeded(reserves, loans)) System.out.println("N");
			else System.out.println("S");
		}
		scanner.close();
	}
	
	private static boolean isBailoutNeeded(int[] reserves, int[][] loans){
		for(int[] loan : loans){
			reserves[loan[0]] -= loan[2]; // draw loan value from debtor's reserves
			reserves[loan[1]] += loan[2]; // deposit loan value to creditor's reserves
		}
		
		for(int bankReserves : reserves){
			if(bankReserves < 0) return true;
		}
		
		return false;
	}
}
