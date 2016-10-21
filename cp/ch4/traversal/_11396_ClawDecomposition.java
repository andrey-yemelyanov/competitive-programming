package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _11396_ClawDecomposition{
	public static void main(String[] args){
		String data = "";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int n = s.nextInt();
			if(n == 0) break;
			Map<Integer, List<Integer>> graph = new HashMap<>();
			for(int i = 0; i < n; i++){
				graph.put(i, new ArrayList<Integer>());
			}
			while(true){
				int v1 = s.nextInt() - 1; int v2 = s.nextInt() - 1;
				if(v1 == -1 && v2 == -1) break;
				graph.get(v1).add(v2);
				graph.get(v2).add(v1);
			}
			if(bipartite(graph, 0)) System.out.println("YES");
			else System.out.println("NO");
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