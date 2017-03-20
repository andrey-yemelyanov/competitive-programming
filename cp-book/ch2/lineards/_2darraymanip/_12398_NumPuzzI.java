import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12398 NumPuzz I
Problem url: https://uva.onlinejudge.org/external/123/12398.pdf
Author: Andrey Yemelyanov
*/
public class _12398_NumPuzzI {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int c = 1;
    while(s.hasNextLine()){
      String moves = s.nextLine();
      int[][] m = getInitialMatrix(moves);
      System.out.printf("Case #%d:\n%s\n", c++, toString(m));
    }
  }

  static String toString(int[][] m){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < m.length; i++){
      for(int j = 0; j < m[0].length; j++){
        sb.append(m[i][j]);
        if(j < m[0].length - 1) sb.append(" ");
      }
      if(i < m.length - 1) sb.append("\n");
    }
    return sb.toString();
  }

  static final Map<Character, List<Character>> map = new HashMap<>();
  static{
    map.put('a', Arrays.asList('b', 'd'));
    map.put('b', Arrays.asList('a', 'c', 'e'));
    map.put('c', Arrays.asList('b', 'f'));
    map.put('d', Arrays.asList('a', 'e', 'g'));
    map.put('e', Arrays.asList('b', 'd', 'f', 'h'));
    map.put('f', Arrays.asList('c', 'e', 'i'));
    map.put('g', Arrays.asList('d', 'h'));
    map.put('h', Arrays.asList('g', 'e', 'i'));
    map.put('i', Arrays.asList('h', 'f'));
  }

  static int[][] getInitialMatrix(String moves){
    final int dim = 3;
    int[][] m = new int[dim][dim];
    for(int i = moves.length() - 1; i >= 0; i--){
      char move = moves.charAt(i);
      int row = getRow(move, dim); int col = getCol(move, dim);
      m[row][col] += 1;
      for(char neighbor : map.get(move)){
        row = getRow(neighbor, dim); col = getCol(neighbor, dim);
        m[row][col] += 1;
      }
    }
    for(int i = 0; i < m.length; i++){
      for(int j = 0; j < m[0].length; j++){
        m[i][j] %= 10;
      }
    }
    return m;
  }

  static int getRow(char move, int dim){
    return (move - 'a') / dim;
  }

  static int getCol(char move, int dim){
    return (move - 'a') % dim;
  }
}
