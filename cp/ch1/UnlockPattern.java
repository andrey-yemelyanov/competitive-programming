package helvidios.cp.ch1;
import java.util.*;

public class UnlockPattern {
	public static void main(String... args){
		System.out.println(countUnlockPatterns(4));
	}
	
	static final int nPoints = 9;
	static Map<Integer, Set<Integer>> map = new HashMap<>();
	static{
		map.put(1, new HashSet<>(Arrays.asList(7,3,9)));
		map.put(2, new HashSet<>(Arrays.asList(8)));
		map.put(3, new HashSet<>(Arrays.asList(9,1,7)));
		map.put(4, new HashSet<>(Arrays.asList(6)));
		map.put(5, new HashSet<Integer>());
		map.put(6, new HashSet<>(Arrays.asList(4)));
		map.put(7, new HashSet<>(Arrays.asList(1,9,3)));
		map.put(8, new HashSet<>(Arrays.asList(2)));
		map.put(9, new HashSet<>(Arrays.asList(3,7,1)));
	}
	
	static boolean pathValid(int point1, int point2, boolean[] visited){
		return !map.get(point1).contains(point2) || visited[intermediatePoint(point1, point2)];
	}
	
	static int intermediatePoint(int point1, int point2){
		return (point1 + point2) / 2;
	}
		
	static int countUnlockPatterns(int minPatternLength){
		nUnlockPatterns = 0;
		for(int i = 1; i <= nPoints; i++){
			boolean[] visited = new boolean[nPoints + 1];
			visited[i] = true;
			countUnlockPatterns(i, visited, minPatternLength, 1);
		}
		return nUnlockPatterns;
	}
	
	static int nUnlockPatterns = 0;
	static void countUnlockPatterns(
			int source, 
			boolean[] visited, 
			int minPatternLength,
			int nVisited){
		for(int i = 1; i <= nPoints; i++){
			if(!visited[i] && pathValid(i, source, visited)){
				visited[i] = true;
				if(nVisited + 1 >= minPatternLength) nUnlockPatterns++;
				countUnlockPatterns(i, visited, minPatternLength, nVisited + 1);
				visited[i] = false;
			}
		}
	}
}
