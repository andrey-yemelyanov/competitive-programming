import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12455 Bars
Problem url: https://uva.onlinejudge.org/external/124/12455.pdf
Author: Andrey Yemelyanov
*/
public class _12455_Bars {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int n = s.nextInt();
      int p = s.nextInt();
      int[] bars = new int[p];
      for(int i = 0; i < p; i++){
        bars[i] = s.nextInt();
      }
      if(canObtainBarOfLength(n, bars)) System.out.println("YES");
      else System.out.println("NO");
    }
  }

  static boolean canObtainBarOfLength(int n, int[] bars){
    return canObtainBarOfLength(n, bars, 0, 0);
  }

  static boolean canObtainBarOfLength(int n, int[] bars, int i, int length){
    if(i == bars.length) return length == n;
    return canObtainBarOfLength(n, bars, i + 1, length) || canObtainBarOfLength(n, bars, i + 1, length + bars[i]);
  }
}
