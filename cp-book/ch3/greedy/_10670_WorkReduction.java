import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10670 Work Reduction
Problem url: https://uva.onlinejudge.org/external/106/10670.pdf
Author: Andrey Yemelyanov
*/
public class _10670_WorkReduction {
  static class Agency{
    public String name;
    public int reduceByOneCost;
    public int reduceByHalfCost;
    public int minCost;
    public Agency(String name, int reduceByOneCost, int reduceByHalfCost, int minCost){
      this.name = name;
      this.reduceByOneCost = reduceByOneCost;
      this.reduceByHalfCost = reduceByHalfCost;
      this.minCost = minCost;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int N = s.nextInt(); int M = s.nextInt();
      int nAgencies = s.nextInt(); s.nextLine();
      Agency[] agencies = new Agency[nAgencies];
      for(int i = 0; i < nAgencies; i++){
        String line = s.nextLine();
        String name = line.split(":")[0];
        int reduceByOneCost = Integer.parseInt(line.split(":")[1].split(",")[0]);
        int reduceByHalfCost = Integer.parseInt(line.split(":")[1].split(",")[1]);
        int minCost = minCost(N, M, reduceByOneCost, reduceByHalfCost);
        agencies[i] = new Agency(name, reduceByOneCost, reduceByHalfCost, minCost);
      }
      Arrays.sort(agencies, new Comparator<Agency>(){
        @Override
        public int compare(Agency a1, Agency a2){
          if(a1.minCost == a2.minCost) return a1.name.compareTo(a2.name);
          return Integer.compare(a1.minCost, a2.minCost);
        }
      });
      System.out.printf("Case %d\n", c);
      Arrays.stream(agencies).map(a -> a.name + " " + a.minCost).forEach(System.out::println);
    }
  }

  static int minCost(int N, int M, int reduceByOneCost, int reduceByHalfCost){
    int cost = 0;
    while(true){
      if(N / 2 < M){
        cost += (N - M) * reduceByOneCost;
        break;
      }
      if(reduceByHalfCost < (N - N / 2) * reduceByOneCost){
        N /= 2;
        cost += reduceByHalfCost;
      }else{
        cost += (N - N / 2) * reduceByOneCost;
        N /= 2;
      }
      if(N == M) break;
    }
    return cost;
  }
}
