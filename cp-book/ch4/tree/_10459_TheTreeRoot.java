import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10459 The Tree Root
Problem url: https://uva.onlinejudge.org/external/104/10459.pdf
Author: Andrey Yemelyanov
*/
public class _10459_TheTreeRoot {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt();
      Map<Integer, List<Integer>> tree = new HashMap<>();
      for(int u = 0; u < N; u++){
        tree.put(u, new ArrayList<>());
        int deg = s.nextInt();
        for(int i = 0; i < deg; i++){
          int v = s.nextInt() - 1;
          tree.putIfAbsent(v, new ArrayList<>());
          tree.get(u).add(v);
          tree.get(v).add(u);
        }
      }
      int[] height = analyzeRoots(tree);
      List<Integer> bestRoots = bestRoots(tree, height);
      List<Integer> worstRoots = worstRoots(tree, height);
      System.out.printf(
        "Best Roots  : %s\n", bestRoots.stream()
                                       .map(i -> i + 1)
                                       .map(Object::toString)
                                       .collect(Collectors.joining(" "))
      );
      System.out.printf(
        "Worst Roots : %s\n", worstRoots.stream()
                                       .map(i -> i + 1)
                                       .map(Object::toString)
                                       .collect(Collectors.joining(" "))
      );
    }
  }

  static List<Integer> bestRoots(Map<Integer, List<Integer>> tree, int[] height){
    int minHeight = Arrays.stream(height)
                          .min()
                          .getAsInt();
    List<Integer> list = new ArrayList<>();
    for(int v = 0; v < height.length; v++){
      if(height[v] == minHeight) list.add(v);
    }
    return list;
  }

  static List<Integer> worstRoots(Map<Integer, List<Integer>> tree, int[] height){
    int maxHeight = Arrays.stream(height)
                          .max()
                          .getAsInt();
    List<Integer> list = new ArrayList<>();
    for(int v = 0; v < height.length; v++){
      if(height[v] == maxHeight) list.add(v);
    }
    return list;
  }

  static int[] analyzeRoots(Map<Integer, List<Integer>> tree){
    int[] verticesAtDiameterDistance = verticesAtDiameterDistance(tree);
    int u = verticesAtDiameterDistance[0]; int v = verticesAtDiameterDistance[1];
    int[] distU = bfs(tree, u); int[] distV = bfs(tree, v);
    int[] height = new int[tree.size()];
    for(int i = 0; i < height.length; i++){
      height[i] = max(distU[i], distV[i]);
    }
    return height;
  }

  static int[] verticesAtDiameterDistance(Map<Integer, List<Integer>> tree){
    int[] dist = bfs(tree, 0);
    int maxDist = Arrays.stream(dist)
                          .max()
                          .getAsInt();
    for(int u = 0; u < tree.size(); u++){
      if(dist[u] == maxDist){
        int[] dist2 = bfs(tree, u);
        int maxDist2 = Arrays.stream(dist2)
                              .max()
                              .getAsInt();
        for(int v = 0; v < tree.size(); v++){
          if(dist2[v] == maxDist2){
            return new int[] {u, v};
          }
        }
      }
    }
    return null;
  }

  static final int INF = 1000000;
  static int[] bfs(Map<Integer, List<Integer>> tree, int root){
    int[] dist = new int[tree.size()];
    Queue<Integer> q = new LinkedList<>();
    q.add(root);
    for(int i = 0; i < dist.length; i++) dist[i] = INF;
    dist[root] = 0;
    while(!q.isEmpty()){
      int u = q.remove();
      for(int v : tree.get(u)){
        if(dist[v] == INF){
          q.add(v);
          dist[v] = dist[u] + 1;
        }
      }
    }
    return dist;
  }
}
