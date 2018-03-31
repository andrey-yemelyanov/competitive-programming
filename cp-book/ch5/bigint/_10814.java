import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10814 Simplifying Fractions
Problem url: https://uva.onlinejudge.org/external/108/10814.pdf
Author: Andrey Yemelyanov
*/
public class _10814 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt(); s.nextLine();
    while(nTests-- > 0){
      String[] line = s.nextLine().split("\\s+/\\s+");
      BigInteger p = new BigInteger(line[0]);
      BigInteger q = new BigInteger(line[1]);
      BigInteger gcd = p.gcd(q);
      System.out.printf("%s / %s\n", p.divide(gcd).toString(), q.divide(gcd).toString());
    }
  }
}
