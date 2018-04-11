import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10338 Mischievous Children
Problem url: https://uva.onlinejudge.org/external/103/10338.pdf
Author: Andrey Yemelyanov
*/
public class _10338 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      System.out.printf("Data set %d: %d\n", c, countArrangements(s.next()));
    }
  }

  static long countArrangements(String word){
    int[] freq = new int['Z' - 'A' + 1];
    for(char c : word.toCharArray()) freq[c - 'A']++;
    long count = factorial(word.length());
    for(int i : freq){
      if(i > 1) count /= factorial(i);
    }
    return count;
  }

  static long factorial(int n){
    long f = 1;
    for(int i = 1; i <= n; i++) f *= i;
    return f;
  }
}
