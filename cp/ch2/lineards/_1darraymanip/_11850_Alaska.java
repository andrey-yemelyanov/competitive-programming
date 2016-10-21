package helvidios.cp.ch2.lineards._1darraymanip;

import java.util.Scanner;

public class _11850_Alaska {
	public static final int TOTAL_DISTANCE = 1422;
	public static final int CAR_RANGE = 200;
	public static void main(String... args){
		String data = "2\n"
				+ "0\r\n" + 
				"900\r\n" + 
				"8\r\n" + 
				"1400\r\n" + 
				"1200\r\n" + 
				"1000\r\n" + 
				"800\r\n" + 
				"600\r\n" + 
				"400\r\n" + 
				"200\r\n" + 
				"0\n"
				+ "0";
		Scanner scanner = new Scanner(data);
		while(true){
			int nStations = scanner.nextInt();
			if(nStations == 0) break;
			int[] stations = new int[nStations];
			for(int i = 0; i < nStations; i++){
				stations[i] = scanner.nextInt();
			}
			if(tripPossible(stations)) System.out.println("POSSIBLE");
			else System.out.println("IMPOSSIBLE");
		}
		scanner.close();
	}
	
	public static boolean tripPossible(int[] stations){
		boolean[] route = new boolean[TOTAL_DISTANCE * 2 + 1]; // built a round-trip route
		for(int station : stations){
			route[station] = true;
			route[TOTAL_DISTANCE * 2 - station] = true;
		}
		
		for(int coordinate = 0; coordinate < route.length; coordinate++){
			if(stationAvailable(coordinate, route) && 
					getDistanceToNextStation(coordinate, route) > CAR_RANGE) return false;
		}
		
		return true;
	}
	
	static int getDistanceToNextStation(int currentStation, boolean[] route){
		int coordinate = currentStation + 1;
		for(; coordinate < route.length && !stationAvailable(coordinate, route); coordinate++);
		return coordinate - currentStation;
	}
	
	static boolean stationAvailable(int mileMark, boolean[] stations){
		return stations[mileMark];
	}
}
