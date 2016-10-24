import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 590 Always on the run
Problem url: https://uva.onlinejudge.org/external/5/590.pdf
Author: yemelyanov
*/
public class _590 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int caseNum = 1;
    while(s.hasNext()){
      nCities = s.nextInt(); int k = s.nextInt();
      if(nCities == 0 && k == 0) break;
      flightSchedules = new ArrayList<>();
      for(int i = 0; i < nCities * nCities; i++){
        flightSchedules.add(new ArrayList<>());
      }
      int m = 1; int nRead = 0;
      for(int i = 0; i < nCities * (nCities - 1); i++){
        int d = s.nextInt();
        for(int j = 0; j < d; j++){
          flightSchedules.get(m).add(s.nextInt());
        }
        m++; nRead++;
        if(nRead == nCities){
          nRead = 0; m++;
        }
      }
      int minCost = escape(k);
      System.out.printf("Scenario #%d\n", caseNum++);
      if(minCost == INF){
        System.out.println("No flight possible.");
      }else{
        System.out.printf("The best flight costs %d.\n", minCost);
      }
      System.out.println();
    }
  }

  static int escape(int k){
    memo = new int[nCities][k + 1];
    for(int i = 0; i < memo.length; i++){
      for(int j = 0; j < memo[0].length; j++){
        memo[i][j] = INF;
      }
    }
    return escape(0, nCities - 1, k, 0);
  }

  static int escape(int current, int dest, int daysLeft, int currentDay){
    if(daysLeft < 0) return INF;
    if(current == dest && daysLeft == 0) return 0;
    if(memo[current][daysLeft] != INF) return memo[current][daysLeft];
    int minCost = INF;
    for(int city = 0; city < nCities; city++){
      if(city != current && flightAvailable(current, city, currentDay)){
        int cost = escape(city, dest, daysLeft - 1, currentDay + 1);
        minCost = min(minCost, cost + getFlightCost(current, city, currentDay));
      }
    }
    return memo[current][daysLeft] = minCost;
  }

  static final int INF = 10000000;
  static int[][] memo;
  static List<List<Integer>> flightSchedules;
  static int nCities;
  static int getFlightCost(int from, int to, int day){
    List<Integer> schedule = flightSchedules.get(from * nCities + to);
    return schedule.get(day % schedule.size());
  }
  static boolean flightAvailable(int from, int to, int day){
    return getFlightCost(from, to, day) != 0;
  }
}
