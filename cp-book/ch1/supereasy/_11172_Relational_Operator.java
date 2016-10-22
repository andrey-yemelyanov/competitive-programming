package helvidios.cp.ch1.supereasy;

import java.util.Scanner;

public class _11172_Relational_Operator {
	public static void main(String... args){
		String data = "3\n"
				+ "10 20\n"
				+ "20 10\n"
				+ "10 10";
		
		Scanner scanner = new Scanner(data);
		int count = scanner.nextInt();
		while(count-- > 0){
			int num1 = scanner.nextInt();
			int num2 = scanner.nextInt();
			if(num1 > num2) System.out.println(">");
			else if(num1 == num2) System.out.println("=");
			else System.out.println("<");
		}
		scanner.close();
	}
}
