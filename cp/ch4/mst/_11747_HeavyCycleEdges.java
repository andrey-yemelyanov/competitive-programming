package helvidios.cp.ch4.mst;

import java.util.*;
import static java.lang.Math.*;

public class _11747_HeavyCycleEdges{
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
		String data = "3 3\r\n0 1 1\r\n1 2 2\r\n2 0 3\r\n4 5\r\n0 1 1\r\n1 2 2\r\n2 3 3\r\n3 1 4\r\n0 2 0\r\n3 1\r\n0 1 1\r\n0 0\r\n";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int n = s.nextInt(); int m = s.nextInt();
			if(n == 0 && m == 0) break;
			List<Edge> edgeList = new ArrayList<>();
			for(int i = 0; i < m; i++){
				edgeList.add(new Edge(s.nextInt(), s.nextInt(), s.nextInt()));
			}
			List<Integer> heaviestCycleEdges = new ArrayList<>();
			if(mst(edgeList, n, heaviestCycleEdges)){
				System.out.println(toString(heaviestCycleEdges));
			}else System.out.println("forest");
		}
	}

	static String toString(List<Integer> list){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < list.size(); i++){
			sb.append(list.get(i));
			if(i < list.size() - 1) sb.append(" ");
		}
		return sb.toString();
	}

	static boolean mst(List<Edge> edgeList, int nVertices, List<Integer> heaviestCycleEdges){
		Collections.sort(edgeList, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
				return Integer.compare(e1.weight, e2.weight);
			}
		});

		UnionFind uf = new UnionFind(nVertices);
		for(Edge e : edgeList){
			if(!uf.isSameSet(e.vertex1, e.vertex2)){
				uf.unionSet(e.vertex1, e.vertex2);
			}else{
				heaviestCycleEdges.add(e.weight);
			}
		}

		return heaviestCycleEdges.size() > 0;
	}
}