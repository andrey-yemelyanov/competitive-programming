import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10763 Foreign Exchange
Problem url: https://uva.onlinejudge.org/external/107/10763.pdf
Author: Andrey Yemelyanov
*/
public class _10763_ForeignExchange {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
	  while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      int[] v1 = new int[n];
      int[] v2 = new int[n];
      for(int i = 0; i < n; i++){
        v1[i] = s.nextInt();
        v2[i] = s.nextInt();
      }
      Arrays.sort(v1);
      Arrays.sort(v2);
      boolean exchangePossible = true;
      for(int i = 0; i < n; i++){
        if(v1[i] != v2[i]){
          exchangePossible = false;
          break;
        }
      }
      if(exchangePossible) System.out.println("YES");
      else System.out.println("NO");
    }
  }
}
