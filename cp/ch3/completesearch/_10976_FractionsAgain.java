package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _10976_FractionsAgain {
	static class Pair{
		public long x;
		public long y;
		public Pair(long x, long y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String... args){
		String data = "2\n12\n10000";
		Scanner scanner = new Scanner(data);
		StringBuilder builder = new StringBuilder();
		while(scanner.hasNextInt()){
			int k = scanner.nextInt();
			builder.append(printSolutions(solve(k), k));
		}
		System.out.print(builder.toString());
		scanner.close();
	}
	
	public static String printSolutions(List<Pair> solutions, int k){
		StringBuilder builder = new StringBuilder();
		builder.append(solutions.size());
		builder.append("\n");
		for(Pair solution : solutions){
			builder.append(String.format("1/%1$d = 1/%2$d + 1/%3$d\n", k, solution.x, solution.y));
		}
		return builder.toString();
	}
	
	public static List<Pair> solve(int k){
		List<Pair> solutions = new ArrayList<Pair>();
		for(int y = k + 1; y <= 2 * k; y++){
			long x = k * y / (y - k);
			if(x * (y - k) == k * y) solutions.add(new Pair(x, y));
		}
		return solutions;
	}
}
