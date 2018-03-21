import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 443 Humble Numbers
Problem url: https://uva.onlinejudge.org/external/4/443.pdf
Author: Andrey Yemelyanov
*/
public class _443 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Long> humbleNumbers = generateHumbleNumbers();
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.printf("The %d%s humble number is %d.\n", n, getSuffix(n), humbleNumbers.get(n - 1));
    }
  }

  static List<Long> generateHumbleNumbers(){
    List<Long> humbleNumbers = new ArrayList<>();
    final int MAX = 31;
    for(int i = 0; i < MAX; i++){
      for(int j = 0; j < MAX; j++){
        for(int k = 0; k < MAX; k++){
          for(int l = 0; l < MAX; l++){
            humbleNumbers.add((long)(pow(2, i) * pow(3, j) * pow(5, k) * pow(7, l)));
          }
        }
      }
    }
    Collections.sort(humbleNumbers);
    //System.out.println(humbleNumbers.indexOf(3072L));
    return humbleNumbers;
  }

  static String getSuffix(int n){
    if(map.containsKey(n % 100)) return map.get(n % 100);
    if(map.containsKey(n % 10)) return map.get(n % 10);
    return "th";
  }

  static Map<Integer, String> map = new HashMap<>();
  static {
    map.put(1, "st");
    map.put(2, "nd");
    map.put(3, "rd");
    map.put(11, "th");
    map.put(12, "th");
    map.put(13, "th");
  }
}
