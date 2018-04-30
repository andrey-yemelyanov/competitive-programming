import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10056 What is the Probability ?
Problem url: https://uva.onlinejudge.org/external/100/10056.pdf
Author: Andrey Yemelyanov
*/
public class _10056 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    s.useLocale(Locale.US);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int N = s.nextInt(); double p = s.nextDouble(); int I = s.nextInt();
      System.out.printf("%.4f\n", probabilityOfWinning(N, I, p));
    }
  }

  static double probabilityOfWinning(int N, int I, double p){
    final double EPS = 1E-8;
    double probability = 0.0;
    int round = 0;
    while(true){
      double toAdd = p * pow(1 - p, N * round + I - 1);
      round++;
      if(toAdd < EPS) break;
      probability += toAdd;
    }
    return probability;
  }
}
