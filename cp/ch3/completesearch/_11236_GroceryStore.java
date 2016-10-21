package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class _11236_GroceryStore {
	public static void main(String... args){
		StringBuilder out = new StringBuilder();
		List<double[]> solutions = solve();
		for(double[] solution : solutions){
			out.append(String.format(Locale.US, "%1$.2f %2$.2f %3$.2f %4$.2f\n", 
					solution[0],
					solution[1],
					solution[2],
					solution[3]));
		}
		System.out.print(out.toString());
		//System.out.println(solutions.size());
	}
	
	public static List<double[]> solve(){
		final int maxAmount = 2000;
		final int M = 1000000;
		List<double[]> solutions = new ArrayList<double[]>();
		for(int a = 2; a < maxAmount; a++){
			for(int b = a; b < maxAmount - a; b++){
				for(int c = b; c < maxAmount - (a + b); c++){
					if(a * b * c != M){
						int d = M * (a + b + c) / (a * b * c - M);
						if(d >= c && (a * b * c * d) / M == a + b + c + d && a + b + c + d <= maxAmount) {
							solutions.add(new double[]{a/100.0, b/100.0, c/100.0, d/100.0});
						}
					}
				}
			}
		}
		return solutions;
	}
}
