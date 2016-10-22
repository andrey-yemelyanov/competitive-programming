package helvidios.cp.ch2.ownlibs.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _11991_EasyProblemFromRujiaLiu {
	public static void main(String... args){
		String data = "8 4\r\n" + 
				"1 3 2 2 4 3 2 1\r\n" + 
				"1 3\r\n" + 
				"2 4\r\n" + 
				"3 2\r\n" + 
				"4 2";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int n = scanner.nextInt(); int m = scanner.nextInt();
			int[] array = new int[n];
			for(int i = 0; i < array.length; i++){
				array[i] = scanner.nextInt();
			}
			int[][] queries = new int[m][2];
			for(int i = 0; i < queries.length; i++){
				queries[i][0] = scanner.nextInt();
				queries[i][1] = scanner.nextInt();
			}
			int[] result = findOccurrences(array, queries);
			StringBuilder builder = new StringBuilder();
			for(int i : result){
				builder.append(i);
				builder.append("\n");
			}
			System.out.print(builder.toString());
		}
		scanner.close();
	}
	
	public static int[] findOccurrences(int[] array, int[][] queries){
		int[] occurrences = new int[queries.length];
		
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for(int i = 0; i < array.length; i++){
			if(!map.containsKey(array[i])){
				map.put(array[i], new ArrayList<Integer>());
			}
			map.get(array[i]).add(i);
		}
		
		for(int i = 0; i < queries.length; i++){
			int k = queries[i][0];
			int v = queries[i][1];
			List<Integer> vOccurrences = map.get(v);
			if(k > vOccurrences.size()){
				occurrences[i] = 0;
			}else{
				occurrences[i] = vOccurrences.get(k - 1) + 1;
			}
		}
		
		return occurrences;
	}
}
