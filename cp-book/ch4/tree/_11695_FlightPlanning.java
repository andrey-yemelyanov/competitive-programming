import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11695 Flight Planning
Problem url: https://uva.onlinejudge.org/external/116/11695.pdf
Author: Andrey Yemelyanov
*/
public class _11695_FlightPlanning {
  /*
    Stores diameter value as well as the two vertices at diameter distance
  */
  static class Diameter{
    public int value;
    public int u;
    public int v;
    public Diameter(int value, int u, int v){
      this.value = value;
      this.u = u;
      this.v = v;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    StringBuilder sb = new StringBuilder();
    while(nTests-- > 0){
      int n = s.nextInt();
      Map<Integer, List<Integer>> tree = new HashMap<>();
      for(int i = 0; i < n; i++) tree.put(i, new ArrayList<>());
      for(int i = 0; i < n - 1; i++){
        int v1 = s.nextInt() - 1; int v2 = s.nextInt() - 1;
        connect(tree, v1, v2);
      }
      int minDiameter = optimizeFlights(tree);
      sb.append(minDiameter + "\n");
      sb.append(String.format("%d %d\n", cancelledFlight[0] + 1, cancelledFlight[1] + 1));
      sb.append(String.format("%d %d\n", newFlight[0] + 1, newFlight[1] + 1));
    }
    System.out.println(sb.toString());
  }

  static int[] cancelledFlight;
  static int[] newFlight;
  static int optimizeFlights(Map<Integer, List<Integer>> tree){
    Diameter d = getDiameter(tree, 0);
    int minDiameter = d.value;
    bfs(tree, d.u);
    //System.out.println("Tree diameter = " + minDiameter);
    //System.out.printf("Diameter (%d) vertices: u=%d and v=%d\n", minDiameter, d.u, d.v);
    //System.out.println("Parent vector: " + Arrays.toString(parent));
    List<Integer> diameterPath = getPath(d.u, d.v);
    //System.out.println(diameterPath);
    for(int i = 0; i < diameterPath.size() - 1; i++){
      int v1 = diameterPath.get(i); int v2 = diameterPath.get(i + 1);
      // remove edge (v1,v2)
      disconnect(tree, v1, v2);
      //System.out.printf("Disconnect at edge (%d, %d)\n", v1, v2);
      // get center vertices for the two disconnected subtrees
      int c1 = getCenter(tree, v1);
      int c2 = getCenter(tree, v2);
      // connect the two center vertices
      connect(tree, c1, c2);
      //System.out.printf("Connect %d and %d\n", c1, c2);
      Diameter dNew = getDiameter(tree, c1);
      //System.out.printf("dNew=%d\n\n", dNew.value);
      if(dNew.value <= minDiameter){
        minDiameter = dNew.value;
        cancelledFlight = new int[] {v1, v2};
        newFlight = new int[] {c1, c2};
      }
      // add back edge (v1,v2)
      connect(tree, v1, v2);
      // remove edge(c1,c2)
      disconnect(tree, c1, c2);
    }
    return minDiameter;
  }

  static void disconnect(Map<Integer, List<Integer>> tree, int v1, int v2){
    tree.get(v1).remove(new Integer(v2));
    tree.get(v2).remove(new Integer(v1));
  }

  static void connect(Map<Integer, List<Integer>> tree, int v1, int v2){
    //System.out.println(tree);
    tree.get(v1).add(v2);
    tree.get(v2).add(v1);
  }

  static int getCenter(Map<Integer, List<Integer>> tree, int treeVertex){
    Diameter d = getDiameter(tree, treeVertex);
    bfs(tree, d.u);
    List<Integer> diameterPath = getPath(d.u, d.v);
    return diameterPath.get(diameterPath.size() / 2);
  }

  static Diameter getDiameter(Map<Integer, List<Integer>> tree, int treeVertex){
    int[] dist = bfs(tree, treeVertex);
    int maxDist = Arrays.stream(dist)
                        .filter(d -> d != INF)
                        .max()
                        .getAsInt();
    for(int u = 0; u < tree.size(); u++){
      if(dist[u] == maxDist){
        int[] dist2 = bfs(tree, u);
        int maxDist2 = Arrays.stream(dist2)
                             .filter(d -> d != INF)
                             .max()
                             .getAsInt();
        for(int v = 0; v < tree.size(); v++){
          if(dist2[v] == maxDist2){
            return new Diameter(maxDist2, u, v);
          }
        }
      }
    }
    return null;
  }

  static final int INF = 100000000;
  static int[] parent;
  static int[] bfs(Map<Integer, List<Integer>> tree, int root){
    parent = new int[tree.size()];
    parent[root] = root;
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
          parent[v] = u;
        }
      }
    }
    return dist;
  }

  static List<Integer> getPath(int u, int v){
    List<Integer> path = new ArrayList<>();
    int current = v;
    while(current != u){
      path.add(current);
      current = parent[current];
    }
    path.add(u);
    return path;
  }
}
