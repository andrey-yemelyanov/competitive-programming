package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _10503_DominoesSolitaire {
	public static void main(String... args){
		String data = "3\r\n" + 
				"4\r\n" + 
				"0 1\r\n" + 
				"3 4\r\n" + 
				"2 1\r\n" + 
				"5 6\r\n" + 
				"2 2\r\n" + 
				"3 2\r\n" + 
				"2\r\n" + 
				"4\r\n" + 
				"0 1\r\n" + 
				"3 4\r\n" + 
				"1 4\r\n" + 
				"4 4\r\n" + 
				"3 2\r\n" + 
				"5 6\r\n" + 
				"0\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			if(n == 0) break;
			int m = scanner.nextInt();
			Domino leftmost = new Domino(scanner.nextInt(), scanner.nextInt());
			Domino rightmost = new Domino(scanner.nextInt(), scanner.nextInt());
			List<Domino> dominoes = new ArrayList<Domino>();
			for(int i = 0; i < m; i++){
				dominoes.add(new Domino(scanner.nextInt(), scanner.nextInt()));
			}
			if(solutionsExists(leftmost, rightmost, n, dominoes)) System.out.println("YES");
			else System.out.println("NO");
		}
		scanner.close();
	}
	
	static class Domino{
		public int d1;
		public int d2;
		public Domino(int d1, int d2){
			this.d1 = d1;
			this.d2 = d2;
		}
		public Domino rotate(){
			return new Domino(d2, d1);
		}
	}
	
	static boolean ruleSatisfied(Domino newDomino, Domino dominoToTheLeft){
		return newDomino.d1 == dominoToTheLeft.d2 || newDomino.d2 == dominoToTheLeft.d2;
	}
	
	static boolean solutionExists(Domino[] solution, List<Domino> dominoes, int position){
		if(position == solution.length - 1){
			return solution[position - 1].d2 == solution[position].d1;
		}
		for(int i = 0; i < dominoes.size(); i++){
			Domino newDomino = dominoes.get(i);
			Domino prevDomino = solution[position - 1];
			if(ruleSatisfied(newDomino, prevDomino)){
				if(prevDomino.d2 != newDomino.d1){
					newDomino = newDomino.rotate();
				}
				solution[position] = newDomino;
				List<Domino> remainingDominoes = new ArrayList<Domino>(dominoes);
				remainingDominoes.remove(i);
				if(solutionExists(solution, remainingDominoes, position + 1)) return true;
			}
		}
		return false;
	}
	
	static boolean solutionsExists(Domino leftmost, Domino rightmost, int n, List<Domino> dominoes){
		Domino[] solution = new Domino[n + 2];
		solution[0] = leftmost;
		solution[solution.length - 1] = rightmost;
		return solutionExists(solution, dominoes, 1);
	}
}
