package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _00247_CallingCircles{
	public static void main(String[] args){
		String data = "5 6\r\nBen Alexander\r\nAlexander Dolly\r\nDolly Ben\r\nDolly Benedict\r\nBenedict Dolly\r\nAlexander Aaron\r\n14 34\r\nJohn Aaron\r\nAaron Benedict\r\nBetsy John\r\nBetsy Ringo\r\nRingo Dolly\r\nBenedict Paul\r\nJohn Betsy\r\nJohn Aaron\r\nBenedict George\r\nDolly Ringo\r\nPaul Martha\r\nGeorge Ben\r\nAlexander George\r\nBetsy Ringo\r\nAlexander Stephen\r\nMartha Stephen\r\nBenedict Alexander\r\nStephen Paul\r\nBetsy Ringo\r\nQuincy Martha\r\nBen Patrick\r\nBetsy Ringo\r\nPatrick Stephen\r\nPaul Alexander\r\nPatrick Ben\r\nStephen Quincy\r\nRingo Betsy\r\nBetsy Benedict\r\nBetsy Benedict\r\nBetsy Benedict\r\nBetsy Benedict\r\nBetsy Benedict\r\nBetsy Benedict\r\nQuincy Martha\r\n0 0";
		Scanner s = new Scanner(data);
		int dataset = 1; boolean firstSet = true;
		while(s.hasNext()){
			int n = s.nextInt(); int m = s.nextInt();
			if(n == 0 && m == 0) break;
			Map<String, List<String>> graph = new HashMap<>();
			for(int i = 0; i < m; i++){
				String v1 = s.next(); String v2 = s.next();
				if(!graph.containsKey(v1)){
					graph.put(v1, new ArrayList<String>());
				}
				if(!graph.containsKey(v2)){
					graph.put(v2, new ArrayList<String>());
				}
				graph.get(v1).add(v2);
			}
			List<List<String>> sccs = findSCCs(graph);
			if(firstSet) firstSet = false;
			else System.out.println();
			System.out.printf("Calling circles for data set %d:\n", dataset++);
			for(List<String> scc : sccs){
				System.out.println(toString(scc));
			}
		}
	}

	static String toString(List<String> set){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < set.size(); i++){
			sb.append(set.get(i));
			if(i < set.size() - 1) sb.append(", ");
		}
		return sb.toString();
	}

	static List<List<String>> findSCCs(Map<String, List<String>> graph){
		List<List<String>> sccs = new ArrayList<>();
		Map<String, Integer> dfsNum = new HashMap<>();
		Map<String, Integer> dfsLow = new HashMap<>();
		Set<String> onStack = new HashSet<>();
		Stack<String> stack = new Stack<>();
		for(String word : graph.keySet()){
			dfsNum.put(word, UNVISITED);
			dfsLow.put(word, 0);
		}
		dfsNumCounter = 0;
		for(String word : graph.keySet()){
			if(dfsNum.get(word) == UNVISITED){
				tarjanSCC(graph, word, dfsNum, dfsLow, onStack, stack, sccs);
			}
		}
		return sccs;
	}

	static int UNVISITED = -1;
	static int dfsNumCounter = 0;
	static void tarjanSCC(
		Map<String, List<String>> graph, 
		String u, 
		Map<String, Integer> dfsNum, 
		Map<String, Integer> dfsLow, 
		Set<String> onStack,
		Stack<String> stack,
		List<List<String>> sccs){
		dfsNum.put(u, dfsNumCounter++);
		dfsLow.put(u, dfsNum.get(u));
		stack.push(u);
		onStack.add(u);

		for(String v : graph.get(u)){
			if(dfsNum.get(v) == UNVISITED){
				tarjanSCC(graph, v, dfsNum, dfsLow, onStack, stack, sccs);
			}
			if(onStack.contains(v)){
				dfsLow.put(u, min(dfsLow.get(u), dfsLow.get(v)));
			}
		}

		if(dfsLow.get(u) == dfsNum.get(u)){
			List<String> scc = new ArrayList<>();
			while(true){
				String v = stack.pop();
				onStack.remove(v);
				scc.add(v);
				if(u == v) {
					sccs.add(scc);
					break;
				}
			}
		}
	}
}