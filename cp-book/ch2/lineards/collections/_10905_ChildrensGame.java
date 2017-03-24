import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10905 Children's Game
Problem url: https://uva.onlinejudge.org/external/109/10905.pdf
Author: Andrey Yemelyanov
*/
public class _10905_ChildrensGame {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break;
      List<String> numbers = new ArrayList<>();
      for(int i = 0; i < N; i++){
        numbers.add(s.next());
      }
      sortByDigits(numbers);
      System.out.println(
        numbers.stream()
               .collect(Collectors.joining(""))
      );
    }
  }

  static void sortByDigits(List<String> numbers){
    Collections.sort(numbers, new Comparator<String>(){
      @Override
      public int compare(String n1, String n2){
        return compareByDigits(n1, n2);
      }
    });
  }

  static int compareByDigits(String n1, String n2){
    int len1 = n1.length();
    int len2 = n2.length();
    for(int i = 0; i < min(len1, len2); i++){
      int d1 = Character.getNumericValue(n1.charAt(i));
      int d2 = Character.getNumericValue(n2.charAt(i));
      if(d1 > d2) return -1;
      if(d2 > d1) return 1;
    }
    if(len1 != len2){
      if(len1 < len2){
        return compareByDigits(n1, n2.substring(len1));
      }else{
        return compareByDigits(n1.substring(len2), n2);
      }
    }
    return 0;
  }
}
