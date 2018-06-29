import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11475 Extend to Palindromes
Problem url: https://uva.onlinejudge.org/external/114/11475.pdf
Author: Andrey Yemelyanov
*/
public class _11475 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      System.out.println(toPalindrome(s.next()));
    }
  }

  static String toPalindrome(String str){
    int[] b = kmpPreprocess(reverse(str) + str);
    if(b[b.length - 1] >= str.length()) return str;
    return str + reverse(str).substring(b[b.length - 1]);
  }

  static int[] kmpPreprocess(String pattern){
    int[] b = new int[pattern.length() + 1];
    int i = 0; int j = -1; b[0] = -1;
    while(i < pattern.length()){
      while(j >= 0 && pattern.charAt(i) != pattern.charAt(j)) j = b[j];
      i++; j++;
      b[i] = j;
    }
    return b;
  }

  static String reverse(String str){
    char[] c = str.toCharArray();
    for(int i = 0; i < c.length / 2; i++){
      char temp = c[i];
      c[i] = c[c.length - 1 - i];
      c[c.length - 1 - i] = temp;
    }
    return new String(c);
  }
}
