package helvidios.cp.ch2.ownlibs.graph;

import java.util.*;

public class _599_TheForrestForTheTrees {
	public static class Pair{
		public String node1;
		public String node2;
		public Pair(String node1, String node2){
			this.node1 = node1;
			this.node2 = node2;
		}
	}
	public static class UndirectedGraph<T> implements Iterable<T>{
		private final Map<T, Set<T>> mGraph = new HashMap<T, Set<T>>();
		private int V, E;
		
	    public boolean addNode(T node) {
	        /* If the node already exists, don't do anything. */
	        if (mGraph.containsKey(node))
	            return false;

	        /* Otherwise, add the node with an empty set of outgoing edges. */
	        mGraph.put(node, new HashSet<T>());
	        
	        V++;
	        
	        return true;
	    }
	    
	    public boolean nodeExists(T node) {
	        return mGraph.containsKey(node);
	    }
	    
	    public void addEdge(T one, T two) {
	        /* Confirm both endpoints exist. */
	        if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
	            throw new NoSuchElementException("Both nodes must be in the graph.");

	        /* Add the edge in both directions. */
	        mGraph.get(one).add(two);
	        mGraph.get(two).add(one);
	        
	        E++;
	    }
	    
	    public void removeEdge(T one, T two) {
	        /* Confirm both endpoints exist. */
	        if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
	            throw new NoSuchElementException("Both nodes must be in the graph.");

	        /* Remove the edges from both adjacency lists. */
	        mGraph.get(one).remove(two);
	        mGraph.get(two).remove(one);
	        
	        E--;
	    }
	    
	    public boolean edgeExists(T one, T two) {
	        /* Confirm both endpoints exist. */
	        if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
	            throw new NoSuchElementException("Both nodes must be in the graph.");     
	        
	        /* Graph is symmetric, so we can just check either endpoint. */
	        return mGraph.get(one).contains(two);
	    }
	    
	    public Set<T> edgesFrom(T node) {
	        /* Check that the node exists. */
	        Set<T> arcs = mGraph.get(node);
	        if (arcs == null)
	            throw new NoSuchElementException("Source node does not exist.");

	        return Collections.unmodifiableSet(arcs);
	    }
	    
	    public boolean isEmpty() {
	        return mGraph.isEmpty();
	    }
	    
	    public String toString() {
	        return mGraph.toString();
	    }
		
		@Override
		public Iterator<T> iterator() {
			return mGraph.keySet().iterator();
		}
		
		public int V(){
			return V;
		}
		
		public int E(){
			return E;
		}
	}
	
	public static void main(String... args){
		String data = "2\r\n" + 
				"(A,B)\r\n" + 
				"(B,C)\r\n" + 
				"(B,D)\r\n" + 
				"(D,E)\r\n" + 
				"(E,F)\r\n" + 
				"(B,G)\r\n" + 
				"(G,H)\r\n" + 
				"(G,I)\r\n" + 
				"(J,K)\r\n" + 
				"(K,L)\r\n" + 
				"(K,M)\r\n" + 
				"****\r\n" + 
				"A,B,C,D,E,F,G,H,I,J,K,L,M,N\r\n" + 
				"(A,B)\r\n" + 
				"(A,C)\r\n" + 
				"(C,F)\r\n" + 
				"**\r\n" + 
				"A,B,C,D,F\r\n" + 
				"";
		String data2 = "1\n"+
				"******\n"+
				"A,B,C,H,I,J\n";
		String data3 = "1\r\n" + 
				"(A,B)\r\n" + 
				"(A,C)\r\n" + 
				"(B,D)\r\n" + 
				"(B,E)\r\n\n" + 
				"(C,F)\r\n" + 
				"(C,G)\r\n" + 
				"(J,K)\r\n" + 
				"**********\r\n" + 
				"A,B,C,D,E,F,G,J,K,   Z,Y,W";
		String data4 = "1\r\n" + 
				"*\r\n" + 
				"W";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			UndirectedGraph<String> graph = buildGraph(scanner);
			int[] result = countTreesAndAcorns(graph);
			System.out.printf("There are %1$d tree(s) and %2$d acorn(s).\n", result[0], result[1]);
		}
		scanner.close();
	}
	
	private static UndirectedGraph<String> buildGraph(Scanner scanner){
		List<Pair> edges = new ArrayList<Pair>();
		while(true){
			String edge = scanner.next();
			if(edge.contains("*")) break;
			edges.add(new Pair(edge.substring(1, 2), edge.substring(3, 4)));
		}
		scanner.nextLine();
		String[] nodes = scanner.nextLine().split(",");
		UndirectedGraph<String> g = new UndirectedGraph<String>();
		for(String node : nodes){
			g.addNode(node.trim());
		}
		for(Pair edge : edges){
			g.addEdge(edge.node1, edge.node2);
		}
		return g;
	}
	
	public static int[] countTreesAndAcorns(UndirectedGraph<String> graph){
		// number of connected components = V - E
		int nConnectedComponents = graph.V() - graph.E();
		if(nConnectedComponents <= 0) return new int[]{0,0};
		int nAcorns = 0;
		for(String node : graph){
			if(graph.edgesFrom(node).isEmpty()) nAcorns++;
		}
		
		int nTrees = nConnectedComponents - nAcorns;
		return new int[]{nTrees, nAcorns};
	}
}
