package helvidios.cp.ch1.adhoc.reallifeeasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _161_TrafficLights {
	public static final int FIVE_HOURS = 5 * 60 * 60;
	public static final int ORANGE_CYCLE = 5;
	public static void main(String... args){
		/*String data = "19 20 \n\n  0\r\n" + 
				"30\r\n" + 
				"     25    35 0\r\n" + 
				"10\n11\n25\n\n0" +
				"\n   0   \n"+
				"0 0 0\r\n" + 
				"";*/
		String data2 = "19 20 0 \r\n" + 
				"30 \r\n" + 
				"25 35 0 \r\n" + 
				"30 58	62 32 31 32 22 64 89 90 \r\n" + 
				"10 27 10 29 56 35 28 \r\n" + 
				"45 56 58 \r\n" + 
				"29 28 54 78 58	96 32 10 28 35 \r\n" + 
				"36 98 75 32 54 85 96 \r\n" + 
				"32 42 45 \r\n" + 
				"10 27 77 88 28 62 52 35 53 90 \r\n" + 
				"50 51 52 53 56 55 56 57 58 59 \r\n" + 
				"60 61 62	63 64 65 66 67 68 69 \r\n" + 
				"70 71 72 73 74 75 76 77 \r\n" + 
				"78 79 \r\n" + 
				"80 81 82 83 84 85 86 87 88 89 \r\n" + 
				"90 31 32 33 34 35 36 37 38 41 \r\n" + 
				"0 \r\n" + 
				"10 10 0 \r\n" + 
				"11 10 12 13 14 0 \r\n" + 
				"10 11 12 13 14 15 16 17 18 19 20 0 \r\n" + 
				"10 26 0 \r\n" + 
				"42 87 88 90 0 \r\n" + 
				"53 54 55 68 20 \r\n" + 
				"0 \r\n" + 
				"50 51 52 53 54 55 56 25 \r\n" + 
				"18 0 \r\n" + 
				"56 90 10 25 32 64 58 52 0 \r\n" + 
				"0 0 0 ";
		//String data3 = "10 26 0 0 0 0";
		Scanner scanner = new Scanner(data2);
		List<Integer> cycles = new ArrayList<Integer>();
		int nZeros = 0;
		while(scanner.hasNextInt()){
			int cycle = scanner.nextInt();
			if(cycle != 0){
				cycles.add(cycle);
			}else{
				nZeros++;
				if(nZeros == 3) break;
				if(!cycles.isEmpty()){
					nZeros = 0;
					int secondsElapsed = calculateSecondsToSync(cycles);
					if(secondsElapsed > FIVE_HOURS) {
						System.out.println("Signals fail to synchronise in 5 hours");
					}
					else{
						int hours = secondsElapsed / 3600;
						int minutes = (secondsElapsed % 3600) / 60;
						int seconds = secondsElapsed % 60;
						System.out.println(String.format("%02d:%02d:%02d", hours, minutes, seconds));
					}
					cycles.clear();
				}
			}
		}
		scanner.close();
	}
	
	public static boolean allGreen(List<Integer> cycles, int currentTime){
		for(int i = 0; i < cycles.size(); i++){
			int cycle = cycles.get(i);
			int state = currentTime % (2*cycle);
			boolean redOrOrange = state >= cycle - ORANGE_CYCLE;
			if(redOrOrange) return false;
		}
		return true;
	}
	
	public static int calculateSecondsToSync(List<Integer> cycles){
		int currentTime = 0;
		int skipTime = skipTime(cycles);
		while(currentTime <= FIVE_HOURS){
			currentTime++;
			if(currentTime > skipTime && allGreen(cycles, currentTime)) break;
		}
		
		return currentTime;
	}
	
	public static int skipTime(List<Integer> cycles){
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < cycles.size(); i++){
			if(cycles.get(i) < min)
				min = cycles.get(i);
		}
		return min - ORANGE_CYCLE;
	}
}
