import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 471 Magic Numbers
Problem url: https://uva.onlinejudge.org/external/4/471.pdf
Author: Andrey Yemelyanov
*/
public class _471_MagicNumbers {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    s.nextLine();
    while(nTests-- > 0){
      long N = s.nextLong();
      printPairs(N);
      if(nTests > 0) System.out.println();
    }
  }

  static void printPairs(long N){
    final long MAX = 9876543210L;
    for(long s2 = 1; s2 * N <= MAX; s2++){
      if(allDigitsDistinct(s2) && allDigitsDistinct(s2 * N)){
        System.out.println(s2 * N + " / " + s2 + " = " + N);
      }
    }
  }

  static boolean allDigitsDistinct(long n){
    int set = 0;
    while(n > 0){
      int digit = (int)(n % 10);
      if((set & (1 << digit)) > 0) return false;
      set |= (1 << digit);
      n /= 10;
    }
    return true;
  }
}
