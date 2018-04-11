import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10892 LCM Cardinality
Problem url: https://uva.onlinejudge.org/external/108/10892.pdf
Author: Andrey Yemelyanov
*/
public class _10892 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break;
      System.out.printf("%d %d\n", N, lcmCardinality(N));
    }
  }

  static int lcmCardinality(int N){
    int cardinality = 0;
    List<Integer> divisors = getDivisors(N);
    for(int i = 0; i < divisors.size(); i++){
      for(int j = i + 1; j < divisors.size(); j++){
        if(lcm(divisors.get(i), divisors.get(j)) == N) cardinality++;
      }
    }
    return cardinality + 1;
  }

  static List<Integer> getDivisors(int n){
    List<Integer> divisors = new ArrayList<>();
    for(int i = 1; i <= (int)sqrt(n); i++){
      if(n % i == 0){
        divisors.add(i);
        if(n / i != i) divisors.add(n / i);
      }
    }
    return divisors;
  }

  static int gcd(int a, int b){return b == 0 ? a : gcd(b, a % b);}
  static int lcm(int a, int b){return a * (b / gcd(a, b));}
}
