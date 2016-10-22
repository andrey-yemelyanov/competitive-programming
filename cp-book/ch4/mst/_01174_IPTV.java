package helvidios.cp.ch4.mst;

import java.util.*;
import static java.lang.Math.*;

public class _01174_IPTV{
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
		String data = "1\r\n\r\n4\r\n5\r\nlisbon london 6\r\nlisbon paris 5\r\nlondon paris 1\r\nlondon berlin 2\r\nparis berlin 10\r\n";
		Scanner s = new Scanner(data);
		int nTests = s.nextInt();
		for(int i = 0; i < nTests; i++){
			int V = s.nextInt();
			int E = s.nextInt();
			Map<String, Integer> map = new HashMap<>();
			List<Edge> edgeList = new ArrayList<>();
			int vertexId = 0;
			for(int j = 0; j < E; j++){
				String v1 = s.next();
				String v2 = s.next();
				int weight = s.nextInt();
				if(!map.containsKey(v1)){
					map.put(v1, vertexId++);
				}
				if(!map.containsKey(v2)){
					map.put(v2, vertexId++);
				}
				edgeList.add(new Edge(map.get(v1), map.get(v2), weight));		
			}
			System.out.println(mst(edgeList));
			if(i < nTests - 1) System.out.println();
		}
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