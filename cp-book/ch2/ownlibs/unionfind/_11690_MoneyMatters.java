import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11690 Money Matters
Problem url: https://uva.onlinejudge.org/external/116/11690.pdf
Author: Andrey Yemelyanov
*/
public class _11690_MoneyMatters {
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
      int n = s.nextInt(); int m = s.nextInt();
      Map<Integer, Integer> map = new HashMap<>();
      for(int i = 0; i < n; i++){
        map.put(i, s.nextInt());
      }
      UnionFind uf = new UnionFind(n);
      for(int i = 0; i < m; i++){
        uf.unionSet(s.nextInt(), s.nextInt());
      }
      Map<Integer, List<Integer>> groups = new HashMap<>();
      for(int i = 0; i < n; i++){
        int groupId = uf.findSet(i);
        groups.putIfAbsent(groupId, new ArrayList<>());
        groups.get(groupId).add(i);
      }
      boolean possible = true;
      for(int groupId : groups.keySet()){
        if(groups.get(groupId).stream().mapToInt(i -> map.get(i)).sum() != 0) possible = false;
      }
      if(possible) System.out.println("POSSIBLE");
      else System.out.println("IMPOSSIBLE");
    }
  }
}
