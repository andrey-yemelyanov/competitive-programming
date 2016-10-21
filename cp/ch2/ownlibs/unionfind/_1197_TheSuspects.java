package helvidios.cp.ch2.ownlibs.unionfind;

import java.util.Scanner;

public class _1197_TheSuspects {
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
		String data = "100 4\r\n" + 
				"2 1 2\r\n" + 
				"5 10 13 11 12 14\r\n" + 
				"2 0 1\r\n" + 
				"2 99 2\r\n" + 
				"200 2\r\n" + 
				"1 5\r\n" + 
				"5 1 2 3 4 5\r\n" + 
				"1 0\r\n" + 
				"0 0\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(true){
			int nStudents = scanner.nextInt();
			int nGroups = scanner.nextInt();
			if(nStudents == 0 && nGroups == 0) break;
			int[][] groups = new int[nGroups][];
			for(int i = 0; i < nGroups; i++){
				int groupSize = scanner.nextInt();
				groups[i] = new int[groupSize];
				for(int j = 0; j < groupSize; j++){
					groups[i][j] = scanner.nextInt();
				}
			}
			
			System.out.println(nSuspects(nStudents, groups));
		}
		scanner.close();
	}
	
	public static int nSuspects(int nStudents, int[][] groups){
		UnionFindStructure uf = new UnionFindStructure(nStudents);
		for(int[] group : groups){
			int firstStudent = group[0];
			for(int i = 1; i < group.length; i++){
				uf.unionSet(firstStudent, group[i]);
			}
		}
		
		return uf.sizeOfSet(0);
	}
}
