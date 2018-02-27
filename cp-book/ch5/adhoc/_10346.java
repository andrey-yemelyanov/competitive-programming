import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10346 Peter's Smokes
Problem url: https://uva.onlinejudge.org/external/103/10346.pdf
Author: Andrey Yemelyanov
*/
public class _10346 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt(); int k = s.nextInt();
      System.out.println(howManyCigarettes(n, k));
    }
  }

  static int howManyCigarettes(int n, int k){
    int nCigs = n;
    int nButts = n;
    while(nButts >= k){
      nCigs += nButts / k;
      nButts = (nButts / k) + (nButts % k);
    }
    return nCigs;
  }
}
