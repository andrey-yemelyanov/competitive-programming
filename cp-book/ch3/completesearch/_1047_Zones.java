package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _1047_Zones {
	public static class TowerConfiguration{
		public int nCustomersServed;
		public Integer[] towers;
		public TowerConfiguration(Integer[] towers, int nCustomersServed){
			this.towers = towers;
			this.nCustomersServed = nCustomersServed;
		}
	}
	public static class CommonServiceArea{
		public Integer[] towers;
		public int nCustomers;
		public CommonServiceArea(Integer[] towers, int nCustomers){
			this.towers = towers;
			this.nCustomers = nCustomers;
		}
	}
	public static void main(String... args){
		String data = "5 3\r\n" + 
				"15 20 25 30 24\r\n" + 
				"5\r\n" + 
				"2 1 2 7\r\n" + 
				"3 1 2 3 3\r\n" + 
				"2 2 3 2\r\n" + 
				"2 3 4 5\r\n" + 
				"2 4 5 6\r\n" + 
				"5 3\r\n" + 
				"25 25 25 25 25\r\n" + 
				"4\r\n" + 
				"2 1 2 5\r\n" + 
				"2 2 3 5\r\n" + 
				"2 3 4 5\r\n" + 
				"2 4 5 5\r\n" + 
				"5 3\r\n" + 
				"25 25 25 25 25\r\n" + 
				"0\r\n" + 
				"0 0";
		String data2 = "2  2\r\n" + 
				"2 2\r\n" + 
				"1\r\n" + 
				"2  1 2      1\r\n" + 
				"3  2\r\n" + 
				"4 4 4\r\n" + 
				"4\r\n" + 
				"2  1 2      1\r\n" + 
				"2  1 3      1\r\n" + 
				"2  2 3      1\r\n" + 
				"3  1 2 3    1\r\n" + 
				"4  2\r\n" + 
				"7 7 7 7\r\n" + 
				"9\r\n" + 
				"2  1 2      1\r\n" + 
				"2  1 3      1\r\n" + 
				"2  2 4      1\r\n" + 
				"2  3 4      1\r\n" + 
				"3  1 2 3    1\r\n" + 
				"3  1 2 4    1\r\n" + 
				"3  1 3 4    1\r\n" + 
				"3  2 3 4    1\r\n" + 
				"4  1 2 3 4  1\r\n" + 
				"0 0";
		Scanner scanner = new Scanner(data2);
		int caseNum = 0;
		while(scanner.hasNext()){
			int nTowersPlanned = scanner.nextInt();
			int nTowersBuilt = scanner.nextInt();
			if(nTowersBuilt == 0 && nTowersPlanned == 0) break;
			int[] customersServed = new int[nTowersPlanned];
			for(int i = 0; i < nTowersPlanned; i++){
				customersServed[i] = scanner.nextInt();
			}
			CommonServiceArea[] areas = new CommonServiceArea[scanner.nextInt()];
			for(int i = 0; i < areas.length; i++){
				Integer[] towers = new Integer[scanner.nextInt()];
				for(int j = 0; j < towers.length; j++){
					towers[j] = scanner.nextInt();
				}
				areas[i] = new CommonServiceArea(towers, scanner.nextInt());
			}
			TowerConfiguration config = findOptimalTowerConfiguration(nTowersPlanned, nTowersBuilt, customersServed, areas);
			System.out.println(printRecommendation(config, ++caseNum));
		}
		scanner.close();
	}
	
	public static String printRecommendation(TowerConfiguration config, int caseNum){
		StringBuilder out = new StringBuilder();
		out.append(String.format("Case Number  %1$d\n", caseNum));
		out.append(String.format("Number of Customers: %1$d\n", config.nCustomersServed));
		out.append("Locations recommended: ");
		for(int i = 0; i < config.towers.length; i++){
			out.append(config.towers[i]);
			if(i < config.towers.length - 1) out.append(" ");
		}
		out.append("\n");
		return out.toString();
	}
	
	public static TowerConfiguration findOptimalTowerConfiguration(
			int nTowersPlanned, 
			int nTowersBuilt,
			int[] customersServed,
			CommonServiceArea[] commonServiceAreas){
		int maxCustomersServed = Integer.MIN_VALUE;
		List<Integer> bestTowerConfiguration = null;
		for(int i = 1; i < (1 << nTowersPlanned); i++){ // generate all subsets of nTowersPlanned that have length nTowersBuilt
			List<Integer> subset = new ArrayList<Integer>();
			for(int j = 0; j < nTowersPlanned; j++){
				if((i & (1 << j)) != 0){
					subset.add(j + 1);
				}
			}
			if(subset.size() == nTowersBuilt){
				int total = totalCustomersServed(subset, customersServed, commonServiceAreas);
				if(total > maxCustomersServed){
					maxCustomersServed = total;
					bestTowerConfiguration = subset;
				}
			}
		}
		return new TowerConfiguration(bestTowerConfiguration.toArray(new Integer[0]), maxCustomersServed);
	}
	
	public static int totalCustomersServed(
			List<Integer> towers,
			int[] customersServed,
			CommonServiceArea[] commonServiceAreas){
		int union = 0;
		for(int tower : towers){
			union += customersServed[tower - 1];
		}
		
		for(CommonServiceArea commonServiceArea : commonServiceAreas){
			int common = 0;
			for(int tower : commonServiceArea.towers){
				if(towers.contains(tower)){
					common++;
				}
			}
			if(common > 1) union -= (common - 1) * commonServiceArea.nCustomers;
		}
		
		return union;
	}
}
