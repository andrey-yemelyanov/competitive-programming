package helvidios.cp.ch3.dp;

import java.util.*;

public class _11341_TermStrategy {
    public static void main(String[] args) {
        String data = "2\r\n4 5\r\n5 5 6 7 8\r\n5 5 6 7 8\r\n5 6 7 8 8\r\n6 7 8 9 9\r\n4 5\r\n4 5 6 7 8\r\n4 5 6 7 8\r\n5 6 7 8 8\r\n6 7 8 9 9";
        String data2 = "1\r\n2 2\r\n9 10\r\n1 2";
        String data3 = "1\r\n3 2\r\n9 10\r\n9 10\n1 2";
        String data4 = "1\r\n8 9\r\n4 9 9 10 10 10 10 10 10\r\n7 7 8 9 9 10 10 10 10\r\n2 9 10 10 10 10 10 10 10\r\n10 10 10 10 10 10 10 10 10\r\n6 7 8 10 10 10 10 10 10\r\n3 8 8 9 10 10 10 10 10\r\n10 10 10 10 10 10 10 10 10\r\n2 10 10 10 10 10 10 10 10";
        Scanner scanner = new Scanner(data);
        int nTests = scanner.nextInt();
        while(nTests-- > 0){
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int[][] L = new int[N][M + 1];
            for(int i = 0; i < N; i++){
                for(int j = 1; j < M + 1; j++){
                    L[i][j] = scanner.nextInt();
                }
            }
            double averagePoint = averagePoint(L, N, M);
            if(averagePoint >= 5.0){
                System.out.println(String.format(Locale.ENGLISH, "Maximal possible average mark - %.2f.", averagePoint));
            }else{
                System.out.println("Peter, you shouldn't have played billiard that much.");
            }
        }
        scanner.close();
    }

    static double averagePoint(int[][] L, int N, int M){
        memo = new int[N + 1][M + 1][M + 1];
        solution = new int[N];

        for(int i = 0; i < memo.length; i++){
            for(int j = 0; j < memo[0].length; j++){
                for(int k = 0; k < memo[0][0].length; k++){
                    memo[i][j][k] = -1;
                }
            }
        }

        int totalPoints = points(L, 0, 1, M);
        boolean anyExamFailed = Arrays.stream(solution).anyMatch(i -> i == 0);
        if(anyExamFailed) return 0;
        return (1e-9 + totalPoints) / N;
    }

    static int[][][] memo;
    static int[] solution;
    static int points(int[][] L, int id, int hStudied, int hLeft){
        if(hLeft == 0) return 0;
        if(id == L.length) return 0;
        if(memo[id][hStudied][hLeft] != -1) return memo[id][hStudied][hLeft];
        int points = 0;
        if(L[id][hStudied] < 5) {
            points = points(L, id, hStudied + 1, hLeft - 1);
        }
        else {
            points = Math.max(
                    points(L, id, hStudied + 1, hLeft - 1), // study one more hour
                    L[id][hStudied] + points(L, id + 1, 1, hLeft - 1)); // study next subject
        }
        memo[id][hStudied][hLeft] = points;
        solution[id] = points;
        return points;
    }
}