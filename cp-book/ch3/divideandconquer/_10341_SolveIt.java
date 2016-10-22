package helvidios.cp.ch3.divideandconquer;

import java.util.*;

public class _10341_SolveIt {
	public static void main(String... args){
		String data = "0 0 0 0 -2 1\r\n" + 
				"1 0 0 0 -1 2\r\n" + 
				"1 -1 1 -1 -1 1\r\n" + 
				"";
		String data2 = "0 0 0 0 0 0";
		String data3 = "16 -1 12 -2 -12 4\r\n" + 
				"4 -9 10 -2 -4 8\r\n" + 
				"4 -15 19 0 -5 6\r\n" + 
				"10 -5 20 -2 -11 4\r\n" + 
				"16 -4 18 -7 -2 1\r\n" + 
				"17 0 6 -8 -4 7\r\n" + 
				"20 -3 5 -6 0 2\r\n" + 
				"8 -7 18 -3 -12 10";
		String data4 = "0 0 0 0 -2 1\r\n" + 
				"1 0 0 0 -1 2\r\n" + 
				"1 -1 1 -1 -1 1\r\n" + 
				"0 0 0 0 0 0\r\n" + 
				"1 -20 3 -20 -5 6\r\n" + 
				"2 -20 3 -20 -5 6\r\n" + 
				"3 -20 3 -20 -5 6\r\n" + 
				"4 -20 3 -20 -5 6\r\n" + 
				"5 -20 3 -20 -5 6\r\n" + 
				"6 -20 3 -20 -5 6\r\n" + 
				"3 -4 1 -3 -2 5\r\n" + 
				"6 -11 8 -20 -3 1\r\n" + 
				"4 -4 4 -4 -4 5\r\n" + 
				"17 -6 2 -8 -1 3\r\n" + 
				"16 -1 12 -2 -12 4\r\n" + 
				"4 -9 10 -2 -4 8\r\n" + 
				"4 -15 19 0 -5 6";
		String data5 = "16 -1 9 -11 -14 18\r\n" + 
				"11 -18 11 -7 -6 16\r\n" + 
				"12 -3 1 -4 -18 19\r\n" + 
				"12 -2 10 -3 -8 1\r\n" + 
				"6 -15 11 -19 -7 -13\r\n" + 
				"9 -7 10 0 -4 -1\r\n" + 
				"15 -1 18 -8 -7 5\r\n" + 
				"20 -6 6 -12 -18 -14\r\n" + 
				"5 -5 3 -10 -15 -1\r\n" + 
				"12 -15 18 -9 -4 -19\n"
				+ "0 0 0 0 0 0";
		String data6 = "0 0 0 0 -1 1";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int p = scanner.nextInt();
			int q = scanner.nextInt();
			int r = scanner.nextInt();
			int s = scanner.nextInt();
			int t = scanner.nextInt();
			int u = scanner.nextInt();
			double x = f(0.0, p, q, r, s, t, u);
			double y = f(1.0, p, q, r, s, t, u);
			if(x > 0 && y > 0) System.out.println("No solution");
			else if(x < 0 && y < 0) System.out.println("No solution");
			else if(x == 0) System.out.printf("%.4f\n", 0.0);
			else if(y == 0) System.out.printf("%.4f\n", 1.0);
			else{
				double[] bounds = bounds(p, q, r, s, t, u);
				double root = findRoot(bounds[0], bounds[1], p, q, r, s, t, u);
				System.out.println(String.format(Locale.US, "%.4f", root));
			}
		}
		scanner.close();
	}
	
	static double findRoot(double a, double b, int p, int q, int r, int s, int t, int u){
		final double epsilon = 0.00000001;
		while(true){
			if(Math.abs(a - b) < epsilon) {
				return a;
			}
			double mid = (a + b) / 2.0;
			if(f(mid, p, q, r, s, t, u) > 0){
				b = mid;
			}else{
				a = mid;
			}
		}
	}
	
	static double[] bounds(int p, int q, int r, int s, int t, int u){
		double neg = Double.MIN_VALUE, pos = Double.MIN_VALUE;
		for(double c = 0.0; c <= 1.0; c += 0.0001){
			double f = f(c, p, q, r, s, t, u);
			if(f < 0) {
				neg = c;
			}else if(f > 0){
				pos = c;
			}
			if(neg != Double.MIN_VALUE && pos != Double.MIN_VALUE) break;
		}
		return new double[]{neg, pos};
	}
	
	static double f(double c, int p, int q, int r, int s, int t, int u){
		return p * Math.exp(-c) + q * Math.sin(c) + r * Math.cos(c) + s * Math.tan(c) + t * c * c + u;
	}
}
