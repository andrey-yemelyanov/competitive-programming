package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _524_PrimeRingProblem {
	public static void main(String... args){
		String data = "6\n8";
		Scanner scanner = new Scanner(data);
		int caseNum = 0;
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			List<Integer> numbers = new ArrayList<Integer>();
			for(int i = 2; i <= n; i++){
				numbers.add(i);
			}
			List<List<Integer>> solutions = findPrimeRing(numbers);
			System.out.println("Case " + (++caseNum) + ":");
			System.out.println(toString(solutions));
			if(scanner.hasNext()) System.out.println();
		}
		scanner.close();
	}
	
	static String toString(List<List<Integer>> solutions){
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < solutions.size(); i++){
			for(int j = 0; j < solutions.get(i).size(); j++){
				str.append(solutions.get(i).get(j));
				if(j < solutions.get(i).size() - 1){
					str.append(" ");
				}
			}
			if(i < solutions.size() - 1) str.append("\n");
		}
		return str.toString();
	}
	
	static List<List<Integer>> findPrimeRing(List<Integer> numbers){
		List<List<Integer>> solutions = new ArrayList<List<Integer>>();
		List<Integer> primeRing = new ArrayList<Integer>();
		primeRing.add(1);
		findPrimeRing(solutions, numbers, primeRing);
		return solutions;
	}
	
	static void findPrimeRing(List<List<Integer>> solutions, List<Integer> numbers, List<Integer> primeRing){
		if(numbers.size() == 0){
			if(isPrime(primeRing.get(primeRing.size() - 1) + 1)){
				solutions.add(new ArrayList<Integer>(primeRing));
			}
			return;
		}
		for(int i = 0; i < numbers.size(); i++){
			if(isPrime(primeRing.get(primeRing.size() - 1) + numbers.get(i))){
				List<Integer> remainingNumbers = new ArrayList<Integer>(numbers);
				remainingNumbers.remove(i);
				primeRing.add(numbers.get(i));
				findPrimeRing(solutions, remainingNumbers, primeRing);
				primeRing.remove(primeRing.size() - 1);
			}
		}
	}
	
	static boolean isPrime(int n){
		//check if n is a multiple of 2
	    if (n%2==0) return false;
	    //if not, then just check the odds
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0) return false;
	    }
	    return true;
	}
}
