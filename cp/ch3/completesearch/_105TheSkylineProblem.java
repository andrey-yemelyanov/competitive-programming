package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _105TheSkylineProblem {
	public static class Building{
		public int x1;
		public int x2;
		public int height;
		public Building(int x1, int x2, int height){
			this.x1 = x1;
			this.x2 = x2;
			this.height = height;
		}
	}
	public static void main(String... args){
		String data = "1 11 5\r\n" + 
				"2 6 7\r\n" + 
				"3 13 9\r\n" + 
				"12 7 16\r\n" + 
				"14 3 25\r\n" + 
				"19 18 22\r\n" + 
				"23 13 29\r\n" + 
				"24 4 28\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		List<Building> buildings = new ArrayList<Building>();
		while(scanner.hasNext()){
			int left = scanner.nextInt();
			int height = scanner.nextInt();
			int right = scanner.nextInt();
			buildings.add(new Building(left, right, height));
		}
		List<Integer> skyline = getSkyline(buildings);
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < skyline.size(); i++){
			if(i != 0) builder.append(" ");
			builder.append(skyline.get(i));
		}
		System.out.println(builder.toString());
		scanner.close();
	}
	
	public static List<Integer> getSkyline(List<Building> buildings){
		int currentHeight = 0;
		Map<Double, Integer> heightMap = initHeightMap(buildings);
		List<Integer> skyline = new ArrayList<Integer>();
		int maxCoord = rightmostBuilding(buildings);
		for(double coord = 0; coord <= maxCoord + 0.5; coord += 0.5){
			int maxHeight = maxHeightAt(coord, heightMap);
			if(maxHeight > currentHeight){
				skyline.add((int)coord);
				skyline.add(maxHeight);
				currentHeight = maxHeight;
			}else if(maxHeight < currentHeight){
				skyline.add((int)(coord - 0.5));
				skyline.add(maxHeight);
				currentHeight = maxHeight;
			}
		}
		return skyline;
	}
	
	public static int rightmostBuilding(List<Building> buildings){
		int maxCoord = 0;
		for (Building building : buildings) {
			if(building.x2 > maxCoord){
				maxCoord = building.x2;
			}
		}
		return maxCoord;
	}
	
	public static int maxHeightAt(double coord, Map<Double, Integer> heightMap){
		if(!heightMap.containsKey(coord)) return 0;
		return heightMap.get(coord);
	}
	
	public static Map<Double, Integer> initHeightMap(List<Building> buildings){
		Map<Double, Integer> map = new HashMap<Double, Integer>();
		for (Building building : buildings) {
			for(double coord = building.x1; coord <= building.x2; coord += 0.5){
				if(!map.containsKey(coord)){
					map.put(coord, building.height);
				}
				else if(building.height > map.get(coord)){
					map.put(coord, building.height);
				}
			}
		}
		return map;
	}
}
