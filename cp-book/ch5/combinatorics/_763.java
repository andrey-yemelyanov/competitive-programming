import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.*;

/*
Problem name: 763 Fibinary Numbers
Problem url: https://uva.onlinejudge.org/external/7/763.pdf
Author: Andrey Yemelyanov
*/
public class _763 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    boolean firstOutput = true;
    while(s.hasNext()){
      String fibinaryNum1 = s.next();
      String fibinaryNum2 = s.next();
      if(!firstOutput) System.out.println();
      else firstOutput = false;
      System.out.println(add(fibinaryNum1, fibinaryNum2));
    }
  }

  static String add(String fibinaryNum1, String fibinaryNum2){
    return toFibinaryNumber(toBase10(fibinaryNum1).add(toBase10(fibinaryNum2)));
  }

  static final BigInteger[] fib = generateFibonacciSequence(101);

  static BigInteger[] generateFibonacciSequence(int n){
    BigInteger[] fib = new BigInteger[n];
    fib[0] = BigInteger.valueOf(1);
    fib[1] = BigInteger.valueOf(2);
    for(int i = 2; i < fib.length; i++){
      fib[i] = fib[i - 2].add(fib[i - 1]);
    }
    return fib;
  }

  static String toFibinaryNumber(BigInteger num){
    if(num.equals(BigInteger.ZERO)) return "0";
    StringBuilder bitString = new StringBuilder();
    int k = fib.length - 1;
    while(!num.equals(BigInteger.ZERO)){
      while(fib[k].compareTo(num) > 0) {
        k--;
        if(bitString.length() > 0) bitString.append("0");
      }
      num = num.subtract(fib[k]);
      bitString.append("1");
      k--;
    }
    while(k-- >= 0) bitString.append("0");
    return bitString.toString();
  }

  static BigInteger toBase10(String fibinaryNum){
    BigInteger num = BigInteger.ZERO;
    for(int i = 0; i < fibinaryNum.length(); i++){
      if(fibinaryNum.charAt(i) == '1'){
        num = num.add(fib[fibinaryNum.length() - i - 1]);
      }
    }
    return num;
  }
}
