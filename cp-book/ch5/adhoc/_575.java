import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 575 Skew Binary
Problem url: https://uva.onlinejudge.org/external/5/575.pdf
Author: Andrey Yemelyanov
*/
public class _575 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
	  while(s.hasNext()){
      String skewBinary = s.next();
      if(skewBinary.equals("0")) break;
      System.out.println(toDecimal(skewBinary));
    }
  }

  static int toDecimal(String skewBinary){
    int result = 0;
    for(int i = 0; i < skewBinary.length(); i++){
      result += Character.getNumericValue(skewBinary.charAt(i)) * (pow(2, skewBinary.length() - i) - 1);
    }
    return result;
  }
}
