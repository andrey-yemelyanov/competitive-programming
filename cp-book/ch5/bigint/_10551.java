import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10551 Basic Remains
Problem url: https://uva.onlinejudge.org/external/105/10551.pdf
Author: Andrey Yemelyanov
*/
public class _10551 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int b = s.nextInt();
      if(b == 0) break;
      System.out.println(
        new BigInteger(s.next(), b).mod(new BigInteger(s.next(), b)).toString(b)
      );
    }
  }
}
