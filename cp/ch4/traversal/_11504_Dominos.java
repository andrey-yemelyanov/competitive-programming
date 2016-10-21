package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _11504_Dominos{
	public static void main(String[] args){
		String data = "1\r\n3 2\r\n1 2\r\n2 3\r\n";
		String data2 = "1\r\n5 4\r\n1 2\r\n3 2\r\n2 4\r\n2 5";
		String data3 = "1\r\n10 13\r\n1 2\r\n2 1\r\n3 4\r\n4 3\r\n5 6\r\n6 5\r\n7 8\r\n8 7\r\n9 10\r\n10 9\r\n4 6\r\n5 10\r\n10 1";
		String data4 = "1\r\n8 9\r\n8 1\r\n1 3\r\n3 2\r\n2 1\r\n3 4\r\n4 5\r\n5 7\r\n7 6\r\n6 4";
		String data5 = "1\r\n8 13\r\n1 2\r\n2 3\r\n4 1\r\n4 3\r\n2 4\r\n3 5\r\n5 3\r\n6 5\r\n8 5\r\n8 7\r\n7 8\r\n6 7\r\n7 6";
		Scanner s = new Scanner(data5);
		int nTests = s.nextInt();
		StringBuilder sb = new StringBuilder();
		while(nTests-- > 0){
			int n = s.nextInt(); int m = s.nextInt();
			graph = new HashMap<>();
			Map<Integer, List<Integer>> inverseGraph = new HashMap<>();
			for(int i = 1; i <= n; i++){
				graph.put(i, new ArrayList<Integer>());
				inverseGraph.put(i, new ArrayList<Integer>());
			}
			for(int i = 0; i < m; i++){
				int v1 = s.nextInt(); int v2 = s.nextInt();
				graph.get(v1).add(v2);
				inverseGraph.get(v2).add(v1);
			}
			List<List<Integer>> sccs = findSCCs(graph);
			/*for(List<Integer> scc : sccs){
				System.out.println(scc);
			}*/
			int count = 0;
			for(List<Integer> scc : sccs){
				if(!sccHasIncomingEdges(new HashSet<>(scc), inverseGraph)){
					count++;
				}
			}
			sb.append(count + "\n");	
		}
		System.out.print(sb.toString());
	}

	static boolean sccHasIncomingEdges(Set<Integer> scc, Map<Integer, List<Integer>> inverseGraph){
		for(int v : scc){
			for(int incoming : inverseGraph.get(v)){
				if(!scc.contains(incoming)) return true;
			}
		}
		return false;
	}

	static List<List<Integer>> findSCCs(Map<Integer, List<Integer>> graph){
		sccs = new ArrayList<>();
		dfsNum = new HashMap<>();
		dfsLow = new HashMap<>();
		onStack = new HashSet<>();
		stack = new Stack<>();
		for(Integer v : graph.keySet()){
			dfsNum.put(v, UNVISITED);
			dfsLow.put(v, 0);
		}
		dfsNumCounter = 0;
		for(Integer v : graph.keySet()){
			if(dfsNum.get(v) == UNVISITED){
				tarjanSCCIterative(v);
			}
		}
		return sccs;
	}

	static int UNVISITED = -1;
	static int dfsNumCounter = 0;
	static Map<Integer, Integer> dfsNum;
	static Map<Integer, Integer> dfsLow;
	static Stack<Integer> stack;
	static Set<Integer> onStack;
	static Map<Integer, List<Integer>> graph;
	static List<List<Integer>> sccs;
	static void tarjanSCC(Integer u){
		dfsNum.put(u, dfsNumCounter++);
		dfsLow.put(u, dfsNum.get(u));
		stack.push(u);
		onStack.add(u);

		for(Integer v : graph.get(u)){
			if(dfsNum.get(v) == UNVISITED){
				tarjanSCC(v);
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

	static void tarjanSCCIterative(int u){
		Stack<Integer> callStack = new Stack<>();
		callStack.push(u);
		dfsNum.put(u, dfsNumCounter++);
		dfsLow.put(u, dfsNum.get(u));
		stack.push(u);
		onStack.add(u);

		lbl:while(!callStack.isEmpty()){
			u = callStack.peek();
			for(int v : graph.get(u)){
				if(dfsNum.get(v) == UNVISITED){
					callStack.push(v);
					dfsNum.put(v, dfsNumCounter++);
					dfsLow.put(v, dfsNum.get(v));
					stack.push(v);
					onStack.add(v);	
					continue lbl;
				}
				if(onStack.contains(v)){
					dfsLow.put(u, min(dfsLow.get(u), dfsLow.get(v)));
				}
			}

			if(callStack.peek() == u){ // no new vertex has been pushed onto the call stack
				//System.out.printf("%d dfsLow=%d, dfsNum=%d\n", u, dfsLow.get(u), dfsNum.get(u));
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
				callStack.pop();	
			}
		}
	}
}