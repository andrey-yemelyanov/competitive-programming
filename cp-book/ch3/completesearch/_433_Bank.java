import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 433 Bank (Not Quite O.C.R.)
Problem url: https://uva.onlinejudge.org/external/4/433.pdf
Author: Andrey Yemelyanov
*/
public class _433_Bank {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    s.nextLine();
    while(nTests-- > 0){
      int[] input = readAccountNumber(s);
      //System.out.println("Acc num -> " + Arrays.toString(input));
      int accNum = deduceAccountNumber(input);
      if(accNum == AMBIGUOUS) System.out.println("ambiguous");
      else if(accNum == FAILURE) System.out.println("failure");
      else System.out.printf("%09d\n", accNum);
    }
  }

  static final int AMBIGUOUS = -1;
  static final int FAILURE = -2;
  static int deduceAccountNumber(int[] input){
    if(isValidAccountNumber(input)) return toInt(input);
    List<Integer> accNums = new ArrayList<>();
    for(int i = 0; i < input.length; i++){
      int ledPattern = input[i];
      for(int possibleDigit : possibleDigits(ledPattern)){
        input[i] = possibleDigit;
        if(isValidAccountNumber(input)) accNums.add(toInt(input));
        if(accNums.size() > 1) return AMBIGUOUS;
      }
      input[i] = ledPattern;
    }
    if(accNums.size() == 0) return FAILURE;
    return accNums.get(0);
  }

  static List<Integer> possibleDigits(int ledPattern){
    List<Integer> digits = new ArrayList<>();
    for(int digit : map.keySet()){
      if((ledPattern & digit) == ledPattern) digits.add(digit);
    }
    return digits;
  }

  static boolean isValidAccountNumber(int[] accNum){
    int total = 0;
    for(int i = accNum.length - 1; i >= 0; i--){
      if(!map.containsKey(accNum[i])) return false;
      int digit = map.get(accNum[i]);
      total += digit * (accNum.length - i);
    }
    return (total % 11) == 0;
  }

  static int toInt(int[] accNum){
    int n = 0;
    for(int i = 0; i < accNum.length; i++){
      n = n * 10 + map.get(accNum[i]);
    }
    return n;
  }

  /*
    Encoding used
        1
      6 7 2
      5 4 3
  */
  static final Map<Integer, Integer> map = new HashMap<>();
  static{
    map.put(6,    1);    // 0000 0110
    map.put(91,   2);    // 0101 1011
    map.put(79,   3);    // 0100 1111
    map.put(102,  4);    // 0110 0110
    map.put(109,  5);    // 0110 1101
    map.put(125,  6);    // 0111 1101
    map.put(7,    7);    // 0000 0111
    map.put(127,  8);    // 0111 1111
    map.put(111,  9);    // 0110 1111
    map.put(63,   0);    // 0011 1111
  }

  static final char EMPTY = ' ';
  static int[] readAccountNumber(Scanner s){
    int[] accNum = new int[9];
    // process line 1
    String[] input = breakIntoGroups(s.nextLine());
    for(int i = 0; i < input.length; i++){
      if(input[i].charAt(1) != EMPTY){
        accNum[i] = turnOnLed(accNum[i], 1);
      }
    }
    // process line 2
    input = breakIntoGroups(s.nextLine());
    for(int i = 0; i < input.length; i++){
      if(input[i].charAt(0) != EMPTY){
        accNum[i] = turnOnLed(accNum[i], 6);
      }
      if(input[i].charAt(1) != EMPTY){
        accNum[i] = turnOnLed(accNum[i], 7);
      }
      if(input[i].charAt(2) != EMPTY){
        accNum[i] = turnOnLed(accNum[i], 2);
      }
    }
    // process line 3
    input = breakIntoGroups(s.nextLine());
    for(int i = 0; i < input.length; i++){
      if(input[i].charAt(0) != EMPTY){
        accNum[i] = turnOnLed(accNum[i], 5);
      }
      if(input[i].charAt(1) != EMPTY){
        accNum[i] = turnOnLed(accNum[i], 4);
      }
      if(input[i].charAt(2) != EMPTY){
        accNum[i] = turnOnLed(accNum[i], 3);
      }
    }
    return accNum;
  }

  static String[] breakIntoGroups(String line){
    line = String.format("%-27s", line);
    String[] groups = new String[9];
    //System.out.printf("Break into groups (line size = %d) -> '%s'\n", line.length(), line);
    for(int i = 0; i < groups.length; i++){
      groups[i] = line.substring(3 * i, 3 * (i + 1));
    }
    return groups;
  }

  static int turnOnLed(int digit, int ledNum){
    return digit | (1 << (ledNum - 1));
  }
}
