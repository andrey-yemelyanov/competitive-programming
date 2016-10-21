package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _00624_CD{
	public static void main(String[] args){
		/*System.out.println(printSolution(optimize(Arrays.asList(1,3,4), 5))); // 0
		System.out.println(printSolution(optimize(Arrays.asList(9,8,4,2), 10))); // 0
		System.out.println(printSolution(optimize(Arrays.asList(10,5,7,4), 20))); // 1
		System.out.println(printSolution(optimize(Arrays.asList(10,23,1,2,3,4,5,7), 90))); // 35
		System.out.println(printSolution(optimize(Arrays.asList(4,10,44,43,12,9,8,2), 45))); // 0*/
		String data = "5 3 1 3 4\n10 4 9 8 4 2\n20 4 10 5 7 4\n90 8 10 23 1 2 3 4 5 7\n45 8 4 10 44 43 12 9 8 2";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int maxLength = scanner.nextInt();
			int n = scanner.nextInt();
			List<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < n; i++){
				list.add(scanner.nextInt());
			}
			System.out.println(printSolution(optimize(list, maxLength)));
		}
		scanner.close();
	}
	
	static String printSolution(List<Integer> solution){
		StringBuilder out = new StringBuilder();
		int sum = 0;
		for(int i : solution){
			out.append(i + " ");
			sum += i;
		}
		out.append("sum:" + sum);
		return out.toString();
	}

	static List<Integer> optimize(List<Integer> list, int maxLength){
		solution = new ArrayList<Integer>();
		minCost = Integer.MAX_VALUE;
		optimize(solution, list, 0, maxLength);
		return solution;
	}
	
	static List<Integer> solution;
	static int minCost;
	static void optimize(List<Integer> subset, List<Integer> list, int index, int maxLength){
 		if(maxLength < 0) return;
		if(index == list.size()) {
			if(maxLength < minCost){
				minCost = maxLength;
				solution = subset;
			}
			return;
		}
		List<Integer> newSubsetInclude = new ArrayList<Integer>(subset);
		newSubsetInclude.add(list.get(index));
		List<Integer> newSubsetExclude = new ArrayList<Integer>(subset);
		optimize(newSubsetInclude, list, index + 1, maxLength - list.get(index));
		optimize(newSubsetExclude, list, index + 1, maxLength);
	}
}