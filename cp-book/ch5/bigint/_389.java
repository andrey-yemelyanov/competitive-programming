import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 389 Basically Speaking
Problem url: https://uva.onlinejudge.org/external/3/389.pdf
Author: Andrey Yemelyanov
*/
public class _389 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      System.out.println(printNumber(convert(s.next(), s.nextInt(), s.nextInt())));
    }
  }

  static String convert(String num, int fromBase, int toBase){
    return Integer.toString(Integer.parseInt(num, fromBase), toBase);
  }

  static String printNumber(String value){
    if(value.length() <= 7) return String.format("%7s", value.toUpperCase());
    return String.format("%7s", "ERROR");
  }
}
