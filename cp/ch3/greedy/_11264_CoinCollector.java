package helvidios.cp.ch3.greedy;

import java.util.*;

public class _11264_CoinCollector {
	public static void main(String... args){
		String data = "2\r\n" + 
				"6\r\n" + 
				"1 2 4 8 16 32\r\n" + 
				"6\r\n" + 
				"1 3 6 8 15 20\r\n" + 
				""; // 6 4
		String data2 = "8\r\n" + 
				"6\r\n" + 
				"1 2 4 8 16 32\r\n" + 
				"6\r\n" + 
				"1 3 6 8 15 20\r\n" + 
				"7\r\n" + 
				"1 5 9 74 111 121 159\r\n" + 
				"10\r\n" + 
				"1 2 3 4 5 6 7 8 9 10\r\n" + 
				"5\r\n" + 
				"1 2 4 8 15\r\n" + 
				"8\r\n" + 
				"1 5 9 17 25 33 42 100\r\n" + 
				"16\r\n" + 
				"1 2 4 17 58 69 125 254 478 1023 10000 145236 172589 172590 1000000 10000000\r\n" + 
				"2\r\n" + 
				"1 2\n"; // 6 4 5 2 4	5 14 2
		
		Scanner scanner = new Scanner(data2);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int nDenominations = scanner.nextInt();
			int[] denominations = new int[nDenominations];
			for(int i = 0; i < denominations.length; i++){
				denominations[i] = scanner.nextInt();
			}
			System.out.println(maxDenominations(denominations));
		}
		scanner.close();
	}
	
	static int maxDenominations(int[] denominations){
		int amount = denominations[0];
		int nCoins = 1;
		for(int i = 1; i < denominations.length; i++){
			if(i == denominations.length - 1 || amount + denominations[i] < denominations[i + 1]){
				amount += denominations[i];
				nCoins++;
			}
		}
		return nCoins;
	}
}
