import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11847 Cut the Silver Bar
Problem url: https://uva.onlinejudge.org/external/118/11847.pdf
Author: Andrey Yemelyanov
*/
public class _11847 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
	  while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.println((int)floor(logBase2(n)));
    }
  }

  static double logBase2(int n){
    return log10(n) / log10(2);
  }
}
