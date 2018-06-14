import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10298 Power Strings
Problem url: https://uva.onlinejudge.org/external/102/10298.pdf
Author: Andrey Yemelyanov
*/
public class _10298 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String str = s.next().trim();
      if(str.equals(".")) break;
      System.out.println(findN(str));
    }
  }

  static int findN(String str){
    for(int div : getDivisors(str.length())){
      int periodLen = str.length() / div;
      String period = str.substring(0, periodLen);
      boolean periodFound = true;
      for(int i = 0; i < str.length(); i += periodLen){
        if(!str.substring(i, i + periodLen).equals(period)){
          periodFound = false;
          break;
        }
      }
      if(periodFound) return div;
    }
    return 1;
  }

  static List<Integer> getDivisors(int n){
    List<Integer> divisors = new ArrayList<>();
    List<Integer> v = new ArrayList<>();
    for(int i = 1; i <= sqrt(n); i++){
      if(n % i == 0){
        divisors.add(i);
        if(n / i != i){
          v.add(n / i);
        }
      }
    }
    for(int i = v.size() - 1; i >= 0; i--) divisors.add(v.get(i));
    Collections.reverse(divisors);
    return divisors;
  }
}
