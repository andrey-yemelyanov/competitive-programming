import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10090 Marbles
Problem url: https://uva.onlinejudge.org/external/100/10090.pdf
Author: Andrey Yemelyanov
*/
public class _10090 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break;
      int c1 = s.nextInt(); int n1 = s.nextInt();
      int c2 = s.nextInt(); int n2 = s.nextInt();
      long[] solution = solve(N, c1, n1, c2, n2);
      if(solution == null) System.out.println("failed");
      else System.out.println(solution[0] + " " + solution[1]);
    }
  }

  static int gcd(int a, int b){return b == 0 ? a : gcd(b, a % b);}

  static long[] solve(long N, long c1, long n1, long c2, long n2){
    int d = gcd((int)n1, (int)n2);
    if(N % d != 0) return null;
    extendedEuclid((int)n1, (int)n2);
    long from = (long)ceil((-x * N) / (double)n2);
    long to = (long)floor((y * N) / (double)n1);
    if(from > to) return null;
    long lowestCost = Long.MAX_VALUE; long bestM1 = 0; long bestM2 = 0;
    for(long n : new long[]{from, to}){
      long m1 = (x * N + n2 * n) / d;
      long m2 = (y * N - n1 * n) / d;
      long cost = c1 * m1 + c2 * m2;
      if(cost < lowestCost){
        lowestCost = cost;
        bestM1 = m1; bestM2 = m2;
      }
    }
    return new long[]{bestM1, bestM2};
  }

  static int x; static int y; static int d;
  static void extendedEuclid(int a, int b) {
    if (b == 0) { x = 1; y = 0; d = a; return; } // base case
    extendedEuclid(b, a % b); // similar as the original gcd
    int x1 = y;
    int y1 = x - (a / b) * y;
    x = x1;
    y = y1;
  }
}
