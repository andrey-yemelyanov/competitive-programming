import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12004 Bubble Sort
Problem url: https://uva.onlinejudge.org/external/120/12004.pdf
Author: Andrey Yemelyanov
*/
public class _12004 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    final int maxN = 100000;
    Rational[] arr = new Rational[maxN + 1];
    arr[1] = new Rational(0, 1);
    for(int n = 2; n <= maxN; n++){
      arr[n] = arr[n - 1].add(new Rational(n - 1, 2));
    }
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      int n = s.nextInt();
      System.out.printf("Case %d: %s\n", c, arr[n].toString());
    }
  }

  static class Rational{
    long numerator;
    long denominator;
    public Rational(long numerator, long denominator){
      this.numerator = numerator;
      this.denominator = denominator;
      normalize();
    }
    @Override
    public String toString(){
      if(numerator == 0) return "0";
      if(denominator == 1) return new Long(numerator).toString();
      return String.format("%d/%d", numerator, denominator);
    }
    public Rational add(Rational other){
      return new Rational(
        numerator * other.denominator + other.numerator * denominator,
        denominator * other.denominator);
    }
    private void normalize(){
      long gcd = gcd(numerator, denominator);
      numerator /= gcd;
      denominator /= gcd;
    }
    private long gcd(long a, long b){
      if(b == 0) return a;
      return gcd(b, a % b);
    }
  }

  static void runSimulation(int n){
    final int nRuns = 10000;
    long countSum = 0;
    for(int run = 0; run < nRuns; run++){
      countSum += findSwaps(n, randomArray(n));
    }
    System.out.printf("Avg # of swaps after %d runs on a %d element array = %d/%d -> %.1f\n", nRuns, n, countSum, nRuns, (double)countSum / nRuns);
  }

  static int findSwaps(int n, int a[])
  {
    int count = 0, i, j, temp;
    int[] b = new int[100000];
    for( i = 0; i < n; i++ ) {
      b[i] = a[i];
    }
    for( i = 0; i < n; i++ ) {
      for( j = 0; j < n - 1; j++ ) {
        if( b[j] > b[j+1] ) {
          temp = b[j];
          b[j] = b[j+1];
          b[j+1] = temp;
          count++;
        }
      }
    }
    return count;
  }

  static int[] randomArray(int n){
    int[] arr = new int[n];
    for(int i = 0; i < n; i++) arr[i] = i + 1;
    // shuffle elements in the array
    Random r = new Random();
    for(int i = 0; i < n; i++){
      int k = r.nextInt(n - i) + i;
      swap(i, k, arr);
    }
    return arr;
  }

  static void swap(int i, int j, int[] arr){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
