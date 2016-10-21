package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _11566_YumCha {
    public static void main(String[] args) {
        String data = "3 9 3 2\n" +
                "4 7 5 6 9\n" +
                "10 2 1 3 8\n" +
                "\n" +
                "3 10 5 2\n" +
                "6 7 5 6 9\n" +
                "10 9 10 10 8\n" +
                "\n" +
                "3 100 1 2\n" +
                "1 10 10 10 10\n" +
                "2 10 10 10 10\n" +
                "\n" +
                "\n" +
                "9 4 3 2\n" +
                "4 2 0 0 0 0 0 0 0 0 0 \n" +
                "10 1 0 0 0 0 0 0 0 0 0\n" +
                "\n" +
                "3 10 5 2\n" +
                "6 7 5 6 9\n" +
                "10 9 10 10 8\n" +
                "3 10 5 2\n" +
                "6 7 5 6 9\n" +
                "10 9 10 2 8\n" +
                "3 10 5 2\n" +
                "6 7 5 6 9\n" +
                "10 2 1 3 8\n" +
                "\n" +
                "\n" +
                "3 10 0 3\n" +
                "6 0 0 0 0\n" +
                "10 9 10 10 8\n" +
                "7 10 10 10 10\n" +
                "\n" +
                "\n" +
                "5 10 0 1\n" +
                "10 1 1 1 1 1 2\n" +
                "\n" +
                "5 100 0 10\n" +
                "10 0 0 0 0 0 1\n" +
                "10 0 0 0 0 2 3\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 1 2 0 0 0\n" +
                "10 0 10 4 0 0 0\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 0 10 0 0 0\n" +
                "10 0 0 0 5 0 1\n" +
                "10 0 0 0 0 5 5\n" +
                "\n" +
                "\n" +
                "5 100 0 10\n" +
                "10 0 0 0 0 0 1\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 0 0 0 0 0\n" +
                "10 0 0 0 0 0 1\n" +
                "\n" +
                "\n" +
                "9 100 20 10\n" +
                "9 2 4 10 1 9 1 5 9 6 4\n" +
                "1 3 5 5 5 5 6 3 10 4 9\n" +
                "4 2 2 1 0 8 3 3 4 8 1\n" +
                "4 7 10 3 1 1 7 6 8 8 1\n" +
                "4 6 3 2 2 9 2 6 7 1 8\n" +
                "10 8 9 7 1 3 7 4 9 9 7\n" +
                "7 3 5 1 2 1 8 2 4 4 4\n" +
                "6 6 2 7 2 3 5 0 5 4 7\n" +
                "7 10 5 3 6 2 7 4 4 7 7\n" +
                "7 5 2 5 8 4 8 1 4 8 8\n" +
                "\n" +
                "9 100 20 12\n" +
                "9 2 4 10 1 9 1 5 9 6 4\n" +
                "1 3 5 5 5 5 6 3 10 4 9\n" +
                "4 2 2 1 0 8 3 3 4 8 1\n" +
                "4 7 10 3 1 1 7 6 8 8 1\n" +
                "4 6 3 2 2 9 2 6 7 1 8\n" +
                "10 8 9 7 1 3 7 4 9 9 7\n" +
                "7 3 5 1 2 1 8 2 4 4 4\n" +
                "6 6 2 7 2 3 5 0 5 4 7\n" +
                "7 10 5 3 6 2 7 4 4 7 7\n" +
                "7 5 2 5 8 4 8 1 4 8 8\n" +
                "7 5 2 5 8 4 8 1 4 8 1\n" +
                "3 2 3 7 10 3 4 9 3 7 9\n" +
                "\n" +
                "9 100 20 20\n" +
                "9 2 4 10 1 9 1 5 9 6 4\n" +
                "1 3 5 5 5 5 6 3 10 4 9\n" +
                "4 2 2 1 0 8 3 3 4 8 1\n" +
                "4 7 10 3 1 1 7 6 8 8 1\n" +
                "4 6 3 2 2 9 2 6 7 1 8\n" +
                "10 8 9 7 1 3 7 4 9 9 7\n" +
                "7 3 5 1 2 1 8 2 4 4 4\n" +
                "6 6 2 7 2 3 5 0 5 4 7\n" +
                "7 10 5 3 6 2 7 4 4 7 7\n" +
                "7 5 2 5 8 4 8 1 4 8 8\n" +
                "6 7 0 9 4 9 8 9 5 4 9\n" +
                "7 1 7 1 6 2 8 9 2 8 8\n" +
                "8 1 7 7 7 1 5 5 8 1 2\n" +
                "5 4 10 1 8 6 10 2 9 0 8\n" +
                "3 5 0 10 4 3 6 5 8 2 6\n" +
                "1 7 7 7 2 0 1 1 5 0 5\n" +
                "2 0 7 6 2 6 3 8 7 9 2\n" +
                "5 6 4 7 9 0 5 2 1 6 3\n" +
                "2 7 7 5 9 6 4 8 4 3 0\n" +
                "3 2 3 7 10 3 4 9 3 7 9\n" +
                "0 0 0 0";
        String data2 = "9 4 3 2\n" +
                "4 2 0 0 0 0 0 0 0 0 0 \n" +
                "10 1 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0";
        Scanner scanner = new Scanner(data2);
        while(scanner.hasNext()){
            int N = scanner.nextInt();
            int x = scanner.nextInt();
            int T = scanner.nextInt();
            int K = scanner.nextInt();
            if(N == 0 && x == 0 && T == 0 && K == 0) break;
            int[] price = new int[K]; int[] value = new int[K];
            for(int i = 0; i < K; i++){
                price[i] = scanner.nextInt();
                int dimsumValue = 0;
                for(int j = 0; j < N + 1; j++){
                    dimsumValue += scanner.nextInt();
                }
                value[i] = dimsumValue;
            }
            System.out.printf("%.2f\n", maxValue(price, value, N, T, K, x));
        }
        scanner.close();
    }

    static double maxValue(int[] price, int[] value, int N, int T, int K, int x){
        memo = new int[K + 1][2 * (N + 1)][200 * (N + 1)];
        for(int i = 0; i < memo.length; i++){
            for(int j = 0; j < memo[0].length; j++){
                for(int k = 0; k < memo[0][0].length; k++){
                    memo[i][j][k] = -1;
                }
            }
        }

        int totalValue = maxValue(0, 0, 0, price, value, N, T, x);
        return (double)totalValue / (N + 1);
    }

    static int[][][] memo;
    static int maxValue(int i, int nOrdered, int totalPrice, int[] price, int[] value, int N, int T, int x){
        int totalToPay = totalPrice + (N + 1) * T; // add tea charge
        totalToPay += (int)ceil(0.1 * totalToPay); // add service charge
        if(totalToPay > (N + 1) * x || nOrdered > 2 * (N + 1)) return Integer.MIN_VALUE;
        if(i == price.length || totalToPay == (N + 1) * x || nOrdered == 2 * (N + 1)) return 0;
        if(memo[i][nOrdered][totalPrice] != -1) return memo[i][nOrdered][totalPrice];
        int maxValue = max(maxValue(i + 1, nOrdered, totalPrice, price, value, N, T, x),
                max(value[i] + maxValue(i + 1, nOrdered + 1, totalPrice + price[i], price, value, N, T, x),
                       2 * value[i] + maxValue(i + 1, nOrdered + 2, totalPrice + 2 * price[i], price, value, N, T, x)));
        memo[i][nOrdered][totalPrice] = maxValue;
        return maxValue;
    }
}
