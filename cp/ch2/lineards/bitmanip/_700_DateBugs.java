package helvidios.cp.ch2.lineards.bitmanip;

import java.util.Scanner;

public class _700_DateBugs {
	public static void main(String... args){
		String data = "2\r\n" + 
				"1941 1900 2000\r\n" + 
				"2005 1904 2040\r\n" + 
				"2\r\n" + 
				"1998 1900 2000\r\n" + 
				"1999 1900 2000\r\n" + 
				"0\r\n" + 
				"\r\n" + 
				"";
		String data2 = "2\r\n" + 
				"1941 1900 2000\r\n" + 
				"2005 1904 2040\r\n" + 
				"2\r\n" + 
				"1998 1900 2000\r\n" + 
				"1999 1900 2000\r\n" + 
				"1\r\n" + 
				"1938 1900 2000\r\n" + 
				"1\r\n" + 
				"1938 1900 2100\r\n" + 
				"1\r\n" + 
				"12 11 22\r\n" + 
				"4\r\n" + 
				"1 0 3\r\n" + 
				"0 0 5\r\n" + 
				"2 0 7\r\n" + 
				"1 0 11\r\n" + 
				"2\r\n" + 
				"132 100 200\r\n" + 
				"135 100 200\r\n" + 
				"5\r\n" + 
				"101 100 200\r\n" + 
				"101 100 201\r\n" + 
				"101 100 202\r\n" + 
				"101 100 203\r\n" + 
				"101 100 204\r\n" + 
				"5\r\n" + 
				"101 100 200\r\n" + 
				"101 100 201\r\n" + 
				"100 100 202\r\n" + 
				"101 100 203\r\n" + 
				"101 100 204\r\n" + 
				"2\r\n" + 
				"1998 1000 2000\r\n" + 
				"1935 1930 1941\r\n" + 
				"2\r\n" + 
				"1999 1000 2000\r\n" + 
				"1936 1930 1941\r\n" + 
				"2\r\n" + 
				"1000 1000 2000\r\n" + 
				"1937 1930 1941\r\n" + 
				"1\r\n" + 
				"0 0 1\r\n" + 
				"20\r\n" + 
				"2 2 3\r\n" + 
				"31 31 32\r\n" + 
				"3 3 4\r\n" + 
				"37 37 38\r\n" + 
				"5 5 6\r\n" + 
				"41 41 42\r\n" + 
				"7 7 8\r\n" + 
				"43 43 44\r\n" + 
				"11 11 12\r\n" + 
				"47 47 48\r\n" + 
				"13 13 14\r\n" + 
				"53 53 54\r\n" + 
				"71 71 72\r\n" + 
				"17 17 18\r\n" + 
				"59 59 60\r\n" + 
				"19 19 20\r\n" + 
				"61 61 62\r\n" + 
				"23 23 24\r\n" + 
				"67 67 68\r\n" + 
				"29 29 30\r\n" + 
				"3\r\n" + 
				"9 1 10\r\n" + 
				"0 0 11\r\n" + 
				"0 0 101\r\n" + 
				"10\r\n" + 
				"1942 1900 2000\r\n" + 
				"1942 1900 2000\r\n" + 
				"1942 1900 2000\r\n" + 
				"1942 1900 2000\r\n" + 
				"1942 1900 2000\r\n" + 
				"1942 1900 2000\r\n" + 
				"1942 1900 2000\r\n" + 
				"1942 1900 2000\r\n" + 
				"1942 1900 2000\r\n" + 
				"1942 1900 2000\r\n" + 
				"2\r\n" + 
				"1240 1234 1244\r\n" + 
				"1913 1900 2001\r\n" + 
				"1\r\n" + 
				"2000 0 9999\r\n" + 
				"2\r\n" + 
				"0 0 9999\r\n" + 
				"1492 1492 1493\r\n" + 
				"2\r\n" + 
				"1 0 9999\r\n" + 
				"1492 1492 1493\r\n" + 
				"0";
		Scanner scanner = new Scanner(data2);
		int index = 0;
		while(scanner.hasNext()){
			int nComputers = scanner.nextInt();
			if(nComputers == 0) break;
			int[][] d = new int[nComputers][3];
			for(int i = 0; i < nComputers; i++){
				d[i][0] = scanner.nextInt();
				d[i][1] = scanner.nextInt();
				d[i][2] = scanner.nextInt();
			}
			int year = actualYear(d);
			if(year == -1) System.out.printf("Case #%1$d:\nUnknown bugs detected.\n", ++index);
			else System.out.printf("Case #%1$d:\nThe actual year is %2$d.\n", ++index, year);
			System.out.println();
		}
		scanner.close();
	}
	
	public static int actualYear(int[][] data){
		int actualYear = -1;
		int maxA = Integer.MIN_VALUE;
		for(int[] c : data){
			if(c[1] > maxA) maxA = c[1];
		}
		
		final int max = 10000;
		int year = maxA;
		boolean yearFound = false;
		for(; year < max; year++){
			yearFound = true;
			for(int[] c : data){
				if(yearShown(year, c[1], c[2]) != c[0]){
					yearFound = false;
				};
			}
			if(yearFound) break;
		}
		
		if(yearFound) actualYear = year; 
		
		return actualYear;
	}
	
	public static int yearShown(int year, int a, int b){
		if(year >= a && year < b) return year;
		return ((year - b) % (b - a)) + a;
	}
}
