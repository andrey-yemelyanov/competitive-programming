import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10192 Vacation
Problem url: https://uva.onlinejudge.org/external/101/10192.pdf
Author: Andrey Yemelyanov
*/
public class _10192 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int c = 1;
    while(s.hasNext()){
      String cityList1 = s.nextLine();
      if(cityList1.equals("#")) break;
      String cityList2 = s.nextLine();
      System.out.printf("Case #%d: you can visit at most %d cities.\n", c++, lcs(cityList1, cityList2));
    }
  }

  static int lcs(String a, String b){
    char[] s1 = a.toCharArray();
    char[] s2 = b.toCharArray();
    int[][] lcs = new int[s1.length + 1][s2.length + 1];
    for(int i = 0; i <= s1.length; i++){
      for(int j = 0; j <= s2.length; j++){
        if(i == 0 || j == 0) lcs[i][j] = 0;
        else if(s1[i - 1] == s2[j - 1]) lcs[i][j] = 1 + lcs[i - 1][j - 1];
        else lcs[i][j] = max(lcs[i - 1][j], lcs[i][j - 1]);
      }
    }
    return lcs[s1.length][s2.length];
  }
}
