import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10233 Dermuba Triangle
Problem url: https://uva.onlinejudge.org/external/102/10233.pdf
Author: Andrey Yemelyanov
*/
public class _10233 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int house1 = s.nextInt(); int house2 = s.nextInt();
      double[] house1Coord = toDecartesCoord(house1);
      double[] house2Coord = toDecartesCoord(house2);
      System.out.printf(Locale.US, "%.3f\n",
        dist(house1Coord[0], house1Coord[1],
             house2Coord[0], house2Coord[1]));
    }
  }

  static double dist(double x1, double y1, double x2, double y2){
    return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
  }

  static double[] toDecartesCoord(int house){
    double triangleHeight = sin(PI / 3);
    double subHeight = 0.5 * tan(PI / 6);
    int level = (int)floor(sqrt(house));

    double y = level * triangleHeight;
    if(level % 2 == 0){
      if(house % 2 == 0) y += (triangleHeight - subHeight);
      else y += subHeight;
    }else{
      if(house % 2 == 0) y += subHeight;
      else y += (triangleHeight - subHeight);
    }

    double x = 0.0;
    int mid = (int)((pow(level + 1, 2) + pow(level, 2)) / 2);
    if(house < mid) x = -(mid - house) * 0.5;
    else x = (house - mid) * 0.5;

    return new double[] {x, y};
  }
}
