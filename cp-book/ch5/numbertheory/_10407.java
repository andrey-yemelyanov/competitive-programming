import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10407 Simple division
Problem url: https://uva.onlinejudge.org/external/104/10407.pdf
Author: Andrey Yemelyanov
*/
public class _10407 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      List<Integer> l = new ArrayList<>();
      while(true){
        int n = s.nextInt();
        if(n == 0) break;
        l.add(n);
      }
      if(!l.isEmpty()){
        int[] a = new int[l.size()];
        for(int i = 0; i < a.length; i++) a[i] = l.get(i);
        System.out.println(d(a));
      }
    }
  }

  static int d(int[] a){
    int s = a[0];
    for(int i = 0; i < a.length; i++) {
      a[i] -= s;
      if(a[i] < 0) a[i] *= -1;
    }
    return gcd(a);
  }

  static int gcd(int[] a){
    int gcd = a[0];
    for(int i = 1; i < a.length; i++) gcd = gcd(gcd, a[i]);
    return gcd;
  }

  static int gcd(int a, int b){return b == 0 ? a : gcd(b, a % b);}
}
