package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _193_GraphColoring {
	public static void main(String... args){
		String data = "1\r\n" + 
				"6 8\r\n" + 
				"1 2\r\n" + 
				"1 3\r\n" + 
				"2 4\r\n" + 
				"2 5\r\n" + 
				"3 4\r\n" + 
				"3 6\r\n" + 
				"4 6\r\n" + 
				"5 6";
		Scanner scanner = new Scanner(data);
		int nGraphs = scanner.nextInt();
		while(nGraphs-- > 0){
			int nVertices = scanner.nextInt();
			int nEdges = scanner.nextInt();
			List<List<Integer>> graph = new ArrayList<List<Integer>>();
			for(int i = 0; i < nVertices; i++){
				graph.add(new ArrayList<Integer>());
			}
			while(nEdges-- > 0){
				int v1 = scanner.nextInt() - 1;
				int v2 = scanner.nextInt() - 1;
				graph.get(v1).add(v2);
				graph.get(v2).add(v1);
			}
			List<Integer> mis = findMaximumIndependentSet(graph);
			System.out.println(mis.size());
			System.out.println(toString(mis));
		}
		scanner.close();
	}
	
	static String toString(List<Integer> mis){
		StringBuilder str = new StringBuilder();
		for(int v : mis){
			str.append((v + 1) + " ");
		}
		return str.toString().trim();
	}
	
	static List<Integer> findMaximumIndependentSet(List<List<Integer>> graph){
		Set<Integer> vertices = new HashSet<Integer>();
		for(int i = 0; i < graph.size(); i++) vertices.add(i);
		maxIndependentSet = new ArrayList<Integer>();
		findMaximumIndependentSet(vertices, graph, new ArrayList<Integer>());
		return maxIndependentSet;
	}
	
	static List<Integer> maxIndependentSet = new ArrayList<Integer>();
	static void findMaximumIndependentSet(Set<Integer> vertices, List<List<Integer>> graph, List<Integer> mis){
		if(vertices.isEmpty()){
			if(mis.size() > maxIndependentSet.size()){
				maxIndependentSet = new ArrayList<Integer>(mis);
			}
			return;
		}
		for(int v : vertices){
			mis.add(v);
			Set<Integer> remainingVertices = new HashSet<Integer>(vertices);
			for(int neighbor : graph.get(v)){
				remainingVertices.remove(neighbor);
			}
			remainingVertices.remove(v);
			findMaximumIndependentSet(remainingVertices, graph, mis);
			mis.remove(mis.size() - 1);
		}
	}
}
