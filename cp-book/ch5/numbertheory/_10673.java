import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10673 Play with Floor and Ceil
Problem url: https://uva.onlinejudge.org/external/106/10673.pdf
Author: Andrey Yemelyanov
*/
public class _10673 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int c = s.nextInt();
      double div = c / (double) s.nextInt();
      long a = (long)floor(div);
      long b = (long)ceil(div);
      extendedEuclid((int)a, (int)b);
      System.out.printf("%d %d\n", x * (c / d), y * (c / d));
    }
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
