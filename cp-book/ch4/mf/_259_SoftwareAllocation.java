import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 259 Software Allocation
Problem url: https://uva.onlinejudge.org/external/2/259.pdf
Author: Andrey Yemelyanov
*/
public class _259_SoftwareAllocation {
  static final int maxApps = 26;
  static final int maxComputers = 10;
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int V = maxApps + maxComputers + 2;
    final int source = 0; final int sink = V - 1;
    while(s.hasNext()){
      int[][] res = new int[V][V];
      int totalApps = 0;
      while(s.hasNext()){
        String line = s.nextLine();
        if(line == null || line.isEmpty()) break;
        int appVertex = line.charAt(0) - 'A' + 1;
        int appCount = Character.getNumericValue(line.charAt(1));
        totalApps += appCount;
        res[source][appVertex] = appCount;
        String computers = line.split("\\s+")[1].replace(";","");
        for(char c : computers.toCharArray()){
          int computerVertex = c - '0' + maxApps + 1;
          res[appVertex][computerVertex] = INF;
        }
      }
      for(int v = maxApps + 1; v < V - 1; v++) res[v][sink] = 1;
      int maxFlow = maxFlow(res, source, sink);
      if(maxFlow < totalApps){
        System.out.println("!");
      }else{
        int[] matching = getMatching(res);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < matching.length; i++){
          if(matching[i] == 0) sb.append("_");
          else{
            sb.append((char)(matching[i] - 1 + 'A'));
          }
        }
        System.out.println(sb.toString());
      }
    }
  }

  static int[] getMatching(int[][] res){
    int[] matching = new int[maxComputers];
    for(int computer = maxApps + 1; computer < res.length - 1; computer++){
      for(int app = 1; app < maxApps + 1; app++){
        if(res[computer][app] == 1){
          matching[computer - maxApps - 1] = app;
        }
      }
    }
    //System.out.println(Arrays.toString(matching));
    return matching;
  }

  static final int UNDEF = -1;
  static final int INF = 100000000;
  static int f;
  static void augment(int v, int source, int minEdge, int[][] res, int[] p){
    if(v == source){
      f = minEdge;
      return;
    }else if(p[v] != UNDEF){
      augment(p[v], source, min(minEdge, res[p[v]][v]), res, p);
      res[p[v]][v] -= f;
      res[v][p[v]] += f;
    }
  }

  static int maxFlow(int[][] res, int source, int sink){
    int V = res.length;
    int maxFlow = 0;
    while(true){
      f = 0;
      boolean[] visited = new boolean[V];
      visited[source] = true;
      Queue<Integer> q = new LinkedList<>();
      q.add(source);
      int[] p = new int[V]; for(int i = 0; i < V; i++) p[i] = UNDEF;
      while(!q.isEmpty()){
        int u = q.remove();
        if(u == sink) break;
        for(int v = 0; v < V; v++){
          if(res[u][v] > 0 && !visited[v]){
            visited[v] = true;
            q.add(v);
            p[v] = u;
          }
        }
      }
      augment(sink, source, INF, res, p);
      if(f == 0) break;
      maxFlow += f;
    }
    return maxFlow;
  }
}
