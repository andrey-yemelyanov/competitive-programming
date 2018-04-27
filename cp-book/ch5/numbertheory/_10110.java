import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10110 Light, more light
Problem url: https://uva.onlinejudge.org/external/101/10110.pdf
Author: Andrey Yemelyanov
*/
public class _10110 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
	  while(s.hasNext()){
      long n = s.nextLong();
      if(n == 0) break;
      long sqrt = (long)sqrt(n);
      if(sqrt * sqrt == n) System.out.println("yes");
      else System.out.println("no");
    }
  }
}
