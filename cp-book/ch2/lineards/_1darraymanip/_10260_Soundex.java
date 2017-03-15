import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10260 Soundex
Problem url: https://uva.onlinejudge.org/external/102/10260.pdf
Author: Andrey Yemelyanov
*/
public class _10260_Soundex {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      System.out.println(encode(s.next()));
    }
  }

  static String encode(String word){
    StringBuilder sb = new StringBuilder();
    int prevCode = 0;
    for(char c : word.toCharArray()){
      int code = getCode(c);
      if(code != NO_CODE){
        if(code != prevCode){
          sb.append(code);
        }
      }
      prevCode = code;
    }
    return sb.toString();
  }

  static int[] codeMap = new int[26];
  static{
    setCode('B', 1);
    setCode('F', 1);
    setCode('P', 1);
    setCode('V', 1);

    setCode('C', 2);
    setCode('G', 2);
    setCode('J', 2);
    setCode('K', 2);
    setCode('Q', 2);
    setCode('S', 2);
    setCode('X', 2);
    setCode('Z', 2);

    setCode('D', 3);
    setCode('T', 3);

    setCode('L', 4);

    setCode('M', 5);
    setCode('N', 5);

    setCode('R', 6);
  }
  static void setCode(char c, int code){
    codeMap[c - 'A'] = code;
  }
  static final int NO_CODE = 0;
  static int getCode(char c){
    return codeMap[c - 'A'];
  }
}
