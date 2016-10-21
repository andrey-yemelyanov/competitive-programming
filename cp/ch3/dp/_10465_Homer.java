package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _10465_Homer    {
    public static void main(String[] args) {
        String data = "3 5 54\n3 5 55";
        String data2 = "88 2 5038\r\n34 31 3509\r\n79 10 8419\r\n37 33 2175\r\n64 6 8227\r\n54 35 7026\r\n94 8 4689";
        String data3 = "3 4 4";
        Scanner scanner = new Scanner(data2);
        while(scanner.hasNext()){
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int t = scanner.nextInt();
            memo = new int[t + 1];
            for(int i = 0; i < memo.length; i++){
                memo[i] = -1;
            }
            for(int i = min(m, n); i <= t; i++){
                maxBurgers(i, m, n);
            }
            if(memo[t] > 0) System.out.println(memo[t]);
            else{
                int s = t;
                while(--s >= 0 && memo[s] <= 0);
                if(s < 0) System.out.printf("%d %d\n", 0, t);
                else System.out.printf("%d %d\n", memo[s], t - s);
            }
        }
        scanner.close();
    }

    static int[] memo;
    static final int NEG_INF = -1000000;
    static int maxBurgers(int t, int m, int n){
        if(t == 0) return 0;
        if(t < m && t < n) return NEG_INF;
        if(memo[t] != -1) return memo[t];
        int maxBurgers = NEG_INF;
        if(t >= m) maxBurgers = 1 + maxBurgers(t - m, m, n);
        if(t >= n) maxBurgers = max(maxBurgers, 1 + maxBurgers(t - n, m, n));
        return memo[t] = maxBurgers;
    }
}
