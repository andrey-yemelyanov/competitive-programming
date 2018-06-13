import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11452 Dancing the Cheeky-Cheeky
Problem url: https://uva.onlinejudge.org/external/114/11452.pdf
Author: Andrey Yemelyanov
*/
public class _11452 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      String steps = s.next();
      System.out.println(getNextSteps(steps));
    }
  }

  static final int NEXT_STEPS_LEN = 8;
  static String getNextSteps(String steps){
    String period = findPeriod(steps);
    String remainingSteps = steps.substring(2 * period.length());
    String nextSteps = period.substring(remainingSteps.length());
    if(nextSteps.length() > NEXT_STEPS_LEN) return nextSteps.substring(0, NEXT_STEPS_LEN) + "...";
    int i = 0;
    while(nextSteps.length() != 8){
      nextSteps += period.charAt(i);
      i = (i + 1) % period.length();
    }
    return nextSteps + "...";
  }

  static String findPeriod(String steps){
    for(int i = 1; i <= steps.length(); i++){
      String str1 = steps.substring(0, i);
      String str2 = steps.substring(i, steps.length());
      if(str2.startsWith(str1) && str1.length() * 3 > steps.length()) return str1;
    }
    return "";
  }
}
