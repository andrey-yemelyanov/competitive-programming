package helvidios.cp.ch3.dp;

import java.util.*;
import java.util.stream.*;

public class _10616_DivisibleGroupSum {
    public static void main(String[] args) {
        String data = "10 2\r\n1\r\n2\r\n3\r\n4\r\n5\r\n6\r\n7\r\n8\r\n9\r\n10\r\n5 1\r\n5 2\r\n5 1\r\n2\r\n3\r\n4\r\n5\r\n6\r\n6 2\r\n0 0";
        String data2 = "6 7\r\n299\r\n-837\r\n563\r\n-985\r\n316\r\n380\r\n13 1\r\n18 1\r\n14 2\r\n17 6\r\n18 3\r\n1 6\r\n7 5\r\n12 3\r\n-646\r\n-801\r\n-664\r\n-856\r\n307\r\n-618\r\n-267\r\n-768\r\n-341\r\n589\r\n954\r\n-779\r\n2 4\r\n2 4\r\n2 4\r\n19 5\r\n387\r\n-833\r\n-865\r\n837\r\n109\r\n874\r\n-979\r\n-600\r\n794\r\n-35\r\n-249\r\n-853\r\n164\r\n-356\r\n849\r\n-972\r\n26\r\n-419\r\n818\r\n17 7\r\n16 10\r\n4 2\r\n4 9\r\n2 9\r\n2 4\r\n135\r\n675\r\n7 2\r\n16 2\r\n1 1\r\n7 2\r\n8 1\r\n-764\r\n692\r\n-288\r\n-736\r\n-726\r\n294\r\n639\r\n959\r\n8 8\r\n15 7\r\n564\r\n600\r\n203\r\n-425\r\n349\r\n-290\r\n-625\r\n-517\r\n942\r\n869\r\n12\r\n-719\r\n235\r\n-995\r\n22\r\n17 6\r\n8 6\r\n20 3\r\n7 10\r\n19 10\r\n9 3\r\n20 4\r\n3 1\r\n303\r\n244\r\n-48\r\n5 2\r\n7 2\r\n-810\r\n160\r\n679\r\n202\r\n442\r\n-530\r\n-236\r\n8 1\r\n20 6\r\n7 7\r\n-35\r\n205\r\n846\r\n-442\r\n-975\r\n-825\r\n224\r\n7 4\r\n20 5\r\n11 3\r\n7 5\r\n20 5\r\n20 5\r\n20 6\r\n5 3\r\n281\r\n532\r\n965\r\n702\r\n-70\r\n7 3\r\n19 3\r\n8 2\r\n0 0";
        Scanner scanner = new Scanner(data2);
        int set = 1;
        while(scanner.hasNext()){
            int N = scanner.nextInt();
            int Q = scanner.nextInt();
            if(N == 0 && Q == 0) break;
            long[] numbers = new long[N];
            for(int i = 0; i < N; i++){
                numbers[i] = scanner.nextLong();
            }
            int[][] queries = new int[Q][2];
            for(int i = 0; i < Q; i++){
                queries[i][0] = scanner.nextInt();
                queries[i][1] = scanner.nextInt();
            }

            long[] answers = count(queries, numbers);
            System.out.printf("SET %d:\n", set++);
            for(int i = 0; i < answers.length; i++){
                System.out.printf("QUERY %d: %d\n", i + 1, answers[i]);
            }
        }
        scanner.close();
    }

    static long[] count(int[][] queries, long[] numbers){
        long[] answers = new long[queries.length];
        for(int q = 0; q < queries.length; q++){
            memo = new long[numbers.length + 1][21][11];
            for(int i = 0; i < memo.length; i++){
                for(int j = 0; j < memo[0].length; j++){
                    for(int k = 0; k < memo[0][0].length; k++){
                        memo[i][j][k] = -1;
                    }
                }
            }

            int d = queries[q][0];
            int m = queries[q][1];
            answers[q] = count(0, 0, m, numbers, d);
        }
        return answers;
    }

    static long[][][] memo;
    static long count(int i, int sum, int m, long[] numbers, int d){
        if(m == 0) return (sum == 0) ? 1 : 0;
        if(i == numbers.length) return 0;
        if(memo[i][sum][m] != -1) return memo[i][sum][m];
        long count = count(i + 1, mod(sum + numbers[i], d), m - 1, numbers, d)
                + count(i + 1, sum, m, numbers, d);
        memo[i][sum][m] = count;
        return count;
    }

    static int mod(long x, long y){
        return (int)(((x % y) + y) % y);
    }
}