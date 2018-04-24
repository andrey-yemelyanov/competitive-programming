import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10212 The Last Non-zero Digit.
Problem url: https://uva.onlinejudge.org/external/102/10212.pdf
Author: Andrey Yemelyanov
*/
public class _10212 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      System.out.println(getLastDigit(s.nextInt(), s.nextInt()));
    }
  }

  static int getLastDigit(int N, int M){
    long result = 1;
    for(long i = N; i > N - M; i--){
      result = discardTrailingZeros(result * i) % MOD;
    }
    return (int)(result % 10);
  }

  static final int MOD = 1000000000;
  static long discardTrailingZeros(long n){
    while(n % 10 == 0) n /= 10;
    return n;
  }
}
