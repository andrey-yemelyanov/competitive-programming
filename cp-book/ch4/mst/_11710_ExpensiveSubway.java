package helvidios.cp.ch4.mst;

import java.util.*;
import static java.lang.Math.*;

public class _11710_ExpensiveSubway{
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
		String data = "3 3\r\nPicadilly\r\nVictoria\r\nQueensway\r\nPicadilly Victoria 2\r\nQueensway Victoria 10\r\nQueensway Picadilly 20\r\nPicadilly\r\n4 2\r\nPicadilly\r\nVictoria\r\nQueensway\r\nTemple\r\nPicadilly Victoria 2\r\nTemple Queensway 100\r\nTemple\r\n0 0";
		String data2 = "2 0\r\na\r\nb\r\na\r\n0 0";
		String data3 = "1 0\r\na\r\na\r\n2 0\r\na\r\nb\r\na\r\n0 0";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int nStations = s.nextInt(); int nConnections = s.nextInt();
			if(nStations == 0 && nConnections == 0) break;
			Map<String, Integer> map = new HashMap<>();
			int stationId = 0;
			for(int i = 0; i < nStations; i++){
				map.put(s.next(), stationId++);
			}
			//System.out.println(map);
			List<Edge> edgeList = new ArrayList<>();
			for(int i = 0; i < nConnections; i++){
				edgeList.add(new Edge(map.get(s.next()), map.get(s.next()), s.nextInt()));
			}
			int minTravelCost = minTravelCost(edgeList, nStations);
			if(minTravelCost == IMPOSSIBLE) System.out.println("Impossible");
			else System.out.println(minTravelCost);
			s.next();
		}
	}

	static final int IMPOSSIBLE = -1;
	static int minTravelCost(List<Edge> edgeList, int nStations){
		int[] mst = mst(edgeList, nStations);
		if(mst[1] == nStations - 1) return mst[0];
		return IMPOSSIBLE;
	}

	static int[] mst(List<Edge> edgeList, int nStations){
		Collections.sort(edgeList, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
				return Integer.compare(e1.weight, e2.weight);
			}
		});

		int mstCost = 0; int mstSize = 0;
		UnionFind uf = new UnionFind(nStations);
		for(Edge e : edgeList){
			if(!uf.isSameSet(e.vertex1, e.vertex2)){
				mstCost += e.weight;
				mstSize++;
				uf.unionSet(e.vertex1, e.vertex2);
			}
		}

		return new int[]{mstCost, mstSize};
	}
}