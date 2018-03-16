import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 264 Count on Cantor
Problem url: https://uva.onlinejudge.org/external/2/264.pdf
Author: Andrey Yemelyanov
*/
public class _264 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      printRational(s.nextInt());
    }
  }

  static void printRational(int n){
    int diagonal = 0;
    int sum = 0;
    for(int i = 1;;i++){
      sum += i;
      if(sum >= n){
        diagonal = i;
        break;
      }
    }

    System.out.printf("TERM %d IS ", n);
    if(diagonal % 2 == 0){
      int numerator = n - (sum - diagonal);
      int denominator = diagonal + 1 - numerator;
      System.out.printf("%d/%d\n", numerator, denominator);
    }else{
      int denominator = n - (sum - diagonal);
      int numerator = diagonal + 1 - denominator;
      System.out.printf("%d/%d\n", numerator, denominator);
    }
  }
}
