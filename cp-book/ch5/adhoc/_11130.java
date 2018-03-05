import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11130 Billiard bounces
Problem url: https://uva.onlinejudge.org/external/111/11130.pdf
Author: Andrey Yemelyanov
*/
public class _11130 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int a = s.nextInt();
      int b = s.nextInt();
      int v = s.nextInt();
      int A = s.nextInt();
      int seconds = s.nextInt();
      if(a == 0 && b == 0 && v == 0 && A == 0 && seconds == 0) break;
      int[] result = runSimulation(a, b, v, A, seconds);
      System.out.printf("%d %d\n", result[0], result[1]);
    }
  }

  static int[] runSimulation(int a, int b, int v, int A, int s){
    double d = v * s / 2.0;
    double radians = A * PI / 180;
    double y = d * sin(radians);
    double x = d * cos(radians);
    int nY = (y < b / 2.0) ? 0 : ((int)floor((y - b / 2.0) / b) + 1);
    int nX = (x < a / 2.0) ? 0 : ((int)floor((x - a / 2.0) / a) + 1);
    return new int[]{nX, nY};
  }
}
