import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;
import java.math.BigInteger;

/*
Problem name: 10235 Simply Emirp
Problem url: https://uva.onlinejudge.org/external/102/10235.pdf
Author: Andrey Yemelyanov
*/
public class _10235 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int n = s.nextInt();
      if(!isPrime(n)) System.out.printf("%d is not prime.\n", n);
      else if(isEmirp(n)) System.out.printf("%d is emirp.\n", n);
      else System.out.printf("%d is prime.\n", n);
    }
  }

  static boolean isEmirp(int n){
    int reversed = reverseDigits(n);
    return reversed != n && isPrime(reversed);
  }

  static boolean isPrime(int n){
    return BigInteger.valueOf(n).isProbablePrime(10);
  }

  static int reverseDigits(int n){
    int reversed = 0;
    while(n != 0){
      int digit = n % 10;
      reversed = reversed * 10 + digit;
      n /= 10;
    }
    return reversed;
  }
}
