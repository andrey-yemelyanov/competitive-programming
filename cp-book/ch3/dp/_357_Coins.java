package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _357_Coins {
    public static void main(String[] args) {
        String data = "17\n" +
                "11\n" +
                "4\n";
        String data2 = "99";
        Scanner scanner = new Scanner(data2);
        int[] coinValues = new int[]{1,5,10,25,50};
        memo = new long[30001][coinValues.length + 1];
        for(int i = 0; i < memo.length; i++){
            for(int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }
        while(scanner.hasNext()){
            int value = scanner.nextInt();
            long nWays = countWays(coinValues, value, 0);
            if(nWays == 1){
                System.out.printf("There is only 1 way to produce %d cents change.\n", value);
            }else{
                System.out.printf("There are %d ways to produce %d cents change.\n", nWays, value);
            }
        }
        scanner.close();
    }

    static long[][] memo;
    static long countWays(int[] coinValues, int value, int i){
        if(i == coinValues.length) return 0;
        if(value < 0) return 0;
        if(value == 0) return 1;
        if(memo[value][i] != -1) return memo[value][i];
        long nWays = countWays(coinValues, value, i + 1)
                + countWays(coinValues, value - coinValues[i], i);
        memo[value][i] = nWays;
        return nWays;
    }
}
