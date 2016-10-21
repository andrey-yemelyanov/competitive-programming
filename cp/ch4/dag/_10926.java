import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10926 How Many Dependencies?
Problem url: https://uva.onlinejudge.org/external/109/10926.pdf
Author: yemelyanov
*/
public class _10926 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int nTasks = s.nextInt();
      if(nTasks == 0) break;
      Map<Integer, List<Integer>> dag = new HashMap<>();
      for(int i = 0; i < nTasks; i++){
        dag.put(i, new ArrayList<>());
      }
      for(int i = 0; i < nTasks; i++){
        int nDep = s.nextInt();
        while(nDep-- > 0){
          int dependency = s.nextInt() - 1;
          dag.get(dependency).add(i);
        }
      }
      System.out.println(nodeWithMaxDependencies(dag, toIncoming(dag)) + 1);
    }
  }

  static Map<Integer, List<Integer>> toIncoming(Map<Integer, List<Integer>> dag){
    Map<Integer, List<Integer>> incoming = new HashMap<>();
    for(int v : dag.keySet()){
      incoming.put(v, new ArrayList<>());
    }
    for(int u : dag.keySet()){
      for(int v : dag.get(u)){
        incoming.get(v).add(u);
      }
    }
    return incoming;
  }

  static List<Integer> terminalVertices(Map<Integer, List<Integer>> dag){
    List<Integer> list = new ArrayList<>();
    for(int v : dag.keySet()){
      if(dag.get(v).size() == 0) list.add(v);
    }
    return list;
  }

  static int nodeWithMaxDependencies(Map<Integer, List<Integer>> dag, Map<Integer, List<Integer>> incoming){
    memo = new ArrayList<>();
    for(int i = 0; i < dag.size(); i++){
      memo.add(null);
    }
    int maxDeps = terminalVertices(dag).stream()
                                       .mapToInt(v -> explore(v, dag, incoming).size())
                                       .max()
                                       .getAsInt();
    for(int i = 0; i < memo.size(); i++){
      if(memo.get(i).size() == maxDeps) return i;
    }
    return -1;
  }

  static List<Set<Integer>> memo;
  static Set<Integer> explore(int source, Map<Integer, List<Integer>> dag, Map<Integer, List<Integer>> incoming){
    if(memo.get(source) != null) return memo.get(source);
    List<Integer> inVertices = incoming.get(source);
    Set<Integer> deps = new HashSet<>();
    deps.addAll(inVertices);
    for(int v : inVertices){
      deps.addAll(explore(v, dag, incoming));
    }
    memo.set(source, deps);
    return deps;
  }
}
