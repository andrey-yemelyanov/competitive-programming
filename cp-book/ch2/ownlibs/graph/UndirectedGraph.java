package helvidios.cp.ch2.ownlibs.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class UndirectedGraph<T> implements Iterable<T>{
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