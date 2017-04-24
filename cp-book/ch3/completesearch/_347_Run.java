import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 347 Run
Problem url: https://uva.onlinejudge.org/external/3/347.pdf
Author: Andrey Yemelyanov
*/
public class _347_Run {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int c = 1;
    int[] preprocess = preprocess();
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      System.out.printf("Case %d: %d\n", c++, preprocess[n]);
    }
  }

  static int[] preprocess(){
    final int MAX_R = 9876543;
    int[] preprocess = new int[MAX_R + 2];
    for(int i = MAX_R; i > 9; i--){
      if(isRunaroundNumber(i)) preprocess[i] = i;
      else preprocess[i] = preprocess[i + 1];
    }
    return preprocess;
  }

  static boolean isRunaroundNumber(int n){
    if(!allDigitsDistinct(n)) return false;
    int nDigitsUsed = 0; int nDigits = countDigits(n);
    boolean[] used = new boolean[nDigits];
    int i = 0;
    while(true){
      i = (i + getDigit(n, i, nDigits)) % nDigits;
      if(used[i]) return false;
      used[i] = true;
      nDigitsUsed++;
      if(nDigitsUsed == nDigits) return i == 0;
    }
  }

  static int getDigit(int n, int i, int nDigits){
    return (n / (int)pow(10, nDigits - i - 1)) % 10;
  }

  static int countDigits(int n){
    if(n == 0) return 1;
    return (int)(log10(n) + 1);
  }

  static boolean allDigitsDistinct(int n){
    int set = 0;
    while(n > 0){
      int digit = n % 10;
      if((set & (1 << digit)) > 0) return false;
      set |= (1 << digit);
      n /= 10;
    }
    return true;
  }
}
