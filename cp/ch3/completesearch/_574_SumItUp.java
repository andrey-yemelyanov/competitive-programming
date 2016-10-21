package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _574_SumItUp {
	public static void main(String... args){
		String data = "4 6 4 3 2 2 1 1\r\n" + 
				"5 3 2 1 1\r\n" + 
				"400 12 50 50 50 50 50 50 25 25 25 25 25 25\r\n" +
				"20 10 1 2 3 4 5 6 7 8 9 10\n" +
				"0 0";
		String data2 = "12 6 4 4 4 4 3 1\r\n" + 
				"0 0";
		Scanner scanner = new Scanner(data);
		while(true){
			int t = scanner.nextInt();
			int n = scanner.nextInt();
			if(t == 0 && n == 0) break;
			int[] numbers = new int[n];
			for(int i = 0; i < n; i++){
				numbers[i] = scanner.nextInt();
			}
			List<List<Integer>> sums = findSums(numbers, t);
			System.out.print(toString(sums, t));
		}
		scanner.close();
	}
	
	static String toString(List<List<Integer>> sums, int t){
		StringBuilder out = new StringBuilder();
		out.append(String.format("Sums of %1$d:\n", t));
		if(sums.size() == 0) out.append("NONE\n");
		else{
			for(List<Integer> sum : sums){
				for(int i = 0; i < sum.size(); i++){
					out.append(sum.get(i));
					if(i < sum.size() - 1) out.append("+");
				}
				out.append("\n");
			}
		}
		return out.toString();
	}
	
	static List<List<Integer>> findSums(int[] numbers, int t){
		List<List<Integer>> sums = new ArrayList<List<Integer>>();
		findSums(sums, new ArrayList<Integer>(), numbers, 0, t, 0);
		return sums;
	}
	
	static void findSums(List<List<Integer>> sums, List<Integer> sum, int[] numbers, int i, int t, int runningSum){
		if(i == numbers.length){
			if(runningSum == t) {
				if(sums.size() == 0 || !sumExists(sums, sum)){
					sums.add(new ArrayList<Integer>(sum));
				}
			}
			return;
		}
		if(runningSum + numbers[i] <= t){
			sum.add(numbers[i]);
			findSums(sums, sum, numbers, i + 1, t, runningSum + numbers[i]);
			sum.remove(sum.size() - 1);
		}
		findSums(sums, sum, numbers, i + 1, t, runningSum);
	}
	
	static boolean listsEqual(List<Integer> list1, List<Integer> list2){
		if(list1.size() != list2.size()) return false;
		for(int i = 0; i < list1.size(); i++){
			if(list1.get(i) != list2.get(i)) return false;
		}
		return true;
	}
	
	static boolean sumExists(List<List<Integer>> sums, List<Integer> sum){
		for(List<Integer> s : sums){
			if(listsEqual(sum, s)) return true;
		}
		return false;
	}
}
