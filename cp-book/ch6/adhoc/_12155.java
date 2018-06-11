import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12155 ASCII Diamondi
Problem url: https://uva.onlinejudge.org/external/121/12155.pdf
Author: Andrey Yemelyanov
*/
public class _12155 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int caseNum = 1;
    StringBuilder sb = new StringBuilder();
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break;
      int row1 = s.nextInt(); int col1 = s.nextInt();
      int row2 = s.nextInt(); int col2 = s.nextInt();
      if(sb.length() != 0) sb.append("\n");
      sb.append(String.format("Case %d:\n", caseNum++));
      buildPattern(N, row1, col1, row2, col2, sb);
    }
    System.out.println(sb.toString());
  }

  static void buildPattern(int N, int row1, int col1, int row2, int col2, StringBuilder sb){
    for(int row = row1; row <= row2; row++){
      sb.append(buildPatternRow(N, row, col1, col2));
      if(row < row2) sb.append("\n");
    }
  }

  static String buildPatternRow(int N, int row, int col1, int col2){
    int diamondHeight = N * 2 - 1;
    int diamondWidth = diamondHeight;
    char[] pattern = buildDiamondLayer(N, row % diamondHeight).toCharArray();
    StringBuilder sb = new StringBuilder();
    int j = col1 % diamondWidth;
    for(int i = col1; i <= col2; i++){
      sb.append(pattern[j]);
      j = (j + 1) % diamondWidth;
    }
    return sb.toString();
  }

  static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
  static String buildDiamondLayer(int N, int layer){
    int diamondHeight = N * 2 - 1;
    int diamondWidth = diamondHeight;
    char[] str = new char[diamondWidth];
    for(int i = 0; i < str.length; i++) str[i] = '.';
    int center = diamondHeight / 2;
    int offset = abs(layer - center);
    char firstChar = alphabet.charAt(offset % alphabet.length());
    str[center] = firstChar;
    for(int i = center + 1; i <= center + N - 1 - offset; i++){
      str[i] = alphabet.charAt((alphabet.indexOf(firstChar) + i - center) % alphabet.length());
    }
    for(int i = center - 1; i >= center - N + 1 + offset; i--){
      str[i] = alphabet.charAt((alphabet.indexOf(firstChar) + center - i) % alphabet.length());
    }
    return new String(str);
  }
}
