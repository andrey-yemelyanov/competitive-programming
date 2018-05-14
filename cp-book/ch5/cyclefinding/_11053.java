import java.util.*;
import java.util.function.Function;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11053 Flavius Josephus Reloaded
Problem url: https://uva.onlinejudge.org/external/110/11053.pdf
Author: Andrey Yemelyanov
*/
public class _11053 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt(); if(N == 0) break; int a = s.nextInt(); int b = s.nextInt();
      Function<Long, Long> func = x -> (a % N * x % N * x % N + b % N) % N;
      System.out.println(N - floydCycleFinding(0, func)[1]);
    }
  }

  static int[] floydCycleFinding(long x0, Function<Long, Long> func){
    // 1st part: find k * MU; hare's speed is 2x tortoise's
    long tortoise = func.apply(x0); long hare = func.apply(func.apply(x0));
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
