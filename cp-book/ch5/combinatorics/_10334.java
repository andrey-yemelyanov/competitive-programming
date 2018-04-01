import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10334 Ray Through Glasses
Problem url: https://uva.onlinejudge.org/external/103/10334.pdf
Author: Andrey Yemelyanov
*/
public class _10334 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      System.out.println(fib[s.nextInt()]);
    }
  }

  static BigInteger[] fib;
  static{
    fib = new BigInteger[1001];
    fib[0] = BigInteger.valueOf(1);
    fib[1] = BigInteger.valueOf(2);
    for(int i = 2; i < fib.length; i++){
      fib[i] = fib[i - 1].add(fib[i - 2]);
    }
  }
}
