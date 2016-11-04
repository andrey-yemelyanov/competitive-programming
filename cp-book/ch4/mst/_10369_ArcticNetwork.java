import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10369 Arctic Network
Problem url: https://uva.onlinejudge.org/external/103/10369.pdf
Author: Andrey Yemelyanov
*/
public class _10369_ArcticNetwork {
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
    int N = s.nextInt();
    while(N-- > 0){
      int S = s.nextInt(); int P = s.nextInt();
      List<Integer[]> outposts = new ArrayList<>();
      for(int i = 0; i < P; i++){
        outposts.add(new Integer[] {s.nextInt(), s.nextInt()});
      }
      List<Edge> edgeList = new ArrayList<>();
      for(int i = 0; i < outposts.size(); i++){
        for(int j = i + 1; j < outposts.size(); j++){
          edgeList.add(new Edge(i, j, distance(outposts.get(i)[0], outposts.get(i)[1], outposts.get(j)[0], outposts.get(j)[1])));
        }
      }
      System.out.printf("%.2f\n", msf(edgeList, P, S));
    }
  }

  static double distance(int x1, int y1, int x2, int y2){
    return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
  }

  static double msf(List<Edge> edgeList, int nVertices, int nComponents){
		Collections.sort(edgeList, new Comparator<Edge>(){
			@Override
			public int compare(Edge e1, Edge e2){
				return Double.compare(e1.weight, e2.weight);
			}
		});

		UnionFind uf = new UnionFind(nVertices);
    double minCost = 0;
		for(Edge e : edgeList){
			if(!uf.isSameSet(e.vertex1, e.vertex2)){
				uf.unionSet(e.vertex1, e.vertex2);
        minCost = max(minCost, e.weight);
        if(uf.nDisjointSets() == nComponents) break;
			}
		}

		return minCost;
	}
}
