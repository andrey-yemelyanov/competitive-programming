import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10268 498-bis
Problem url: https://uva.onlinejudge.org/external/102/10268.pdf
Author: Andrey Yemelyanov
*/
public class _10268 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int x = s.nextInt();
      s.nextLine();
      String[] line = s.nextLine().split("\\s+");
      int[] c = new int[line.length];
      for(int i = 0; i < c.length; i++) c[i] = Integer.parseInt(line[i]);
      System.out.println(evalPolynomialDerivative(c, x));
    }
  }

  static int evalPolynomialDerivative(int[] c, int x){
    int n = c.length - 1;
    int result = c[0] * n;
    for(int i = 1; i < n; i++){
      result = result * x + c[i] * (n - i);
    }
    return result;
  }
}
