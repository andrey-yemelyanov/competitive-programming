import java.util.*;
import java.util.function.Function;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11634 Generate random numbers
Problem url: https://uva.onlinejudge.org/external/116/11634.pdf
Author: Andrey Yemelyanov
*/
public class _11634 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int a0 = s.nextInt(); if(a0 == 0) break;
      System.out.println(countDistinct(a0));
    }
  }

  static int countDistinct(int a0){
    int count = 0;
    boolean[] dat = new boolean[10000];
    int a = a0;
    while(!dat[a]){
      dat[a] = true;
      a = func(a);
      count++;
    }
    return count;
  }

  static int func(int x){
    return (x * x / 100) % 10000;
  }
}
