import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10600 ACM Contest and Blackout
Problem url: https://uva.onlinejudge.org/external/106/10600.pdf
Author: Andrey Yemelyanov
*/
public class _10600_AcmContestAndBlackout {
  private static class Edge{
		public int vertex1;
		public int vertex2;
		public int weight;
		public Edge(int vertex1, int vertex2, int weight){
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.weight = weight;
		}
    @Override
    public boolean equals(Object obj){
      if(obj == null) return false;
      Edge other = (Edge)obj;
      return vertex1 == other.vertex1 && vertex2 == other.vertex2 && weight == other.weight;
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
    Scanner s = new Scanner(System.in);
    int T = s.nextInt();
    while(T-- > 0){
      int N = s.nextInt(); int M = s.nextInt();
      List<Edge> edgeList = new ArrayList<>();
      for(int i = 0; i < M; i++){
        edgeList.add(new Edge(s.nextInt() - 1, s.nextInt() - 1, s.nextInt()));
      }
      int[] msts = mst(edgeList, N);
      System.out.printf("%d %d\n", msts[0], msts[1]);
    }
  }

  static int[] mst(List<Edge> edgeList, int nVertices){
    Collections.sort(edgeList, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
				return Double.compare(e1.weight, e2.weight);
			}
		});

    List<Edge> mst = mst(edgeList, nVertices, null);
    int bestMst = mst.stream()
                     .mapToInt(edge -> edge.weight)
                     .sum();

    int secondBestMst = Integer.MAX_VALUE;
    for(Edge e : mst){
      List<Edge> mst2 = mst(edgeList, nVertices, e);
      if(mst2 != null){
        secondBestMst = min(secondBestMst, mst2.stream()
                                               .mapToInt(edge -> edge.weight)
                                               .sum());
      }
    }

    return new int[] {bestMst, secondBestMst};
  }

  static List<Edge> mst(List<Edge> edgeList, int nVertices, Edge excludedEdge){
		UnionFind uf = new UnionFind(nVertices);
    List<Edge> mst = new ArrayList<>();
		for(Edge e : edgeList){
			if(!e.equals(excludedEdge) && !uf.isSameSet(e.vertex1, e.vertex2)){
				uf.unionSet(e.vertex1, e.vertex2);
        mst.add(e);
			}
		}
    if(uf.nDisjointSets > 1) return null;
		return mst;
	}
}
