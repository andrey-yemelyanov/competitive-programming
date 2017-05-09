import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11342 Three-square
Problem url: https://uva.onlinejudge.org/external/113/11342.pdf
Author: Andrey Yemelyanov
*/
public class _11342_ThreeSquare {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    StringBuilder out = new StringBuilder();
    while(nTests-- > 0){
      Integer[] solution = solveThreeSquare(s.nextInt());
      if(solution != null) {
        out.append(solution[0]);
        out.append(" ");
        out.append(solution[1]);
        out.append(" ");
        out.append(solution[2]);
        out.append("\n");
      }
      else out.append("-1\n");
    }
    System.out.print(out.toString());
  }

  static Map<Integer, Integer[]> map = new HashMap<>();
  static int[] square;
  static{
    square = new int[255];
    for(int i = 0; i < square.length; i++){
      square[i] = (int)pow(i, 2);
    }
  }

  static Integer[] solveThreeSquare(int K){
    if(map.containsKey(K)) return map.get(K);
    int max = (int)ceil(sqrt(K));
    for(int a = 0; a <= max; a++){
      for(int b = a; b <= max; b++){
        if(square[a] + square[b] >= K) break;
        for(int c = b; c <= max; c++){
          if(square[a] + square[b] + square[c] > K) break;
          if(square[a] + square[b] + square[c] == K){
            map.put(K, new Integer[] {a, b, c});
            return map.get(K);
          }
        }
      }
    }
    map.put(K, null);
    return null;
  }
}
