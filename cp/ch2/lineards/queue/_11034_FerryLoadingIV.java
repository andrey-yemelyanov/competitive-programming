package helvidios.cp.ch2.lineards.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _11034_FerryLoadingIV {
	public static final boolean LEFT_BANK = true;
	public static final boolean RIGHT_BANK = false;
	public static void main(String... args){
		String data = "4\r\n" + 
				"20 4\r\n" + 
				"380 left\r\n" + 
				"720 left\r\n" + 
				"1340 right\r\n" + 
				"1040 left\r\n" + 
				"15 4\r\n" + 
				"380 left\r\n" + 
				"720 left\r\n" + 
				"1340 right\r\n" + 
				"1040 left\r\n" + 
				"15 4\r\n" + 
				"380 left\r\n" + 
				"720 left\r\n" + 
				"1340 left\r\n" + 
				"1040 left\r\n" + 
				"15 4\r\n" + 
				"380 right\r\n" + 
				"720 right\r\n" + 
				"1340 right\r\n" + 
				"1040 right";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int ferryLength = scanner.nextInt();
			int nCars = scanner.nextInt();
			Queue<Integer> leftBankQueue = new LinkedList<Integer>();
			Queue<Integer> rightBankQueue = new LinkedList<Integer>();
			for(int i = 0; i < nCars; i++){
				int length = scanner.nextInt();
				if(scanner.next().equals("left")) leftBankQueue.add(length);
				else rightBankQueue.add(length);
			}
			System.out.println(getNumberOfTrips(leftBankQueue, rightBankQueue, ferryLength));
		}
		scanner.close();
	}
	
	public static int getNumberOfTrips(
			Queue<Integer> leftBankQueue, 
			Queue<Integer> rightBankQueue,
			int ferryLength){
		int tripCount = 0;
		boolean currentBank = LEFT_BANK;
		int transportedCars = 0;
		int totalCars = leftBankQueue.size() + rightBankQueue.size();
		Queue<Integer> ferryQueue = new LinkedList<Integer>();
		
		while(transportedCars != totalCars){
			// load cars from the current bank
			int availableLength = ferryLength * 100;
			Queue<Integer> currentBankQueue = currentBank == LEFT_BANK ? leftBankQueue : rightBankQueue;
			while(!currentBankQueue.isEmpty() && availableLength - currentBankQueue.element() >= 0){
				availableLength -= currentBankQueue.element(); 
				ferryQueue.add(currentBankQueue.remove());
			}
			
			// transport cars to the other bank
			tripCount++;
			currentBank = !currentBank;
			
			// unload all cars
			while(!ferryQueue.isEmpty()){
				transportedCars++;
				ferryQueue.remove();
			}
		}
		
		return tripCount;
	}
}
