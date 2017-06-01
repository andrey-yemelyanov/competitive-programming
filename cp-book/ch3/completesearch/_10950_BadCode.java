import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10950 Bad Code
Problem url: https://uva.onlinejudge.org/external/109/10950.pdf
Author: Andrey Yemelyanov
*/
public class _10950_BadCode {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int _case = 1;
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break;
      Map<Character, String> charCodes = new TreeMap<>();
      for(int i = 0; i < N; i++){
        charCodes.put(s.next().charAt(0), s.next());
      }
      String encryptedString = s.next();
      System.out.println("Case #" + _case++);
      decrypt(encryptedString, charCodes).stream().forEach(System.out::println);
      System.out.println();
    }
  }

  static List<String> decrypt(String encryptedString, Map<Character, String> charCodes){
    List<String> decryptedStrings = new ArrayList<>();
    decrypt(encryptedString, 0, "", decryptedStrings, charCodes);
    return decryptedStrings;
  }

  static boolean decrypt(String encryptedString, int i, String decryptedString,
    List<String> decryptedStrings, Map<Character, String> charCodes){
      if(decryptedStrings.size() == 100) return true;
      if(i == encryptedString.length()){
        decryptedStrings.add(decryptedString);
        return false;
      }
      for(char c : charCodes.keySet()){
        String charCode = charCodes.get(c);
        if(containsCode(encryptedString, i, charCode)){
          boolean done = decrypt(encryptedString, i + charCode.length(),
              decryptedString + c, decryptedStrings, charCodes);
          if(done) return true;
        }
        if(containsCode(encryptedString, i, "0" + charCode)){
          boolean done = decrypt(encryptedString, i + charCode.length() + 1,
              decryptedString + c, decryptedStrings, charCodes);
          if(done) return true;
        }
      }
      return false;
    }

    static boolean containsCode(String encryptedString, int i, String code){
      if(i + code.length() > encryptedString.length()) return false;
      return encryptedString.substring(i, i + code.length()).equals(code);
    }
}
