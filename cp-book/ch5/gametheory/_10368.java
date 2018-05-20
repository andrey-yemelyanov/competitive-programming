import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10368 Euclid's Game
Problem url: https://uva.onlinejudge.org/external/103/10368.pdf
Author: Andrey Yemelyanov
*/
public class _10368 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      long a = s.nextLong(); long b = s.nextLong();
      if(a == 0 && b == 0) break;
      int winner = play(a, b, STAN);
      if(winner == STAN) System.out.println("Stan wins");
      else System.out.println("Ollie wins");
    }
  }

  static final int STAN = 0;
  static final int OLLIE = 1;
  static int play(long a, long b, int turn){
    long max = max(a, b); long min = min(a, b);
    if(max % min == 0) return turn;
    if(turn == STAN){
      for(long i = max / min; i >= 1; i--){
        if(play(min, max - min * i, OLLIE) == STAN) return STAN;
      }
      return OLLIE;
    }else{
      for(long i = max / min; i >= 1; i--){
        if(play(min, max - min * i, STAN) == OLLIE) return OLLIE;
      }
      return STAN;
    }
  }
}
