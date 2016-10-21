package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _11420_ChestOfDrawers {
    public static void main(String[] args) {
        String data = "6 2\n6 3\n6 4\n-1 -1";
        Scanner scanner = new Scanner(data);
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            int s = scanner.nextInt();
            if(n < 0 && s < 0) break;
            System.out.println(countWays(n, s));
        }
        scanner.close();
    }

    static long countWays(int n, int s){
        memo = new long[2][65][65];
        for(int i = 0; i < memo.length; i++){
            for(int j = 0; j < memo[0].length; j++){
                for(int k = 0; k < memo[0][0].length; k++){
                    memo[i][j][k] = -1;
                }
            }
        }
        return countWays(1, 0, 0, n, s);
    }

    static long[][][] memo;
    static long countWays(
            int prevDrawerLocked,
            int currentDrawer,
            int nSecuredDrawers,
            int n, int s){
        if(currentDrawer == n){
            if(nSecuredDrawers == s) return 1;
            else return 0;
        }
        if(memo[prevDrawerLocked][currentDrawer][nSecuredDrawers] != -1)
            return memo[prevDrawerLocked][currentDrawer][nSecuredDrawers];
        long nWays = countWays(
                0, currentDrawer + 1, nSecuredDrawers, n, s);
        nWays += countWays(
                1, currentDrawer + 1, nSecuredDrawers + prevDrawerLocked, n, s);
        memo[prevDrawerLocked][currentDrawer][nSecuredDrawers] = nWays;
        return nWays;
    }
}
