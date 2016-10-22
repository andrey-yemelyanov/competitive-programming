package helvidios.cp.ch4.traversal;

import java.util.*;

import static java.lang.Math.*;

public class _11902_Dominator {
    public static void main(String[] args) {
        String data = "2\n" +
                "5\n" +
                "0 1 1 0 0\n" +
                "0 0 0 1 0\n" +
                "0 0 0 1 0\n" +
                "0 0 0 0 1\n" +
                "0 0 0 0 0\n" +
                "1\n" +
                "1";
        Scanner scanner = new Scanner(data);
        int nTests = scanner.nextInt();
        StringBuilder out = new StringBuilder();
        for(int c = 1; c <= nTests; c++){
            int nNodes = scanner.nextInt();
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int i = 0; i < nNodes; i++){
                graph.put(i, new ArrayList<>());
            }
            scanner.nextLine();
            for(int i = 0; i < graph.size(); i++) {
                String[] line = scanner.nextLine().split("\\s+");
                for (int j = 0; j < graph.size(); j++) {
                    if(line[j].equals("1")){
                        graph.get(i).add(j);
                    }
                }
            }
            boolean[][] m = dominatorMatrix(graph);
            out.append(String.format("Case %d:\n", c));
            for(int i = 0; i < graph.size(); i++) {
                out.append(printLine(m.length) + "\n");
                for (int j = 0; j < graph.size(); j++) {
                    out.append("|" + (m[i][j] ? "Y" : "N"));
                }
                out.append("|\n");
            }
            out.append(printLine(m.length) + "\n");
        }
        System.out.print(out.toString());
        scanner.close();
    }

    static String printLine(int length){
        StringBuilder s = new StringBuilder();
        s.append("+");
        for(int i = 1; i <= length * 2 - 1; i++){
            s.append("-");
        }
        s.append("+");
        return s.toString();
    }

    static void dfs(Map<Integer, List<Integer>> graph,
                    boolean[] visited, int source,
                    int disabledNode){
        if(source == disabledNode) return;
        if(visited[source]) return;
        visited[source] = true;
        for(int neighbor : graph.get(source)){
            dfs(graph, visited, neighbor, disabledNode);
        }
    }

    static boolean[][] dominatorMatrix(Map<Integer, List<Integer>> graph){
        boolean[][] m = new boolean[graph.size()][graph.size()];
        boolean[] reachable1 = new boolean[graph.size()];
        dfs(graph, reachable1, 0, -1);

        for(int i = 0; i < graph.size(); i++){
            boolean[] reachable2 = new boolean[graph.size()];
            dfs(graph, reachable2, 0, i);
            for(int j = 0; j < reachable2.length; j++){
                if(reachable1[j] && !reachable2[j]){
                    m[i][j] = true;
                }
            }
        }

        return m;
    }
}
