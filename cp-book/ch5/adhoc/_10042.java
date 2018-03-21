import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10042 Smith Numbers
Problem url: https://uva.onlinejudge.org/external/100/10042.pdf
Author: Andrey Yemelyanov
*/
public class _10042 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int n = s.nextInt();
      int m = n + 1;
      while(!isSmithNumber(m)) m++;
      System.out.println(m);
    }
  }

  static boolean isSmithNumber(int n){
    int factorsDigitSum = 0;
    List<Integer> factors = factorize(n);
    if(factors.size() == 1) return false;
    for(int factor : factors) factorsDigitSum += digitSum(factor);
    return digitSum(n) == factorsDigitSum;
  }

  static List<Integer> factorize(int n){
    //System.out.print(n + "=>");
    List<Integer> factors = new ArrayList<>();
    while(n % 2 == 0){
      factors.add(2);
      n /= 2;
    }
    for(int i = 3; i <= sqrt(n); i += 2){
      while(n % i == 0){
        factors.add(i);
        n /= i;
      }
    }
    if(n > 2) factors.add(n);
    //System.out.println(factors);
    return factors;
  }

  static int digitSum(int n){
    int sum = 0;
    while(n != 0){
      sum += n % 10;
      n /= 10;
    }
    return sum;
  }
}
