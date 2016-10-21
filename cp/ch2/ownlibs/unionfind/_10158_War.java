package helvidios.cp.ch2.ownlibs.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _10158_War {
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
		String data = "10\r\n" + 
				"1 0 1\r\n" + 
				"1 1 2\r\n" + 
				"2 0 5\r\n" + 
				"3 0 2\r\n" + 
				"3 8 9\r\n" + 
				"4 1 5\r\n" + 
				"4 1 2\r\n" + 
				"4 8 9\r\n" + 
				"1 8 9\r\n" + 
				"1 5 2\r\n" + 
				"3 5 2\r\n" + 
				"0 0 0";
		String data2 = "10\r\n" + 
				"4 4 1\r\n" + 
				"1 6 5\r\n" + 
				"4 2 3\r\n" + 
				"3 1 0\r\n" + 
				"2 5 3\r\n" + 
				"1 2 5\r\n" + 
				"2 9 8\r\n" + 
				"1 8 0\r\n" + 
				"4 9 3\r\n" + 
				"2 3 0\r\n" + 
				"4 7 3\r\n" + 
				"2 4 9\r\n" + 
				"1 4 2\r\n" + 
				"2 6 3\r\n" + 
				"1 6 9\r\n" + 
				"1 2 1\r\n" + 
				"1 7 5\r\n" + 
				"2 1 8\r\n" + 
				"1 3 0\r\n" + 
				"1 7 0\r\n" + 
				"1 2 8\r\n" + 
				"3 5 6\r\n" + 
				"4 2 7\r\n" + 
				"2 0 3\r\n" + 
				"1 6 7\r\n" + 
				"2 4 8\r\n" + 
				"4 4 6\r\n" + 
				"1 9 4\r\n" + 
				"4 2 1\r\n" + 
				"4 3 0\n"	+ 
				"0 0 0";
		String data3 = "6\r\n" + 
				"1 0 1\r\n" + 
				"2 1 5\r\n" + 
				"2 3 4\r\n" + 
				"2 2 3\r\n" + 
				"1 1 2\r\n" + 
				"3 3 5\r\n" + 
				"4 3 5\r\n" + 
				"4 0 5\r\n" + 
				"4 1 3\r\n" + 
				"0 0 0";
		Scanner scanner = new Scanner(data2);
		int nPeople = scanner.nextInt();
		UnionFindStructure uf = new UnionFindStructure(nPeople);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		while(true){
			int command = scanner.nextInt();
			int person1 = scanner.nextInt();
			int person2 = scanner.nextInt();
			if(command == 0 && person1 == 0 && person2 == 0) break;
			switch (command) {
				case 1:
					if(!setFriends(uf, map, person1, person2)) System.out.println("-1");
					break;
				case 2:
					if(!setEnemies(uf, map, person1, person2)) System.out.println("-1");
					break;
				case 3:
					System.out.println(areFriends(uf, person1, person2) ? "1" : "0");
					break;
				case 4:
					System.out.println(areEnemies(uf, map, person1, person2) ? "1" : "0");
					break;
			}
		}
		scanner.close();
	}
	
	public static boolean areEnemies(
			UnionFindStructure uf, 
			Map<Integer, Integer> enemies,
			int person1, 
			int person2){
		if(!hasEnemy(uf, enemies, person1) || !hasEnemy(uf, enemies, person2)) return false;
		return enemyOf(uf, enemies, person1) == uf.findSet(person2);
	}
	
	public static boolean areFriends(
			UnionFindStructure uf, 
			int person1, 
			int person2){
		return uf.findSet(person1) == uf.findSet(person2);
	}
	
	public static boolean setEnemies(
			UnionFindStructure uf, 
			Map<Integer, Integer> enemies,
			int person1, 
			int person2){
		if(person1 == person2) return false;
		if(areFriends(uf, person1, person2)) return false;
		if(areEnemies(uf, enemies, person1, person2)) return true;
		
		// if both persons already have enemies, make the new person and the other enemy friends for both persons
		if(hasEnemy(uf, enemies, person1) && hasEnemy(uf, enemies, person2)){
			setFriends(uf, enemies, person1, enemyOf(uf, enemies, person2));
			setFriends(uf, enemies, person2, enemyOf(uf, enemies, person1));
		}else if(hasEnemy(uf, enemies, person1)){
			setFriends(uf, enemies, person2, enemyOf(uf, enemies, person1));
		}else if(hasEnemy(uf, enemies, person2)){
			setFriends(uf, enemies, person1, enemyOf(uf, enemies, person2));
		}
		enemies.put(uf.findSet(person1), uf.findSet(person2));
		enemies.put(uf.findSet(person2), uf.findSet(person1));
		return true;
	}
	
	public static boolean setFriends(
			UnionFindStructure uf, 
			Map<Integer, Integer> enemies,
			int person1, 
			int person2){
		if(areEnemies(uf, enemies, person1, person2)) return false;
		if(areFriends(uf, person1, person2)) return true;
		
		// get a combined list of enemies
		List<Integer> combinedEnemies = new ArrayList<Integer>();
		if(hasEnemy(uf, enemies, person1)) combinedEnemies.add(enemyOf(uf, enemies, person1));
		if(hasEnemy(uf, enemies, person2)) combinedEnemies.add(enemyOf(uf, enemies, person2));
		
		// unite person1 and person2 into the same friendly set
		uf.unionSet(person1, person2);
		
		// if there are two combined enemies, make them friends by uniting them into the same set
		if(combinedEnemies.size() == 2){
			uf.unionSet(combinedEnemies.get(0), combinedEnemies.get(1));
			enemies.put(uf.findSet(combinedEnemies.get(0)), uf.findSet(person1));
			enemies.put(uf.findSet(person1), uf.findSet(combinedEnemies.get(0)));
		}
		// if only one enemy, set the new set representative to point to this enemy
		else if(combinedEnemies.size() == 1){
			enemies.put(uf.findSet(person1), uf.findSet(combinedEnemies.get(0)));
		}
		return true;
	}
	
	private static boolean hasEnemy(
			UnionFindStructure uf, 
			Map<Integer, Integer> enemies, 
			int person){
		return enemies.containsKey(uf.findSet(person));
	}
	
	private static int enemyOf(
			UnionFindStructure uf, 
			Map<Integer, Integer> enemies, 
			int person){
		return enemies.get(uf.findSet(person));
	}
}
