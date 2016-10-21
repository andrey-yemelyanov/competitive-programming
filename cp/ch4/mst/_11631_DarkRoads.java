package helvidios.cp.ch4.mst;

import java.util.*;
import static java.lang.Math.*;

public class _11631_DarkRoads{
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
	public static void main(String[] args){
		String data = "7 11\r\n0 1 7\r\n0 3 5\r\n1 2 8\r\n1 3 9\r\n1 4 7\r\n2 4 5\r\n3 4 15\r\n3 5 6\r\n4 5 8\r\n4 6 9\r\n5 6 11\r\n0 0";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int m = s.nextInt(); int n = s.nextInt();
			if(m == 0 && n == 0) break;
			List<Edge> edgeList = new ArrayList<>();
			for(int i = 0; i < n; i++){
				edgeList.add(new Edge(s.nextInt(), s.nextInt(), s.nextInt()));
			}
			System.out.println(totalSavings(edgeList, mst(edgeList)));
		}
	}

	static int totalSavings(List<Edge> edgeList, int mst){
		int totalDailyCost = 0;
		for(Edge e : edgeList){
			totalDailyCost += e.weight;
		}
		return totalDailyCost - mst;
	}

	static int nVertices(List<Edge> edgeList){
		Set<Integer> vertices = new HashSet<>();
		for(Edge e : edgeList){
			vertices.add(e.vertex1);
			vertices.add(e.vertex2);
		}
		return vertices.size();
	}

	static int mst(List<Edge> edgeList){
		Collections.sort(edgeList, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
				return Integer.compare(e1.weight, e2.weight);
			}
		});

		int mstCost = 0;
		UnionFind uf = new UnionFind(nVertices(edgeList));
		for(Edge e : edgeList){
			if(!uf.isSameSet(e.vertex1, e.vertex2)){
				mstCost += e.weight;
				uf.unionSet(e.vertex1, e.vertex2);
			}
		}

		return mstCost;
	}
}