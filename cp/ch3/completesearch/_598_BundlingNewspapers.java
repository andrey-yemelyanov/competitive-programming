package helvidios.cp.ch3.completesearch;

import java.util.*;

public class _598_BundlingNewspapers {
	public static void main(String... args){
		String data = "3\r\n" + 
				"\r\n" + 
				"2 3\r\n" + 
				"Times\r\n" + 
				"Herald-Tribune\r\n" + 
				"Post\r\n" + 
				"New Advocate\r\n" + 
				"\r\n" + 
				"*\r\n" + 
				"Times\r\n" + 
				"Herald-Tribune\r\n" + 
				"Post\r\n" + 
				"New Advocate\r\n" + 
				"\r\n" + 
				"3\r\n" + 
				"Times\r\n" + 
				"Herald-Tribune\r\n" + 
				"Post\r\n" + 
				"New Advocate";
		String data2 = "3\r\n" + 
				"\r\n" + 
				"2 3\r\n" + 
				"Times\r\n" + 
				"Herald-Tribune\r\n" + 
				"Post\r\n" + 
				"New Advocate\r\n" + 
				"\r\n" + 
				"*\r\n" + 
				"abc\r\n" + 
				"def\r\n" + 
				"\r\n" + 
				"1\r\n" + 
				"pqm";
		Scanner scanner = new Scanner(data2);
		int nDatasets = scanner.nextInt();
		scanner.nextLine();
		scanner.nextLine();
		while(nDatasets-- > 0){
			String line1 = scanner.nextLine();
			int from = 0, to = 0;
			List<String> newspapers = new ArrayList<String>();
			String line;
			while(scanner.hasNext() && !(line = scanner.nextLine()).equals("")){
				newspapers.add(line);
			}
			if(line1.contains("*")){
				from = 1; to = newspapers.size();
			}else{
				String[] s = line1.split(" ");
				if(s.length == 2){
					from = Integer.parseInt(s[0]);
					to = Integer.parseInt(s[1]);
				}else{
					from = to = Integer.parseInt(s[0]);
				}
			}
			List<List<String>> subsets = generateSubsets(newspapers);
			for(int i = from; i <= to; i++){
				System.out.println("Size " + i);
				for(String subset : subsets.get(i)){
					System.out.println(subset);
				}
				System.out.println();
			}
			if(nDatasets > 0) System.out.println();
		}
		scanner.close();
	}
	
	static List<List<String>> generateSubsets(List<String> newspapers){
		List<List<String>> subsets = new ArrayList<List<String>>();
		for(int i = 0; i < newspapers.size() + 1; i++){
			subsets.add(new ArrayList<String>());
		}
		generateSubsets(subsets, newspapers, new ArrayList<String>(), 0);
		return subsets;
	}
	
	static void generateSubsets(List<List<String>> subsets, List<String> newspapers, List<String> subset, int i){
		if(i == newspapers.size()){
			subsets.get(subset.size()).add(toString(subset));
			return;
		}
		subset.add(newspapers.get(i));
		generateSubsets(subsets, newspapers, subset, i + 1);
		subset.remove(subset.size() - 1);
		generateSubsets(subsets, newspapers, subset, i + 1);
	}
	
	static String toString(List<String> subset){
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < subset.size(); i++){
			str.append(subset.get(i));
			if(i < subset.size() - 1) str.append(", ");
		}
		return str.toString();
	}
}
