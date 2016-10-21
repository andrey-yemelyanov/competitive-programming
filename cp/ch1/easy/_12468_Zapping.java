package helvidios.cp.ch1.easy;

import java.util.Scanner;

public class _12468_Zapping {
	public static void main(String... args){
		String data = "3 9\r\n" + 
				"0 99\r\n" + 
				"\r\n" + 
				"12 27\r\n" + 
				"\r\n" + 
				"-1 -1\r\n";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNextInt()){
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			if(a == -1 && b == -1) break;
			
			System.out.println(Math.min(
					forwardPushCount(a, b), 
					backwardPushCount(a, b)));
		}
		scanner.close();
	}
	
	private static int forwardPushCount(int a, int b){
		int count = 0;
		while(a != b){
			count++;
			a = (a + 1) % 100;
		}
		return count;
	}
	
	private static int backwardPushCount(int a, int b){
		int count = 0;
		while(a != b){
			count++;
			a--;
			if(a < 0) a = 99;
		}
		return count;
	}
}
