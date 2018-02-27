import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11875 Brick Game
Problem url: https://uva.onlinejudge.org/external/118/11875.pdf
Author: Andrey Yemelyanov
*/
public class _11875 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
	  int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int N = s.nextInt();
      int[] players = new int[N];
      for(int i = 0; i < N; i++) players[i] = s.nextInt();
      System.out.printf("Case %d: %d\n", c, players[N / 2]);
    }
  }
}
