package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _00459_GraphConnectivity{
   public static void main(String[] args){
      String data = "2\r\n\r\nE\r\nAB\r\nCE\r\nDB\r\nEC\r\n\r\nE\r\nAB\r\nCE\r\nDB\r\nEC";
      String data2 = "2\n\nC\n\nB";
      Scanner s = new Scanner(data2);
      int nTests = s.nextInt(); s.nextLine();
      for(int i = 0; i < nTests; i++){
         char max = s.next().charAt(0);
         if(s.hasNext()) s.nextLine();
         Map<Character, Set<Character>> graph = new HashMap<>();
         for(char c = 'A'; c <= max; c++){
            graph.put(c, new HashSet<Character>());
         }
         while(s.hasNext()){
            String line = s.nextLine(); 
            if(line.trim().isEmpty()) break;
            else{
               char v1 = line.charAt(0);
               char v2 = line.charAt(1);
               graph.get(v1).add(v2); graph.get(v2).add(v1);
            }
         }
         System.out.println(countConnectedComponents(graph));
         if(i < nTests - 1) System.out.println();
      }
   }
   
   static void dfs(Map<Character, Set<Character>> graph, Character v, Set<Character> visited){
      if(visited.contains(v)) return;
      visited.add(v);
      for(char neighbor : graph.get(v)){
         dfs(graph, neighbor, visited);
      }
   }
   
   static int countConnectedComponents(Map<Character, Set<Character>> graph){
      int n = 0;
      Set<Character> visited = new HashSet<>();
      for(char v : graph.keySet()){
         if(!visited.contains(v)){
            dfs(graph, v, visited);
            n++;
         }
      }
      return n;
   }
}