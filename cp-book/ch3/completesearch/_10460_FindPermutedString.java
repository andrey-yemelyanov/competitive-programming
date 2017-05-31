import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10460 Find the Permuted String
Problem url: https://uva.onlinejudge.org/external/104/10460.pdf
Author: Andrey Yemelyanov
*/
public class _10460_FindPermutedString {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- >  0){
      String str = s.next();
      int n = s.nextInt();
      System.out.println(getNthPermutation(str, n));
    }
  }

  static String getNthPermutation(String str, int n){
    StringBuilder p = new StringBuilder();
    p.append(str.charAt(0));
    getNthPermutation(n, str, p, BigInteger.ONE, factorial(str.length()), 1);
    return p.toString();
  }

  static BigInteger factorial(int n){
    BigInteger f = BigInteger.ONE;
    for(int i = 1; i <= n; i++){
      f = f.multiply(BigInteger.valueOf(i));
    }
    return f;
  }

  static void getNthPermutation(int n, String str, StringBuilder p, BigInteger from, BigInteger to, int i){
    if(p.length() == str.length()) return;
    BigInteger[] range = getRange(p.toString(), from, to, BigInteger.valueOf(n));
    p.insert(range[0].intValue(), str.charAt(i));
    getNthPermutation(n, str, p, range[1], range[2], i + 1);
  }

  static BigInteger[] getRange(String str, BigInteger from, BigInteger to, BigInteger n){
    //System.out.println("from = " + from + " to = " + to);
    int nRanges = str.length() + 1;
    BigInteger nPerRange = to.subtract(from).add(BigInteger.ONE).divide(BigInteger.valueOf(nRanges));
    for(int range = 0; range < nRanges; range++){
      BigInteger low = from.add(nPerRange.multiply(BigInteger.valueOf(range)));
      BigInteger high = low.add(nPerRange).subtract(BigInteger.ONE);
      if(n.compareTo(low) >= 0 && n.compareTo(high) <= 0){
        return new BigInteger[]{BigInteger.valueOf(range), low, high};
      }
    }
    return null;
  }
}
