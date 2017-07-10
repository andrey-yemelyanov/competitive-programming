import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 410 Station Balance
Problem url: https://uva.onlinejudge.org/external/4/410.pdf
Author: Andrey Yemelyanov
*/
public class _410_StationBalance {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int set = 1;
    while(s.hasNext()){
      int C = s.nextInt(); int S = s.nextInt();
      int[] M = new int[S];
      for(int i = 0; i < S; i++){
        M[i] = s.nextInt();
      }
      int[] distr = minimizeImbalance(C, M);
      double imbalance = getImbalance(distr, C);
      System.out.printf("Set #%d\n", set++);
      for(int i = 0; i < distr.length / 2; i++){
        String items = "";
        if(distr[i] != 0) items += distr[i];
        if(distr[distr.length - i - 1] != 0) {
          if(!items.equals("")) items += " ";
          items += distr[distr.length - i - 1];
        }
        if(!items.equals("")) items = " " + items;
        System.out.printf(" %d:%s\n", i, items);
      }
      System.out.printf(Locale.US, "IMBALANCE = %.5f\n", imbalance);
      System.out.println();
    }
  }

  static int[] minimizeImbalance(int C, int[] M){
    int S = M.length;
    int[] distr = M;
    if(S < 2 * C){
      distr = new int[2 * C];
      for(int i = 0; i < M.length; i++){
        distr[i] = M[i];
      }
    }
    Arrays.sort(distr);
    return distr;
  }

  static double getImbalance(int[] distr, int C){
    double avgLoad = Arrays.stream(distr).sum() / (double) C;
    double imbalance = 0;
    for(int i = 0; i < distr.length / 2; i++){
      int load = distr[i] + distr[distr.length - i - 1];
      imbalance += abs(load - avgLoad);
    }
    return imbalance;
  }
}
