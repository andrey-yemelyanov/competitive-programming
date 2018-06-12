import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 941 Permutations
Problem url: https://uva.onlinejudge.org/external/9/941.pdf
Author: Andrey Yemelyanov
*/
public class _941 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      System.out.println(getPermutation(sortString(s.next()), s.nextLong()));
    }
  }

  static String sortString(String str){
    // sort the string
    char[] chars = str.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }

  static String getPermutation(String str, long n){
    if(n == 0) return str;

    // find index of the char with which the n-th permutation starts
    long nPermutations = factorial(str.length() - 1);
    int i = (int)(n / nPermutations);

    // recursively build the rest of the n-th permutation
    return str.charAt(i) + getPermutation(str.substring(0, i) + str.substring(i + 1),
                                          n - nPermutations * i);
  }

  static long factorial(int n){
    long result = 1;
    for(int i = 1; i <= n; i++) result *= i;
    return result;
  }
}
