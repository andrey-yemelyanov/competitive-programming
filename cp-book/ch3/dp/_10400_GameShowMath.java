import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10400 Game Show Math
Problem url: https://uva.onlinejudge.org/external/104/10400.pdf
Author: Andrey Yemelyanov
*/
public class _10400_GameShowMath {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int n = s.nextInt();
      int[] p = new int[n];
      for(int i = 0; i < n; i++){
        p[i] = s.nextInt();
      }
      int target = s.nextInt();
      int[][] memo = new int[n][OFFSET * 2 + 1];
      memo[0][p[0] + OFFSET] = TRUE;
      if(targetReachable(p, target, memo)){
        Stack<Character> ops = getSolution(p, target, memo);
        for(int i : p){
          System.out.print(i);
          if(!ops.isEmpty()) System.out.print(ops.pop());
        }
        System.out.println("=" + target);
      }else System.out.println("NO EXPRESSION");
    }
  }

  static Stack<Character> getSolution(int[] p, int target, int[][] memo){
    Stack<Character> ops = new Stack<>();
    if(memo[p.length - 1][target + OFFSET] == TRUE){
      getSolution(p, target, p.length - 1, memo, ops);
    }
    return ops;
  }

  static void getSolution(int[] p, int target, int i, int[][] memo, Stack<Character> ops){
    if(i == 0) return;
    final int MAX = 2 * OFFSET;
    int divideTarget = target * p[i] + OFFSET;
    int addTarget = target - p[i] + OFFSET;
    int multiplyTarget = target / p[i] + OFFSET;
    int subtractTarget = target + p[i] + OFFSET;
    if(divideTarget >= 0 && divideTarget <= MAX && memo[i - 1][divideTarget] == TRUE){
      ops.push('/');
      getSolution(p, divideTarget - OFFSET, i - 1, memo, ops);
    }else if(addTarget >= 0 && addTarget <= MAX && memo[i - 1][addTarget] == TRUE){
      ops.push('+');
      getSolution(p, addTarget - OFFSET, i - 1, memo, ops);
    }else if(multiplyTarget >= 0 && multiplyTarget <= MAX && memo[i - 1][multiplyTarget] == TRUE){
      ops.push('*');
      getSolution(p, multiplyTarget - OFFSET, i - 1, memo, ops);
    }else if(subtractTarget >= 0 && subtractTarget <= MAX && memo[i - 1][subtractTarget] == TRUE){
      ops.push('-');
      getSolution(p, subtractTarget - OFFSET, i - 1, memo, ops);
    }
  }

  static boolean targetReachable(int[] p, int target, int[][] memo){
    return targetReachable(p, p.length - 1, target, memo);
  }

  static final int OFFSET = 32000;
  static final int NA = 0;
  static final int TRUE = 1;
  static final int FALSE = 2;
  static boolean targetReachable(int[] p, int i, int target, int[][] memo){
    if(i == 0) return p[i] == target;
    if(target < -OFFSET || target > OFFSET) return false;
    if(memo[i][target + OFFSET] != NA) return memo[i][target + OFFSET] == TRUE;
    boolean isReachable =
      targetReachable(p, i - 1, target * p[i], memo) || // '/'
      targetReachable(p, i - 1, target - p[i], memo) || // '+'
      (target % p[i] == 0 && targetReachable(p, i - 1, target / p[i], memo)) || // '*'
      targetReachable(p, i - 1, target + p[i], memo); // '-'
    memo[i][target + OFFSET] = isReachable ? TRUE : FALSE;
    return isReachable;
  }
}
