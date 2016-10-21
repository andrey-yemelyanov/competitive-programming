package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _10004_Bicoloring{
	public static void main(String[] args){
		String data = "3\r\n3\r\n0 1\r\n1 2\r\n2 0\r\n3\r\n2\r\n0 1\r\n1 2\r\n9\r\n8\r\n0 1\r\n0 2\r\n0 3\r\n0 4\r\n0 5\r\n0 6\r\n0 7\r\n0 8\r\n0";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int n = s.nextInt();
			if(n == 0) break;
			Map<Integer, List<Integer>> graph = new HashMap<>();
			for(int i = 0; i < n; i++){
				graph.put(i, new ArrayList<Integer>());
			}
			int m = s.nextInt();
			for(int i = 0; i < m; i++){
				int v1 = s.nextInt(); int v2 = s.nextInt();
				graph.get(v1).add(v2);
				graph.get(v2).add(v1);
			}
			if(bipartite(graph, 0)) System.out.println("BICOLORABLE.");
			else System.out.println("NOT BICOLORABLE.");
		}
	}

	static boolean bipartite(Map<Integer, List<Integer>> graph, int source){
		final int INF = -1;
		Queue<Integer> q = new LinkedList<>();
		q.add(source);
		int[] color = new int[graph.size()];
		for(int i = 0; i < color.length; i++){
			color[i] = INF;
		}
		color[source] = 0;
		boolean isBipartite = true;
		while(!q.isEmpty() && isBipartite){
			int v = q.remove();
			for(int neighbor : graph.get(v)){
				if(color[neighbor] == INF){
					color[neighbor] = 1 - color[v];
					q.add(neighbor);
				}else if(color[neighbor] == color[v]){
					isBipartite = false;
					break;
				}
			}
		}
		return isBipartite;
	}
}