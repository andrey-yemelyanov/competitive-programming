import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 498 Polly the Polynomial
Problem url: https://uva.onlinejudge.org/external/4/498.pdf
Author: Andrey Yemelyanov
*/
public class _498 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String[] line = s.nextLine().split("\\s+");
      int[] c = new int[line.length];
      for(int i = 0; i < c.length; i++){
        c[i] = Integer.parseInt(line[i]);
      }
      line = s.nextLine().split("\\s+");
      int[] x = new int[line.length];
      for(int i = 0; i < x.length; i++){
        x[i] = Integer.parseInt(line[i]);
      }
      double[] result = evalPolynomial(c, x);
      StringJoiner sj = new StringJoiner(" ");
      for(double d : result) sj.add(Long.toString(new Double(d).longValue()));
      System.out.println(sj.toString());
    }
  }

  static double[] evalPolynomial(int[] c, int[] x){
    double[] result = new double[x.length];
    for(int i = 0; i < x.length; i++){
      result[i] = evalPolynomial(c, x[i]);
    }
    return result;
  }

  static double evalPolynomial(int[] c, int x){
    int n = c.length - 1;
    double val = 0;
    for(int i = 0; i < c.length; i++){
      val += c[i] * pow(x, n - i);
    }
    return val;
  }
}
