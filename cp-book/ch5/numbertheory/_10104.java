import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10104 Euclid Problem
Problem url: https://uva.onlinejudge.org/external/101/10104.pdf
Author: Andrey Yemelyanov
*/
public class _10104 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int A = s.nextInt(); int B = s.nextInt();
      extendedEuclid(A, B);
      System.out.printf("%d %d %d\n", x, y, d);
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
