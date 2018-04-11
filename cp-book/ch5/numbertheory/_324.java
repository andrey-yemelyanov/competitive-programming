import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.*;

/*
Problem name: 324 Factorial Frequencies
Problem url: https://uva.onlinejudge.org/external/3/324.pdf
Author: Andrey Yemelyanov
*/
public class _324 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      int[] digit = countFreqs(n);
      System.out.printf("%d! --\n", n);
      System.out.printf("    (0)  %3d    (1)  %3d    (2)  %3d    (3)  %3d   (4)  %3d\n", digit[0], digit[1], digit[2], digit[3], digit[4]);
      System.out.printf("    (5)  %3d    (6)  %3d    (7)  %3d    (8)  %3d   (9)  %3d\n", digit[5], digit[6], digit[7], digit[8], digit[9]);
    }
  }

  static int[] countFreqs(int n){
    BigInteger f = BigInteger.ONE;
    for(int i = 1; i <= n; i++) {
      f = f.multiply(BigInteger.valueOf(i));
    }
    char[] digits = f.toString().toCharArray();
    int[] freq = new int[10];
    for(char c : digits){
      freq[Character.getNumericValue(c)]++;
    }
    return freq;
  }
}
