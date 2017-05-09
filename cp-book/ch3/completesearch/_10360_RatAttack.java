import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10360 Rat Attack
Problem url: https://uva.onlinejudge.org/external/103/10360.pdf
Author: Andrey Yemelyanov
*/
public class _10360_RatAttack {
  public static class RatPopulation{
    public int x;
    public int y;
    public int populationSize;
    public RatPopulation(int x, int y, int populationSize){
      this.x = x;
      this.y = y;
      this.populationSize = populationSize;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int d = s.nextInt();
      int n = s.nextInt();
      List<RatPopulation> rats = new ArrayList<>();
      for(int i = 0; i < n; i++){
        rats.add(new RatPopulation(s.nextInt(), s.nextInt(), s.nextInt()));
      }
      int[] optimal = findOptimalBombPlacement(d, rats);
      System.out.printf("%d %d %d\n", optimal[0], optimal[1], optimal[2]);
    }
  }

  static int[] findOptimalBombPlacement(int d, List<RatPopulation> rats){
    int[][] killed = new int[1025][1025];
    for(RatPopulation rat : rats){
      int fromCol = rat.y >= d ? rat.y - d : 0;
      int toCol = rat.y + d < killed[0].length ? rat.y + d : killed[0].length - 1;
      int fromRow = rat.x >= d ? rat.x - d : 0;
      int toRow = rat.x + d < killed.length ? rat.x + d : killed.length - 1;
      for(int i = fromRow; i <= toRow; i++){
        for(int j = fromCol; j <= toCol; j++){
          killed[i][j] += rat.populationSize;
        }
      }
    }
    int maxKill = Integer.MIN_VALUE;
    int x = 0; int y = 0;
    for(int i = 0; i < killed.length; i++){
      for(int j = 0; j < killed[i].length; j++){
        if(killed[i][j] > maxKill){
          maxKill = killed[i][j];
          x = i;
          y = j;
        }
      }
    }
    return new int[]{x, y, maxKill};
  }
}
