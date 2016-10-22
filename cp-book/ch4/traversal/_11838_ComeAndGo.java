package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _11838_ComeAndGo{
	public static void main(String[] args){
		String data = "";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int n = s.nextInt(); int m = s.nextInt();
			if(n == 0 && m == 0) break;
			Map<Integer, List<Integer>> graph = new HashMap<>();
			for(int i = 1; i <= n; i++){
				graph.put(i, new ArrayList<Integer>());
			}
			for(int i = 0; i < m; i++){
				int v1 = s.nextInt(); int v2 = s.nextInt(); boolean twoWay = s.nextInt() == 2;
				graph.get(v1).add(v2);
				if(twoWay) graph.get(v2).add(v1);
			}
			List<List<Integer>> sccs = findSCCs(graph);
			if(sccs.size() == 1) System.out.println(1);
			else System.out.println(0);
		}
	}

	static List<List<Integer>> findSCCs(Map<Integer, List<Integer>> graph){
		List<List<Integer>> sccs = new ArrayList<>();
		Map<Integer, Integer> dfsNum = new HashMap<>();
		Map<Integer, Integer> dfsLow = new HashMap<>();
		Set<Integer> onStack = new HashSet<>();
		Stack<Integer> stack = new Stack<>();
		for(Integer v : graph.keySet()){
			dfsNum.put(v, UNVISITED);
			dfsLow.put(v, 0);
		}
		dfsNumCounter = 0;
		for(Integer v : graph.keySet()){
			if(dfsNum.get(v) == UNVISITED){
				tarjanSCC(graph, v, dfsNum, dfsLow, onStack, stack, sccs);
			}
		}
		return sccs;
	}

	static int UNVISITED = -1;
	static int dfsNumCounter = 0;
	static void tarjanSCC(
		Map<Integer, List<Integer>> graph, 
		Integer u, 
		Map<Integer, Integer> dfsNum, 
		Map<Integer, Integer> dfsLow, 
		Set<Integer> onStack,
		Stack<Integer> stack,
		List<List<Integer>> sccs){
		dfsNum.put(u, dfsNumCounter++);
		dfsLow.put(u, dfsNum.get(u));
		stack.push(u);
		onStack.add(u);

		for(Integer v : graph.get(u)){
			if(dfsNum.get(v) == UNVISITED){
				tarjanSCC(graph, v, dfsNum, dfsLow, onStack, stack, sccs);
			}
			if(onStack.contains(v)){
				dfsLow.put(u, min(dfsLow.get(u), dfsLow.get(v)));
			}
		}

		if(dfsLow.get(u) == dfsNum.get(u)){
			List<Integer> scc = new ArrayList<>();
			while(true){
				Integer v = stack.pop();
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