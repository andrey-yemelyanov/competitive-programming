import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11827 Maximum GCD
Problem url: https://uva.onlinejudge.org/external/118/11827.pdf
Author: Andrey Yemelyanov
*/
public class _11827 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    while(nTests-- > 0){
      String[] line = s.nextLine().split("\\s+");
      int[] ints = new int[line.length];
      for(int i = 0; i < ints.length; i++) ints[i] = Integer.parseInt(line[i]);
      System.out.println(maxGcd(ints));
    }
  }

  static int maxGcd(int[] ints){
    int maxGcd = 0;
    for(int i = 0; i < ints.length; i++){
      for(int j = i + 1; j < ints.length; j++){
        maxGcd = max(maxGcd, gcd(ints[i], ints[j]));
      }
    }
    return maxGcd;
  }

  static int gcd(int a, int b){return b == 0 ? a : gcd(b, a % b);}
}
