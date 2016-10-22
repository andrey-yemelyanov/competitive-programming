package helvidios.cp.ch2.lineards.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class _10858_UniqueFactorization {
	public static void main(String... args){
		String data = "1\n20\n2000000\n0";
		Scanner scanner = new Scanner(data);
		StringBuilder builder = new StringBuilder();
		while(scanner.hasNext()){
			int number = scanner.nextInt();
			if(number == 0) break;
			List<List<Integer>> list = findFactorizations(number);
			builder.append(list.size());
			builder.append("\n");
			if(list.size() > 0) {
				builder.append(printFactorizations(list));
				builder.append("\n");
			}
		}
		System.out.print(builder.toString());
		scanner.close();
	}
	
	static String printFactorizations(List<List<Integer>> list){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < list.size(); i++){
			for(int j = 0; j < list.get(i).size(); j++){
				builder.append(list.get(i).get(j));
				if(j < list.get(i).size() - 1) builder.append(" ");
			}
			if(i < list.size() - 1) builder.append("\n");
		}
		return builder.toString();
	}
	
	public static List<List<Integer>> findFactorizations(int number){
		List<List<Integer>> factorizations = new ArrayList<List<Integer>>();
		Stack<Integer> factorization = new Stack<Integer>();
		findFactorizations(factorizations, factorization, number, 2);
		factorizations.remove(factorizations.size() - 1);
		return factorizations;
	}
	
	public static void findFactorizations(
			List<List<Integer>> factorizations,
			Stack<Integer> factorization,
			int number,
			int div){
		for(int i = div; i <= Math.sqrt(number); i++){
			if(number % i == 0){
				factorization.push(i);
				findFactorizations(factorizations, factorization, number / i, i);
				factorization.pop();
			}
		}
		
		factorization.push(number);
		factorizations.add(new ArrayList<Integer>(factorization));
		factorization.pop();
	}
}
