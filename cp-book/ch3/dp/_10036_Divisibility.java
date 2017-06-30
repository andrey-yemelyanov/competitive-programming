import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10036 Divisibility
Problem url: https://uva.onlinejudge.org/external/100/10036.pdf
Author: Andrey Yemelyanov
*/
public class _10036_Divisibility {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int M = s.nextInt();
    while(M-- > 0){
      int N = s.nextInt(); int K = s.nextInt();
      int[] sequence = new int[N];
      for(int i = 0; i < N; i++){
        sequence[i] = s.nextInt();
      }
      if(divisible(sequence, K)) System.out.println("Divisible");
      else System.out.println("Not divisible");
    }
  }

  static boolean divisible(int[] sequence, int K){
    System.out.println(Arrays.toString(sequence));
    return false;
  }
}
