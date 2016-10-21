package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _11060_Beverages{
	static Map<String, Integer> nodes;
	public static void main(String[] args){
		String data = "3\r\nvodka\r\nwine\r\nbeer\r\n2\r\nwine vodka\r\nbeer wine\r\n\r\n5\r\nwine\r\nbeer\r\nrum\r\napple-juice\r\ncachaca\r\n6\r\nbeer cachaca\r\napple-juice beer\r\napple-juice rum\r\nbeer rum\r\nbeer wine\r\nwine cachaca\r\n\r\n10\r\ncachaca\r\nrum\r\napple-juice\r\ntequila\r\nwhiskey\r\nwine\r\nvodka\r\nbeer\r\nmartini\r\ngin\r\n11\r\nbeer whiskey\r\napple-juice gin\r\nrum cachaca\r\nvodka tequila\r\napple-juice martini\r\nrum gin\r\nwine whiskey\r\napple-juice beer\r\nbeer rum\r\nwine vodka\r\nbeer tequila";
		String data2 = "3\r\na\r\nb\r\nc\r\n1\r\nc a";
		Scanner s = new Scanner(data);
		int caseNum = 1;
		while(s.hasNext()){
			int n = s.nextInt();
			Map<String, List<String>> graph = new TreeMap<>();
			nodes = new TreeMap<String, Integer>();
			for(int i = 0; i < n; i++){
				nodes.put(s.next(), i);
			}
			for(String node : nodes.keySet()){
				graph.put(node, new ArrayList<String>());
			}
			int m = s.nextInt();
			for(int i = 0; i < m; i++){
				graph.get(s.next()).add(s.next());
			}
			List<String> toposort = getToposort(graph);
			System.out.printf("Case #%d: Dilbert should drink beverages in this order: %s.\n", caseNum++, toString(toposort));
			System.out.println();
		}
	}

	static String toString(List<String> toposort){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < toposort.size(); i++){
			sb.append(toposort.get(i));
			if(i < toposort.size() - 1) sb.append(" ");
		}
		return sb.toString();
	}

	static List<String> getToposort(Map<String, List<String>> graph){
		List<String> toposort = new ArrayList<>();
		PriorityQueue<String> q = new PriorityQueue<>(10, new Comparator<String>(){
			@Override public int compare(String s1, String s2){
				return Integer.compare(nodes.get(s1), nodes.get(s2));
			}
		});
		for(String node : graph.keySet()){
			if(indegree(node, graph) == 0) q.add(node);
		}

		while(!q.isEmpty()){
			String node = q.remove();
			toposort.add(node);
			for(String neighbor : graph.get(node).toArray(new String[0])){
				graph.get(node).remove(neighbor);
				if(indegree(neighbor, graph) == 0) q.add(neighbor);
			}
		}

		return toposort;
	}

	static int indegree(String node, Map<String, List<String>> graph){
		int indegree = 0;
		for(String v : graph.keySet()){
			if(graph.get(v).contains(node)) indegree++;
		}
		return indegree;
	}
}