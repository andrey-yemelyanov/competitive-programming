package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _318_DominoEffect {
	static class Pair{
		public int v1;
		public int v2;
		public Pair(int v1, int v2){
			if(v1 < v2){
				this.v1 = v1;
				this.v2 = v2;
			}else{
				this.v1 = v2;
				this.v2 = v1;
			}
		}
	}
    public static void main(String[] args) {
        String data = "2 1\r\n1 2 27\r\n3 3\r\n1 2 5\r\n1 3 5\r\n2 3 5\r\n0 0";
		String data2 = "3 3\r\n1 2 5\r\n1 3 10\r\n2 3 5\r\n0 0\r\n"; // = 10.0
		String data3 = "3 3\r\n1 2 1\r\n2 3 2\r\n1 3 3\r\n0 0"; // 3.0
		String data4 = "1 1\n1 1 1\n0 0";
      	String data5 = "1 0\n0 0";
      	String data6 = "100 10\r\n1 39 7218\r\n1 88 783\r\n1 92 8460\r\n88 21 3278\r\n39 87 744\r\n39 88 1063\r\n39 82 4476\r\n87 1 4219\r\n87 35 4504\r\n35 15 2508\r\n0 0"; // 9602.0 15
      	String data7 = "3 3\n1 2 3\n2 3 2\n1 3 2\n0 0";
      	String data8 = "3 2\n1 2 3\n1 3 3\n0 0";
        Scanner scanner = new Scanner(data);
		int system = 1;
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			if(n == 0 && m == 0) break;
			Map<Integer, List<Integer>> graph = new HashMap<>();
			int nextId = n + 1;
			for(int i = 1; i <= n; i++){
				graph.put(i, new ArrayList<Integer>());
			}
			Pair[] map = new Pair[10000000];
			for(int i = 0; i < m; i++){
				int kd1 = scanner.nextInt();
				int kd2 = scanner.nextInt();
				int time = scanner.nextInt();
				nextId = connectKeyDominoes(graph, kd1, kd2, nextId, time, map);
			}
			int[] distance = new int[graph.size() + 1];
			bfs(1, graph, distance);
			List<Integer> lastDominoesToFall = new ArrayList<>();
			int maxDistance = 0;
          	for(int i = 1; i < distance.length; i++){
              	if(distance[i] != POS_INF && distance[i] > maxDistance)
					maxDistance = distance[i];
			}
			for(int i = 1; i < distance.length; i++){
				if(distance[i] == maxDistance){
					lastDominoesToFall.add(i);
				}
			}
			double totalSeconds = (double)maxDistance;
			
			boolean neighborsFound = false;
			for(int lastDomino : lastDominoesToFall){
				int neighbor = -1;
				for(int v : graph.get(lastDomino)){
					if(distance[v] == distance[lastDomino]){
						neighbor = v;
						break;
					}
				}
				if(neighbor != -1){
					neighborsFound = true;
					int kd1 = 0, kd2 = 0;
					if(lastDomino > n){
						kd1 = map[lastDomino].v1;
						kd2 = map[lastDomino].v2;
					}else if(neighbor > n){
						kd1 = map[neighbor].v1;
						kd2 = map[neighbor].v2;
					}
					totalSeconds += 0.5;
					System.out.print(String.format(Locale.US, "System #%d\nThe last domino falls after %.1f seconds, between key dominoes %d and %d.\n\n", system++, totalSeconds, kd1, kd2));
					break;
				}
			}
			
			if(!neighborsFound){
				int lastDomino = lastDominoesToFall.get(0);
				if(lastDomino <= n){
					System.out.print(String.format(Locale.US, "System #%d\nThe last domino falls after %.1f seconds, at key domino %d.\n\n", system++, totalSeconds, lastDomino));
				}else{
					System.out.print(String.format(Locale.US, "System #%d\nThe last domino falls after %.1f seconds, between key dominoes %d and %d.\n\n", system++, totalSeconds, map[lastDomino].v1, map[lastDomino].v2));
				}
			}
		}
        scanner.close();
    }
	
	static final int POS_INF = 1000000;
	static void bfs(int source, Map<Integer, List<Integer>> graph, int[] distance){
		Queue<Integer> q = new LinkedList<>();
		q.add(source);
		for(int i = 0; i < distance.length; i++){
			distance[i] = POS_INF;
		}
		distance[source] = 0;
		while(!q.isEmpty()){
			int v = q.remove();
			for(int neighbor : graph.get(v)){
				if(distance[neighbor] == POS_INF){
					distance[neighbor] = distance[v] + 1;
					q.add(neighbor);
				}
			}
		}
	}
	
	static int connectKeyDominoes(
		Map<Integer, List<Integer>> graph,
		int kd1, int kd2,
		int nextId, int time, Pair[] map){
		int i = nextId;
		if(time == 1){
			addEdge(graph, kd1, kd2);
		}else{
			for(; i < nextId + time - 1; i++){
				if(i == nextId) addEdge(graph, kd1, i);
				else addEdge(graph, i - 1, i);
				map[i] = new Pair(kd1, kd2);
			}
			addEdge(graph, kd2, i - 1);
		}
		return i;
	}
	
	static void addEdge(Map<Integer, List<Integer>> graph, int v1, int v2){
		if(!graph.containsKey(v1)) graph.put(v1, new ArrayList<Integer>());
		if(!graph.containsKey(v2)) graph.put(v2, new ArrayList<Integer>());
		graph.get(v1).add(v2); graph.get(v2).add(v1);
	}
}
