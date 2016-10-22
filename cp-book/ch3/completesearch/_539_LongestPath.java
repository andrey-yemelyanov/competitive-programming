package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _539_LongestPath{
	public static void main(String[] args){
		String data = "3 2\r\n0 1\r\n1 2\r\n15 16\r\n0 2\r\n1 2\r\n2 3\r\n3 4\r\n3 5\r\n4 6\r\n5 7\r\n6 8\r\n7 8\r\n7 9\r\n8 10\r\n9 11\r\n10 12\r\n11 12\r\n10 13\r\n12 14\r\n0 0";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int nNodes = scanner.nextInt();
			int nEdges = scanner.nextInt();
			if(nNodes == 0 && nEdges == 0) break;
			List<List<Integer>> graph = new ArrayList<List<Integer>>();
			for(int i = 0; i < nNodes; i++){
				graph.add(new ArrayList<Integer>());
			}
			for(int i = 0; i < nEdges; i++){
				int v1 = scanner.nextInt();
				int v2 = scanner.nextInt();
				graph.get(v1).add(v2);
				graph.get(v2).add(v1);
			}
			int maxPathLength = Integer.MIN_VALUE;
			for(int i = 0; i < nNodes; i++){
				int len = explore(graph, i);
				maxPathLength = Math.max(maxPathLength, len);
			}
			System.out.println(maxPathLength);
		}
		scanner.close();
	}
	
	static int maxLength;
	static int explore(List<List<Integer>> graph, int source){
		maxLength = Integer.MIN_VALUE;
		List<Set<Integer>> visited = new ArrayList<Set<Integer>>();
		for(int i = 0; i < graph.size(); i++){
			visited.add(new HashSet<Integer>());
		}
		explore(graph, visited, source, 0);
		return maxLength;
	}
	
	static void explore(List<List<Integer>> graph, List<Set<Integer>> visited, int source, int length){
		if(graph.get(source).size() == visited.get(source).size()){
			if(length > maxLength) maxLength = length;
		}else{
			for(int v : graph.get(source)){
				if(!visited.get(source).contains(v)){
					visited.get(source).add(v);
					visited.get(v).add(source);
					explore(graph, visited, v, length + 1);
					visited.get(source).remove(v);
					visited.get(v).remove(source);
				}
			}
		}
	}
}