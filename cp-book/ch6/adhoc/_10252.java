import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10252 Common Permutation
Problem url: https://uva.onlinejudge.org/external/102/10252.pdf
Author: Andrey Yemelyanov
*/
public class _10252 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String a = s.nextLine(); String b = s.nextLine();
      System.out.println(getX(a, b));
    }
  }

  static String getX(String a, String b){
    Map<Character, Integer> aMap = getFreq(a);
    Map<Character, Integer> bMap = getFreq(b);
    List<Character> x = new ArrayList<>();
    for(char key : aMap.keySet()){
      if(bMap.containsKey(key)){
        for(int i = 0; i < min(aMap.get(key), bMap.get(key)); i++){
          x.add(key);
        }
      }
    }
    Collections.sort(x);
    return x.stream().map(e->e.toString()).collect(Collectors.joining());
  }

  static Map<Character, Integer> getFreq(String s){
    Map<Character, Integer> map = new HashMap<>();
    for(char c : s.toCharArray()){
      map.putIfAbsent(c, 0);
      map.put(c, map.get(c) + 1);
    }
    return map;
  }
}
