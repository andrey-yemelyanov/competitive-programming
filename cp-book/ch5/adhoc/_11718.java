import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11718 Fantasy of a Summation
Problem url: https://uva.onlinejudge.org/external/117/11718.pdf
Author: Andrey Yemelyanov
Big thanks to https://yuting-zhang.github.io/uva/2016/12/26/UVa-11718.html for explanation
*/
public class _11718 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int n = s.nextInt(); int K = s.nextInt(); int MOD = s.nextInt();
      int[] A = new int[n];
      for(int i = 0; i < n; i++) A[i] = s.nextInt();
      System.out.printf("Case %d: %d\n", c, getResult(A, K, MOD));
    }
  }

  static long getResult(int[] A, int K, int MOD){
    long modSum = 0;
    for(int i = 0; i < A.length; i++){
      modSum += A[i];
      modSum %= MOD;
    }
    return (modSum * modPow(A.length, K - 1, MOD) * (K % MOD)) % MOD;
  }

  static long modPow(int base, int exp, int mod){
    if(exp == 0) return 1;
    if(exp % 2 == 0){
      long result = modPow(base, exp / 2, mod);
      return (result * result) % mod;
    }else{
      long result = modPow(base, exp / 2, mod);
      return (base * ((result * result) % mod)) % mod;
    }
  }
}
