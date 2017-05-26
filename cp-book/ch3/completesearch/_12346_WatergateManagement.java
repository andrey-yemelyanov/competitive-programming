import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12346 Water Gate Management
Problem url: https://uva.onlinejudge.org/external/123/12346.pdf
Author: Andrey Yemelyanov
*/
public class _12346_WatergateManagement {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    Gate[] gates = new Gate[n];
    for(int i = 0; i < n; i++){
      gates[i] = new Gate(s.nextInt(), s.nextInt());
    }
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int V = s.nextInt(); int T = s.nextInt();
      long cost = getMinFlushingCost(gates, V, T);
      if(cost == IMPOSSIBLE) System.out.printf("Case %d: IMPOSSIBLE\n", c);
      else System.out.printf("Case %d: %d\n", c, cost);
    }
  }

  static long getMinFlushingCost(Gate[] gates, int V, int T){
    return getMinFlushingCost(gates, V, T, 0, 0, 0);
  }

  static class Gate{
    public int flowRate;
    public int cost;
    public Gate(int flowRate, int cost){
      this.flowRate = flowRate;
      this.cost = cost;
    }
  }

  static final long IMPOSSIBLE = Long.MAX_VALUE;
  static long getMinFlushingCost(Gate[] gates, int V, int T, int i, long cost, long throughput){
    if(i == gates.length){
      if(throughput >= V) return cost;
      return IMPOSSIBLE;
    }
    return min(
      getMinFlushingCost(gates, V, T, i + 1, cost, throughput),
      getMinFlushingCost(gates, V, T, i + 1, cost + gates[i].cost, throughput + gates[i].flowRate * T)
    );
  }
}
