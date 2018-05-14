import java.util.*;
import java.util.function.Function;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 350 Pseudo-Random Numbers
Problem url: https://uva.onlinejudge.org/external/3/350.pdf
Author: Andrey Yemelyanov
*/
public class _350 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int c = 1;
    while(s.hasNext()){
      int Z = s.nextInt(); int I = s.nextInt(); int M = s.nextInt(); int L = s.nextInt();
      if(Z == 0 && I == 0 && M == 0 && L == 0) break;
      Function<Integer, Integer> func = x -> (Z * x + I) % M;
      System.out.printf("Case %d: %d\n", c++, floydCycleFinding(L, func)[1]);
    }
  }

  static int[] floydCycleFinding(int x0, Function<Integer, Integer> func){
    // 1st part: find k * MU; hare's speed is 2x tortoise's
    int tortoise = func.apply(x0); int hare = func.apply(func.apply(x0));
    while(tortoise != hare){tortoise = func.apply(tortoise); hare = func.apply(func.apply(hare));}
    // 2nd part: find MU; hare and tortoise move at the same speed
    int MU = 0; hare = x0;
    while(tortoise != hare) {tortoise = func.apply(tortoise); hare = func.apply(hare); MU++;}
    // 3rd part: find LAMBDA, hare moves, tortoise stays
    int LAMBDA = 1; hare = func.apply(tortoise);
    while(tortoise != hare) {hare = func.apply(hare); LAMBDA++;}
    return new int[]{MU, LAMBDA};
  }
}
