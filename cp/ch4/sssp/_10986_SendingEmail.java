package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;

public class _10986_SendingEmail{
	private static class Edge{
		public int v;
		public int cost;
		public Edge(int v, int cost){
			this.v = v;
			this.cost = cost;
		}
	}
	public static void main(String[] args){
		String data = "3\r\n2 1 0 1\r\n0 1 100\r\n3 3 2 0\r\n0 1 100\r\n0 2 200\r\n1 2 50\r\n2 0 0 1";
		Scanner s = new Scanner(data);
		int nTests = s.nextInt();
		for(int i = 1; i <= nTests; i++){
			int n = s.nextInt(); int m = s.nextInt(); int S = s.nextInt(); int T = s.nextInt();
			Map<Integer, List<Edge>> graph = new HashMap<>();
			for(int j = 0; j < n; j++){
				graph.put(j, new ArrayList<Edge>());
			}
			for(int k = 0; k < m; k++){
				int v1 = s.nextInt(); int v2 = s.nextInt(); int cost = s.nextInt();
				graph.get(v1).add(new Edge(v2, cost));
				graph.get(v2).add(new Edge(v1, cost));
			}
			dijkstra(S, graph);
			if(dist[T] == INF) System.out.printf("Case #%d: unreachable\n", i);
			else System.out.printf("Case #%d: %d\n", i, dist[T]);
		}
	}

	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	static void dijkstra(int source, Map<Integer, List<Edge>> graph){
		dist = new int[graph.size() + 1];
		for(int i = 0; i < dist.length; i++){
			dist[i] = INF;
		}
		dist[source] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(10, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
				return Integer.compare(e1.cost, e2.cost);
			}
		});
		pq.add(new Edge(source, 0));
		while(!pq.isEmpty()){
			Edge e = pq.remove();
			if(e.cost > dist[e.v]) continue;
			for(Edge next : graph.get(e.v)){
				if(dist[e.v] + next.cost < dist[next.v]){
					dist[next.v] = dist[e.v] + next.cost;
					pq.add(next);
				}
			}
		}
	}
}