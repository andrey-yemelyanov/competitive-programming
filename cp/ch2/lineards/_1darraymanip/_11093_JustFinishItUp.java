package helvidios.cp.ch2.lineards._1darraymanip;

import java.util.Scanner;

public class _11093_JustFinishItUp {
	public static final int NOT_POSSIBLE = -1;
	public static void main(String... args){
		String data = "2\r\n" + 
				"\r\n" + 
				"5\r\n" + 
				"\r\n" + 
				"1 1 1 1 1\r\n" + 
				"\r\n" + 
				"1 1 2 1 1\r\n" + 
				"\r\n" + 
				"7\r\n" + 
				"\r\n" + 
				"1 1 1 10 1 1 1\r\n" + 
				"\r\n" + 
				"2 2 2 2 2 2 2\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		for(int i = 1; i <= nTestCases; i++){
			int nStations = scanner.nextInt();
			int[] p = new int[nStations];
			int[] q = new int[nStations];
			for(int j = 0; j < p.length; j++){
				p[j] = scanner.nextInt();
			}
			for(int j = 0; j < q.length; j++){
				q[j] = scanner.nextInt();
			}
			int start = racePossible(p, q);
			if(start == NOT_POSSIBLE) System.out.println("Case " + i + ": Not possible");
			else System.out.println("Case " + i + ": Possible from station " + start);
		}
		scanner.close();
	}
	
	public static int racePossible(int[] p, int[] q){
		if(!fuelAvailable(p, q)) return NOT_POSSIBLE;
		for(int start = 0; start < p.length; start++){
			int currentStation = start;
			int nextStation = nextStation(currentStation, p);
			int fuel = p[currentStation];
			
			while(nextStation != start){
				fuel -= q[currentStation];
				if(fuel < 0) break;
				else fuel += p[nextStation];
				currentStation = nextStation;
				nextStation = nextStation(currentStation, p);
			}
			
			if(nextStation == start) return start + 1;
		}
		
		return NOT_POSSIBLE;
	}
	
	static int nextStation(int currentStation, int[] p){
		return (currentStation + 1) % p.length;
	}
	
	public static boolean fuelAvailable(int[] p, int[] q){
		return sum(p) >= sum(q);
	}
	
	static int sum(int[] array){
		int sum = 0;
		for(int i = 0; i < array.length; i++){
			sum += array[i];
		}
		return sum;
	}
}
