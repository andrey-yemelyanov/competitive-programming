import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 256 Quirksome Squares
Problem url: https://uva.onlinejudge.org/external/2/256.pdf
Author: Andrey Yemelyanov
*/
public class _256_QuirksomeSquares {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int nDigits = s.nextInt();
      String format = "%0" + nDigits + "d";
      getQuirksomeNumbers(nDigits).stream()
                                  .map(i -> String.format(format, i))
                                  .forEach(System.out::println);
    }
  }

  static List<Integer> getQuirksomeNumbers(int nDigits){
    List<Integer> numbers = new ArrayList<>();
    for(int n = 0; n <= pow(10, nDigits) - 1; n++){
      if(isQuirksomeNumber(n, nDigits)) numbers.add(n);
    }
    return numbers;
  }

  static boolean isQuirksomeNumber(int n, int nDigits){
    int n1 = n / (int)pow(10, nDigits / 2);
    int n2 = n % (int)pow(10, nDigits / 2);
    return pow((n1 + n2), 2) == n;
  }
}
