package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _12376_AsLongAs{
   static class Node{
      public int id;
      public int value;
      public Node(int id, int value){
         this.id = id;
         this.value = value;
      }
   }
   public static void main(String[] args){
      String data = "1\r\n6 6\r\n0 8 9 2 7 5\r\n5 4\r\n5 3\r\n1 5\r\n0 1\r\n0 2\r\n2 1";
      Scanner s = new Scanner(data);
      int nTests = s.nextInt();
      for(int i = 1; i <= nTests; i++){
         int nNodes = s.nextInt();
         int nEdges = s.nextInt();
         int[] nodeValues = new int[nNodes];
         Map<Integer, PriorityQueue<Node>> graph = new HashMap<>();
         for(int j = 0; j < nNodes; j++){
            nodeValues[j] = s.nextInt();
            graph.put(j, new PriorityQueue<Node>(100, new Comparator<Node>(){
               public int compare(Node n1, Node n2){
                  return Integer.compare(n2.value, n1.value);
               }
            }));
         }
         for(int j = 0; j < nEdges; j++){
            int from = s.nextInt();
            int to = s.nextInt();
            graph.get(from).add(new Node(to, nodeValues[to]));
         }
         int[] result = learn(graph);
         System.out.printf("Case %d: %d %d\n", i, result[0], result[1]);
      }
   }
   
   static int[] learn(Map<Integer, PriorityQueue<Node>> graph){
      return learn(graph, 0, 0);
   }
   
   static int[] learn(Map<Integer, PriorityQueue<Node>> graph,
      int sourceNode, int totalValue){
      Node nextNode = graph.get(sourceNode).peek();
      if(nextNode == null) return new int[] {totalValue, sourceNode};
      return learn(graph, nextNode.id, totalValue + nextNode.value);
   }
}