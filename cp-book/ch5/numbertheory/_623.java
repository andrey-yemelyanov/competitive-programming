import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.*;

/*
Problem name: 623 500!
Problem url: https://uva.onlinejudge.org/external/6/623.pdf
Author: Andrey Yemelyanov
*/
public class _623 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int n = s.nextInt();
      System.out.printf("%d!\n", n);
      System.out.println(factorial(n));
    }
  }

  static BigInteger factorial(int n){
    BigInteger f = BigInteger.ONE;
    for(int i = 1; i <= n; i++) f = f.multiply(BigInteger.valueOf(i));
    return f;
  }
}
