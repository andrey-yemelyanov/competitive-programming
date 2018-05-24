import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11203 Can you decide it for ME?
Problem url: https://uva.onlinejudge.org/external/112/11203.pdf
Author: Andrey Yemelyanov
*/
public class _11203 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      if(isTheorem(s.next())) System.out.println("theorem");
      else System.out.println("no-theorem");
    }
  }

  static List<Character> allowedChars = Arrays.asList('M', 'E', '?');
  static boolean validate(String str){
    for(char c : str.toCharArray()) if(!allowedChars.contains(c)) return false;
    List<Integer> mLocation = find('M', str);
    List<Integer> eLocation = find('E', str);
    if(mLocation.size() != 1
      || eLocation.size() != 1
      || eLocation.get(0) < mLocation.get(0)) return false;
    return true;
  }

  static boolean isAxiom(String str){
    int i = 0; int count = 0;
    for(;str.charAt(i) != 'M'; i++) count++;
    if(count < 1) return false;
    int x = count;
    count = 0; i++;
    for(;str.charAt(i) != 'E'; i++) count++;
    if(count != 1) return false;
    count = 0; i++;
    for(;i < str.length(); i++) count++;
    return count - x == 1;
  }

  static boolean isTheorem(String str){
    return validate(str) && (isAxiom(str) || isTheorem(applyProductionRule(str)));
  }

  static String applyProductionRule(String str){
    return str.substring(0, str.indexOf('E') - 1) + str.substring(str.indexOf('E'), str.length() - 1);
  }

  static List<Integer> find(char c, String str){
    List<Integer> location = new ArrayList<>();
    for(int i = 0; i < str.length(); i++){
      if(str.charAt(i) == c) location.add(i);
    }
    return location;
  }
}
