import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.*;

/*
Problem name: 11879 Multiple of 17
Problem url: https://uva.onlinejudge.org/external/118/11879.pdf
Author: Andrey Yemelyanov
*/
public class _11879 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      BigInteger num = s.nextBigInteger();
      if(num.equals(BigInteger.ZERO)) break;
      System.out.println(isMultipleOf17(num) ? 1 : 0);
    }
  }

  static boolean isMultipleOf17(BigInteger num){
    return num.divideAndRemainder(new BigInteger("17"))[1].equals(BigInteger.ZERO);
  }
}
