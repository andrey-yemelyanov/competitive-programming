import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10048 Audiophobia
Problem url: https://uva.onlinejudge.org/external/100/10048.pdf
Author: Andrey Yemelyanov
*/
public class _10048_Audiophobia {
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
    Scanner s = new Scanner(System.in);
    int caseNum = 1;
    boolean firstOut = true;
    while(s.hasNext()){
      int C = s.nextInt(); int S = s.nextInt(); int Q = s.nextInt();
      if(C == 0 && S == 0 && Q == 0) break;
      List<Edge> edgeList = new ArrayList<>();
      for(int i = 0; i < S; i++){
        edgeList.add(new Edge(s.nextInt() - 1, s.nextInt() - 1, s.nextInt()));
      }
      List<Integer[]> queries = new ArrayList<>();
      for(int i = 0; i < Q; i++){
        queries.add(new Integer[] {s.nextInt() - 1, s.nextInt() - 1});
      }
      if(!firstOut) System.out.println();
      else firstOut = false;
      System.out.printf("Case #%d\n", caseNum++);
      List<Integer> result = minimax(edgeList, C, queries);
      result.forEach(i -> {
        if(i != INF) System.out.println(i); else System.out.println("no path");
      });
    }
  }

  static List<Integer> minimax(List<Edge> edgeList, int nVertices, List<Integer[]> queries){
    List<Integer> result = new ArrayList<>();
    Map<Integer, List<Edge>> mst = mst(edgeList, nVertices);
    for(Integer[] q : queries){
      int from = q[0]; int to = q[1];
      result.add(minimax(mst, from, to));
    }
    return result;
  }

  static final int INF = Integer.MAX_VALUE;
  static int minimax(Map<Integer, List<Edge>> mst, int from, int to){
    if(!mst.containsKey(from)) return INF;
    return minimax(mst, from, to, -1);
  }

  static int minimax(Map<Integer, List<Edge>> mst, int from, int to, int previous){
    if(from == to) return 0;
    int minMaxEdgeWeight = INF;
    for(Edge e : mst.get(from)){
      if(e.vertex2 != previous){
        minMaxEdgeWeight = min(minMaxEdgeWeight, max(e.weight, minimax(mst, e.vertex2, to, from)));
      }
    }
    return minMaxEdgeWeight;
  }

  static Map<Integer, List<Edge>> mst(List<Edge> edgeList, int nVertices){
		Collections.sort(edgeList, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
				return Integer.compare(e1.weight, e2.weight);
			}
		});

		UnionFind uf = new UnionFind(nVertices);
    Map<Integer, List<Edge>> mst = new HashMap<>();
		for(Edge e : edgeList){
			if(!uf.isSameSet(e.vertex1, e.vertex2)){
				uf.unionSet(e.vertex1, e.vertex2);
        mst.putIfAbsent(e.vertex1, new ArrayList<>());
        mst.putIfAbsent(e.vertex2, new ArrayList<>());
        mst.get(e.vertex1).add(e);
        mst.get(e.vertex2).add(new Edge(e.vertex2, e.vertex1, e.weight));
			}
		}

		return mst;
	}
}
