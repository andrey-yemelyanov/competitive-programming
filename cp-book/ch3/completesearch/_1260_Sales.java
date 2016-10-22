package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _1260_Sales {
	public static void main(String... args){
		String data = "2\r\n" + 
				"5\r\n" + 
				"38 111 102 111 177\r\n" + 
				"8\r\n" + 
				"276 284 103 439 452 276 452 398\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int size = scanner.nextInt();
			int[] sales = new int[size];
			for(int i = 0; i < sales.length; i++){
				sales[i] = scanner.nextInt();
			}
			System.out.println(solve(sales));
		}
		scanner.close();
	}
	
	public static int solve(int[] sales){
		int[] b = new int[sales.length - 1];
		for(int day = 1; day < sales.length; day++){
			int count = 0;
			for(int previousDay = 0; previousDay < day; previousDay++){
				if(sales[previousDay] <= sales[day]){
					count++;
				}
			}
			b[day - 1] = count;
		}
		
		int sum = 0;
		for(int item : b){
			sum += item;
		}
		return sum;
	}
}
