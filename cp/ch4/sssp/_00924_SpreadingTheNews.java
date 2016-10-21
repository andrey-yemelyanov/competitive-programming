package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;

public class _00924_SpreadingTheNews{
	public static void main(String[] args){
		String data = "6\r\n2 1 2\r\n2 3 4\r\n3 0 4 5\r\n1 4\r\n0\r\n2 0 2\r\n3\r\n0\r\n4\r\n5";
		String data2 = "5\r\n1 1\r\n1 2\r\n1 3\r\n1 4 \r\n1 0\r\n5\r\n0\r\n1\r\n2\r\n3\r\n4";
		Scanner s = new Scanner(data2);
		while(s.hasNext()){
			int nEmp = s.nextInt();
			Map<Integer, List<Integer>> graph = new HashMap<>();
			for(int i = 0; i < nEmp; i++){
				graph.put(i, new ArrayList<Integer>());
			}
			for(int i = 0; i < nEmp; i++){
				int nFriends = s.nextInt();
				for(int j = 0; j < nFriends; j++){
					graph.get(i).add(s.nextInt());
				}
			}
			int nTests = s.nextInt();
			for(int i = 0; i < nTests; i++){
				int source = s.nextInt();
				bfs(graph, source);
				int[] boomStats = getBoomStats();
				int maxBoomSize = boomStats[0]; int firstBoomDay = boomStats[1];
				if(maxBoomSize == 1 && firstBoomDay == 0) System.out.println(0);
				else System.out.println(maxBoomSize + " " + firstBoomDay);
			}
		}
	}

	static int[] getBoomStats(){
		Map<Integer, Integer> map = new TreeMap<>();
		for(int i = 0; i < dist.length; i++){
			if(dist[i] != INF){
				if(!map.containsKey(dist[i])){
					map.put(dist[i], 0);
				}	
				map.put(dist[i], map.get(dist[i]) + 1);
			}
		}

		if(map.size() == 1) return new int[] {1, 0};
		int maxBoomSize = 0; int firstBoomDay = 0;
		for(int day : map.keySet()){
			if(day != 0 && map.get(day) > maxBoomSize){
				maxBoomSize = map.get(day);
				firstBoomDay = day;
			}
		}

		return new int[] {maxBoomSize, firstBoomDay};
	}

	static final int INF = -1;
    static int[] dist;
    static void bfs(Map<Integer, List<Integer>> graph, int source){
        dist = new int[graph.keySet().size()];
        for(int i = 0; i < dist.length; i++){
            dist[i] = INF;
        }
        dist[source] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        while(!q.isEmpty()){
            int u = q.remove();
            for(int neighbor : graph.get(u)){
                if(dist[neighbor] == INF){
                    dist[neighbor] = dist[u] + 1;
                    q.add(neighbor);
                }
            }
        }
    }
}