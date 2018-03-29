import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10931 Parity
Problem url: https://uva.onlinejudge.org/external/109/10931.pdf
Author: Andrey Yemelyanov
*/
public class _10931 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.printf("The parity of %s is %d (mod 2).\n", bitString(n), parity(n));
    }
  }

  static String bitString(int n){
    String bitString = "";
    while(n > 0){
      bitString = (n & 1) + bitString;
      n >>= 1;
    }
    return bitString;
  }

  static int parity(int n){
    int parity = 0;
    while(n > 0){
      parity += n & 1;
      n >>= 1;
    }
    return parity;
  }
}
