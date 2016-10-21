package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _00872_Ordering{
	public static void main(String[] args){
		String data = "2\r\nA B F G\r\nA<B B<F\n\nA B F G\r\nA<B";
		String data2 = "8\r\n\nA B F G\r\nA<B B<F\r\n\r\nG F B A\r\nA<B B<F\r\n\r\nA B F G\r\nA<G\r\n\r\nZ X I K\r\nK<Z I<X\r\n\r\nA B C D E F G\r\nA<B B<A\r\n\r\nA B C\r\nA<B B<C C<A\r\n\r\nQ V C E R H\r\nV<E R<Q Q<H E<C H<V\r\n\r\nQ V C E R H J\r\nV<E R<Q Q<H E<C H<V";
		String data3 = "2\r\n\r\nA B C\r\nA<B B<C C<A\r\n\r\nA B\r\nA<B B<A";
		String data4 = "1\r\n\r\nA B C\r\nA<B B<C C<A";
		Scanner s = new Scanner(data2);
		int nTests = s.nextInt(); s.nextLine();s.nextLine();
		while(nTests-- > 0){
			String[] vertices = s.nextLine().split("\\s+");
			Arrays.sort(vertices);
			//System.out.println(Arrays.toString(vertices));
			Map<Character, Set<Character>> graph = new TreeMap<>();
			for(String v : vertices){
				graph.put(v.charAt(0), new HashSet<Character>());
			}
			String[] constraints = s.nextLine().split("\\s+");
			for(String constraint : constraints){
				String[] edge = constraint.split("<");
				graph.get(edge[0].trim().charAt(0)).add(edge[1].trim().charAt(0));
			}
			//System.out.println(graph);
			List<String> orderings = findAllToposortOrderings(graph);
			if(orderings.size() == 0) System.out.println("NO");
			else System.out.println(toString(orderings));
			if(nTests > 0) System.out.println();
			if(nTests > 0) s.nextLine();
		}
	}

	static String toString(List<String> orderings){
		StringBuilder sb = new StringBuilder();
		for(int k = 0; k < orderings.size(); k++){
			String ordering = orderings.get(k);
			char[] chars = ordering.toCharArray();
			for(int i = 0; i < chars.length; i++){
				sb.append(chars[i]);
				if(i < chars.length - 1) sb.append(" ");
			}
			if(k < orderings.size() - 1) sb.append("\n");
		}
		return sb.toString();
	}

	static List<String> findAllToposortOrderings(Map<Character, Set<Character>> graph){
		Set<Character> visited = new HashSet<>();
		List<String> orderings = new ArrayList<>();
		findAllToposortOrderings(graph, visited, "", orderings);
		return orderings;
	}

	static void findAllToposortOrderings(Map<Character, Set<Character>> graph, Set<Character> visited, String ordering, List<String> orderings){
		if(allVisited(graph, visited)){
			orderings.add(ordering);
			return;
		}

		for(char v : graph.keySet()){
			if(!visited.contains(v)){
				if(valid(graph, v, visited)){
					visited.add(v);
					findAllToposortOrderings(graph, visited, ordering + v, orderings);
					visited.remove(v);
				}
			}
		}
	}

	static boolean allVisited(Map<Character, Set<Character>> graph, Set<Character> visited){
		for(char v : graph.keySet()){
			if(!visited.contains(v)) return false;
		}
		return true;
	}

	static boolean valid(Map<Character, Set<Character>> graph, char v, Set<Character> visited){
		for(char origin : graph.keySet()){
			if(graph.get(origin).contains(v) && !visited.contains(origin)) return false;
		}
		return true;
	}
}