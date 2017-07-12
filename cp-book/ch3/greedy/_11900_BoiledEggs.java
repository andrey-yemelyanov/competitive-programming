import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11900 Boiled Eggs
Problem url: https://uva.onlinejudge.org/external/119/11900.pdf
Author: Andrey Yemelyanov
*/
public class _11900_BoiledEggs {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int n = s.nextInt(); int P = s.nextInt(); int Q = s.nextInt();
      int[] eggs = new int[n];
      for(int i = 0; i < n; i++) eggs[i] = s.nextInt();
      System.out.printf("Case %d: %d\n", c, maxBoiledEggs(eggs, P, Q));
    }
  }

  static int maxBoiledEggs(int[] eggs, int P, int Q){
    int nEggs = 0; int totalWeight = 0;
    for(int i = 0; i < eggs.length; i++){
      if(nEggs == P) return nEggs;
      if(totalWeight + eggs[i] <= Q){
        totalWeight += eggs[i];
        nEggs++;
      }
    }
    return nEggs;
  }
}
