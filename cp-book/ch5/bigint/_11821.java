import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.*;

/*
Problem name: 11821 High-Precision Number
Problem url: https://uva.onlinejudge.org/external/118/11821.pdf
Author: Andrey Yemelyanov
*/
public class _11821 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      List<BigDecimal> numbers = new ArrayList<>();
      while(true){
        BigDecimal num = s.nextBigDecimal();
        if(num.equals(BigDecimal.ZERO)) break;
        numbers.add(num);
      }
      System.out.println(sum(numbers).toPlainString());
    }
  }

  static BigDecimal sum(List<BigDecimal> numbers){
    BigDecimal sum = BigDecimal.ZERO;
    for(BigDecimal num : numbers){
      sum = sum.add(num);
    }
    return sum.stripTrailingZeros();
  }
}
