import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10036 Divisibility
Problem url: https://uva.onlinejudge.org/external/100/10036.pdf
Author: Andrey Yemelyanov
*/
public class _10036_Divisibility {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int M = s.nextInt();
    while(M-- > 0){
      int N = s.nextInt(); int K = s.nextInt();
      int[] sequence = new int[N];
      for(int i = 0; i < N; i++){
        sequence[i] = s.nextInt();
      }
      if(divisible(sequence, K)) System.out.println("Divisible");
      else System.out.println("Not divisible");
    }
  }

  static boolean divisible(int[] sequence, int K){
    return remainderPossible(sequence, K);
  }

  static boolean remainderPossible(int[] sequence, int K){
    int[][] memo = new int[sequence.length][2 * K];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], NA);
    return remainderPossible(0, 0, sequence, K, memo);
  }

  static final int NA = -1;
  static final int TRUE = 1;
  static final int FALSE = 2;
  static boolean remainderPossible(int i, int rem, int[] sequence, int K, int[][] memo){
    final int OFFSET = K;
    if(i == sequence.length - 1) return sequence[i] % K == rem;
    if(memo[i][rem + OFFSET] != NA) return memo[i][rem + OFFSET] == TRUE ? true : false;
    memo[i][rem + OFFSET] =
      (remainderPossible(i + 1, (rem + sequence[i]) % K, sequence, K, memo) ||
      remainderPossible(i + 1, (rem - sequence[i]) % K, sequence, K, memo)) ? TRUE : FALSE;
    return memo[i][rem + OFFSET] == TRUE ? true : false;
  }
}
