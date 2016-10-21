package helvidios.cp.ch3.completesearch;

import java.math.BigInteger;
import java.util.Scanner;

public class _927_IntegerSequences {
	public static void main(String... args){
		String data = "2\n" + 
				"4 3 0 0 0 23\r\n" + 
				"25\r\n" + 
				"100\r\n" + 
				"1 0 1\r\n" + 
				"1\n"
				+ "6";
		Scanner scanner = new Scanner(data);
		/*System.out.println(findKthElement(100, 25, new int[]{3,0,0,0,23}));
		System.out.println(findKthElement(21, 1, new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}));*/
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int degree = scanner.nextInt();
			int[] polynomial = new int[degree + 1];
			for(int i = 0; i < polynomial.length; i++){
				polynomial[i] = scanner.nextInt();
			}
			int d = scanner.nextInt();
			int k = scanner.nextInt();
			System.out.println(findKthElement(k, d, polynomial));
		}
		scanner.close();
	}
	
	public static BigInteger findKthElement(int k, int d, int[] polynomial){
		// find lowest m such that d * SUM(1,2,3,...,m) >= k
		int m = 1;
		// upper bound for m is where SUM(1,2,3,...,m) = round_up(k_max/d_min)
		// k_max = 1000000, d_min = 1 => m = 1414
		final int upperBound = 1414;
		for(;m <= upperBound; m++){
			if(d * sum(m) >= k) break;
		}
		return evalPolynomial(polynomial, m);
	}
	
	private static BigInteger evalPolynomial(int[] polynomial, int n){
		BigInteger result = new BigInteger("0");
		for(int coefficient = 0; coefficient < polynomial.length; coefficient++){
			result = result.add(new BigInteger(Integer.toString(polynomial[coefficient]))
				.multiply(new BigInteger(Integer.toString(n)).pow(coefficient)));
		}
		return result;
	}
	
	private static int sum(int n){
		return (n * (1 + n)) / 2;
	}
}
