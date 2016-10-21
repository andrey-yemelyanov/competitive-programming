package helvidios.cp.ch3.completesearch;

import java.util.*;

public class UVa_11090_GoingInCycle {
	static class Vertex{
		public int id;
		public int weight;
		public Vertex(int id, int weight){
			this.id = id;
			this.weight = weight;
		}
	}
	public static void main(String[] args){
		String data = "2\r\n2 1\r\n1 2 1\r\n2 2\r\n1 2 2\r\n2 1 3";
		String data2 = "1 \r\n3 5 \r\n1 2 1 \r\n2 3 5 \r\n3 1 1 \r\n3 1 5 \r\n2 3 1 ";
		String data3 = "1\r\n\r\n18 63\r\n17 9 10\r\n3 16 12\r\n18 4 5\r\n13 3 4\r\n18 5 11\r\n12 16 4\r\n13 2 1\r\n15 4 13\r\n6 10 7\r\n10 6 17\r\n17 3 9\r\n2 5 6\r\n3 4 17\r\n4 8 8\r\n8 12 2\r\n3 12 4\r\n4 10 12\r\n7 4 2\r\n17 4 3\r\n2 16 8\r\n2 3 1\r\n6 18 6\r\n6 3 8\r\n13 7 5\r\n14 15 4\r\n15 7 15\r\n2 8 16\r\n16 10 11\r\n17 7 18\r\n1 9 18\r\n4 7 16\r\n7 9 15\r\n4 12 19\r\n11 2 6\r\n2 13 17\r\n5 8 15\r\n2 9 8\r\n11 6 11\r\n7 2 12\r\n4 18 10\r\n18 1 20\r\n6 5 2\r\n15 1 18\r\n15 18 13\r\n9 14 12\r\n16 14 5\r\n2 4 13\r\n6 17 12\r\n5 16 8\r\n11 4 6\r\n10 3 12\r\n8 3 1\r\n3 8 12\r\n17 12 12\r\n18 9 6\r\n1 12 10\r\n6 16 12\r\n9 10 20\r\n1 11 8\r\n8 13 11\r\n15 14 11\r\n16 1 15\r\n12 13 18";
		Scanner scanner = new Scanner(data3);
		int nTestCases = scanner.nextInt();
		int caseNum = 0;
		while(nTestCases-- > 0){
			List<List<Vertex>> graph = new ArrayList<List<Vertex>>();
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			for(int i = 0; i < n; i++){
				graph.add(new ArrayList<Vertex>());
			}
			for(int i = 0; i < m; i++){
				int v1 = scanner.nextInt() - 1;
				int v2 = scanner.nextInt() - 1;
				int weight = scanner.nextInt();
				graph.get(v1).add(new Vertex(v2, weight));
			}
			
			double minWeight = Double.MAX_VALUE;
			for(int source = 0; source < graph.size(); source++){
				minWeight = Math.min(minWeight, findMinWeight(graph, source, minWeight));
			}
			System.out.print("Case #" + (++caseNum) + ": ");
			if(minWeight == Double.MAX_VALUE) System.out.println("No cycle found.");
			else System.out.printf("%.2f\n", minWeight);
		}
	}
	
	static double findMinWeight(List<List<Vertex>> graph, int source, double bestSoFar){
		minMeanCycleWeight = Double.MAX_VALUE;
		boolean[] visited = new boolean[graph.size()];
		findMinWeight(graph, source, source, visited, 0, 0, bestSoFar);
		return minMeanCycleWeight;
	}
	
	static double minMeanCycleWeight;
	static void findMinWeight(
		List<List<Vertex>> graph,
		int source,
		int originalSource,
		boolean[] visited,
		int nEdges,
		long totalWeight,
		double bestSoFar){
			if(source == originalSource && nEdges > 0){
				double meanWeight = (double) totalWeight / nEdges;
				minMeanCycleWeight = Math.min(minMeanCycleWeight, meanWeight);
				return;
			}
			
			for(Vertex neighbor : graph.get(source)){
				if(!visited[neighbor.id]){
					visited[neighbor.id] = true;
					double runningMean = (double) (totalWeight + neighbor.weight) / (nEdges + 1);
					if(runningMean <= bestSoFar && runningMean <= minMeanCycleWeight){
						findMinWeight(graph, neighbor.id, originalSource, visited, nEdges + 1, totalWeight + neighbor.weight, bestSoFar);
					}
					visited[neighbor.id] = false;
				}
			}
		}
}
