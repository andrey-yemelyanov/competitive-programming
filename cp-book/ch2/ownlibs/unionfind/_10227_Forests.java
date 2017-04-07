import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10227 Forests
Problem url: https://uva.onlinejudge.org/external/102/10227.pdf
Author: Andrey Yemelyanov
*/
public class _10227_Forests {
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
    int nTests = s.nextInt(); s.nextLine();
    while(nTests-- > 0){
      int P = s.nextInt(); int T = s.nextInt(); s.nextLine();
      //System.out.println(P + " " + T);
      Map<Integer, Set<Integer>> opinions = new HashMap<>();
      for(int person = 0; person < P; person++){
        opinions.put(person, new HashSet<>());
      }
      while(s.hasNext()){
        String line = s.nextLine();
        if(line.isEmpty()) break;
        String[] tokens = line.split("\\s+");
        int person = Integer.parseInt(tokens[0]) - 1;
        int tree = Integer.parseInt(tokens[1]);
        opinions.get(person).add(tree);
      }
      System.out.println(getDiffOpinions(opinions, P));
      if(nTests > 0) System.out.println();
    }
  }

  static int getDiffOpinions(Map<Integer, Set<Integer>> opinions, int nPeople){
    //System.out.println(opinions);
    UnionFind uf = new UnionFind(nPeople);
    for(int person1 = 0; person1 < nPeople; person1++){
      for(int person2 = person1 + 1; person2 < nPeople; person2++){
        if(opinions.get(person1).equals(opinions.get(person2))){
          uf.unionSet(person1, person2);
        }
      }
    }
    return uf.nDisjointSets();
  }
}
