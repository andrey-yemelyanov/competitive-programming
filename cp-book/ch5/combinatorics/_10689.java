import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10689 Yet another Number Sequence
Problem url: https://uva.onlinejudge.org/external/106/10689.pdf
Author: Andrey Yemelyanov
*/
public class _10689 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int a = s.nextInt(); int b = s.nextInt(); int n = s.nextInt(); int m = s.nextInt();
      System.out.println(getLastFibonacciDigits(a, b, n, m));
    }
  }

  static final int[] period = new int[]{60, 300, 1500, 15000};
  static final int[] mod = new int[]{10, 100, 1000, 10000};

  static int getLastFibonacciDigits(int a, int b, int n, int m){
    int k = (n % period[m - 1]) + 1;
    int[] fib = new int[k];
    fib[0] = a % mod[m - 1];
    if(k == 1) return fib[0];
    fib[1] = b % mod[m - 1];
    for(int i = 2; i < k; i++){
      fib[i] = (fib[i - 1] + fib[i - 2]) % mod[m - 1];
    }
    return fib[k - 1];
  }
}
