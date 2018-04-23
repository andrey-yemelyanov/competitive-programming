import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;
import java.math.*;

/*
Problem name: 10176 Ocean Deep! - Make it shallow!!
Problem url: https://uva.onlinejudge.org/external/101/10176.pdf
Author: Andrey Yemelyanov
*/
public class _10176 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      StringBuilder sb = new StringBuilder();
      sb.append(s.next().trim());
      while(sb.charAt(sb.length() - 1) != '#'){
        sb.append(s.next().trim());
      }
      sb.deleteCharAt(sb.indexOf("#"));
      if(isDivisibleByPrime(sb.toString())) System.out.println("YES");
      else System.out.println("NO");
    }
  }

  static final int MOD = 131071;
  static boolean isDivisibleByPrime(String binaryString){
    int res = 0;
    for(int i = 0; i < binaryString.length(); i++){
      int pow = binaryString.length() - i - 1;
      if(binaryString.charAt(i) == '1') {
        res = (res + modPow(2, pow, MOD)) % MOD;
      }
    }
    return res == 0;
  }

  static int modPow(long x, long y, long mod){
    long res = 1;
    x %= mod;
    while(y > 0){
      if(y % 2 != 0) res = (res * x) % mod;
      y >>= 1;
      x = (x * x) % mod;
    }
    return (int)res;
  }
}
