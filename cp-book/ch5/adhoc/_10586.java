import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10586 Polynomial Remains
Problem url: https://uva.onlinejudge.org/external/105/10586.pdf
Author: Andrey Yemelyanov
*/
public class _10586 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt(); int k = s.nextInt();
      if(n == -1 && k == -1) break;
      int[] c = new int[n + 1];
      for(int i = 0; i < c.length; i++) c[i] = s.nextInt();
      divide(c, k);
      System.out.println(toString(c));
    }
  }

  static String toString(int[] c){
    int index = c.length - 1;
    while(index >= 0 && c[index] == 0) index--;
    if(index < 0) return "0";
    StringJoiner sj = new StringJoiner(" ");
    for(int i = 0; i <= index; i++){
      sj.add(Integer.toString(c[i]));
    }
    return sj.toString();
  }

  static void divide(int[] c, int k){
    int i = c.length - 1;
    while(true){
      while(i >= 0 && c[i] == 0) i--;
      if(i < 0) return;
      if(i - k < 0) return;
      c[i - k] -= c[i];
      c[i] = 0;
    }
  }
}
