package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _11080_PlaceGuards{
	static final int INF = -1;
	public static void main(String[] args){
		String data = "2\r\n4 2\r\n0 1\r\n2 3\r\n5 5\r\n0 1\r\n1 2\r\n2 3\r\n0 4\r\n3 4";
		String data2 = "1\r\n5 3\r\n0 1\r\n3 2\r\n2 1";
		Scanner s = new Scanner(data2);
		int nTests = s.nextInt();
		while(nTests-- > 0){
			int n = s.nextInt();
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
			int[] color = new int[graph.size()];
			for(int i = 0; i < color.length; i++){
				color[i] = INF;
			}
			
			int nGuards = 0;
			int color1 = 0; int color2 = 1;
			for(int i = 0; i < graph.size(); i++){
				if(color[i] == INF){
					boolean isBipartite = bipartite(graph, i, color, color1, color2);
					if(!isBipartite){
						nGuards = -1;
						break;
					}else{
						nGuards += min(countColor(color1, color), countColor(color2, color));
					}
					color1 += 2; color2 += 2;
				}
			}	
			
			System.out.println(nGuards);
		}
	}

	static int countColor(int c, int[] color){
		int count = 0;
		for(int i = 0; i < color.length; i++){
			if(color[i] == c) count++;
		}
		if(count == 0) return Integer.MAX_VALUE;
		return count;
	}

	static boolean bipartite(Map<Integer, List<Integer>> graph, int source, int[] color,
		int color1, int color2){
		Queue<Integer> q = new LinkedList<>();
		q.add(source);
		color[source] = color1;
		boolean isBipartite = true;
		while(!q.isEmpty() && isBipartite){
			int v = q.remove();
			for(int neighbor : graph.get(v)){
				if(color[neighbor] == INF){
					color[neighbor] = changeColor(color1, color2, color[v]);
					q.add(neighbor);
				}else if(color[neighbor] == color[v]){
					isBipartite = false;
					break;
				}
			}
		}
		return isBipartite;
	}

	static int changeColor(int color1, int color2, int current){
		if(current == color1) return color2;
		return color1;
	}
}