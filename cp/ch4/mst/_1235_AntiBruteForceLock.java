package helvidios.cp.ch4.mst;

import java.util.*;
import static java.lang.Math.*;

public class _1235_AntiBruteForceLock{
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
		String data = "4\r\n2 1155 2211\r\n3 1111 1155 5511\r\n3 1234 5678 9090\r\n4 2145 0213 9113 8113";
		String data2 = "1\n2 0001 0009";
		String data3 = "1\n1 0000";
		Scanner s = new Scanner(data);
		int nTests = s.nextInt();
		boolean zeroKeyPresent = false;
		for(int i = 0; i < nTests; i++){
			int V = s.nextInt();
			List<Integer> vertices = new ArrayList<>();
			for(int j = 0; j < V; j++){
				vertices.add(s.nextInt());
			}
			boolean jumpToZeroesAllowed = false;
			if(!vertices.contains(0)) {
				vertices.add(0);
			}else{
				jumpToZeroesAllowed = true;
			}
			System.out.println(mst(buildEdgeList(vertices), jumpToZeroesAllowed, vertices));
		}
	}

	static List<Edge> buildEdgeList(List<Integer> vertices){
		List<Edge> edgeList = new ArrayList<>();
		for(int i = 0; i < vertices.size(); i++){
			for(int j = i + 1; j < vertices.size(); j++){
				edgeList.add(new Edge(i, j, nRolls(vertices.get(i), vertices.get(j))));
			}
		}
		/*for(Edge e : edgeList){
			System.out.printf("%d-%d=%d\n", vertices.get(e.vertex1), vertices.get(e.vertex2), e.weight);
		}*/
		return edgeList;
	}

	static int nRolls(int combination1, int combination2){
		int nRolls = 0;

		int digit1 = 0, digit2 = 0;
		
		// get number of rolls for the 1st digit (from the left)
		nRolls += countRolls(combination1 / 1000, combination2 / 1000);
		// get number of rolls for the 2nd digit (from the left)
		nRolls += countRolls((combination1 % 1000) / 100, (combination2 % 1000) / 100);
		// get number of rolls for the 3rd digit (from the left)
		nRolls += countRolls((combination1 % 100) / 10, (combination2 % 100) / 10);
		// get number of rolls for the 4th digit (from the left)
		nRolls += countRolls(combination1 % 10, combination2 % 10);

		return nRolls;
	}

	static int countRolls(int d1, int d2){
		return min(abs(d1 - d2), (10 - max(d1, d2) + min(d1, d2)));
	}

	static int nVertices(List<Edge> edgeList){
		Set<Integer> vertices = new HashSet<>();
		for(Edge e : edgeList){
			vertices.add(e.vertex1);
			vertices.add(e.vertex2);
		}
		return vertices.size();
	}

	static int mst(List<Edge> edgeList, boolean jumpToZeroesAllowed, List<Integer> vertices){
		Collections.sort(edgeList, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
				return Integer.compare(e1.weight, e2.weight);
			}
		});

		int mstCost = 0;
		UnionFind uf = new UnionFind(nVertices(edgeList));
		boolean edgeWithZeroesAdded = false;
		for(Edge e : edgeList){
			if(!uf.isSameSet(e.vertex1, e.vertex2)){
				if(vertices.get(e.vertex1) != 0 && vertices.get(e.vertex2) != 0){
					mstCost += e.weight;
					uf.unionSet(e.vertex1, e.vertex2);
				}
				else if(jumpToZeroesAllowed || !edgeWithZeroesAdded){
					mstCost += e.weight;
					uf.unionSet(e.vertex1, e.vertex2);
					edgeWithZeroesAdded = true;
				}
			}
		}

		return mstCost;
	}
}