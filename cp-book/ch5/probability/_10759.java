import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10759 Dice Throwing
Problem url: https://uva.onlinejudge.org/external/107/10759.pdf
Author: Andrey Yemelyanov
*/
public class _10759 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt(); int x = s.nextInt();
      if(n == 0 && x == 0) break;
      System.out.println(printFraction(P(n, x)));
    }
  }

  static String printFraction(long[] fraction){
    if(fraction[0] == 0) return new Long(fraction[0]).toString();
    if(fraction[0] == fraction[1]) return "1";
    return fraction[0] + "/" + fraction[1];
  }

  static long[] P(int nDice, int x){
    long num = countEvents(nDice, x);
    long denom = (long)pow(6, nDice);
    long gcd = gcd(num, denom);
    return new long[]{num / gcd, denom / gcd};
  }

  static long countEvents(int nDice, int x){
    long[][] memo = new long[nDice + 1][6 * nDice + 1];
    for(int i = 0; i < memo.length; i++){
      for(int j = 0; j < memo[0].length; j++){
        memo[i][j] = -1;
      }
    }
    return countEvents(nDice, 0, x, memo);
  }

  static long countEvents(int nDiceLeft, int score, int x, long[][] memo){
    if(nDiceLeft == 0){
      if(score >= x) return 1;
      return 0;
    }
    if(memo[nDiceLeft][score] != -1) return memo[nDiceLeft][score];
    long nEvents = 0;
    for(int i = 1; i <= 6; i++) nEvents += countEvents(nDiceLeft - 1, score + i, x, memo);
    return memo[nDiceLeft][score] = nEvents;
  }

  static long gcd(long a, long b){return b != 0 ? gcd(b, a % b) : a;}
}
