package helvidios.cp.ch2.ownlibs.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class _10507_WakingUpBrain {
	public static final int NEVER = Integer.MIN_VALUE; 
	public static void main(String... args){
		String data = "6\r\n" + 
				"11\r\n" + 
				"HAB\r\n" + 
				"AB\r\n" + 
				"AC\r\n" + 
				"AH\r\n" + 
				"BD\r\n" + 
				"BC\r\n" + 
				"BF\r\n" + 
				"CD\r\n" + 
				"CF\r\n" + 
				"CH\r\n" + 
				"DF\r\n" + 
				"FH\n\n"
				+ "6\r\n" + 
				"11\r\n" + 
				"HAB\r\n" + 
				"AB\r\n" + 
				"AC\r\n" + 
				"AH\r\n" + 
				"BD\r\n" + 
				"BC\r\n" + 
				"BF\r\n" + 
				"CD\r\n" + 
				"CF\r\n" + 
				"CH\r\n" + 
				"DF\r\n" + 
				"FH";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNextInt()){
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			String directStimulationAreas = scanner.next();
			String[] connections = new String[m];
			for(int i = 0; i < m; i++){
				connections[i] = scanner.next();
			}
			int nYears = wakeUp(connections, n, directStimulationAreas);
			if(nYears == NEVER) System.out.println("THIS BRAIN NEVER WAKES UP");
			else System.out.printf("WAKE UP IN, %1$d, YEARS\n", nYears);
		}
		
		scanner.close();
	}
	
	public static int wakeUp(
			String[] connections, 
			int nSleepingAreas, 
			String directStimulationAreas){
		Map<Character, List<Character>> adjList = buildAdjList(connections);
		Set<Character> awakeSet = new HashSet<Character>();
		for(char c : directStimulationAreas.toCharArray()){
			awakeSet.add(c);
		}
		
		int nYears = 0;
		while(true){
			Set<Character> s = new HashSet<Character>();
			for(char c : adjList.keySet()){
				if(!awakeSet.contains(c)){
					if(connectedToThreeAwakeAreas(awakeSet, adjList, c)){
						s.add(c);
					}
				}
			}
			
			if(s.size() == 0) break;
			awakeSet.addAll(s);
			nYears++;
		}
		
		if(awakeSet.size() >= nSleepingAreas) return nYears;
		
		return NEVER;
	}
	
	private static boolean connectedToThreeAwakeAreas(
			Set<Character> awakeSet, 
			Map<Character, List<Character>> adjList,
			char c){
		List<Character> adjAreas = adjList.get(c);
		int awake = 0;
		for(char adjArea : adjAreas){
			if(awakeSet.contains(adjArea)){
				awake++;
			}
		}
		return awake >= 3;
	}
	
	private static Map<Character, List<Character>> buildAdjList(String[] connections){
		Map<Character, List<Character>> adj = new HashMap<Character, List<Character>>();
		for(String connection : connections){
			char vertex1 = connection.charAt(0);
			char vertex2 = connection.charAt(1);
			
			if(!adj.containsKey(vertex1)){
				adj.put(vertex1, new ArrayList<Character>());
			}
			adj.get(vertex1).add(vertex2);
			
			if(!adj.containsKey(vertex2)){
				adj.put(vertex2, new ArrayList<Character>());
			}
			adj.get(vertex2).add(vertex1);
		}
		return adj;
	}
}
