import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 11526 H(n)
Problem url: https://uva.onlinejudge.org/external/115/11526.pdf
Author: Andrey Yemelyanov
*/
public class _11526 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int n = s.nextInt();
      System.out.println(H(n));
    }
  }

  static long H(int n){
    int k = (int)sqrt(n);
    long result = 0;
    for(int i = 1; i <= k; i++){
      result += n / i;
    }
    return 2 * result - k * k;
  }
}
