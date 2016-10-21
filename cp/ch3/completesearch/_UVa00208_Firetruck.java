package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _UVa00208_Firetruck {
	public static void main(String... args){
		String data = "6\r\n" + 
				"1 2\r\n" + 
				"1 3\r\n" + 
				"3 4\r\n" + 
				"3 5\r\n" + 
				"4 6\r\n" + 
				"5 6\r\n" + 
				"2 3\r\n" + 
				"2 4\r\n" + 
				"0 0\r\n" + 
				"4\r\n" + 
				"2 3\r\n" + 
				"3 4\r\n" + 
				"5 1\r\n" + 
				"1 6\r\n" + 
				"7 8\r\n" + 
				"8 9\r\n" + 
				"2 5\r\n" + 
				"5 7\r\n" + 
				"3 1\r\n" + 
				"1 8\r\n" + 
				"4 6\r\n" + 
				"6 9\r\n" + 
				"0 0";
		String data2 = "14\r\n" + 
				"1 8\r\n" + 
				"2 11\r\n" + 
				"3 4\r\n" + 
				"3 6\r\n" + 
				"4 14\r\n" + 
				"5 6\r\n" + 
				"5 8\r\n" + 
				"6 11\r\n" + 
				"6 12\r\n" + 
				"8 14\r\n" + 
				"9 14\r\n" + 
				"10 14\r\n" + 
				"11 14\r\n" + 
				"0 0";
		Scanner scanner = new Scanner(data);
		int caseNum = 1;
		while(scanner.hasNext()){
			int destination = scanner.nextInt() - 1;
			List<List<Integer>> graph = new ArrayList<List<Integer>>();
			int nVertices = 20;
			for(int i = 0; i < nVertices; i++){
				graph.add(new ArrayList<Integer>());
			}
			while(true){
				int v1 = scanner.nextInt() - 1;
				int v2 = scanner.nextInt() - 1;
				if(v1 == -1 && v2 == -1) break;
				graph.get(v1).add(v2);
				graph.get(v2).add(v1);
			}
			for(List<Integer> list : graph){
				Collections.sort(list);
			}
			List<List<Integer>> routes = findRoutes(graph, 0, destination);
			System.out.println(toString(caseNum++, routes, destination));
		}
		scanner.close();
	}
	
	static String toString(int caseNum, List<List<Integer>> routes, int destination){
		StringBuilder str = new StringBuilder();
		str.append("CASE " + caseNum + ":\n");
		for(List<Integer> route : routes){
			for(int i = 0; i < route.size(); i++){
				str.append(route.get(i) + 1);
				if(i < route.size() - 1) str.append(" ");
			}
			str.append("\n");
		}
		str.append(String.format("There are %1$d routes from the firestation to streetcorner %2$d.", 
				routes.size(), destination + 1));
		return str.toString();
	}
	
	static List<List<Integer>> findRoutes(List<List<Integer>> graph, int source, int destination){
		List<List<Integer>> routes = new ArrayList<List<Integer>>();
		boolean[] visited = new boolean[graph.size()];
		visited[source] = true;
		List<Integer> route = new ArrayList<Integer>();
		route.add(source);
		
		boolean[] destinationReachableFrom = new boolean[graph.size()];
		dfs(graph, destinationReachableFrom, destination);
		
		findRoutes(graph, source, destination, routes, route, visited, destinationReachableFrom);
		return routes;
	}
	
	static void dfs(List<List<Integer>> graph, boolean[] destinationReachableFrom, int source){
		destinationReachableFrom[source] = true;
		for(int neighbor : graph.get(source)){
			if(!destinationReachableFrom[neighbor]){
				dfs(graph, destinationReachableFrom, neighbor);
			}
		}
	}
	
	static void findRoutes(
			List<List<Integer>> graph, 
			int source, 
			int destination, 
			List<List<Integer>> routes, 
			List<Integer> route, 
			boolean[] visited,
			boolean[] destinationReachableFrom){
		if(source == destination){
			routes.add(new ArrayList<Integer>(route));
			return;
		}
		for(int neighbor : graph.get(source)){
			if(!visited[neighbor] && destinationReachableFrom[neighbor]){
				visited[neighbor] = true;
				route.add(neighbor);
				findRoutes(graph, neighbor, destination, routes, route, visited, destinationReachableFrom);
				route.remove(route.size() - 1);
				visited[neighbor] = false;
			}
		}
	}
}
