import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 902 Password Search
Problem url: https://uva.onlinejudge.org/external/9/902.pdf
Author: Andrey Yemelyanov
*/
public class _902 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int passwordSize = s.nextInt();
      String string = s.next();
      System.out.println(getPassword(string,passwordSize));
    }
  }

  static String getPassword(String string, int passwordSize){
    Map<String, Integer> map = new HashMap<>();
    for(String substr : getAllSubstrings(passwordSize, string)){
      map.putIfAbsent(substr, 0);
      map.put(substr, map.get(substr) + 1);
    }
    int maxFreq = 0;
    String password = null;
    for(String key : map.keySet()){
      if(map.get(key) > maxFreq){
        maxFreq = map.get(key);
        password = key;
      }
    }
    return password;
  }

  static List<String> getAllSubstrings(int substringSize, String string){
    List<String> substr = new ArrayList<>();
    for(int i = 0; i < string.length() - substringSize + 1; i++){
      substr.add(string.substring(i, i + substringSize));
    }
    return substr;
  }
}
