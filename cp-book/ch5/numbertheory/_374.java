import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 374 Big Mod
Problem url: https://uva.onlinejudge.org/external/3/374.pdf
Author: Andrey Yemelyanov
*/
public class _374 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      System.out.println(powMod(s.nextInt(), s.nextInt(), s.nextInt()));
    }
  }

  static int powMod(int x, int y, int mod){
    int res = 1;
    x = x % mod;
    while(y > 0){
      if((y & 1) == 1) res = (res * x) % mod;
      y >>= 1;
      x = (x * x) % mod;
    }
    return res;
  }
}
