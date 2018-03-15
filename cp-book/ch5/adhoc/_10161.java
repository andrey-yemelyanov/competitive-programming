import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10161 Ant on a Chessboard
Problem url: https://uva.onlinejudge.org/external/101/10161.pdf
Author: Andrey Yemelyanov
*/
public class _10161 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      int[] location = findLocation(n);
      System.out.printf("%d %d\n", location[0], location[1]);
    }
  }

  static int[] findLocation(int n){
    int k = (int)ceil(sqrt(n));
    int mid = k * k - k + 1;
    if(n == mid) return new int[]{k, k};
    if(k % 2 == 0){
      if(n > mid) return new int[]{k, k - (n - mid)};
      return new int[]{k - (mid - n), k};
    }else{
      if(n > mid) return new int[]{k - (n - mid), k};
      return new int[]{k, k - (mid - n)};
    }
  }
}
