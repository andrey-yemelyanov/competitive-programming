import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11385 Da Vinci Code
Problem url: https://uva.onlinejudge.org/external/113/11385.pdf
Author: Andrey Yemelyanov
*/
public class _11385 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> fibonacciNumbers = generateFibonacciNumbers();
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int[] key = new int[s.nextInt()];
      for(int i = 0; i < key.length; i++) key[i] = s.nextInt();
      s.nextLine();
      String cipher = s.nextLine();
      System.out.println(decrypt(key, cipher, fibonacciNumbers));
    }
  }

  static String clean(String cipher){
    StringBuilder sb = new StringBuilder();
    for(char c : cipher.toCharArray()){
      if(Character.isLetter(c) && Character.isUpperCase(c)){
        sb.append(c);
      }
    }
    return sb.toString();
  }

  static String decrypt(int[] key, String cipher, List<Integer> fibonacciNumbers){
    cipher = clean(cipher);
    char[] message = new char[getMessageLength(key, fibonacciNumbers)];
    for(int i = 0; i < message.length; i++) message[i] = ' ';
    for(int i = 0; i < key.length; i++){
      message[Collections.binarySearch(fibonacciNumbers, key[i])] = cipher.charAt(i);
    }
    return String.valueOf(message);
  }

  static int getMessageLength(int[] key, List<Integer> fibonacciNumbers){
    int maxFib = 0;
    for(int fib : key) maxFib = max(maxFib, fib);
    return Collections.binarySearch(fibonacciNumbers, maxFib) + 1;
  }

  static List<Integer> generateFibonacciNumbers(){
    List<Integer> fib = new ArrayList<>();
    fib.add(1); fib.add(2);
    while(fib.get(fib.size() - 1) + fib.get(fib.size() - 2) > 0){
      fib.add(fib.get(fib.size() - 1) + fib.get(fib.size() - 2));
    }
    return fib;
  }
}
