package helvidios.cp.ch4.mst;

import java.util.*;
import static java.lang.Math.*;

public class _11228_TransportationSystem{
	private static class Edge{
		public int vertex1;
		public int vertex2;
		public double weight;
		public Edge(int vertex1, int vertex2, double weight){
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.weight = weight;
		}
	}
	private static class Point{
		public int x;
		public int y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
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
		String data = "3\r\n3 100\r\n0 0\r\n1 0\r\n2 0\r\n3 1\r\n0 0\r\n100 0\r\n200 0\r\n4 20\r\n0 0\r\n40 30\r\n30 30\r\n10 10";
		String data2 = "1\r\n4 10\r\n0 10\r\n0 20\r\n0 30\r\n0 40";
		Scanner s = new Scanner(data2);
		int nTests = s.nextInt();
		for(int i = 1; i <= nTests; i++){
			int V = s.nextInt(); int threshold = s.nextInt();
			Point[] cities = new Point[V];
			for(int j = 0; j < cities.length; j++){
				cities[j] = new Point(s.nextInt(), s.nextInt());
			}
			long[] ans = mst(buildEdgeList(cities), threshold);
			System.out.printf("Case #%d: %d %d %d\n", i, ans[2], ans[1], ans[0]);
		}
	}

	static List<Edge> buildEdgeList(Point[] cities){
		List<Edge> edgeList = new ArrayList<>();
		for(int i = 0; i < cities.length; i++){
			for(int j = i + 1; j < cities.length; j++){
				edgeList.add(new Edge(i, j, distance(cities[i], cities[j])));
			}
		}
		return edgeList;
	}

	static int nVertices(List<Edge> edgeList){
		Set<Integer> vertices = new HashSet<>();
		for(Edge e : edgeList){
			vertices.add(e.vertex1);
			vertices.add(e.vertex2);
		}
		return vertices.size();
	}

	static long[] mst(List<Edge> edgeList, int threshold){
		Collections.sort(edgeList, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
				return Double.compare(e1.weight, e2.weight);
			}
		});

		UnionFind uf = new UnionFind(nVertices(edgeList));
		double railroad = 0, road = 0; int nStates = 1;
		for(Edge e : edgeList){
			if(!uf.isSameSet(e.vertex1, e.vertex2)){
				if(e.weight > threshold){
					railroad += e.weight;
					nStates++;
				}else{
					road += e.weight;
				}
				uf.unionSet(e.vertex1, e.vertex2);
			}
		}

		return new long[]{round(railroad), round(road), nStates};
	}

	static double distance(Point p1, Point p2){
		return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
	}
}