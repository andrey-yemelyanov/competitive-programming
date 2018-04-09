import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11538 Chess Queen
Problem url: https://uva.onlinejudge.org/external/115/11538.pdf
Author: Andrey Yemelyanov
*/
public class _11538 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
	  while(s.hasNext()){
      int M = s.nextInt(); int N = s.nextInt();
      if(M == 0 && N == 0) break;
      System.out.println(countWays(M, N));
    }
  }

  static long countWays(long M, long N){
    long result = M * N * (M + N - 2);
    long min = min(M, N); long max = max(M, N);
    long nDiag = 0;
    for(long i = 1; i < min; i++) nDiag += 2 * i * (i + 1);
    nDiag += (min - 1) * (max - min - 1) * min;
    result += 2 * nDiag;
    return result;
  }
}
