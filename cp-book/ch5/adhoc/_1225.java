import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 1225 Digit Counting
Problem url: https://uva.onlinejudge.org/external/12/1225.pdf
Author: Andrey Yemelyanov
*/
public class _1225 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int N = s.nextInt();
      int[] count = countDigits(N);
      for(int i = 0; i < count.length; i++){
        System.out.print(count[i]);
        if(i < count.length - 1) System.out.print(" ");
      }
      System.out.println();
    }
  }

  static int[] countDigits(int N){
    int[] digits = new int[10];
    for(int i = 1; i <= N; i++){
      for(int digit : getDigits(i)){
        digits[digit]++;
      }
    }
    return digits;
  }

  static List<Integer> getDigits(int N){
    List<Integer> digits = new ArrayList<>();
    while(N != 0){
      digits.add(N % 10);
      N /= 10;
    }
    return digits;
  }
}
