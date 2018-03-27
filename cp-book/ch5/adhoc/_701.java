import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 701 The Archeologists' Dilemma
Problem url: https://uva.onlinejudge.org/external/7/701.pdf
Author: Andrey Yemelyanov
*/
public class _701 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
	  while(s.hasNext()){
      int N = s.nextInt();
      System.out.println(findSmallestExp(N));
    }
  }

  static int findSmallestExp(int N){
    // Big thanks to http://www.questtosolve.com/browse.php?pid=701 for a great explanation
    int X = countDigits(N) + 1;
    while(true){
      int ceil = (int)ceil(logBase2(N) + logBase2(10) * X);
      int floor = (int)floor(logBase2(N + 1) + logBase2(10) * X);
      if(ceil == floor) return ceil;
      X++;
    }
  }

  static int countDigits(double n){
    return (int)log10(n) + 1;
  }

  static double logBase2(int n){
    return log10(n) / log10(2);
  }
}
