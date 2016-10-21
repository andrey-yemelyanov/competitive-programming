package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _315_Network{
	public static void main(String[] args){
		String data = "5\r\n5 1 2 3 4\r\n0\r\n6\r\n2 1 3\r\n5 4 6 2\r\n0\r\n0";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int N = s.nextInt();
			if(N == 0) break;
			Map<Integer, List<Integer>> graph = new HashMap<>();
			for(int i = 0; i < N; i++){
				graph.put(i, new ArrayList<Integer>());
			}
			s.nextLine();
			while(true){
				String[] v = s.nextLine().split("\\s+");
				if(v[0].equals("0")) break;
				int source = Integer.parseInt(v[0]) - 1;
				for(int i = 1; i < v.length; i++){
					int dest = Integer.parseInt(v[i]) - 1;
					graph.get(source).add(dest);
					graph.get(dest).add(source);
				}
			}
			int count = countArtVertices(artPoint(graph));
			System.out.println(count);
		}
	}

	static int dfsNumCounter = 0;
	static int UNVISITED = -1;
	static int rootChildren = 0;
	static void artPointAndBridge(Map<Integer, List<Integer>> graph,
		int source, int[] dfsLow, int[] dfsNum, int[] dfsParent, int dfsRoot, boolean[] artVertex){
		dfsLow[source] = dfsNum[source] = dfsNumCounter++;
		for(int neighbor : graph.get(source)){
			if(dfsNum[neighbor] == UNVISITED){
				dfsParent[neighbor] = source;
				if(source == dfsRoot) rootChildren++;
				artPointAndBridge(graph, neighbor, dfsLow, dfsNum, dfsParent, dfsRoot, artVertex);
				if(dfsLow[neighbor] >= dfsNum[source]){
					artVertex[source] = true;
				}
				dfsLow[source] = min(dfsLow[source], dfsLow[neighbor]);
			}else if(neighbor != dfsParent[source]){
				dfsLow[source] = min(dfsLow[source], dfsNum[neighbor]);
			}
		}
	}

	static boolean[] artPoint(Map<Integer, List<Integer>> graph){
		int[] dfsNum = new int[graph.size()];
		for(int i = 0; i < graph.size(); i++){
			dfsNum[i] = UNVISITED;
		}
		int[] dfsLow = new int[graph.size()];
		int[] dfsParent = new int[graph.size()];
		boolean[] artVertex = new boolean[graph.size()];
		int dfsRoot;
		for(int i = 0; i < graph.size(); i++){
			if(dfsNum[i] == UNVISITED){
				dfsRoot = i; rootChildren = 0;
				artPointAndBridge(graph, i, dfsLow, dfsNum, dfsParent, dfsRoot, artVertex);
				artVertex[dfsRoot] = rootChildren > 1;
			}
		}
		return artVertex;
	}

	static int countArtVertices(boolean[] artVertex){
		int count = 0;
		for(int i = 0; i < artVertex.length; i++){
			if(artVertex[i]) count++;
		}
		return count;
	}
}