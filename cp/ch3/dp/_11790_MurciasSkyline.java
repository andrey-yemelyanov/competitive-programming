package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _11790_MurciasSkyline {
    static class Subsequence{
        public int length;
        public int weight;
        public Subsequence(int length, int weight){
            this.length = length;
            this.weight = weight;
        }
    }
    public static void main(String[] args) {
        String data = "3\n" +
                "6\n" +
                "10 100 50 30 80 10\n" +
                "50 10 10 15 20 10\n" +
                "4\n" +
                "30 20 20 10\n" +
                "20 30 40 50\n" +
                "3\n" +
                "80 80 80\n" +
                "15 25 20";
        String data2 = "100\n" +
                "6\n" +
                "5 8 4 5 6 4\n" +
                "5 9 7 8 4 6\n" +
                "6\n" +
                "1 3 10 7 7 3\n" +
                "7 3 9 4 7 3\n" +
                "3\n" +
                "7 1 8\n" +
                "3 8 1\n" +
                "4\n" +
                "1 9 4 8\n" +
                "6 9 5 3\n" +
                "1\n" +
                "4\n" +
                "4\n" +
                "10\n" +
                "10 10 4 10 2 5 1 6 6 10\n" +
                "5 9 1 5 2 4 4 6 3 2\n" +
                "9\n" +
                "10 7 5 6 4 9 6 8 7\n" +
                "10 10 2 3 1 10 3 4 6\n" +
                "3\n" +
                "8 10 2\n" +
                "9 6 6\n" +
                "6\n" +
                "3 7 5 9 9 6\n" +
                "1 7 8 2 2 2\n" +
                "5\n" +
                "6 2 4 5 9\n" +
                "4 2 1 4 2\n" +
                "2\n" +
                "6 1\n" +
                "8 10\n" +
                "4\n" +
                "10 4 6 3\n" +
                "5 1 3 6\n" +
                "9\n" +
                "8 9 8 3 3 1 6 7 1\n" +
                "2 4 8 5 9 1 3 3 10\n" +
                "3\n" +
                "3 2 9\n" +
                "7 9 6\n" +
                "4\n" +
                "4 9 1 4\n" +
                "1 1 3 8\n" +
                "5\n" +
                "2 8 3 3 1\n" +
                "9 7 1 2 9\n" +
                "3\n" +
                "2 4 5\n" +
                "3 1 10\n" +
                "6\n" +
                "9 7 9 7 6 2\n" +
                "4 8 8 8 6 5\n" +
                "7\n" +
                "7 5 10 5 6 1 10\n" +
                "7 5 2 10 1 4 9\n" +
                "6\n" +
                "6 1 10 10 2 1\n" +
                "4 1 1 5 4 6\n" +
                "4\n" +
                "7 1 4 4\n" +
                "9 8 3 5\n" +
                "7\n" +
                "3 9 4 9 10 10 6\n" +
                "10 1 7 8 5 2 7\n" +
                "3\n" +
                "7 6 3\n" +
                "7 6 2\n" +
                "7\n" +
                "8 4 6 10 8 6 8\n" +
                "2 1 10 9 9 6 4\n" +
                "8\n" +
                "10 4 6 7 3 9 8 5\n" +
                "2 7 2 5 7 10 7 1\n" +
                "9\n" +
                "2 3 6 1 7 9 4 6 4\n" +
                "2 10 4 8 1 2 6 5 2\n" +
                "5\n" +
                "10 3 2 7 5\n" +
                "8 9 7 9 9\n" +
                "4\n" +
                "2 1 1 7\n" +
                "7 1 1 1\n" +
                "9\n" +
                "3 4 1 1 6 4 2 4 10\n" +
                "5 8 6 4 9 4 1 3 1\n" +
                "5\n" +
                "10 7 1 7 3\n" +
                "8 9 6 9 4\n" +
                "2\n" +
                "8 1\n" +
                "2 5\n" +
                "3\n" +
                "7 9 9\n" +
                "4 8 5\n" +
                "4\n" +
                "3 8 2 4\n" +
                "8 1 6 7\n" +
                "7\n" +
                "7 10 2 9 8 6 9\n" +
                "2 3 1 7 8 6 3\n" +
                "9\n" +
                "10 2 4 2 10 9 8 4 5\n" +
                "1 1 3 3 4 8 8 5 9\n" +
                "2\n" +
                "8 2\n" +
                "4 7\n" +
                "2\n" +
                "7 2\n" +
                "3 7\n" +
                "5\n" +
                "6 2 5 4 8\n" +
                "3 5 10 6 4\n" +
                "8\n" +
                "4 9 6 2 4 1 3 10\n" +
                "8 7 9 3 9 2 4 6\n" +
                "3\n" +
                "10 10 9\n" +
                "7 4 4\n" +
                "2\n" +
                "3 8\n" +
                "1 8\n" +
                "2\n" +
                "10 9\n" +
                "6 8\n" +
                "1\n" +
                "7\n" +
                "2\n" +
                "4\n" +
                "3 7 3 6\n" +
                "8 4 10 5\n" +
                "8\n" +
                "1 2 4 9 10 9 8 3\n" +
                "7 1 9 10 6 5 7 3\n" +
                "4\n" +
                "5 6 6 1\n" +
                "4 5 6 4\n" +
                "5\n" +
                "6 1 9 6 6\n" +
                "4 1 6 6 2\n" +
                "8\n" +
                "1 10 5 10 2 9 3 7\n" +
                "5 7 4 2 7 6 6 7\n" +
                "9\n" +
                "2 9 6 5 7 7 3 7 3\n" +
                "5 3 3 10 3 7 3 9 7\n" +
                "8\n" +
                "6 10 9 8 10 6 7 7\n" +
                "4 2 9 6 9 8 3 10\n" +
                "1\n" +
                "2\n" +
                "9\n" +
                "7\n" +
                "8 4 7 1 4 7 10\n" +
                "10 3 7 5 5 10 8\n" +
                "3\n" +
                "7 1 7\n" +
                "5 5 9\n" +
                "7\n" +
                "6 9 4 4 7 2 4\n" +
                "4 6 9 1 10 1 4\n" +
                "9\n" +
                "1 10 10 8 10 5 10 10 4\n" +
                "3 6 1 3 7 5 3 3 5\n" +
                "6\n" +
                "5 9 9 9 1 5\n" +
                "2 5 10 5 9 10\n" +
                "2\n" +
                "3 4\n" +
                "8 8\n" +
                "1\n" +
                "9\n" +
                "5\n" +
                "1\n" +
                "1\n" +
                "6\n" +
                "4\n" +
                "1 1 8 2\n" +
                "4 4 8 3\n" +
                "8\n" +
                "10 10 2 3 8 5 7 5\n" +
                "7 1 8 7 1 3 5 7\n" +
                "8\n" +
                "9 1 6 6 10 3 9 1\n" +
                "1 3 9 6 5 9 4 7\n" +
                "5\n" +
                "2 3 7 9 9\n" +
                "3 2 7 4 3\n" +
                "4\n" +
                "10 2 1 5\n" +
                "6 8 8 7\n" +
                "5\n" +
                "4 10 6 8 3\n" +
                "10 7 10 3 1\n" +
                "1\n" +
                "7\n" +
                "4\n" +
                "10\n" +
                "2 3 2 10 2 1 3 9 1 10\n" +
                "8 7 4 10 2 3 7 2 8 3\n" +
                "3\n" +
                "5 1 1\n" +
                "5 3 6\n" +
                "2\n" +
                "3 1\n" +
                "4 10\n" +
                "7\n" +
                "8 5 2 8 5 7 10\n" +
                "6 5 3 3 3 6 10\n" +
                "8\n" +
                "5 3 10 10 1 10 8 6\n" +
                "5 4 1 8 10 5 2 3\n" +
                "8\n" +
                "2 7 4 4 9 2 7 2\n" +
                "1 8 5 2 8 7 9 9\n" +
                "7\n" +
                "8 7 2 9 7 2 8\n" +
                "3 7 8 4 9 4 8\n" +
                "6\n" +
                "4 9 10 7 9 5\n" +
                "7 2 7 10 2 2\n" +
                "1\n" +
                "5\n" +
                "4\n" +
                "7\n" +
                "1 4 5 4 10 9 5\n" +
                "4 6 6 8 5 4 9\n" +
                "8\n" +
                "4 10 3 2 6 9 2 8\n" +
                "1 6 7 5 6 10 6 8\n" +
                "1\n" +
                "8\n" +
                "4\n" +
                "8\n" +
                "2 3 4 9 7 7 9 6\n" +
                "2 10 4 8 1 2 8 9\n" +
                "4\n" +
                "2 3 1 8\n" +
                "5 7 5 7\n" +
                "10\n" +
                "6 8 9 7 3 10 7 7 1 7\n" +
                "2 9 6 3 1 3 6 3 10 6\n" +
                "9\n" +
                "2 7 3 4 8 9 6 5 10\n" +
                "1 3 1 10 9 10 8 4 6\n" +
                "9\n" +
                "9 7 6 2 8 10 2 3 2\n" +
                "1 4 9 3 4 3 1 8 9\n" +
                "6\n" +
                "5 1 5 3 1 1\n" +
                "4 5 7 4 5 8\n" +
                "4\n" +
                "7 1 3 7\n" +
                "9 1 5 4\n" +
                "9\n" +
                "4 7 9 1 9 3 9 9 6\n" +
                "5 9 4 4 5 5 5 3 6\n" +
                "3\n" +
                "5 2 2\n" +
                "3 9 4\n" +
                "10\n" +
                "9 6 8 7 8 9 7 6 9 10\n" +
                "1 5 5 3 6 7 9 8 4 5\n" +
                "6\n" +
                "6 4 4 3 2 5\n" +
                "10 4 1 10 9 7\n" +
                "1\n" +
                "10\n" +
                "8\n" +
                "8\n" +
                "9 5 2 5 6 3 10 8\n" +
                "6 7 1 7 5 6 9 6\n" +
                "3\n" +
                "1 9 7\n" +
                "4 3 4\n" +
                "7\n" +
                "3 9 5 6 6 3 8\n" +
                "2 8 2 5 2 5 4\n" +
                "4\n" +
                "2 9 8 7\n" +
                "10 2 10 3\n" +
                "7\n" +
                "3 5 3 6 5 1 4\n" +
                "2 5 1 9 6 9 6\n" +
                "6\n" +
                "9 4 6 8 5 3\n" +
                "5 4 9 1 4 7\n" +
                "4\n" +
                "2 2 3 4\n" +
                "2 2 7 4\n" +
                "8\n" +
                "10 4 5 2 2 1 5 8\n" +
                "6 3 10 10 8 5 9 4\n" +
                "7\n" +
                "10 3 10 5 5 9 3\n" +
                "3 9 10 7 1 3 1\n" +
                "8\n" +
                "3 5 3 1 1 3 7 5\n" +
                "7 10 10 5 10 4 7 5";
        Scanner scanner = new Scanner(data2);
        int nTestCases = scanner.nextInt();
        for(int c = 1; c <= nTestCases; c++){
            int n = scanner.nextInt();
            int[] heights = new int[n];
            int[] widths = new int[n];
            for(int i = 0; i < n; i++){
                heights[i] = scanner.nextInt();
            }
            for(int i = 0; i < n; i++){
                widths[i] = scanner.nextInt();
            }
            Subsequence lis = lis(heights, widths);
            Subsequence lds = lds(heights, widths);
            if(lis.weight >= lds.weight){
                System.out.printf("Case %d. Increasing (%d). Decreasing (%d).\n", c, lis.weight, lds.weight);
            }else{
                System.out.printf("Case %d. Decreasing (%d). Increasing (%d).\n", c, lds.weight, lis.weight);
            }
        }
        scanner.close();
    }

    static Subsequence[] memo;

    static Subsequence lds(int[] sequence, int[] weights){
        memo = new Subsequence[sequence.length];
        Subsequence lds = null;
        for(int i = sequence.length - 1; i >= 0; i--){
            lds = max(lds, lds(sequence, weights, i));
        }
        return lds;
    }

    static Subsequence lds(int[] sequence, int[] weights, int i){
        if(memo[i] != null) return memo[i];
        if(i == 0){
            return new Subsequence(1, weights[i]);
        }
        Subsequence lds = null;
        for(int j = i - 1; j >= 0; j--){
            if(sequence[j] > sequence[i]){
                lds = max(lds, lds(sequence, weights, j));
            }
        }
        if(lds == null) lds = new Subsequence(1, weights[i]);
        else lds = new Subsequence(lds.length + 1, lds.weight + weights[i]);
        memo[i] = lds;
        return lds;
    }

    static Subsequence lis(int[] sequence, int[] weights){
        memo = new Subsequence[sequence.length];
        Subsequence lis = null;
        for(int i = sequence.length - 1; i >= 0; i--){
            lis = max(lis, lis(sequence, weights, i));
        }
        return lis;
    }

    static Subsequence lis(int[] sequence, int[] weights, int i){
        if(memo[i] != null) return memo[i];
        if(i == 0){
            return new Subsequence(1, weights[i]);
        }
        Subsequence lis = null;
        for(int j = i - 1; j >= 0; j--){
            if(sequence[j] < sequence[i]){
                lis = max(lis, lis(sequence, weights, j));
            }
        }
        if(lis == null) lis = new Subsequence(1, weights[i]);
        else lis = new Subsequence(lis.length + 1, lis.weight + weights[i]);
        memo[i] = lis;
        return lis;
    }

    static Subsequence max(Subsequence sub1, Subsequence sub2){
        if(sub1 == null) return sub2; if(sub2 == null) return sub1;
        if(sub1.weight > sub2.weight) return sub1;
        if(sub2.weight > sub1.weight) return sub2;
        if(sub1.length > sub2.length) return sub1;
        return sub2;
    }
}
