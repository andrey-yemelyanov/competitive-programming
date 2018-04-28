import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 11371 Number Theory for Newbies
Problem url: https://uva.onlinejudge.org/external/113/11371.pdf
Author: Andrey Yemelyanov
*/
public class _11371 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int n = s.nextInt();
      long[] solution = solve(n);
      System.out.printf("%d - %d = %d = 9 * %d\n",
        solution[0],
        solution[1],
        solution[0] - solution[1],
        (solution[0] - solution[1]) / 9);
    }
  }

  static long[] solve(int n){
    List<Integer> digits = toDigits(n);
    Collections.sort(digits, new Comparator<Integer>(){
      @Override
      public int compare(Integer n1, Integer n2){
        return Integer.compare(n2, n1);
      }
    });
    long max = toLong(digits);
    Collections.sort(digits);
    if(digits.get(0) == 0){
      for(int i = 1; i < digits.size(); i++){
        if(digits.get(i) != 0){
          digits.set(0, digits.get(i));
          digits.set(i, 0);
          break;
        }
      }
    }
    long min = toLong(digits);
    return new long[]{max, min};
  }

  static List<Integer> toDigits(int n){
    List<Integer> digits = new ArrayList<>();
    while(n != 0){
      digits.add(n % 10);
      n /= 10;
    }
    return digits;
  }

  static long toLong(List<Integer> digits){
    long n = 0;
    for(int digit : digits) n = n * 10 + digit;
    return n;
  }
}
