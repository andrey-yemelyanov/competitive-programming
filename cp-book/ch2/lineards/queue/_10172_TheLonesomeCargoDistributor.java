package helvidios.cp.ch2.lineards.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class _10172_TheLonesomeCargoDistributor {
	public static void main(String... args){
		String data = "2\n"
				+ "5 2 3\r\n" + 
				"3 4 5 2\r\n" + 
				"2 1 3\r\n" + 
				"0\n"
				+ "3 3 5 1\r\n" + 
				"1 4\r\n" + 
				"5 2 3\r\n" + 
				"3 4 5 2\r\n" + 
				"2 1 3\r\n" + 
				"0\n"
				+ "3 3 5 1\r\n" + 
				"1 4";
		Scanner scanner = new Scanner(data);
		int nSets = scanner.nextInt();
		while(nSets-- > 0){
			int nStations = scanner.nextInt();
			int cargoCarrierCapacity = scanner.nextInt();
			int stationQueueCapacity = scanner.nextInt();
			List<Queue<Integer>> stationQueues = new ArrayList<Queue<Integer>>();
			while(nStations-- > 0){
				stationQueues.add(new LinkedList<Integer>(readQueue(scanner)));
			}
			System.out.println(getFinishingTime(stationQueues, cargoCarrierCapacity, stationQueueCapacity));
		}
		scanner.close();
	}
	
	static List<Integer> readQueue(Scanner scanner){
		List<Integer> list = new ArrayList<Integer>();
		int nElements = scanner.nextInt();
		while(nElements-- > 0){
			list.add(scanner.nextInt());
		}
		return list;
	}
	
	public static int getFinishingTime(
			List<Queue<Integer>> stationQueues,
			int cargoCarrierCapacity,
			int stationQueueCapacity){
		int totalTime = 0;
		
		final int loadTime = 1;
		final int unloadTime = 1;
		final int interstationTripTime = 2;
		
		Stack<Integer> cargoCarrierStack = new Stack<Integer>();
		for(int station = 0;;station = (station + 1) % stationQueues.size()){
			// unload cargo at this station
			while(!cargoCarrierStack.isEmpty()){
				if(cargoCarrierStack.peek() == station + 1){ 
					// unload to platform A
					cargoCarrierStack.pop();
					totalTime += unloadTime;
				}else{
					// attempt to unload to platform B
					if(stationQueues.get(station).size() >= stationQueueCapacity) break;
					stationQueues.get(station).add(cargoCarrierStack.pop());
					totalTime += unloadTime;
				}
			}
			
			// load cargo at this station
			while(cargoCarrierStack.size() < cargoCarrierCapacity){
				if(stationQueues.get(station).isEmpty()) break;
				cargoCarrierStack.push(stationQueues.get(station).remove());
				totalTime += loadTime;
			}
			
			if(jobCompleted(stationQueues, cargoCarrierStack)) break;
			
			totalTime += interstationTripTime;
		}
		
		return totalTime;
	}
	
	static boolean jobCompleted(List<Queue<Integer>> stationQueues, Stack<Integer> cargoCarrierStack){
		for(Queue<Integer> q : stationQueues){
			if(!q.isEmpty()) return false;
		}
		return cargoCarrierStack.isEmpty();
	}
}
