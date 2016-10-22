package helvidios.cp.ch1.adhoc.reallifeharder;

import java.util.Scanner;

public class _11743_CreditCheck {
	public static void main(String... args){
		String data = "2\r\n\n\n\n\n" + 
				"5181 2710 9900 0012\r\n" + 
				"5181 2710 9900 0017";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while (nTestCases-- > 0) {
			String[] line = scanner.nextLine().split("\\s+");
			if(line.length == 4){
				int[] creditCardNumber = new int[16];
				int k = 0;
				for(int i = 0; i < line.length; i++){
					for(int j = 0; j < line[i].length(); j++){
						creditCardNumber[k++] = Character.getNumericValue((line[i].charAt(j)));
					}
				}
				if(isValid(creditCardNumber)) System.out.println("Valid");
				else System.out.println("Invalid");
			}else nTestCases++;
		}
		scanner.close();
	}
	
	public static boolean isValid(int[] creditCardNumber){
		// 1. Starting with the second-last digit and moving backwards, 
		// double every other digit to obtain a list of numbers.
		int[] doubledDigits = new int[8];
		for(int j = 0, i = creditCardNumber.length - 2; i >= 0; i-=2, j++){
			doubledDigits[j] = 2 * creditCardNumber[i];
		}
		
		// 2. Add up the digits of these numbers, 
		int sum1 = 0;
		for(int n : doubledDigits){
			sum1 += sumDigits(n);
		}
		
		// 3. Then add the undoubled digits from the original number.
		int sum2 = 0;
		for(int i = 1; i < creditCardNumber.length; i+=2){
			sum2 += creditCardNumber[i];
		}
		
		// 4. Sum the two results. If the total ends in a 0, 
		// the credit card number is valid, and it is invalid otherwise.
		return (sum1 + sum2) % 10 == 0;
	}
	
	static int sumDigits(int n){
		int sum = 0;
		while(n > 0){
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}
}
