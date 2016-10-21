package helvidios.cp.ch3.greedy;

import java.util.*;

public class Trip{
	public static void main(String[] args){
		String data = "10\r\n1 1 1 2 2 2 3 3 4 4\r\n10\r\n1 1 1 1 1 2 3 4 5 6";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			if(n == 0) break;
			int[] bags = new int[n];
			for(int i = 0; i < n; i++){
				bags[i] = scanner.nextInt();
			}
			List<List<Integer>> baggage = pack(bags);
			System.out.println(toString(baggage));
			System.out.println();
		}
	}
	
	static String toString(List<List<Integer>> baggage){
		StringBuilder str = new StringBuilder();
		str.append(baggage.size());
		str.append("\n");
		for(int i = 0; i < baggage.size(); i++){
			for(int j = 0; j < baggage.get(i).size(); j++){
				str.append(baggage.get(i).get(j));
				if(j < baggage.get(i).size() - 1) str.append(" ");
			}
			if(i < baggage.size() - 1) str.append("\n");
		}
		return str.toString();
	}
	
	static int getMaxFrequency(int[] bags){
		int maxFrequency = 0; int frequency = 0; int currentGroup = 0;
		for(int i = 0; i < bags.length; i++){
			if(bags[i] == currentGroup) frequency++;
			else{
				maxFrequency = Math.max(maxFrequency, frequency);
				frequency = 1;
				currentGroup = bags[i];
			}
		}
		return Math.max(maxFrequency, frequency);
	}
	
	static List<List<Integer>> pack(int[] bags){
		Arrays.sort(bags);
		int nPieces = getMaxFrequency(bags);
		List<List<Integer>> baggage = new ArrayList<List<Integer>>();
		for(int i = 0; i < nPieces; i++) baggage.add(new ArrayList<Integer>());
		for(int i = 0; i < bags.length; i++){
			baggage.get(i % nPieces).add(bags[i]);
		}
		return baggage;
	}
}