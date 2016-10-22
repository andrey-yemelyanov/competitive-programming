package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _775_HamiltonianCycle {
	public static void main(String... args){
		String data = "4\r\n" + 
				"1 2\r\n" + 
				"2 3\r\n" + 
				"2 4\r\n" + 
				"3 4\r\n" + 
				"3 1\r\n" + 
				"%\r\n" + 
				"6\r\n" + 
				"1 2\r\n" + 
				"1 3\r\n" + 
				"1 6\r\n" + 
				"3 2\r\n" + 
				"3 4\r\n" + 
				"5 2\r\n" + 
				"5 4\r\n" + 
				"6 5\r\n" + 
				"6 4\r\n" + 
				"%";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int nVertices = scanner.nextInt();
			List<List<Integer>> graph = new ArrayList<List<Integer>>();
			for(int i = 0; i < nVertices; i++){
				graph.add(new ArrayList<Integer>());
			}
			String next;
			while(!(next = scanner.next()).equals("%")){
				int v1 = Integer.parseInt(next) - 1;
				int v2 = scanner.nextInt() - 1;
				graph.get(v1).add(v2);
				graph.get(v2).add(v1);
			}
			List<Integer> path = new ArrayList<Integer>();
			if(findHamiltonianCycle(graph, path)){
				System.out.println(toString(path));
			}else System.out.println("N");
		}
		scanner.close();
	}
	
	static String toString(List<Integer> path){
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < path.size(); i++){
			str.append(path.get(i) + 1);
			if(i < path.size() - 1) str.append(" ");
		}
		return str.toString();
	}
	
	static boolean findHamiltonianCycle(List<List<Integer>> graph, boolean[] visited, 
			int source, int originalSource, List<Integer> path, int nVerticesVisited){
		if(nVerticesVisited == graph.size()){
			if(graph.get(source).contains(originalSource)){
				path.add(originalSource);
				return true;
			}
		}
		
		for(int neighbor : graph.get(source)){
			if(!visited[neighbor]){
				path.add(neighbor);
				visited[neighbor] = true;
				if(findHamiltonianCycle(graph, visited, neighbor, originalSource, path, nVerticesVisited + 1)){
					return true;
				}
				visited[neighbor] = false;
				path.remove(path.size() - 1);
			}
		}
		
		return false;
	}
	
	static boolean findHamiltonianCycle(List<List<Integer>> graph, List<Integer> path){
		for(int source = 0; source < graph.size(); source++){
			boolean[] visited = new boolean[graph.size()];
			visited[source] =  true;
			path.add(source);
			if(findHamiltonianCycle(graph, visited, source, source, path, 1)){
				return true;
			}
			path.remove(source);
		}
		return false;
	}
}
