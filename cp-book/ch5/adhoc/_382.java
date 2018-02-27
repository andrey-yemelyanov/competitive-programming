import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 382 Perfection
Problem url: https://uva.onlinejudge.org/external/3/382.pdf
Author: Andrey Yemelyanov
*/
public class _382 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    System.out.println("PERFECTION OUTPUT");
    while(true){
      int n = s.nextInt();
      if(n == 0) break;
      int divisorsSum = sum(getProperDivisors(n));
      if(divisorsSum == n) System.out.printf("%5d  PERFECT\n", n);
      else if(divisorsSum < n) System.out.printf("%5d  DEFICIENT\n", n);
      else System.out.printf("%5d  ABUNDANT\n", n);
    }
    System.out.println("END OF OUTPUT");
  }

  private static int sum(List<Integer> list){
    return list.stream().mapToInt(i -> i).sum();
  }

  private static List<Integer> getProperDivisors(int n){
    List<Integer> divisors = new ArrayList<>();
    for(int i = 1; i <= n / 2; i++) if(n % i == 0) divisors.add(i);
    return divisors;
  }
}
