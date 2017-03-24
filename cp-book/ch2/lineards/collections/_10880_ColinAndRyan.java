import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10880 Colin and Ryan
Problem url: https://uva.onlinejudge.org/external/108/10880.pdf
Author: Andrey Yemelyanov
*/
public class _10880_ColinAndRyan {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int i = 1; i <= nTests; i++){
      int C = s.nextInt();
      int R = s.nextInt();
      System.out.print("Case #" + i + ":");
      if(C == R) System.out.println(" 0");
      else{
          List<Integer> Q = getDivisors(C - R).stream()
                                              .filter(d -> d > R)
                                              .collect(Collectors.toList());
          if(Q.size() > 0){
            System.out.println(" " +
              Q.stream()
               .map(Object::toString)
               .collect(Collectors.joining(" "))
            );
          }else{
            System.out.println();
          }
      }
    }
  }

  static List<Integer> getDivisors(int n){
    List<Integer> divisors = new ArrayList<>();
    for(int i = 1; i <= floor(sqrt(n)); i++){
      if(n % i == 0){
        if(n / i == i) divisors.add(i);
        else{
          divisors.add(n / i);
          divisors.add(i);
        }
      }
    }
    Collections.sort(divisors);
    return divisors;
  }
}
