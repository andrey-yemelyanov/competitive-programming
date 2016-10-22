package helvidios.cp.ch1.supereasy;

import java.util.Scanner;

public class _11498_DivisionOfNlogonia {
	public static void main(String... args){
		String data = "3\r\n" + 
				"2 1\r\n" + 
				"10 10\r\n" + 
				"-10 1\r\n" + 
				"0 33\r\n" + 
				"4\r\n" + 
				"-1000 -1000\r\n" + 
				"-1000 -1000\r\n" + 
				"0 0\r\n" + 
				"-2000 -10000\r\n" + 
				"-999 -1001\r\n" + 
				"0";
		Scanner scanner = new Scanner(data);
		while (scanner.hasNext()) {
			int queryCount = scanner.nextInt();
			if(queryCount == 0) break;
			
			int[] divPoint = new int[2];
			divPoint[0] = scanner.nextInt();
			divPoint[1] = scanner.nextInt();
			
			while(queryCount-- > 0){
				int[] residence = new int[2];
				residence[0] = scanner.nextInt();
				residence[1] = scanner.nextInt();
				
				System.out.println(getCountryMembership(divPoint, residence));
			}
		}
		scanner.close();
	}
	
	private static String getCountryMembership(int[] divPoint, int[] residence){
		if(residence[0] == divPoint[0] || residence[1] == divPoint[1])
			return "divisa";
		if(residence[0] < divPoint[0]){
			if(residence[1] < divPoint[1]) return "SO";
			else return "NO";
		}else{
			if(residence[1] < divPoint[1]) return "SE";
			else return "NE";
		}
	}
}
