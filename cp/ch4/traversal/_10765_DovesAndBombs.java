package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _10765_DovesAndBombs{
	public static void main(String[] args){
		String data = "8 4\r\n0 4\r\n1 2\r\n2 3\r\n2 4\r\n3 5\r\n3 6\r\n3 7\r\n6 7\r\n-1 -1\r\n0 0";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int n = s.nextInt();
			int m = s.nextInt();
			if(n == 0 && m == 0) break;
			Map<Integer, List<Integer>> graph = new HashMap<>();
			for(int i = 0; i < n; i++){
				graph.put(i, new ArrayList<Integer>());
			}
			while(true){
				int v1 = s.nextInt();
				int v2 = s.nextInt();
				if(v1 == -1 && v2 == -1) break;
				graph.get(v1).add(v2);
				graph.get(v2).add(v1);
			}
			System.out.println(toStr(bombStations(artPoint(graph)), m));
		}
	}

	static int dfsNumCounter = 0;
	static int UNVISITED = -1;
	static int rootChildren = 0;
	static void artPointAndBridge(Map<Integer, List<Integer>> graph,
		int source, int[] dfsLow, int[] dfsNum, int[] dfsParent, int dfsRoot, int[] artVertex){
		dfsLow[source] = dfsNum[source] = dfsNumCounter++;
		for(int neighbor : graph.get(source)){
			if(dfsNum[neighbor] == UNVISITED){
				dfsParent[neighbor] = source;
				if(source == dfsRoot) rootChildren++;
				artPointAndBridge(graph, neighbor, dfsLow, dfsNum, dfsParent, dfsRoot, artVertex);
				if(dfsLow[neighbor] >= dfsNum[source]){
					artVertex[source]++;
				}
				dfsLow[source] = min(dfsLow[source], dfsLow[neighbor]);
			}else if(neighbor != dfsParent[source]){
				dfsLow[source] = min(dfsLow[source], dfsNum[neighbor]);
			}
		}
	}

	static int[] artPoint(Map<Integer, List<Integer>> graph){
		int[] dfsNum = new int[graph.size()];
		for(int i = 0; i < graph.size(); i++){
			dfsNum[i] = UNVISITED;
		}
		int[] dfsLow = new int[graph.size()];
		int[] dfsParent = new int[graph.size()];
		int[] artVertex = new int[graph.size()];
		int dfsRoot;
		for(int i = 0; i < graph.size(); i++){
			if(dfsNum[i] == UNVISITED){
				dfsRoot = i; rootChildren = 0;
				artPointAndBridge(graph, i, dfsLow, dfsNum, dfsParent, dfsRoot, artVertex);
				artVertex[dfsRoot] = rootChildren > 1 ? 1 : 0;
			}
		}
		return artVertex;
	}

	static String toStr(List<Integer[]> l, int m){
		/*for(Integer[] pair : l){
			System.out.println(Arrays.toString(pair));
		}*/
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++){
			sb.append(l.get(i)[0] + " " + l.get(i)[1]);
			sb.append("\n");
		}
		return sb.toString();
	}

	static List<Integer[]> bombStations(int[] artPoints){
		List<Integer[]> l = new ArrayList<>();
		for(int i = 0; i < artPoints.length; i++){
			l.add(new Integer[]{i, artPoints[i] + 1});
		}
		Collections.sort(l, new Comparator<Integer[]>(){
			public int compare(Integer[] pair1, Integer[] pair2){
				if(pair1[1] == pair2[1]) return Integer.compare(pair1[0], pair2[0]);
				return Integer.compare(pair2[1], pair1[1]);
			}
		});
		return l;
	}
}