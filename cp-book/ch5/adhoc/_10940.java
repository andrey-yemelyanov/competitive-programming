import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10940 Throwing cards away II
Problem url: https://uva.onlinejudge.org/external/109/10940.pdf
Author: Andrey Yemelyanov
*/
public class _10940 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.println(solveFor(n));
    }
  }

  static int solveFor(int n){
    if(isPowerOfTwo(n)) return n;
    int k = (int)pow(2, floor(logBase2(n))) + 1;
    return 2 + 2 * (n - k);
  }

  static double logBase2(int n){
    return log(n) / log(2);
  }

  static boolean isPowerOfTwo(int n){
    return (n & (n - 1)) == 0;
  }

  static void bruteForce(){
    for(int n = 2; n <= 33; n++){
      System.out.printf("n = %d, lastCard = %d\n", n, simulate(n));
    }
  }

  static int simulate(int n){
    Deque<Integer> deck = new LinkedList<>();
    for(int card = 1; card <= n; card++) deck.addLast(card);
    while(deck.size() >= 2){
      deck.removeFirst();
      deck.addLast(deck.removeFirst());
    }
    return deck.peekFirst();
  }
}
