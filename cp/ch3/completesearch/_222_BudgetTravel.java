package helvidios.cp.ch3.completesearch;

import java.util.Locale;
import java.util.Scanner;

public class _222_BudgetTravel {
	public static class Stop{
		public double distance;
		public double gasPrice;
		public Stop(double distance, double gasPrice){
			this.distance = distance;
			this.gasPrice = gasPrice;
		}
	}
	
	static boolean enoughGasToNextStation(double milesPerGallon, double gasRemaining, double distance){
		return milesPerGallon * gasRemaining >= distance;
	}
	
	static double gasConsumption(double milesPerGallon, double distance){
		return distance / milesPerGallon;
	}
	
	static double calculateStopCost(double tankCapacity, double gasRemaining, Stop[] stops, int currentPosition){
		double cost = 200;
		double gasBought = tankCapacity - gasRemaining;
		cost += gasBought * stops[currentPosition].gasPrice;
		return cost;
	}
	
	static boolean moreThanHalfTankLeft(double tankCapacity, double gasRemaining){
		return gasRemaining > (tankCapacity / 2);
	}
	
	static double getMinCost(double tankCapacity,
							 double milesPerGallon,
							 double initialCost,
						     Stop[] stops){
		minCost = Double.MAX_VALUE;
		travel(tankCapacity, milesPerGallon, tankCapacity, stops, 0, initialCost);
		return minCost;
	}
	
	static double minCost;
	static void travel(double tankCapacity,
				double milesPerGallon,
				double gasRemaining,
				Stop[] stops,
				int currentPosition,
				double cost){
		if(currentPosition == stops.length - 1){
			minCost = Math.min(minCost, cost);
			return;
		}
		
		double distanceToNextStop = stops[currentPosition + 1].distance - stops[currentPosition].distance;
		double stopCost = calculateStopCost(tankCapacity, gasRemaining, stops, currentPosition);
		double gasConsumption = gasConsumption(milesPerGallon, distanceToNextStop);
		if(!enoughGasToNextStation(milesPerGallon, gasRemaining, distanceToNextStop)){
			// fill the tank at the current stop
			travel(tankCapacity, 
				   milesPerGallon, 
				   tankCapacity - gasConsumption, 
				   stops, 
				   currentPosition + 1, 
				   cost + stopCost);
		}else if(moreThanHalfTankLeft(tankCapacity, gasRemaining)){
			// skip this stop and travel further
			travel(tankCapacity, 
				   milesPerGallon, 
				   gasRemaining - gasConsumption, 
				   stops, 
				   currentPosition + 1, 
				   cost);
		}else{ // less than half a tank left, but enough to get to the next station
			// explore both options: fill the tank at this stop and do not fill the tank at this stop
			
			// fill the tank at the current stop
			travel(tankCapacity, 
				   milesPerGallon, 
				   tankCapacity - gasConsumption, 
				   stops, 
				   currentPosition + 1, 
				   cost + stopCost);
			
			// skip this stop and travel further
			travel(tankCapacity, 
				   milesPerGallon, 
				   gasRemaining - gasConsumption, 
				   stops, 
				   currentPosition + 1, 
				   cost);
		}
	}
	
	public static void main(String... args){
		String data = "475.6\r\n" + 
				"11.9 27.4 14.98 6\r\n" + 
				"102.0 99.9\r\n" + 
				"220.0 132.9\r\n" + 
				"256.3 147.9\r\n" + 
				"275.0 102.9\r\n" + 
				"277.6 112.9\r\n" + 
				"381.8 100.9\r\n" + 
				"516.3\r\n" + 
				"15.7 22.1 20.87 3\r\n" + 
				"125.4 125.9\r\n" + 
				"297.9 112.9\r\n" + 
				"345.2 99.9\r\n" + 
				"-1";
		Scanner scanner = new Scanner(data);
		scanner.useLocale(Locale.US);
		int dataSetNumber = 1;
		while(true){
			double distanceToDestination = scanner.nextDouble();
			if(distanceToDestination < 0) break;
			double tankCapacity = scanner.nextDouble();
			double milesPerGallon = scanner.nextDouble();
			double initialCost = scanner.nextDouble() * 100;
			int nStops = scanner.nextInt();
			Stop[] stops = new Stop[nStops + 2];
			Stop origin = new Stop(0, 0);
			stops[0] = origin;
			for(int i = 0; i < nStops; i++){
				stops[i + 1] = new Stop(scanner.nextDouble(), scanner.nextDouble());
			}
			stops[stops.length - 1] = new Stop(distanceToDestination, 0);
			double minCost = getMinCost(tankCapacity, milesPerGallon, initialCost, stops);
			System.out.println("Data Set #" + dataSetNumber++);
			System.out.printf("minimum cost = $%1$.2f\n", minCost / 100);
		}
		scanner.close();
	}
}
