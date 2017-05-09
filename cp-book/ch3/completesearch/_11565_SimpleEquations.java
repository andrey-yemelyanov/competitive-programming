import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11565 Simple Equations
Problem url: https://uva.onlinejudge.org/external/115/11565.pdf
Author: Andrey Yemelyanov
*/
public class _11565_SimpleEquations {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int[] solution = solve(s.nextInt(), s.nextInt(), s.nextInt());
      if(solution != null){
        System.out.printf("%d %d %d\n", solution[0], solution[1], solution[2]);
      }else{
        System.out.println("No solution.");
      }
    }
  }

  static int[] solve(int A, int B, int C){
    for(int x = -100; x <= 100; x++){
      for(int y = -100; y <= 100; y++){
        for(int z = -100; z <= 100; z++){
          if(x == y || y == z || x == z) continue;
          if(x + y + z == A && x * y * z == B && pow(x, 2) + pow(y, 2) + pow(z, 2) == C){
            return new int[] {x, y, z};
          }
        }
      }
    }
    return null;
  }
}
