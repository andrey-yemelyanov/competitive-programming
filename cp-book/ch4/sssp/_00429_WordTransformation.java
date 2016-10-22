package helvidios.cp.ch4.sssp;

import java.util.*;

import static java.lang.Math.*;

public class _00429_WordTransformation {
    public static void main(String[] args) {
        String data = "1\n" +
                "dip\n" +
                "lip\n" +
                "mad\n" +
                "map\n" +
                "maple\n" +
                "may\n" +
                "pad\n" +
                "pip\n" +
                "pod\n" +
                "pop\n" +
                "sap\n" +
                "sip\n" +
                "slice\n" +
                "slick\n" +
                "spice\n" +
                "stick\n" +
                "stock\n" +
                "*\n" +
                "spice stock\n" +
                "may pod";
        String data2 = "2\n" +
                "\n" +
                "aa\n" +
                "bb\n" +
                "ac\n" +
                "*\n" +
                "aa ac\n" +
                "\n" +
                "aa\n" +
                "ab\n" +
                "bb\n" +
                "*\n" +
                "aa bb\n";
        String data3 = "2\n" +
                "\n" +
                "dip\n" +
                "lip\n" +
                "mad\n" +
                "map\n" +
                "maple\n" +
                "may\n" +
                "pad\n" +
                "pip\n" +
                "pod\n" +
                "pop\n" +
                "sap\n" +
                "sip\n" +
                "slice\n" +
                "slick\n" +
                "spice\n" +
                "stick\n" +
                "stock\n" +
                "*\n" +
                "spice stock\n" +
                "may pod\n" +
                "\n" +
                "dip\n" +
                "lip\n" +
                "mad\n" +
                "map\n" +
                "mapl\n" +
                "maple\n" +
                "may\n" +
                "pad\n" +
                "pip\n" +
                "pod\n" +
                "pop\n" +
                "sap\n" +
                "sip\n" +
                "slice\n" +
                "slick\n" +
                "spice\n" +
                "stick\n" +
                "stock\n" +
                "*\n" +
                "spice slick\n" +
                "may mad\n" +
                "map map\n";
        String data4 = "1\n" +
                "\n" +
                "axe\n" +
                "axi\n" +
                "bxi\n" +
                "cxi\n" +
                "dxi\n" +
                "dli\n" +
                "dlx\n" +
                "dls\n" +
                "cls\n" +
                "clx\n" +
                "*\n" +
                "axe axi\n" +
                "axe clx\n" +
                "axi cls\n" +
                "axi dli\n" +
                "dli cls\n" +
                "cxi cls";
        Scanner scanner = new Scanner(data4);
        int nTests = scanner.nextInt();
        while(nTests-- > 0){
            List<String> dict = new ArrayList<>();
            Map<Integer, List<Integer>> graph = new HashMap<>();
            while(true){
                String word = scanner.next();
                if(word.equals("*")) break;
                dict.add(word);
                graph.put(dict.indexOf(word), new ArrayList<>());
            }
            // build an undirected graph where there exists an edge between two words if they differ by one letter
            for(int i = 0; i < dict.size(); i++){
                for(int j = i + 1; j < dict.size(); j++){
                    if(wordsDifferByOneLetter(dict.get(i), dict.get(j))){
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }
            scanner.nextLine();
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                if(line.isEmpty()) break;
                String[] pair = line.split("\\s+");
                bfs(graph, dict.indexOf(pair[0]));
                int editDistance = dist[dict.indexOf(pair[1])];
                System.out.printf("%s %s %d\n", pair[0], pair[1], editDistance);
            }
            if(nTests > 0) System.out.println();
        }
        scanner.close();
    }

    static boolean wordsDifferByOneLetter(String word1, String word2){
        if(word1.length() != word2.length()) return false;
        int nDiff = 0;
        for(int i = 0; i < word1.length(); i++){
            if(word1.charAt(i) != word2.charAt(i)) nDiff++;
        }
        return nDiff == 1;
    }

    static final int INF = -1;
    static int[] dist;
    static int[] parent;
    static void bfs(Map<Integer, List<Integer>> graph, int source){
        dist = new int[graph.keySet().size()];
        for(int i = 0; i < dist.length; i++){
            dist[i] = INF;
        }
        dist[source] = 0;
        parent = new int[graph.keySet().size()];
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        while(!q.isEmpty()){
            int u = q.remove();
            for(int neighbor : graph.get(u)){
                if(dist[neighbor] == INF){
                    dist[neighbor] = dist[u] + 1;
                    parent[neighbor] = u;
                    q.add(neighbor);
                }
            }
        }
    }
}
