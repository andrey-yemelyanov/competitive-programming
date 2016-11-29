import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 839 Not so Mobile
Problem url: https://uva.onlinejudge.org/external/8/839.pdf
Author: Andrey Yemelyanov
*/
public class _839_NotSoMobile {
  static class Result{
    public int totalWeight;
    public boolean isInEquilibrium;
    public Result(int totalWeight, boolean isInEquilibrium){
      this.totalWeight = totalWeight;
      this.isInEquilibrium = isInEquilibrium;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    while(nTests-- > 0){
      if(isInEquilibrium(s)) System.out.println("YES");
      else System.out.println("NO");
      if(nTests > 0) System.out.println();
      if(s.hasNext()) s.nextLine();
    }
  }

  static boolean isInEquilibrium(Scanner s){
    return explore(s).isInEquilibrium;
  }

  static Result explore(Scanner s){
    int wLeft = s.nextInt();
    int dLeft = s.nextInt();
    int wRight = s.nextInt();
    int dRight = s.nextInt();
    //System.out.printf("%d %d %d %d\n", wLeft, dLeft, wRight, dRight);
    boolean leftSubMobileInEquilibrium = true;
    boolean rightSubMobileInEquilibrium = true;
    if(wLeft == 0){
      Result result = explore(s);
      wLeft = result.totalWeight;
      leftSubMobileInEquilibrium = result.isInEquilibrium;
    }
    if(wRight == 0){
      Result result = explore(s);
      wRight = result.totalWeight;
      rightSubMobileInEquilibrium = result.isInEquilibrium;
    }
    int totalWeight = wLeft + wRight;
    boolean isInEquilibrium = wLeft * dLeft == wRight * dRight;
    return new Result(totalWeight, isInEquilibrium && leftSubMobileInEquilibrium && rightSubMobileInEquilibrium);
  }
}
