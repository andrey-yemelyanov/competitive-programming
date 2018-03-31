import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 1230 MODEX
Problem url: https://uva.onlinejudge.org/external/12/1230.pdf
Author: Andrey Yemelyanov
*/
public class _1230 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      System.out.println(
        s.nextBigInteger().modPow(
          s.nextBigInteger(),
          s.nextBigInteger()
        )
      );
    }
  }
}
