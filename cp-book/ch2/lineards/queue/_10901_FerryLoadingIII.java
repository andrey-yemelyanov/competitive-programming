package helvidios.cp.ch2.lineards.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _10901_FerryLoadingIII {
	public static final boolean LEFT = true;
	public static final boolean RIGHT = false;
	public static class CarArrival{
		public int id;
		public boolean riverBank;
		public int arrivalTime;
		public CarArrival(int id, boolean riverBank, int arrivalTime){
			this.id = id;
			this.riverBank = riverBank;
			this.arrivalTime = arrivalTime;
		}
		public String toString(){
			return id + " " + (riverBank ? "LEFT" : "RIGHT") + " " + arrivalTime;
		}
	}
	public static void main(String... args){
		String data = "2\r\n" + 
				"2 10 10\r\n" + 
				"0 left\r\n" + 
				"10 left\r\n" + 
				"20 left\r\n" + 
				"30 left\r\n" + 
				"40 left\r\n" + 
				"50 left\r\n" + 
				"60 left\r\n" + 
				"70 left\r\n" + 
				"80 left\r\n" + 
				"90 left\r\n" + 
				"2 10 3\r\n" + 
				"10 right\r\n" + 
				"25 left\r\n" + 
				"40 left";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int ferryCapacity = scanner.nextInt();
			int timeToCrossRiver = scanner.nextInt();
			int nCars = scanner.nextInt();
			CarArrival[] arrivals = new CarArrival[nCars];
			for(int i = 0; i < nCars; i++){
				int time = scanner.nextInt();
				boolean bank = scanner.next().equals("left") ? LEFT : RIGHT;
				arrivals[i] = new CarArrival(i, bank, time);
			}
			System.out.println(printTimes(getTransportTimes(arrivals, ferryCapacity, timeToCrossRiver)));
			if(nTestCases > 0) System.out.println();
		}
		scanner.close();
	}
	
	static String printTimes(int[] times){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < times.length; i++){
			builder.append(times[i]);
			if(i < times.length - 1) builder.append("\n");
		}
		return builder.toString();
	}
	
	static int latestCarInTimeRange(int time, int fromIndex, CarArrival[] carArrivals){
		int i = fromIndex;
		while(i < carArrivals.length && carArrivals[i].arrivalTime <= time) i++;
		return i - 1;
	}
	
	public static int[] getTransportTimes(
			CarArrival[] carArrivals,
			int ferryCapacity, 
			int timeToCrossRiver){
		int[] transportTimes = new int[carArrivals.length];
		int unloadedCars = 0;
		
		Queue<Integer> leftBankQueue = new LinkedList<Integer>();
		Queue<Integer> rightBankQueue = new LinkedList<Integer>();
		Queue<Integer> ferryQueue = new LinkedList<Integer>();
		
		int from = 0;
		boolean currentBank = LEFT;
		int time = 0;
		while(unloadedCars != carArrivals.length){
			while(from <= carArrivals.length - 1){ // wait for car(s) to arrive
				int latest = latestCarInTimeRange(time, from, carArrivals); 
				if(latest >= from){ // new car(s) arrived
					for(int i = from; i <= latest; i++){
						if(carArrivals[i].riverBank == LEFT) leftBankQueue.add(carArrivals[i].id);
						if(carArrivals[i].riverBank == RIGHT) rightBankQueue.add(carArrivals[i].id);
					}
					from = latest + 1;
				}

				// if there are cars at either bank, stop waiting
				if(!leftBankQueue.isEmpty() || !rightBankQueue.isEmpty()) break;
				
				// wait one more minute
				time++;
			}
			
			// load car(s) (if any) from the current bank
			Queue<Integer> currentBankQueue = currentBank == LEFT ? leftBankQueue : rightBankQueue;
			while(ferryQueue.size() < ferryCapacity && !currentBankQueue.isEmpty()){
				ferryQueue.add(currentBankQueue.remove());
			}
			
			// travel to the other bank
			time += timeToCrossRiver;
			currentBank = !currentBank;
			
			// unload car(s) (if any) at the new current bank
			while(!ferryQueue.isEmpty()){
				transportTimes[ferryQueue.remove()] = time;
				unloadedCars++;
			}
		}
		return transportTimes;
	}
}
