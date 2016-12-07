import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10203 Snow Clearing
Problem url: https://uva.onlinejudge.org/external/102/10203.pdf
Author: Andrey Yemelyanov
*/
public class _10203_SnowClearing {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    while(nTests-- > 0){
      solve(s);
      if(nTests > 0) System.out.println();
    }
  }

  static void solve(Scanner s){
    int x = s.nextInt(); int y = s.nextInt(); s.nextLine();
    double distance = 0.0;
    while(true){
      if(!s.hasNext()) break;
      String line = s.nextLine();
      if(line.isEmpty()) break;
      String[] tokens = line.split("\\s+");
      int x1 = Integer.parseInt(tokens[0]);
      int y1 = Integer.parseInt(tokens[1]);
      int x2 = Integer.parseInt(tokens[2]);
      int y2 = Integer.parseInt(tokens[3]);
      distance += 2 * (dist(x1, y1, x2, y2) / 1000);
    }
    double timeInHours = distance / PLOWING_SPEED;
    int minutes = (int) round((timeInHours - (int) timeInHours) * 60);
    if(minutes == 60) {
      System.out.printf("%d:%02d\n", (int) timeInHours + 1, 0);
    }else System.out.printf("%d:%02d\n", (int) timeInHours, minutes);
  }

  static final int PLOWING_SPEED = 20;
  static double dist(int x1, int y1, int x2, int y2){
    return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
  }
}
