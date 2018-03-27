import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10916 Factstone Benchmark
Problem url: https://uva.onlinejudge.org/external/109/10916.pdf
Author: Andrey Yemelyanov
Big thanks to http://www.algorithmist.com/index.php/UVa_10916 for a great explanation
*/
public class _10916 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int year = s.nextInt();
      if(year == 0) break;
      System.out.println(getFactstoneBenchmark(year));
    }
  }

  static int getFactstoneBenchmark(int year){
    int nBits = (int)pow(2, 2 + (year - 1960) / 10);
    int benchmark = 1;
    double logSum = logBase2(benchmark);
    while(logSum < nBits){
      benchmark++;
      logSum += logBase2(benchmark);
    }
    return benchmark - 1;
  }

  static double logBase2(int n){
    return log10(n) / log10(2);
  }
}
