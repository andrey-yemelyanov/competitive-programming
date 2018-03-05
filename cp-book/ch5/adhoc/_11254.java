import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11254 Consecutive Integers
Problem url: https://uva.onlinejudge.org/external/112/11254.pdf
Author: Andrey Yemelyanov
*/
public class _11254 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n < 0) break;
      int[] solution = solveFor(n);
      System.out.printf("%d = %d + ... + %d\n", n, solution[0], solution[1]);
    }
  }

  static int[] solveFor(int n){
    int from = 0; int to = 0;
    for(int r = (int)ceil(sqrt(2 * n)); r >= 1; r--){
      int a = (2 * n - (int)pow(r, 2) + r) / (2 * r);
      if(a != 0 && (2 * a + r - 1) * r / 2 == n){
        from = a; to = a + r - 1;
        break;
      }
    }
    return new int[]{from, to};
  }
}
