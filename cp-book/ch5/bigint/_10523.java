import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.*;

/*
Problem name: 10523 Very Easy !!!
Problem url: https://uva.onlinejudge.org/external/105/10523.pdf
Author: Andrey Yemelyanov
*/
public class _10523 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int N = s.nextInt(); int A = s.nextInt();
      System.out.println(eval(N, A));
    }
  }

  static String eval(int N, int A){
    BigInteger sum = BigInteger.ZERO;
    for(int i = 1; i <= N; i++){
      sum = sum.add(
        new BigInteger(Integer.toString(i))
          .multiply(
            new BigInteger(Integer.toString(A)).pow(i)
          ));
    }
    return sum.toString();
  }
}
