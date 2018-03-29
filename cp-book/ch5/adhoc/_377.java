import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 377 Cowculations
Problem url: https://uva.onlinejudge.org/external/3/377.pdf
Author: Andrey Yemelyanov
*/
public class _377 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTablets = s.nextInt();
    System.out.println("COWCULATIONS OUTPUT");
    while(nTablets-- > 0){
      String num1 = s.next();
      String num2 = s.next();
      for(int i = 0; i < 3; i++){
        num2 = executeOperation(num1, num2, s.next());
      }
      String expected = s.next();
      if(pad(num2).equals(expected)) System.out.println("YES");
      else System.out.println("NO");
    }
    System.out.println("END OF OUTPUT");
  }

  static String executeOperation(String num1, String num2, String operation){
    switch(operation){
      case "A":
        return add(num1, num2);
      case "R":
        return shiftRight(num2);
      case "L":
        return shiftLeft(num2);
      default:
        return num2;
    }
  }

  static String pad(String num){
    final int LEN = 8;
    String paddedNum = num;
    if(num.length() < LEN){
      for(int i = 0; i < LEN - num.length(); i++) paddedNum = "V" + paddedNum;
    }
    return paddedNum;
  }

  static String shiftLeft(String num){
    return num + "V";
  }

  static String shiftRight(String num){
    return "V" + num.substring(0, num.length() - 1);
  }

  static String add(String num1, String num2){
    return decode(encode(num1) + encode(num2));
  }

  static final String alphabet = "VUCD";
  static final Map<Character, Integer> charToInt = new HashMap<>();
  static{
    for(int i = 0; i < alphabet.length(); i++) charToInt.put(alphabet.charAt(i), i);
  }

  static String decode(int num){
    int base = alphabet.length();
    String decodedNum = "";
    do{
      int rem = num % base;
      decodedNum = alphabet.charAt(rem) + decodedNum;
      num /= base;
    }
    while(num > 0);
    return decodedNum;
  }

  static int encode(String num){
    int encodedNum = 0;
    int base = alphabet.length();
    for(int i = 0; i < num.length(); i++){
      encodedNum += charToInt.get(num.charAt(i)) * pow(base, num.length() - 1 - i);
    }
    return encodedNum;
  }
}
