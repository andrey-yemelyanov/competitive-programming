package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _796_CriticalLinks{
	public static void main(String[] args){
		String data = "8\r\n0 (1) 1\r\n1 (3) 2 0 3\r\n2 (2) 1 3\r\n3 (3) 1 2 4\r\n4 (1) 3\r\n7 (1) 6\r\n6 (1) 7\r\n5 (0)\r\n0\r\n";
		String data2 = "2\r\n0 (1) 1\r\n1 (1) 0\r\n\r\n20\r\n0 (1) 1\r\n1 (4) 0 2 4 5\r\n2 (4) 1 5 6 10\r\n3 (1) 4\r\n4 (4) 1 3 5 8\r\n5 (6) 1 2 4 8 9 10\r\n6 (3) 2 10 13\r\n7 (1) 8\r\n8 (4) 4 5 7 9\r\n9 (3) 5 8 10\r\n10 (4) 2 5 9 6\r\n11 (3) 12 13 17\r\n12 (3) 11 14 15\r\n13 (5) 6 11 14 17 18\r\n14 (4) 12 13 15 18\r\n15 (4) 12 14 16 18\r\n16 (1) 15\r\n17 (3) 11 13 19\r\n18 (3) 13 14 15\r\n19 (1) 17\r\n\r\n19\r\n0 (2) 1 2\r\n1 (2) 0 3\r\n2 (2) 0 3\r\n3 (3) 1 2 4\r\n4 (6) 5 6 3 10 7 8\r\n5 (2) 4 6\r\n6 (2) 5 4\r\n7 (3) 9 8 4\r\n8 (2) 4 7\r\n9 (1) 7\r\n10 (3) 11 12 4\r\n11 (2) 10 13\r\n12 (2) 10 13\r\n13 (3) 11 12 14\r\n14 (3) 13 15 16\r\n15 (2) 14 16\r\n16 (4) 14 15 17 18\r\n17 (2) 16 18\r\n18 (2) 16 17\r\n\r\n7\r\n0 (3) 1 2 4\r\n1 (3) 0 2 3\r\n2 (3) 1 0 3\r\n3 (4) 0 1 2 4\r\n4 (3) 3 6 5\r\n5 (1) 4\r\n6 (1) 4\r\n";
		Scanner s = new Scanner(data2);
		while(s.hasNext()){
			int nServers = s.nextInt();
			Map<Integer, List<Integer>> graph = new HashMap<>();
			for(int i = 0; i < nServers; i++){
				graph.put(i, new ArrayList<Integer>());
			}
			for(int i = 0; i < nServers; i++){
				int source = s.nextInt();
				int nConn = Integer.parseInt(s.next().replace("(", "").replace(")", ""));
				for(int k = 0; k < nConn; k++){
					int dest = s.nextInt();
					graph.get(source).add(dest);
					graph.get(dest).add(source);
				}
			}
			List<Integer[]> bridges = bridges(graph);
			System.out.printf("%d critical links\n", bridges.size());
			if(bridges.size() > 0){
				for(Integer[] pair : bridges){
					System.out.printf("%d - %d\n", pair[0], pair[1]);
				}
			}
			System.out.println();
		}
	}

	static int dfsNumCounter = 0;
	static int UNVISITED = -1;
	static int rootChildren = 0;
	static void artPointAndBridge(Map<Integer, List<Integer>> graph,
		int source, int[] dfsLow, int[] dfsNum, int[] dfsParent, int dfsRoot, boolean[] artVertex,
		List<Integer[]> bridges){
		dfsLow[source] = dfsNum[source] = dfsNumCounter++;
		for(int neighbor : graph.get(source)){
			if(dfsNum[neighbor] == UNVISITED){
				dfsParent[neighbor] = source;
				if(source == dfsRoot) rootChildren++;
				artPointAndBridge(graph, neighbor, dfsLow, dfsNum, dfsParent, dfsRoot, artVertex, bridges);
				if(dfsLow[neighbor] >= dfsNum[source]){
					artVertex[source] = true;
				}
				if(dfsLow[neighbor] > dfsNum[source]){
					Integer[] pair = new Integer[]{source, neighbor};
					Arrays.sort(pair);
					bridges.add(pair);
				}
				dfsLow[source] = min(dfsLow[source], dfsLow[neighbor]);
			}else if(neighbor != dfsParent[source]){
				dfsLow[source] = min(dfsLow[source], dfsNum[neighbor]);
			}
		}
	}

	static List<Integer[]> bridges(Map<Integer, List<Integer>> graph){
		int[] dfsNum = new int[graph.size()];
		for(int i = 0; i < graph.size(); i++){
			dfsNum[i] = UNVISITED;
		}
		int[] dfsLow = new int[graph.size()];
		int[] dfsParent = new int[graph.size()];
		boolean[] artVertex = new boolean[graph.size()];
		List<Integer[]> bridges = new ArrayList<>();
		int dfsRoot;
		for(int i = 0; i < graph.size(); i++){
			if(dfsNum[i] == UNVISITED){
				dfsRoot = i; rootChildren = 0;
				artPointAndBridge(graph, i, dfsLow, dfsNum, dfsParent, dfsRoot, artVertex, bridges);
				artVertex[dfsRoot] = rootChildren > 1;
			}
		}
		Collections.sort(bridges, new Comparator<Integer[]>(){
			public int compare(Integer[] pair1, Integer[] pair2){
				if(Integer.compare(pair1[0], pair2[0]) == 0){
					return Integer.compare(pair1[1], pair2[1]);
				}
				return Integer.compare(pair1[0], pair2[0]);
			}
		});
		return bridges;
	}
}