package helvidios.cp.ch1.easy;

import java.util.Scanner;

public class _11332_SummingDigits {
	public static void main(String... args){
		String data = "2\r\n" + 
				"11\r\n" + 
				"47\r\n" + 
				"1234567892\r\n" + 
				"0\r\n"; 
		Scanner scanner = new Scanner(data);
		while(scanner.hasNextInt()){
			int n = scanner.nextInt();
			if(n == 0) break;
			
			System.out.println(g(n));
		}
		scanner.close();
	}
	
	private static int g(int n){
		int sum = sumDigits(n);
		if(sum == n) return sum;
		return g(sum);
	}
	
	private static int sumDigits(int n){
		int sum = 0;
		while (n != 0) {
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}
}
