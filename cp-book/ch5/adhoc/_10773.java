import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10773 Back to Intermediate Math
Problem url: https://uva.onlinejudge.org/external/107/10773.pdf
Author: Andrey Yemelyanov
*/
public class _10773 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      double diff = diff(s.nextInt(), s.nextInt(), s.nextInt());
      if(diff == -1) System.out.printf("Case %d: can't determine\n", c);
      else System.out.printf(Locale.US, "Case %d: %.3f\n", c, diff);
    }
  }

  static double diff(int d, int v, int u){
    if(u == 0 || v == 0 || u <= v) return -1;
    return shortestCrossing(d, v, u) - fastestCrossing(d, v, u);
  }

  static double fastestCrossing(int d, int v, int u){
    double D = sqrt(pow(d, 2) + pow(((double)d * v / u), 2));
    double V = sqrt(pow(u, 2) + pow(v, 2));
    return D / V;
  }

  static double shortestCrossing(int d, int v, int u){
    double D = d;
    double V = sqrt(pow(u, 2) - pow(v, 2));
    return D / V;
  }
}
