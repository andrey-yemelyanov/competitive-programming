package helvidios.cp.ch3.completesearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class _140_Bandwidth {
	public static class Bandwidth{
		public Character[] ordering;
		public int value;
		public Bandwidth(Character[] ordering, int value){
			this.ordering = ordering;
			this.value = value;
		}
		public String toString(){
			StringBuilder out = new StringBuilder();
			for(int i = 0; i < ordering.length; i++){
				out.append(ordering[i]);
				out.append(" ");
			}
			out.append("-> ");
			out.append(value);
			return out.toString();
		}
	}
	
	public static void main(String... args){
		String data = "A:FB;B:GC;D:GC;F:AGH;E:HD\r\n" + 
				"#\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			if(line.contains("#")) break;
			Map<Character, Set<Character>> graph = buildGraph(line.split(";"));
			System.out.println(findMinimalBandwidth(graph));
		}
		scanner.close();
	}
	
	public static Map<Character, Set<Character>> buildGraph(String[] nodes){
		Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
		for(String node : nodes){
			String[] keyValue = node.split(":");
			char vertex = keyValue[0].charAt(0);
			if(!graph.containsKey(vertex)){
				graph.put(vertex, new HashSet<Character>());
			}
			String neighbors = keyValue[1];
			for(char neighbor : neighbors.toCharArray()){
				graph.get(vertex).add(neighbor);
				if(!graph.containsKey(neighbor)){
					graph.put(neighbor, new HashSet<Character>());
				}
				graph.get(neighbor).add(vertex);
			}
		}
		return graph;
	}
	
	public static Bandwidth findMinimalBandwidth(Map<Character, Set<Character>> graph){
		Character[] ordering = getInitialOrdering(graph);
		int minBandwidth = Integer.MAX_VALUE;
		Bandwidth bw = null;
		
		do{
			int bandwidth = getBandwidth(ordering, graph);
			if(bandwidth < minBandwidth){
				minBandwidth = bandwidth;
				bw = new Bandwidth(Arrays.copyOf(ordering, ordering.length), bandwidth);
			}
		}while((ordering = nextPermutation(ordering)) != null);
		
		return bw;
	}
	
	public static int getBandwidth(Character[] ordering, Map<Character, Set<Character>> graph){
		int[] individualBandwidths = new int[ordering.length];
		for(int i = 0; i < ordering.length; i++){
			// find bandwidth for element at index i
			int max = Integer.MIN_VALUE;
			for(Character neighbor : graph.get(ordering[i])){
				int distance = Math.abs(i - indexOf(ordering, neighbor));
				if(distance > max){
					max = distance;
				}
			}
			individualBandwidths[i] = max;
		}
		return maxElement(individualBandwidths);
	}
	
	public static Character[] getInitialOrdering(Map<Character, Set<Character>> graph){
		Character[] ordering = graph.keySet().toArray(new Character[0]);
		Arrays.sort(ordering);
		return ordering;
	}
	
	public static int indexOf(Character[] array, Character element){
		for(int i = 0; i < array.length; i++){
			if(array[i] == element) return i;
		}
		throw new RuntimeException("Element not found.");
	}
	
	public static int maxElement(int[] array){
		int max = Integer.MIN_VALUE;
		for(int i : array){
			if(i > max) max = i;
		}
		return max;
	}
	
	public static Character[] nextPermutation(Character[] permutation){
		// Find the largest index k such that a[k] < a[k + 1].
		int k = permutation.length - 2;
		for(;k >= 0 && permutation[k] >= permutation[k + 1]; k--);
		if(k < 0) return null;
		
		// Find the largest index l greater than k such that a[k] < a[l].
		int l = permutation.length - 1;
		for(;l > k && permutation[k] >= permutation[l];l--);
		
		// Swap the value of a[k] with that of a[l].
		swap(permutation, k, l);
		
		// Reverse the sequence from a[k + 1] up to and including the final element a[n].
		int left = k + 1;
		int right = permutation.length - 1;
		while(left < right){
			swap(permutation, left++, right--);
		}
		
		return permutation;
	}
	
	static void swap(Character[] chars, int i, int j){
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}
}
