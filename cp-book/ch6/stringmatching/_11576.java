import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11576 Scrolling Sign
Problem url: https://uva.onlinejudge.org/external/115/11576.pdf
Author: Andrey Yemelyanov
*/
public class _11576 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      s.nextInt(); int n = s.nextInt();
      List<String> words = new ArrayList<>();
      while(n-- > 0) words.add(s.next());
      System.out.println(countLetters(words));
    }
  }

  static int countLetters(List<String> words){
    int wordLen = words.get(0).length();
    int nLetters = wordLen;
    for(int i = words.size() - 1; i > 0; i--){
      int[] b = kmpPreprocess(words.get(i) + words.get(i - 1));
      nLetters += wordLen - min(wordLen, b[b.length - 1]);
    }
    return nLetters;
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
}
