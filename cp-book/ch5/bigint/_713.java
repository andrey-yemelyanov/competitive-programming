import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.*;

/*
Problem name: 713 Adding Reversed Numbers
Problem url: https://uva.onlinejudge.org/external/7/713.pdf
Author: Andrey Yemelyanov
*/
public class _713 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      System.out.println(add(s.next(), s.next()));
    }
  }

  static String add(String num1, String num2){
    BigInteger x = new BigInteger(new StringBuilder(num1).reverse().toString());
    BigInteger y = new BigInteger(new StringBuilder(num2).reverse().toString());
    BigInteger sum = new BigInteger(new StringBuilder(x.add(y).toString()).reverse().toString());
    return sum.toString();
  }
}
