import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 334 Identifying Concurrent Events
Problem url: https://uva.onlinejudge.org/external/3/334.pdf
Author: Andrey Yemelyanov
*/
public class _334_IdentifyingConcurrentConnections {
  static class Pair{
    int i1; int i2;
    public Pair(int i1, int i2){this.i1 = i1; this.i2 = i2;}
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int caseNum = 1;
    while(s.hasNext()){
      int nComps = s.nextInt();
      if(nComps == 0) break;
      Map<String, List<String>> graph = new HashMap<>();
      for(int i = 0; i < nComps; i++){
        int nEvents = s.nextInt();
        String prevEvent = null;
        for(int j = 0; j < nEvents; j++){
          String event = s.next();
          graph.putIfAbsent(event, new ArrayList<>());
          if(prevEvent != null){
            graph.get(prevEvent).add(event);
          }
          prevEvent = event;
        }
      }
      int nMsg = s.nextInt();
      for(int i = 0; i < nMsg; i++){
        String event1 = s.next(); String event2 = s.next();
        graph.get(event1).add(event2);
      }

      List<String> eventToIdMap = new ArrayList<>();
      int[][] adjMatrix = toAdjMatrix(graph, eventToIdMap);
      //System.out.println(graph);
      //print(adjMatrix);
      transitiveClosure(adjMatrix);
      //print(adjMatrix);
      List<Pair> concurrentEvents = getConcurrentEvents(adjMatrix);
      int nConcurrentEvents = concurrentEvents.size() / 2;
      if(nConcurrentEvents == 0) System.out.printf("Case %d, no concurrent events.\n", caseNum++);
      else{
        System.out.printf("Case %d, %d concurrent events:\n", caseNum++, nConcurrentEvents);
        if(nConcurrentEvents == 1){
          Pair pair = concurrentEvents.get(0);
          System.out.printf("(%s,%s) \n", eventToIdMap.get(pair.i1), eventToIdMap.get(pair.i2));
        }else{
          for(int i = 0; i < 2; i++){
            Pair pair = concurrentEvents.get(i);
            System.out.printf("(%s,%s)", eventToIdMap.get(pair.i1), eventToIdMap.get(pair.i2));
            if(i == 1) System.out.println(" ");
            else System.out.print(" ");
          }
        }
      }
    }
  }

  static List<Pair> getConcurrentEvents(int[][] adjMatrix){
    List<Pair> concurrentEvents = new ArrayList<>();
    int V = adjMatrix.length;
    for(int i = 0; i < V; i++){
      for(int j = 0; j < V; j++){
        if(adjMatrix[i][j] == 0 && adjMatrix[j][i] == 0){
          concurrentEvents.add(new Pair(i, j));
        }
      }
    }
    return concurrentEvents;
  }

  static int[][] toAdjMatrix(Map<String, List<String>> graph, List<String> eventToIdMap){
    int V = graph.size();
    for(String event : graph.keySet()){
      eventToIdMap.add(event);
    }
    int[][] adjMatrix = new int[V][V];
    for(int i = 0; i < V; i++){
      for(int j = 0; j < V; j++){
        if(i == j) adjMatrix[i][j] = 1;
        String event1 = eventToIdMap.get(i); String event2 = eventToIdMap.get(j);
        if(graph.get(event1).contains(event2)) adjMatrix[i][j] = 1;
      }
    }
    return adjMatrix;
  }

  static void transitiveClosure(int[][] adjMatrix){
    int V = adjMatrix.length;
    for(int k = 0; k < V; k++){
      for(int i = 0; i < V; i++){
        for(int j = 0; j < V; j++){
          adjMatrix[i][j] |= (adjMatrix[i][k] & adjMatrix[k][j]);
        }
      }
    }
  }

  static void print(int[][] adjMatrix){
    for(int i = 0; i < adjMatrix.length; i++){
      System.out.println(Arrays.toString(adjMatrix[i]));
    }
    System.out.println();
  }
}
