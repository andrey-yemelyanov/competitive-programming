import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 11078 Open Credit System
Problem url: https://uva.onlinejudge.org/external/110/11078.pdf
Author: Andrey Yemelyanov
*/
public class _11078_OpenCreditSystem {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int t = 0; t < nTests; t++){
      int[] scores = new int[s.nextInt()];
      for(int i = 0; i < scores.length; i++){
        scores[i] = s.nextInt();
      }
      System.out.println(getMaxDiff(scores));
    }
  }

  static int getMaxDiff(int[] scores){
   int maxIndex = 0;
   int maxDiff = Integer.MIN_VALUE;
   for(int i = 1; i < scores.length; i++){
     maxDiff = max(maxDiff, scores[maxIndex] - scores[i]);
     if(scores[i] > scores[maxIndex]){
       maxIndex = i;
     }
   }
   return maxDiff;
 }
}
