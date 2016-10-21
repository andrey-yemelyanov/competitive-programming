package helvidios.cp.ch3.completesearch;

import java.util.Scanner;

public class _1237_ExpertEnough {
	static class CarMaker{
		public String name;
		public int lowestPrice;
		public int highestPrice;
		public CarMaker(String name, int lowestPrice, int highestPrice){
			this.name = name;
			this.lowestPrice = lowestPrice;
			this.highestPrice = highestPrice;
		}
		public boolean priceWithinRange(int queryPrice){
			return queryPrice >= lowestPrice && queryPrice <= highestPrice;
		}
	}
	public static void main(String... args){
		String data = "2 \r\n" + 
				"4 \r\n" + 
				"HONDA 10000 45000 \r\n" + 
				"PEUGEOT 12000 44000 \r\n" + 
				"BMW 30000 75900 \r\n" + 
				"CHEVROLET 7000 37000 \r\n" + 
				"4 \r\n" + 
				"60000 \r\n" + 
				"7500 \r\n" + 
				"5000 \r\n" + 
				"10000\n" +
				"4 \r\n" + 
				"HONDA 10000 45000 \r\n" + 
				"PEUGEOT 12000 44000 \r\n" + 
				"BMW 30000 75900 \r\n" + 
				"CHEVROLET 7000 37000 \r\n" + 
				"4 \r\n" + 
				"60000 \r\n" + 
				"7500 \r\n" + 
				"5000 \r\n" + 
				"10000";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int dbSize = scanner.nextInt();
			CarMaker[] database = new CarMaker[dbSize];
			for(int i = 0; i < database.length; i++){
				database[i] = new CarMaker(scanner.next(), scanner.nextInt(), scanner.nextInt());
			}
			int qSize = scanner.nextInt();
			while(qSize-- > 0){
				CarMaker maker = findManufacturer(database, scanner.nextInt());
				if(maker == null) System.out.println("UNDETERMINED");
				else System.out.println(maker.name);
			}
			if(nTestCases > 0) System.out.println();
		}
		scanner.close();
	}
	
	public static CarMaker findManufacturer(CarMaker[] database, int queryPrice){
		CarMaker found = null;
		for(CarMaker maker : database){
			if(maker.priceWithinRange(queryPrice) && found == null){
				found = maker;
			}else if(maker.priceWithinRange(queryPrice) && found != null){
				return null;
			}
		}
		return found;
	}
}
