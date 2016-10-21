package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;
import java.io.*;

public class _01112_MiceAndMaze{
	private static class Edge{
		public int v;
		public int cost;
		public Edge(int v, int cost){
			this.v = v;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
		String data = "2\r\n\r\n4\r\n2\r\n1\r\n8\r\n1 2 1\r\n1 3 1\r\n2 1 1\r\n2 4 1\r\n3 1 1\r\n3 4 1\r\n4 2 1\r\n4 3 1\r\n\r\n4\r\n2\r\n1\r\n8\r\n1 2 1\r\n1 3 1\r\n2 1 1\r\n2 4 1\r\n3 1 1\r\n3 4 1\r\n4 2 1\r\n4 3 1\r\n";
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader reader = new BufferedReader(new StringReader(data));
		BufferedReader reader = new BufferedReader(new FileReader(new File("MiceTest.txt")));
		int nTests = Integer.parseInt(reader.readLine());
		reader.readLine();
		while(nTests-- > 0){
			int N = Integer.parseInt(reader.readLine());
			int E = Integer.parseInt(reader.readLine());
			int T = Integer.parseInt(reader.readLine());
			int M = Integer.parseInt(reader.readLine());
			Map<Integer, List<Edge>> graph = new HashMap<>();
			for(int i = 1; i <= N; i++){
				graph.put(i, new ArrayList<Edge>());
			}
			for(int i = 0; i < M; i++){
				String[] l = reader.readLine().split("\\s+");
				int from = Integer.parseInt(l[0]);
				int to = Integer.parseInt(l[1]);
				int cost = Integer.parseInt(l[2]);
				graph.get(to).add(new Edge(from, cost)); // reverse all edges
			}
			dijkstra(E, graph);
			int n = 0;
			for(int i = 1; i <= N; i++){
				if(dist[i] <= T) n++;
			}
			System.out.println(n);
			if(nTests > 0) System.out.println();
			reader.readLine();
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