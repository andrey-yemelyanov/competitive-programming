import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10543 Traveling Politician
Problem url: https://uva.onlinejudge.org/external/105/10543.pdf
Author: Andrey Yemelyanov
*/
public class _10543 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    StringBuilder out = new StringBuilder();

    while(s.hasNext()){
      try{
        int n = readInt(s); int m = readInt(s); int k = readInt(s);
        if(n == 0 && m == 0 && k == 0) break;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for(int i = 0; i < m; i++) graph.get(readInt(s)).add(readInt(s));
        int minSpeeches = travel(k, graph);
        if(minSpeeches == INF) out.append("LOSER\n");
        else out.append(minSpeeches + "\n");
      }catch(RuntimeException e){
        break;
      }
    }
    System.out.print(out.toString());
  }

  static int readInt(Scanner s){
    while(s.hasNext() && !s.hasNextInt()) s.next();
    if(s.hasNext()) return s.nextInt();
    throw new RuntimeException("No more input");
  }

  static int travel(int k, Map<Integer, List<Integer>> graph){
    memo = new int[graph.size()][MAX_SPEECHES + 1];
    for(int i = 0; i < memo.length; i++){
      for(int j = 0; j < memo[i].length; j++){
        memo[i][j] = UNDEF;
      }
    }
    return travel(0, graph.size() - 1, 1, k - 1, graph);
  }

  static final int MAX_SPEECHES = 20;
  static final int INF = 10000000;
  static final int UNDEF = -1;
  static int[][] memo;
  static int travel(int current, int dest, int nSpeechesGiven, int k, Map<Integer, List<Integer>> graph){
    if(nSpeechesGiven > MAX_SPEECHES) return INF;
    if(current == dest && nSpeechesGiven >= k + nSpeechesGiven) return 1;
    if(memo[current][nSpeechesGiven] != UNDEF) return memo[current][nSpeechesGiven];
    int minSpeeches = INF;
    for(int next : graph.get(current)){
      minSpeeches = min(minSpeeches, travel(next, dest, nSpeechesGiven + 1, k - 1, graph) + 1);
    }
    return memo[current][nSpeechesGiven] = minSpeeches;
  }
}
