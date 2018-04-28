import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 11344 The Huge One
Problem url: https://uva.onlinejudge.org/external/113/11344.pdf
Author: Andrey Yemelyanov
*/
public class _11344 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      String M = s.next();
      int n = s.nextInt();
      boolean isWonderful = true;
      for(int i = 0; i < n; i++){
        if(!isDivisibleBy(s.nextInt(), M)){
          isWonderful = false;
        }
      }
      if(isWonderful) System.out.printf("%s - Wonderful.\n", M);
      else System.out.printf("%s - Simple.\n", M);
    }
  }

  static boolean divisibleBy_1(String number){
    return true;
  }

  static boolean divisibleBy_2(String number){
    // The units digit must be even
    return Character.getNumericValue(number.charAt(number.length() - 1)) % 2 == 0;
  }

  static boolean divisibleBy_3(String number){
    // A positive integer is divisible by 3 if
    // and only if the sum of its digits is divisible by 3.
    return number.chars()
                 .mapToObj(c -> (char) c)
                 .mapToInt(c -> Character.getNumericValue(c))
                 .sum() % 3 == 0;
  }

  static boolean divisibleBy_4(String number){
    // The number formed by its last two digits must be divisible by 4.
    return Integer.parseInt(number.substring(number.length() - 2)) % 4 == 0;
  }

  static boolean divisibleBy_5(String number){
    // The units digit must be 0 or 5.
    return number.charAt(number.length() - 1) == '0' || number.charAt(number.length() - 1) == '5';
  }

  static boolean divisibleBy_6(String number){
    // It must be even and divisible by 3.
    return isDivisibleBy(2, number) && isDivisibleBy(3, number);
  }

  static boolean divisibleBy_7(String number){
    if(number.length() < 3) return Integer.parseInt(number) % 7 == 0;
    int firstDigit = Character.getNumericValue(number.charAt(0));
    int secondDigit = Character.getNumericValue(number.charAt(1));
    int remainder = (firstDigit * 3 + secondDigit) % 7;
    for(int i = 2; i < number.length(); i++){
      int digit = Character.getNumericValue(number.charAt(i));
      remainder = (remainder * 3 + digit) % 7;
    }
    return remainder == 0;
  }

  static boolean divisibleBy_8(String number){
    // The number formed by its last three digits must be divisible by 8.
    return Integer.parseInt(number.substring(number.length() - 3)) % 8 == 0;
  }

  static boolean divisibleBy_9(String number){
    // A positive integer is divisible by 9 if
    // and only if the sum of its digits is divisible by 9.
    return number.chars()
                 .mapToObj(c -> (char) c)
                 .mapToInt(c -> Character.getNumericValue(c))
                 .sum() % 9 == 0;
  }

  static boolean divisibleBy_10(String number){
    //  last digit must be 0
    return number.charAt(number.length() - 1) == '0';
  }

  static boolean divisibleBy_11(String number){
    // the difference of the alternating sum of digits of N is a multiple of 11
    int sum = 0;
    for(int i = 0; i < number.length(); i++){
      int digit = Character.getNumericValue(number.charAt(i));
      if(i % 2 == 0) sum += digit;
      else sum -= digit;
    }
    return sum % 11 == 0;
  }

  static boolean divisibleBy_12(String number){
    // if N is divisible by both 3 and 4.
    return isDivisibleBy(3, number) && isDivisibleBy(4, number);
  }

  static boolean isDivisibleBy(int n, String number){
    if(number.length() < 5) return Integer.parseInt(number) % n == 0;
    switch(n){
      case 1:
        return true;
      case 2:
        return divisibleBy_2(number);
      case 3:
        return divisibleBy_3(number);
      case 4:
        return divisibleBy_4(number);
      case 5:
        return divisibleBy_5(number);
      case 6:
        return divisibleBy_6(number);
      case 7:
        return divisibleBy_7(number);
      case 8:
        return divisibleBy_8(number);
      case 9:
        return divisibleBy_9(number);
      case 10:
        return divisibleBy_10(number);
      case 11:
        return divisibleBy_11(number);
      case 12:
        return divisibleBy_12(number);
      default:
        return false;
    }
  }
}
