package helvidios.cp.ch4.traversal;

import java.util.*;

import static java.lang.Math.*;

public class _12442_ForwardingEmails {
    public static void main(String[] args) {
        String data = "3\n" +
                "3\n" +
                "1 2\n" +
                "2 3\n" +
                "3 1\n" +
                "4\n" +
                "1 2\n" +
                "2 1\n" +
                "4 3\n" +
                "3 2\n" +
                "5\n" +
                "1 2\n" +
                "2 1\n" +
                "5 3\n" +
                "3 4\n" +
                "4 5\n";
        Scanner scanner = new Scanner(data);
        int nTests = scanner.nextInt();
        for(int c = 1; c <= nTests; c++){
            int n = scanner.nextInt();
            int[] graph = new int[n + 1];
            for(int i = 1; i <= n; i++){
                graph[scanner.nextInt()] = scanner.nextInt();
            }
            System.out.printf("Case %d: %d\n",   c,  sendEmail(graph));
        }
        scanner.close();
    }

    static int sendEmail(int[] graph){
        int bestChoice = graph.length;
        int bestReachability = 0;
        boolean[] visited = new boolean[graph.length];
        int[] reach = new int[graph.length];
        for(int i = 1; i < graph.length; i++){
            int reachability = reach[i] != 0
                    ? reach[i]
                    : dfs(graph, i, visited, reach);
            if(reachability > bestReachability){
                bestChoice = i;
                bestReachability = reachability;
            }
        }
        return bestChoice;
    }

    static int dfs(int[] graph,
                    int source, boolean[] visited, int[] reach){
        visited[source] = true;
        int nReached = 1;
        if(!visited[graph[source]]){
            nReached = 1 + dfs(graph, graph[source], visited, reach);
        }
        visited[source] = false;
        return reach[source] = nReached;
    }
}
