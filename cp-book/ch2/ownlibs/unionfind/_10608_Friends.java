import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10608 Friends
Problem url: https://uva.onlinejudge.org/external/106/10608.pdf
Author: Andrey Yemelyanov
*/
public class _10608_Friends {
  static class UnionFind {
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
  	public int findSet(int i){
  		if(p[i] != i){
  			p[i] = findSet(p[i]);
  		}
  		return p[i];
  	}
  	public boolean isSameSet(int i, int j){
  		return findSet(i) == findSet(j);
  	}
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
  	public int nDisjointSets(){
  		return nDisjointSets;
  	}
  	public int sizeOfSet(int i){
  		return size[findSet(i)];
  	}
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int N = s.nextInt(); int M = s.nextInt();
      UnionFind uf = new UnionFind(N);
      for(int i = 0; i < M; i++){
        uf.unionSet(s.nextInt() - 1, s.nextInt() - 1);
      }
      int largestSetSize = 0;
      for(int i = 0; i < N; i++){
        largestSetSize = max(largestSetSize, uf.sizeOfSet(i));
      }
      System.out.println(largestSetSize);
    }
  }
}
