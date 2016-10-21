package helvidios.cp.ch1.medium;

import java.util.Scanner;

public class _661_BlowingFuses {
	private static final int ON = 1;
	private static final int OFF = 0;
	private static final int FUSE_BLOWN = -1;
	public static void main(String... args){
		String data = "2 2 10\r\n" + 
				"5\r\n" + 
				"7\r\n" + 
				"1\r\n" + 
				"2\r\n" + 
				"3 6 10\r\n" + 
				"2\r\n" + 
				"5\r\n" + 
				"7\r\n" + 
				"2\r\n" + 
				"1\r\n" + 
				"2\r\n" + 
				"3\r\n" + 
				"1\r\n" + 
				"3\r\n" + 
				"0 0 0\r\n" + 
				"\r\n"; 
		Scanner scanner = new Scanner(data);
		int sequenceNum = 0;
		while(scanner.hasNext()){
			int deviceCount = scanner.nextInt();
			int opsCount = scanner.nextInt();
			int fuseCapacity = scanner.nextInt();
			if(deviceCount == 0 && opsCount == 0 && fuseCapacity == 0) break;
			
			int[] consumptionLevels = new int[deviceCount];
			int[] deviceStates = new int[deviceCount];
			for(int device = 0; device < consumptionLevels.length; device++)
				consumptionLevels[device] = scanner.nextInt();
			
			int[] deviceOps = new int[opsCount];
			for(int op = 0; op < deviceOps.length; op++)
				deviceOps[op] = scanner.nextInt();
			
			int maxPowerConsumption = getMaximalPowerConsumption(
					fuseCapacity, 
					consumptionLevels, 
					deviceStates, 
					deviceOps);
			if(maxPowerConsumption == FUSE_BLOWN)
				System.out.printf("Sequence %1$d\nFuse was blown.\n\n", ++sequenceNum);
			else System.out.printf("Sequence %1$d\nFuse was not blown.\n"
					+ "Maximal power consumption was %2$d amperes.\n\n", 
					++sequenceNum, maxPowerConsumption);
		}
		scanner.close();
	}
	
	private static int getMaximalPowerConsumption(
			int fuseCapacity, 
			int[] consumptionLevels,
			int[] deviceStates,
			int[] deviceOps){
		int maxPowerConsumption = Integer.MIN_VALUE;
		int totalPowerConsumption = 0;
		
		for(int device : deviceOps){
			totalPowerConsumption += toggleDevice(device - 1, deviceStates, consumptionLevels);
			if(totalPowerConsumption > fuseCapacity) return FUSE_BLOWN;
			if(totalPowerConsumption > maxPowerConsumption)
				maxPowerConsumption = totalPowerConsumption;
		}
		
		return maxPowerConsumption;
	}
	
	private static int toggleDevice(
			int device, 
			int[] deviceStates, 
			int[] consumptionLevels){
		if(deviceStates[device] == OFF){
			deviceStates[device] = ON;
			return consumptionLevels[device];
		}
		
		deviceStates[device] = OFF;
		return -consumptionLevels[device];
	}
}
