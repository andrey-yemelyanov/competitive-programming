import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10912 Simple Minded Hashing
Problem url: https://uva.onlinejudge.org/external/109/10912.pdf
Author: Andrey Yemelyanov
*/
public class _10912_SimpleMindedHashing {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int c = 1;
    while(s.hasNext()){
      int L = s.nextInt(); int S = s.nextInt();
      if(L == 0 && S == 0) break;
      System.out.printf("Case %d: %d\n", c++, count(L, S));
    }
  }

  static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
  static final Map<Character, Integer> codeMap = new HashMap<>();
  static{
    for(int i = 0; i < alphabet.length; i++){
      codeMap.put(alphabet[i], i + 1);
    }
  }

  static int count(int len, int i, int code, int[][][] memo){
    if(len == 0){
      if(code == 0) return 1;
      return 0;
    }
    if(code < 0) return 0;
    if(memo[len][i][code] != -1) return memo[len][i][code];
    int totalCount = 0;
    for(int j = i; j < alphabet.length; j++){
      totalCount += count(len - 1, j + 1, code - codeMap.get(alphabet[j]), memo);
    }
    return memo[len][i][code] = totalCount;
  }

  static int count(int len, int code){
    if(len > 26) return 0;
    int[][][] memo = new int[27][27][code + 1];
    for(int i = 0; i < memo.length; i++){
      for(int j = 0; j < memo[i].length; j++){
        Arrays.fill(memo[i][j], -1);
      }
    }
    return count(len, 0, code, memo);
  }
}
