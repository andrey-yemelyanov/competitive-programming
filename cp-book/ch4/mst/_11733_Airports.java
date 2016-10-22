package helvidios.cp.ch4.mst;

import java.util.*;
import static java.lang.Math.*;
import java.io.*;

public class _11733_Airports{
	private static class Edge{
		public int vertex1;
		public int vertex2;
		public int weight;
		public Edge(int vertex1, int vertex2, int weight){
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.weight = weight;
		}
	}
	private static class UnionFind {
	    private int[] p;
	    private int[] rank;
	    private int[] size;
	    private int nDisjointSets;
	     
	    public UnionFind(int n){
	        p = new int[n];
	        rank = new int[n];
	        size = new int[n];
	         
	        for(int i = 0; i < p.length; i++){
	            p[i] = i;
	            size[i] = 1;
	        }
	         
	        nDisjointSets = n;
	    }
	     
	    /**
	     * Returns identifier for subset which element i is in.
	     */
	    public int findSet(int i){
	        if(p[i] != i){
	            p[i] = findSet(p[i]);
	        }
	        return p[i];
	    }
	     
	    /**
	     * Returns true if elements i and j belong to the same subset.
	     */
	    public boolean isSameSet(int i, int j){
	        return findSet(i) == findSet(j);
	    }
	     
	    /**
	     * Joins subsets of i and j together.
	     */
	    public void unionSet(int i, int j){
	        int iRoot = findSet(i);
	        int jRoot = findSet(j);
	        if(iRoot == jRoot) return;
	         
	        if(rank[iRoot] < rank[jRoot]){
	            p[iRoot] = jRoot;
	            size[jRoot] += size[iRoot];
	        }else if(rank[iRoot] > rank[jRoot]){
	            p[jRoot] = iRoot;
	            size[iRoot] += size[jRoot];
	        }else{
	            p[jRoot] = iRoot;
	            size[iRoot] += size[jRoot];
	            rank[iRoot]++;
	        }
	         
	        nDisjointSets--;
	    }
	     
	    /**
	     * Returns the number of disjoint sets currently in this union-find structure.
	     */
	    public int nDisjointSets(){
	        return nDisjointSets;
	    }
	     
	    /**
	     * Returns the size of set that currently contains item i. 
	     */
	    public int sizeOfSet(int i){
	        return size[findSet(i)];
	    }
	}
	public static void main(String[] args) throws Exception{
		String data = "2\r\n4 4 100\r\n1 2 10\r\n4 3 12\r\n4 1 41\r\n2 3 23\r\n5 3 1000\r\n1 2 20\r\n4 5 40\r\n3 2 30";
		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader in = new BufferedReader(new StringReader(data));
		int nTests = Integer.parseInt(in.readLine());
		for(int i = 1; i <= nTests; i++){
			String[] line = in.readLine().split("\\s+");
			int nVertices = Integer.parseInt(line[0]); 
			int m = Integer.parseInt(line[1]); 
			int airportCost = Integer.parseInt(line[2]);
			List<Edge> edgeList = new ArrayList<>();
			for(int j = 0; j < m; j++){
				String[] edge = in.readLine().split("\\s+");
				edgeList.add(new Edge(Integer.parseInt(edge[0]) - 1, 
					Integer.parseInt(edge[1]) - 1, Integer.parseInt(edge[2])));
			}
			int[] cost = mst(edgeList, nVertices, airportCost);
			System.out.printf("Case #%d: %d %d\n", i, cost[0], cost[1]);
		}
	}

	static int[] mst(List<Edge> edgeList, int nVertices, int airportCost){
		Collections.sort(edgeList, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
				return Integer.compare(e1.weight, e2.weight);
			}
		});

		int mstCost = 0;
		UnionFind uf = new UnionFind(nVertices);
		for(Edge e : edgeList){
			if(!uf.isSameSet(e.vertex1, e.vertex2) && e.weight < airportCost){
				mstCost += e.weight;
				uf.unionSet(e.vertex1, e.vertex2);
			}
		}

		return new int[] {mstCost + uf.nDisjointSets() * airportCost, uf.nDisjointSets};
	}
}