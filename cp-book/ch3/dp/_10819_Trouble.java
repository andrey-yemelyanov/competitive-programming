package helvidios.cp.ch3.dp;

import java.util.*;
import java.util.stream.*;

public class _10819_Trouble {
    public static void main(String[] args) {
        String data = "500 4\r\n100 2\r\n100 3\r\n200 3\r\n400 4";
        Scanner scanner = new Scanner(data);
        while(scanner.hasNext()){
            int budget = scanner.nextInt();
            int n = scanner.nextInt();
            int[] price = new int[n];
            int[] value = new int[n];
            for(int i = 0; i < n; i++){
                price[i] = scanner.nextInt();
                value[i] = scanner.nextInt();
            }
            System.out.println(maxValue(budget, price, value));
        }
        scanner.close();
    }

    static int maxValue(int budget, int[] price, int[] value){
        memo = new int[price.length + 1][15000];
        return maxValue(0, 0, price, value, budget);
    }

    static int[][] memo;
    static final int REFUND_BOUNDARY = 2000;
    static final int REFUND_AMOUNT = 200;
    static int maxValue(int i, int totalExpense, int[] price, int[] value, int m){
        if(totalExpense - m > REFUND_AMOUNT) return -1000;
        if(i == price.length){
            if(totalExpense > m && totalExpense <= REFUND_BOUNDARY) return -1000;
            return 0;
        }

        if(memo[i][totalExpense] != 0) return memo[i][totalExpense];

        int maxValue = Math.max(
                maxValue(i + 1, totalExpense, price, value, m),
                value[i] + maxValue(i + 1, totalExpense + price[i], price, value, m));

        memo[i][totalExpense] = maxValue;
        return maxValue;
    }
}
