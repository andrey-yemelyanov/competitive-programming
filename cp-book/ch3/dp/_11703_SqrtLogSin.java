import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 11703 sqrt log sin
Problem url: https://uva.onlinejudge.org/external/117/11703.pdf
Author: Andrey Yemelyanov
*/
public class _11703_SqrtLogSin {
  static final int MOD = 1000000;
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int[] memo = new int[MOD + 1];
    Arrays.fill(memo, -1);
    while(s.hasNext()){
      int i = s.nextInt();
      if(i == -1) break;
      System.out.println(X(i, memo));
    }
  }

  static final int[] sqrt = new int[MOD + 1];
  static final int[] log = new int[MOD + 1];
  static final int[] sin = new int[MOD + 1];
  static{
    for(int i = 1; i < sqrt.length; i++){
      sqrt[i] = (int)floor(i - sqrt(i));
    }
    for(int i = 1; i < log.length; i++){
      log[i] = (int)floor(log(i));
    }
    for(int i = 1; i < sin.length; i++){
      sin[i] = (int)floor(i * pow(sin(i), 2));
    }
  }

  static int X(int i, int[] memo){
    if(i == 0) return 1;
    if(memo[i] != -1) return memo[i];
    int Xi = X(sqrt[i], memo) + X(log[i], memo) + X(sin[i], memo);
    return memo[i] = Xi % MOD;
  }
}
