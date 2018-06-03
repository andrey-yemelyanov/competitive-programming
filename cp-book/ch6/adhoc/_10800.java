import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10800 Not That Kind of Graph
Problem url: https://uva.onlinejudge.org/external/108/10800.pdf
Author: Andrey Yemelyanov
*/
public class _10800 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      System.out.printf("Case #%d:\n", c);
      System.out.println(toString(buildGraph(s.next())));
      System.out.println();
    }
  }

  static String toString(char[][] graph){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < graph.length; i++){
      sb.append("| ");
      for(int j = 0; j <= getLastPrintableChar(graph, i); j++){
        sb.append(graph[i][j]);
      }
      sb.append("\n");
    }
    sb.append("+-");
    for(int i = 0; i <= graph[0].length; i++){
      sb.append("-");
    }
    return sb.toString();
  }

  static int getLastPrintableChar(char[][] graph, int level){
    int last = 0;
    for(int day = 0; day < graph[level].length; day++){
      if(graph[level][day] != ' ') last = day;
    }
    return last;
  }

  static final Map<Character, Character> m = new HashMap<>();
  static{
    m.put('C', '_');
    m.put('R', '/');
    m.put('F', '\\');
  }
  static char[][] buildGraph(String stockMovement){
    int nDays = stockMovement.length();
    int[] extremes = getExtremes(stockMovement);
    int maxLevel = extremes[1]; int minLevel = extremes[0];
    int height = maxLevel - minLevel + 1;
    char[][] graph = new char[height][nDays];
    for(int i = 0; i < height; i++){
      for(int j = 0; j < nDays; j++){
        graph[i][j] = ' ';
      }
    }
    int level = maxLevel;
    for(int day = 0; day < nDays; day++){
      char todaysMovement = stockMovement.charAt(day);
      graph[level][day] = m.get(todaysMovement);
      if(day + 1 < nDays){
        char tomorrowsMovement = stockMovement.charAt(day + 1);
        if(todaysMovement == 'C'){
          if(tomorrowsMovement == 'F') level++;
        }
        else if(todaysMovement == 'F'){
          if(tomorrowsMovement == 'F') level++;
        }
        else if(todaysMovement == 'R'){
          if(tomorrowsMovement == 'R' || tomorrowsMovement == 'C') level--;
        }
      }
    }
    return graph;
  }

  static int[] getExtremes(String stockMovement){
    int minLevel = 0;
    int maxLevel = 0;
    int level = 0;
    for(int day = 0; day < stockMovement.length(); day++){
      int todaysMovement = stockMovement.charAt(day);
      if(day + 1 < stockMovement.length()){
        char tomorrowsMovement = stockMovement.charAt(day + 1);
        if(todaysMovement == 'C'){
          if(tomorrowsMovement == 'F') level--;
        }
        else if(todaysMovement == 'F'){
          if(tomorrowsMovement == 'F') level--;
        }
        else if(todaysMovement == 'R'){
          if(tomorrowsMovement == 'R' || tomorrowsMovement == 'C') level++;
        }
        minLevel = min(minLevel, level);
        maxLevel = max(maxLevel, level);
      }
    }
    return new int[]{minLevel, maxLevel};
  }
}
