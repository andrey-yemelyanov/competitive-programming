package helvidios.cp.ch2.ownlibs.unionfind;

/**
 * Represents a disjoint-set data structure (also known as union-find data structure)
 * that keeps track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets.
 */
public class UnionFindStructure {
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
