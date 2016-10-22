package helvidios.cp.ch2.ownlibs.unionfind;

import java.util.Scanner;

public class _793_NetworkConnections {
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
		String data = "2\r\n\n" + 
				"10\r\n" + 
				"c 1 5\r\n" + 
				"c 2 7\r\n" + 
				"q 7 1\r\n" + 
				"c 3 9\r\n" + 
				"q 9 6\r\n" + 
				"c 2 5\r\n" + 
				"q 7 5\r\n\n" + 
				"1\r\n" + 
				"q 1 1\r\n" + 
				"c 1 1\r\n" + 
				"q 1 1";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int nComputers = scanner.nextInt();
			UnionFindStructure uf = new UnionFindStructure(nComputers);
			int successes = 0, failures = 0;
			while(scanner.hasNext() && !scanner.hasNextInt()){
				String command = scanner.next();
				int pc1 = scanner.nextInt() - 1;
				int pc2 = scanner.nextInt() - 1;
				if(command.equals("c")){
					connect(uf, pc1, pc2);
				}else{
					if(isConnected(uf, pc1, pc2)){
						successes++;
					}else failures++;
				}
			}
			System.out.println(successes + "," + failures);
			if(nTestCases != 0) System.out.println();
		}
		scanner.close();
	}
	
	public static void connect(UnionFindStructure uf, int pc1, int pc2){
		uf.unionSet(pc1, pc2);
	}
	
	public static boolean isConnected(UnionFindStructure uf, int pc1, int pc2){
		return uf.isSameSet(pc1, pc2);
	}
}
