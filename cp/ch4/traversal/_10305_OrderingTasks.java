package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _10305_OrderingTasks{
	public static void main(String[] args){
		String data = "5 4\r\n1 2\r\n2 3\r\n1 3\r\n1 5\r\n0 0";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int n = s.nextInt();
			int m = s.nextInt();
			if(n == 0 && m == 0) break;
			Map<Integer, List<Integer>> graph = new HashMap<>();
			for(int i = 1; i <= n; i++){
				graph.put(i, new ArrayList<Integer>());
			}
			for(int i = 0; i < m; i++){
				graph.get(s.nextInt()).add(s.nextInt());
			}
			System.out.println(toString(toposort(graph)));
		}
	}

	static String toString(List<Integer> toposort){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < toposort.size(); i++){
			sb.append(toposort.get(i));
			if(i < toposort.size() - 1) sb.append(" ");
		}
		return sb.toString();
	}

	static void dfs(Map<Integer, List<Integer>> graph, int source, boolean[] visited, Stack<Integer> s){
		visited[source] = true;
		for(int neighbor : graph.get(source)){
			if(!visited[neighbor]){
				dfs(graph, neighbor, visited, s);
			}
		}
		s.push(source);
	}

	static List<Integer> toposort(Map<Integer, List<Integer>> graph){
		boolean[] visited = new boolean[graph.size() + 1];
		Stack<Integer> s = new Stack<>();
		for(int v : graph.keySet()){
			if(!visited[v]){
				dfs(graph, v, visited, s);
			}
		}

		List<Integer> ordering = new ArrayList<>();
		while(!s.isEmpty()){
			ordering.add(s.pop());
		}
		return ordering;
	}
}
