import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 11287 Pseudoprime Numbers
Problem url: https://uva.onlinejudge.org/external/112/11287.pdf
Author: Andrey Yemelyanov
*/
public class _11287 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int p = s.nextInt(); int a = s.nextInt();
      if(p == 0 && a == 0) break;
      System.out.println(isBaseAPseudoprime(BigInteger.valueOf(p), BigInteger.valueOf(a)) ? "yes" : "no");
    }
  }

  static boolean isBaseAPseudoprime(BigInteger p, BigInteger a){
    if(p.isProbablePrime(10)) return false;
    return a.modPow(p, p).equals(a);
  }
}
