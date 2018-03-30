import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.*;

/*
Problem name: 343 What Base Is This?
Problem url: https://uva.onlinejudge.org/external/3/343.pdf
Author: Andrey Yemelyanov
*/
public class _343 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      String X = s.next(); String Y = s.next();
      int[] match = getMatch(X, Y);
      if(match == null){
        System.out.printf("%s is not equal to %s in any base 2..36\n", X, Y);
      }else{
        System.out.printf("%s (base %d) = %s (base %d)\n", X, match[0], Y, match[1]);
      }
    }
  }

  static int[] getMatch(String X, String Y){
    final int MAX_BASE = 36;
    for(int base1 = 2; base1 <= MAX_BASE; base1++){
      if(!isBasePossible(X, base1)) continue;
      for(int base2 = 2; base2 <= MAX_BASE; base2++){
        if(!isBasePossible(Y, base2)) continue;
        if(new BigInteger(X, base1).equals(new BigInteger(Y, base2))) {
          return new int[]{base1, base2};
        }
      }
    }
    return null;
  }

  static boolean isBasePossible(String num, int base){
    try{
      new BigInteger(num, base);
      return true;
    }
    catch(NumberFormatException ex){
      return false;
    }
  }
}
