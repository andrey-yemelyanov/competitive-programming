package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _11284_ShoppingTrip {
    public static void main(String[] args) {
        String data = "2\n" +
                "4 5\n" +
                "0 1 1.00\n" +
                "1 2 3.00\n" +
                "1 3 2.00\n" +
                "2 4 4.00\n" +
                "3 4 3.25\n" +
                "3\n" +
                "2 1.50\n" +
                "3 7.00\n" +
                "4 9.00\n" +
                "1 1\n" +
                "0 1 1.50\n" +
                "1\n" +
                "1 2.99";
        Scanner scanner = new Scanner(data);
        scanner.useLocale(Locale.ENGLISH);
        int nTestCases = scanner.nextInt();
        while(nTestCases-- > 0){
            int[][] graph = readGraph(scanner);
            int[] operas = new int[scanner.nextInt() + 1];
            int[] moneySaved = readSavings(scanner, graph.length, operas);
            int[][] cost = floydWarshall(graph);
            int minCost = tsp(operas, cost, moneySaved);
            if(minCost >= 0) System.out.println("Don't leave the house");
            else System.out.printf("Daniel can save $%.2f\n",
                    (double)(minCost * -1) / 100);
        }
        scanner.close();
    }

    static int tsp(int[] operas, int[][] cost, int[] moneySaved){
        memo = new int[operas.length + 1][(1 << operas.length)];
        for(int i = 0; i < memo.length; i++){
            for(int j = 0; j < memo[0].length; j++){
                memo[i][j] = UNDEFINED;
            }
        }
        return tsp(0, 1, operas, cost, moneySaved);
    }

    static int[][] memo;
    static final int POS_INFINITY = (int)1e9;
    static final int UNDEFINED = Integer.MAX_VALUE;
    static int tsp(int pos, int mask, int[] operas, int[][] cost, int[] moneySaved){
        if(mask == (1 << operas.length) - 1) return cost[operas[pos]][0];
        if(memo[pos][mask] != UNDEFINED) return memo[pos][mask];
        int minCost = POS_INFINITY;
        for(int next = 0; next < operas.length; next++){
            if((mask & (1 << next)) == 0){
                minCost = min(minCost,
                        min(tsp(pos, mask | (1 << next), operas, cost, moneySaved),
                                cost[operas[pos]][operas[next]] - moneySaved[operas[next]]
                                        + tsp(next, mask | (1 << next), operas, cost, moneySaved)));
            }
        }
        memo[pos][mask] = minCost;
        return minCost;
    }

    static int[][] floydWarshall(int[][] graph){
        int[][] cost = new int[graph.length][graph.length];
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph.length; j++){
                cost[i][j] = graph[i][j];
            }
        }
        for(int k = 0; k < graph.length; k++){
            for(int i = 0; i < graph.length; i++){
                for(int j = 0; j < graph.length; j++){
                    if(cost[i][k] + cost[k][j] < cost[i][j]){
                        cost[i][j] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }
        return cost;
    }

    static int toCents(double amount){
        return (int)(amount * 100 + 1e-8);
    }

    static int[][] readGraph(Scanner s){
        int N = s.nextInt();
        int M = s.nextInt();
        int[][] graph = new int[N + 1][N + 1];
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph.length; j++){
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = POS_INFINITY;
            }
        }
        for(int i = 0; i < M; i++){
            int v1 = s.nextInt();
            int v2 = s.nextInt();
            int cost = toCents(s.nextDouble());
            graph[v1][v2] = graph[v2][v1] = min(graph[v1][v2], cost);
        }
        return graph;
    }

    static int[] readSavings(Scanner s, int nVertices, int[] operas){
        int[] moneySaved = new int[nVertices];
        for(int i = 0; i < operas.length - 1; i++){
            int store = s.nextInt();
            int savings = toCents(s.nextDouble());
            moneySaved[store] = savings;
            operas[i + 1] = store;
        }
        return moneySaved;
    }
}
