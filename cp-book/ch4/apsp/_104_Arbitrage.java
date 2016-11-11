import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 104 Arbitrage
Problem url: https://uva.onlinejudge.org/external/1/104.pdf
Author: Andrey Yemelyanov
Alg from : https://uva.onlinejudge.org/board/viewtopic.php?f=1&t=652&hilit=104&start=135
*/
public class _104_Arbitrage {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in).useLocale(Locale.US);
    while(s.hasNext()){
      int V = s.nextInt();
      double[][] adjMatrix = new double[V][V];
      for(int i = 0; i < V; i++){
        for(int j = 0; j < V; j++){
          if(i == j) adjMatrix[i][j] = 1.0;
          else adjMatrix[i][j] = s.nextDouble();
        }
      }

      List<Integer> arbitrage = exploreArbitrageOpportunities(adjMatrix, V);
      if(arbitrage == null){
        System.out.println("no arbitrage sequence exists");
      }else{
        System.out.println(arbitrage.stream()
                                    .map(i -> new Integer(i + 1).toString())
                                    .collect(Collectors.joining(" ")));
      }
    }
  }

  static List<Integer> exploreArbitrageOpportunities(double[][] adjMatrix, int maxExchanges){
    int V = adjMatrix.length;
    double[][][] best = new double[V][V][maxExchanges + 1];
    for(int i = 0; i < best.length; i++){
      for(int j = 0; j < best[i].length; j++){
        for(int s = 0; s < best[i][j].length; s++){
          if(s == 1) best[i][j][s] = adjMatrix[i][j];
        }
      }
    }

    int[][][] path = new int[V][V][maxExchanges + 1];
    for(int i = 0; i < best.length; i++){
      for(int j = 0; j < best[i].length; j++){
        for(int s = 0; s < best[i][j].length; s++){
          if(s == 1) path[i][j][s] = i;
        }
      }
    }

    for(int s = 2; s <= maxExchanges; s++){
      for(int k = 0; k < V; k++){
        for(int i = 0; i < V; i++){
          for(int j = 0; j < V; j++){
            double candidate = best[i][k][s - 1] * best[k][j][1];
            if(candidate > best[i][j][s]){
              best[i][j][s] = candidate;
              path[i][j][s] = k; // meaning: vertex k is the vertex just before j
            }
          }
        }
      }
    }

    for(int s = 2; s <= maxExchanges; s++){
      for(int i = 0; i < V; i++){
        if(best[i][i][s] > 1.01){
          //System.out.printf("Currency %d, profit = %f in %d steps\n", i, best[i][i][s], s);
          List<Integer> p = new ArrayList<>();
          buildPath(i, i, s, path, p);
          p.add(i);
          //System.out.println(p);
          return p;
        }
      }
    }

    return null;
  }

  static void buildPath(int i, int j, int s, int[][][] path, List<Integer> p){
    //System.out.printf("Path from %d to %d = %d\n", i, j, path[i][j][s]);
    if(path[i][j][s] == i && s == 1) {
      p.add(i);
      return;
    }
    buildPath(i, path[i][j][s], s - 1, path, p);
    p.add(path[i][j][s]);
  }

  static void print(double[][] adjMatrix){
    for(int i = 0; i < adjMatrix.length; i++){
      System.out.println(Arrays.toString(adjMatrix[i]));
    }
    System.out.println();
  }
}
