package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _301_Transportation {
	public static void main(String... args){
		String data = "10 3 4\r\n" + 
				"0 2 1\r\n" + 
				"1 3 5\r\n" + 
				"1 2 7\r\n" + 
				"2 3 10\r\n" + 
				"10 5 4\r\n" + 
				"3 5 10\r\n" + 
				"2 4 9\r\n" + 
				"0 2 5\r\n" + 
				"2 5 8\r\n" + 
				"0 0 0";
		Scanner scanner = new Scanner(data);
		while(true){
			int trainCapacity = scanner.nextInt();
			int nStations = scanner.nextInt() + 1;
			int nOrders = scanner.nextInt();
			if(trainCapacity == 0 && nStations == 1 && nOrders == 0) break;
			List<Order> orders = new ArrayList<Order>();
			while(nOrders-- > 0){
				orders.add(new Order(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
			}
			System.out.println(getMaxRevenue(orders, trainCapacity, nStations));
		}
		scanner.close();
	}
	
	static class Order{
		public int startingStation;
		public int destinationStation;
		public int nPassengers;
		public Order(int startingStation, int destinationStation, int nPassengers){
			this.startingStation = startingStation;
			this.destinationStation = destinationStation;
			this.nPassengers = nPassengers;
		}
		private int getTicketPrice(){
			return destinationStation - startingStation;
		}
		public int getRevenue(){
			return getTicketPrice() * nPassengers;
		}
		public String toString(){
			return startingStation + " " + destinationStation + " " + nPassengers;
		}
	}
	
	static boolean orderPossible(Order order, int[] trainLoad, int trainCapacity){
		for(int station = order.startingStation; station < order.destinationStation; station++){
			if(trainLoad[station] + order.nPassengers > trainCapacity) return false;
		}
		return true;
	}
	
	static int maxRevenue;
	static void maximizeRevenue(List<Order> orders, int[] trainLoad, int trainCapacity, int currentOrder, int revenue){
		if(currentOrder == orders.size()){
			maxRevenue = Math.max(maxRevenue, revenue);
			return;
		}
		Order order = orders.get(currentOrder);
		if(orderPossible(order, trainLoad, trainCapacity)){
			for(int station = order.startingStation; station < order.destinationStation; station++){
				trainLoad[station] += order.nPassengers;
			}
			maximizeRevenue(orders, trainLoad, trainCapacity, currentOrder + 1, revenue + order.getRevenue());
			for(int station = order.startingStation; station < order.destinationStation; station++){
				trainLoad[station] -= order.nPassengers;
			}
		}
		maximizeRevenue(orders, trainLoad, trainCapacity, currentOrder + 1, revenue);
	}
	
	static int getMaxRevenue(List<Order> orders, int trainCapacity, int nStations){
		maxRevenue = Integer.MIN_VALUE;
		int[] trainLoad = new int[nStations];
		maximizeRevenue(orders, trainLoad, trainCapacity, 0, 0);
		return maxRevenue;
	}
}
