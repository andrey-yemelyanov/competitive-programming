package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _11517_CoinChange {
    public static void main(String[] args) {
        String data = "1\n" +
                "1400\n" +
                "3\n" +
                "500\n" +
                "1000\n" +
                "2000";
        String data2 = "1\n" +
                "8366\n" +
                "50\n" +
                "8079\n" +
                "3292\n" +
                "8007\n" +
                "9881\n" +
                "7512\n" +
                "3939\n" +
                "4569\n" +
                "8436\n" +
                "9913\n" +
                "1773\n" +
                "6034\n" +
                "3988\n" +
                "762\n" +
                "5415\n" +
                "2610\n" +
                "1289\n" +
                "8358\n" +
                "9291\n" +
                "7223\n" +
                "4553\n" +
                "9531\n" +
                "8257\n" +
                "2162\n" +
                "4507\n" +
                "7471\n" +
                "1728\n" +
                "9532\n" +
                "3511\n" +
                "5482\n" +
                "7897\n" +
                "8660\n" +
                "3560\n" +
                "7540\n" +
                "3018\n" +
                "3440\n" +
                "1404\n" +
                "6957\n" +
                "8008\n" +
                "9839\n" +
                "6869\n" +
                "6133\n" +
                "2224\n" +
                "7208\n" +
                "3246\n" +
                "3990\n" +
                "9817\n" +
                "4534\n" +
                "2348\n" +
                "5459\n" +
                "1757";
        String data3 = "1\n" +
                "12 \n" +
                "5 \n" +
                "1\n" +
                "2\n" +
                "2\n" +
                "2\n" +
                "5";
        Scanner scanner = new Scanner(data3);
        int nTests = scanner.nextInt();
        while(nTests-- > 0){
            int charge = scanner.nextInt();
            int nBills = scanner.nextInt();
            int[] bills = new int[nBills];
            for(int i = 0; i < bills.length; i++){
                bills[i] = scanner.nextInt();
            }
            int amountToPay = minAmount(charge, bills);
            int minBills = minBills(amountToPay, bills);
            System.out.println(amountToPay + " " + minBills);
        }
        scanner.close();
    }

    static void swap(int[] bills, int i, int j){
        int temp = bills[i];
        bills[i] = bills[j];
        bills[j] = temp;
    }

    static int minBills(int value, int[] bills){
        memo2 = new int[10001][101];
        for(int i = 0; i < memo2.length; i++){
            for(int j = 0; j < memo2[0].length; j++) {
                memo2[i][j] = -1;
            }
        }
        return minBills(value, 0, bills);
    }

    static int[][] memo2;
    static int minBills(int value, int current, int[] bills){
        if(value == 0) return 0;
        if(value < 0) return 100000;
        if(memo2[value][current] != -1) return memo2[value][current];
        int minBills = 100000;
        for(int i = current; i < bills.length; i++){
            swap(bills, current, i);
            minBills = min(minBills, 1 + minBills(value - bills[current], current + 1, bills));
            swap(bills, current, i);
        }
        memo2[value][current] = minBills;
        return minBills;
    }

    static int minAmount(int charge, int[] bills){
        memo = new int[101][10001];
        for(int i = 0; i < memo.length; i++){
            for(int j = 0; j < memo[0].length; j++){
                memo[i][j] = -1;
            }
        }
        return minAmount(0, charge, bills);
    }

    static int[][] memo;
    static int minAmount(int i, int charge, int[] bills){
        if(charge <= 0) return 0;
        if(i == bills.length) return 20000;
        if(memo[i][charge] != -1) return memo[i][charge];
        int minAmount = min(
                minAmount(i + 1, charge, bills),
                bills[i] + minAmount(i + 1, charge - bills[i], bills));
        memo[i][charge] = minAmount;
        return minAmount;
    }
}
