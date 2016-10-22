package helvidios.cp.ch2.ownlibs.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _11503_VirtualFriends {
	/**
 * Represents a disjoint-set data structure (also known as union-find data structure)
 * that keeps track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets.
 */
public static class UnionFindStructure {
	private int[] p;
	private int[] rank;
	private int[] size;
	private int nDisjointSets;
	
	public UnionFindStructure(int n){
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

	public static void main(String... args){
		String data = "2\r\n" + 
				"3\r\n" + 
				"Fred Barney\r\n" + 
				"Barney Betty\r\n" + 
				"Betty Wilma\n" +
				"3\r\n" + 
				"Fred Barney\r\n" + 
				"Barney Betty\r\n" + 
				"Betty Wilma";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int nFriendships = scanner.nextInt();
			String[][] friendships = new String[nFriendships][2];
			for(int i = 0; i < nFriendships; i++){
				friendships[i][0] = scanner.next();
				friendships[i][1] = scanner.next();
			}
			int[] sizes = formFriendships(friendships);
			StringBuilder builder = new StringBuilder();
			for(int size : sizes){
				builder.append(size);
				builder.append("\n");
			}
			System.out.print(builder.toString());
		}
		scanner.close();
	}
	
	public static int[] formFriendships(String[][] friendships){
		int[] sizes = new int[friendships.length];
		Map<String, Integer> map = mapNamesToIds(friendships);
		UnionFindStructure uf = new UnionFindStructure(map.size());
		for(int i = 0; i < friendships.length; i++){
			String friend1 = friendships[i][0];
			String friend2 = friendships[i][1];
			uf.unionSet(map.get(friend1), map.get(friend2));
			sizes[i] = uf.sizeOfSet(map.get(friend1));
		}
		return sizes;
	}
	
	private static Map<String, Integer> mapNamesToIds(String[][] friendships){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int id = 0;
		for(String[] friendship : friendships){
			String friend1 = friendship[0];
			String friend2 = friendship[1];
			if(!map.containsKey(friend1)){
				map.put(friend1, id++);
			}
			if(!map.containsKey(friend2)){
				map.put(friend2, id++);
			}
		}
		return map;
	}
}
