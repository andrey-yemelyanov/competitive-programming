package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _280_Vertex {
    public static void main(String[] args) {
        String data = "3\r\n1 2 0\r\n2 2 0\r\n3 1 2 0\r\n0\r\n2 1 2\r\n0\r\n";
      	String data2 = "3\r\n2 3 0\r\n3 1 2 0\r\n0\r\n3 1 2 3\r\n8\r\n2 3 4 8 0\r\n3 1 6 0\r\n4 3 5 7 0\r\n5 2 3 6 8 0\r\n6 1 3 4 5 0\r\n7 1 3 5 6 0\r\n8 2 3 4 5 0\r\n0\r\n8 1 2 3 4 5 6 7 8\r\n4\r\n1 2 4 0\r\n2 3 0\r\n3 1 2 4 0\r\n4 1 2 0\r\n0\r\n4 1 2 3 4\r\n1\r\n0\r\n1 1\r\n9\r\n1 6 8 0\r\n2 1 4 6 9 0\r\n3 2 4 5 8 9 0\r\n4 5 7 0\r\n5 1 3 4 8 9 0\r\n6 2 3 4 5 7 9 0\r\n7 1 2 3 4 0\r\n8 1 2 3 4 6 9 0\r\n9 1 2 5 7 0\r\n0\r\n9 1 2 3 4 5 6 7 8 9\r\n8\r\n1 2 3 8 0\r\n2 1 3 5 6 7 8 0\r\n3 4 0\r\n4 1 3 0\r\n5 1 3 7 8 0\r\n6 2 4 7 8 0\r\n7 6 0\r\n8 4 6 7 0\r\n0\r\n8 1 2 3 4 5 6 7 8\r\n9\r\n1 3 4 0\r\n2 1 4 5 9 0\r\n3 4 7 8 0\r\n4 1 2 5 6 7 8 9 0\r\n5 1 3 4 8 9 0\r\n6 2 3 5 7 8 9 0\r\n7 2 3 8 9 0\r\n8 2 4 6 9 0\r\n9 1 2 3 5 7 0\r\n0\r\n9 1 2 3 4 5 6 7 8 9\r\n10\r\n1 7 9 10 0\r\n2 3 4 5 7 0\r\n3 1 2 8 10 0\r\n4 3 5 0\r\n5 1 3 6 0\r\n6 1 2 7 10 0\r\n7 8 9 0\r\n8 1 2 3 4 5 6 9 0\r\n9 2 3 5 8 0\r\n10 1 2 4 7 0\r\n0\r\n10 1 2 3 4 5 6 7 8 9 10\r\n10\r\n1 2 4 5 6 0\r\n2 1 5 6 7 10 0\r\n3 2 4 6 8 9 0\r\n4 2 3 5 7 9 0\r\n5 1 3 4 6 7 8 0\r\n6 1 3 5 9 10 0\r\n7 6 8 9 0\r\n8 2 3 4 6 9 0\r\n9 1 2 3 4 6 0\r\n10 1 3 6 7 8 9 0\r\n0\r\n10 1 2 3 4 5 6 7 8 9 10\r\n7\r\n1 5 0\r\n2 4 0\r\n3 1 2 4 6 0\r\n4 1 2 3 0\r\n5 4 6 7 0\r\n6 5 0\r\n7 2 5 0\r\n0\r\n7 1 2 3 4 5 6 7\r\n0";
        Scanner scanner = new Scanner(data2);
		StringBuilder out = new StringBuilder();
		while(scanner.hasNext()){
			int V = scanner.nextInt();
			if(V == 0) break;
			Map<Integer, List<Integer>> graph = new HashMap<>();
			for(int i = 0; i < V; i++){
				graph.put(i, new ArrayList<Integer>());
			}
			while(true){
				int startingVertex = scanner.nextInt() - 1;
				if(startingVertex < 0) break;
				while(true){
					int endVertex = scanner.nextInt() - 1;
					if(endVertex < 0) break;
					graph.get(startingVertex).add(endVertex);
				}
			}
			int nSources = scanner.nextInt();
			while(nSources-- > 0){
				int source = scanner.nextInt() - 1;
				List<Integer> solution = inaccessibleVertices(source, graph);
				out.append(solution.size());
              	if(solution.size() > 0){
                  	out.append(" ");
                	for(int i = 0; i < solution.size(); i++){
						out.append(solution.get(i) + 1);
						if(i < solution.size() - 1) out.append(" ");
					}  	
              	}
				
				out.append("\n");
			}
		}
		System.out.print(out.toString());
        scanner.close();
    }
	
	static List<Integer> inaccessibleVertices(int source, Map<Integer, List<Integer>> graph){
		List<Integer> inaccessibleVertices = new ArrayList<>();
		boolean[] visited = new boolean[graph.size()];
		dfs(source, graph, visited);
		for(int i = 0; i < visited.length; i++){
			if(!visited[i]) inaccessibleVertices.add(i);
		}
		return inaccessibleVertices;
	}
	
	static void dfs(int source, Map<Integer, List<Integer>> graph, boolean[] visited){
		for(int neighbor : graph.get(source)){
			if(!visited[neighbor]){
				visited[neighbor] = true;
				dfs(neighbor, graph, visited);
			}
		}
	}
}