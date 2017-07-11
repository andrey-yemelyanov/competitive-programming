import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11369 Shopaholic
Problem url: https://uva.onlinejudge.org/external/113/11369.pdf
Author: Andrey Yemelyanov
*/
public class _11369_Shopaholic {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int n = s.nextInt();
      int[] prices = new int[n];
      for(int i = 0; i < n; i++) prices[i] = s.nextInt();
      System.out.println(maxDiscount(prices));
    }
  }

  static int maxDiscount(int[] prices){
    Arrays.sort(prices);
    int discount = 0;
    for(int i = prices.length - 3; i >= 0; i -= 3){
      discount += prices[i];
    }
    return discount;
  }
}
