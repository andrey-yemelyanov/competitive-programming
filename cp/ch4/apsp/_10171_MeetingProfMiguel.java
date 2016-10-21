import java.util.*;
import static java.lang.Math.*;

public class _10171_MeetingProfMiguel {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break; s.nextLine();
      int[][] distYoung = new int[MAX_NODES + 1][MAX_NODES + 1];
      int[][] distOld = new int[MAX_NODES + 1][MAX_NODES + 1];
      for(int i = 0; i < distYoung.length; i++){
        for(int j = 0; j < distYoung.length; j++){
          if(i != j){
            distYoung[i][j] = INF;
            distOld[i][j] = INF;
          }
        }
      }

      for(int i = 0; i < N; i++){
        String[] line = s.nextLine().split("\\s+");
        boolean forYoungOnly = line[0].equals("Y");
        boolean bidirectional = line[1].equals("B");
        int from = line[2].toCharArray()[0] - 'A'; int to = line[3].toCharArray()[0] - 'A';
        int cost = Integer.parseInt(line[4]);
        int[][] dist = distOld;
        if(forYoungOnly) dist = distYoung;
        if(cost < dist[from][to]) {
          dist[from][to] = cost;
        }
        if(bidirectional && cost < dist[to][from]) dist[to][from] = cost;
      }

      int pos1 = s.next().toCharArray()[0] - 'A'; int pos2 = s.next().toCharArray()[0] - 'A';

      floydWarshall(distYoung);
      floydWarshall(distOld);

      int minDist = INF;
      for(int i = 0; i <= MAX_NODES; i++){
        minDist = min(minDist, distYoung[pos1][i] + distOld[pos2][i]);
      }
      if(minDist == INF) System.out.println("You will never meet.");
      else {
        List<Character> list = new ArrayList<>();
        for(int i = 0; i <= MAX_NODES; i++){
          if(distYoung[pos1][i] + distOld[pos2][i] == minDist){
            list.add((char)(i + 'A'));
          }
        }
        System.out.print(minDist + " ");
        for(int i = 0; i < list.size(); i++){
          System.out.print(list.get(i));
          if(i < list.size() - 1) System.out.print(" ");
        }
        System.out.println();
      }
    }
  }

  static void print(int[][] dist){
    for(int i = 0; i < dist.length; i++){
      System.out.println(Arrays.toString(dist[i]));
    }
  }

  static final int INF = 1000000;
  static final int MAX_NODES = 26;
  static void floydWarshall(int[][] dist){
    int V = dist.length;
    for(int k = 0; k < V; k++){
      for(int i = 0; i < V; i++){
        for(int j = 0; j < V; j++){
          dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }
  }
}
