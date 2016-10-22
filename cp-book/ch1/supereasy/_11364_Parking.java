package helvidios.cp.ch1.supereasy;

import java.util.Scanner;

public class _11364_Parking {
	public static void main(String... args){
		String data = "4 \r\n" + 
				"4 \r\n" + 
				"24 13 89 37 \r\n" + 
				"6 \r\n" + 
				"7 30 41 14 39 42\r\n" + 
				"3\n" +
				"0 10 40\n" + 
				"1\n" + 
				"10";
		Scanner scanner = new Scanner(data);
		int testCaseCount = scanner.nextInt();
		while(testCaseCount-- > 0){
			int storeCount = scanner.nextInt();
			int[] positions = new int[storeCount];
			for(int i = 0; i < storeCount; i++)
				positions[i] = scanner.nextInt();
			
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int position : positions){
				if(position < min) min = position;
				if(position > max) max = position;
			}
			System.out.println(2 * (max - min));
		}
		scanner.close();
	}
}
