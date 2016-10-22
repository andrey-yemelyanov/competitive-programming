package helvidios.cp.ch1.adhoc.reallifeeasy;

import java.util.Scanner;

public class _11530_SMSTyping {
	public static final int[] LAYOUT = new int[]{1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,4,1,2,3,1,2,3,4}; 
	
	public static void main(String... args){
		String data = "2\r\n" + 
				"welcome to ulab\r\n" + 
				"good luck and have fun";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		for(int i = 1; i <= nTestCases; ){
			String line = scanner.nextLine();
			if(!line.isEmpty()){
				int keyPressCount = 0;
				for(int c = 0; c < line.length(); c++){
					if(line.charAt(c) == ' ') keyPressCount++;
					else keyPressCount += LAYOUT[line.charAt(c) - 'a'];
				}
				System.out.printf("Case #%1$d: %2$d\n", i, keyPressCount);
				i++;
			}
		}
		scanner.close();
	}
}
