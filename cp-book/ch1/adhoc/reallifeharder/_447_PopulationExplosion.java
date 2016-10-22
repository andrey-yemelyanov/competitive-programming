package helvidios.cp.ch1.adhoc.reallifeharder;

import java.util.Scanner;

public class _447_PopulationExplosion {
	public static void main(String... args){
		String data = "1\r\n" + 
				"\r\n" + 
				"3\r\n" + 
				"5 4\r\n" + 
				"5 5\r\n" + 
				"5 6\r\n" + 
				"5 7";
		String data2 = "2\r\n" + 
				"\r\n" + 
				"3\r\n" + 
				"5 4\r\n" + 
				"5 5\r\n" + 
				"5 6\r\n" + 
				"5 7\r\n" + 
				"\r\n" + 
				"10\r\n" + 
				"1 1\r\n" + 
				"2 2 \r\n" + 
				"3 3\r\n" + 
				"4 4\r\n" + 
				"5 5\r\n" + 
				"6 6\r\n" + 
				"7 7";
		Scanner scanner = new Scanner(data2);
		int nTestCases = scanner.nextInt();
		int current = nTestCases;
		while(current-- > 0){
			int nYears = scanner.nextInt();
			String line = scanner.nextLine();
			int[][] map = new int[20][20];
			while(scanner.hasNextLine() && !(line = scanner.nextLine()).trim().isEmpty()){
				String[] tokens = line.split(" ");
				int i = Integer.parseInt(tokens[0]) - 1;
				int j = Integer.parseInt(tokens[1]) - 1;
				map[i][j] = 1;
			}
			if(current < nTestCases - 1) System.out.println();
			System.out.println(runSimulation(map, nYears));
		}
		scanner.close();
	}
	
	public static String runSimulation(int[][] currentMap, int nYears){
		StringBuilder sb = new StringBuilder();
		sb.append("********************\n");
		if(nYears >= 1){
			sb.append(generatePopulationMap(currentMap));
			sb.append("\n");
			for(int year = 2; year <= nYears; year++){
				sb.append("********************\n");
				currentMap = runSimulation(currentMap);
				sb.append(generatePopulationMap(currentMap));
				sb.append("\n");
			}
		}
		sb.append("********************");
		return sb.toString();
	}
	
	public static String generatePopulationMap(int[][] map){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(map[i][j] == 1) sb.append("O");
				else sb.append(" ");
			}
			if(i < map.length - 1){
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public static int[][] runSimulation(int[][] currentMap){
		int[][] newMap = new int[currentMap.length][currentMap[0].length];
		for(int i = 0; i < currentMap.length; i++){
			for(int j = 0; j < currentMap[0].length; j++){
				int nNeighbors = countNeighbors(currentMap, i, j);
				if(isEmpty(currentMap, i, j) && nNeighbors == 3){
					newMap[i][j] = 1;
				}else if(!isEmpty(currentMap, i, j)){
					if(nNeighbors > 1 && nNeighbors < 4) newMap[i][j] = currentMap[i][j];
				}
			}
		}
		return newMap;
	}
	
	static boolean isEmpty(int[][] map, int i, int j){
		return i >= map.length || i < 0 || j >= map[0].length || j < 0 || map[i][j] == 0;
	}
	
	static int countNeighbors(int[][] map, int i, int j){
		// check all three adjacent locations
		int nNeighbors = 0;
		// north
		if(!isEmpty(map, i - 1, j)) nNeighbors++;
		// south
		if(!isEmpty(map, i + 1, j)) nNeighbors++;
		// west
		if(!isEmpty(map, i, j - 1)) nNeighbors++;
		// east
		if(!isEmpty(map, i, j + 1)) nNeighbors++;
		// north-west
		if(!isEmpty(map, i - 1, j - 1)) nNeighbors++;
		// north-east
		if(!isEmpty(map, i - 1, j + 1)) nNeighbors++;
		// south-east
		if(!isEmpty(map, i + 1, j + 1)) nNeighbors++;
		// south-west
		if(!isEmpty(map, i + 1, j - 1)) nNeighbors++;

		return nNeighbors;
	}
}
