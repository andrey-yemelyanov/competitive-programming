import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10219 Find the ways !
Problem url: https://uva.onlinejudge.org/external/102/10219.pdf
Author: Andrey Yemelyanov
*/
public class _10219 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int n = s.nextInt();
      int k = s.nextInt();
      int nDigits = 0;
      if(k > (n - k)){
        nDigits = (int)(logFactorial(n, k + 1) - logFactorial(n - k, 1)) + 1;
      }else{
        nDigits = (int)(logFactorial(n, n - k + 1) - logFactorial(k, 1)) + 1;
      }
      System.out.println(nDigits);
    }
  }

  static double logFactorial(int n, int k){
    double result = 0;
    for(int i = n; i >= k; i--) {
      result += log10(i);
    }
    return result;
  }
}
