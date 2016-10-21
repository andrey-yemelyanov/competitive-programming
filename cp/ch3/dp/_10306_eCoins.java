package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _10306_eCoins {
    public static void main(String[] args) {
        String data = "3\r\n2 5\r\n0 2\r\n2 0\r\n3 20\r\n0 2\r\n2 0\r\n2 1\r\n3 5\r\n3 0\r\n0 4\r\n5 5\r\n";
        Scanner scanner = new Scanner(data);
        int nTestCases = scanner.nextInt();
        while(nTestCases-- > 0){
            int m = scanner.nextInt();
            int S = scanner.nextInt();
            int[] conventionalValue = new int[m];
            int[] infotechValue = new int[m];
            for(int i = 0; i < m; i++){
                conventionalValue[i] = scanner.nextInt();
                infotechValue[i] = scanner.nextInt();
            }
            long ans = minCoins(S, conventionalValue, infotechValue);
            if(ans == POS_INFINITY) System.out.println("not possible");
            else System.out.println(ans);
        }
        scanner.close();
    }

    static long minCoins(int targetValue, int[] conventionalValue, int[] infotechValue){
        memo = new long[300][300];
        for(int i = 0; i < memo.length; i++){
            for(int j = 0; j < memo[0].length; j++){
                memo[i][j] = -1;
            }
        }
        return coins(0, 0, targetValue * targetValue, conventionalValue, infotechValue);
    }

    static final long POS_INFINITY = Integer.MAX_VALUE;
    static long[][] memo;
    static long coins(int X, int Y, int targetValue, int[] conventionalValue, int[] infotechValue){
        int value = X * X + Y * Y;
        if(value == targetValue) return 0;
        if(value > targetValue) return POS_INFINITY;
        if(memo[X][Y] != -1) return memo[X][Y];
        long coins = POS_INFINITY;
        for(int i = 0; i < conventionalValue.length; i++){
            coins = min(coins, 1 + coins(X + conventionalValue[i], Y + infotechValue[i],
                    targetValue, conventionalValue, infotechValue));
        }
        memo[X][Y] = coins;
        return coins;
    }
}
