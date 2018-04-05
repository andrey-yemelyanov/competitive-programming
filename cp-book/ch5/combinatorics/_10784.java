import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10784 Diagonal
Problem url: https://uva.onlinejudge.org/external/107/10784.pdf
Author: Andrey Yemelyanov
*/
public class _10784 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int c = 1;
    while(s.hasNext()){
      long N = s.nextLong();
      if(N == 0) break;
      System.out.printf("Case %d: %d\n", c++, solve(N));
    }
  }

  static int solve(long N){
    return (int)ceil((3 + sqrt(9 + 8 * N)) / 2);
  }
}
