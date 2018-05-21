import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11278 One-Handed Typist
Problem url: https://uva.onlinejudge.org/external/112/11278.pdf
Author: Andrey Yemelyanov
*/
public class _11278 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      StringBuilder sb = new StringBuilder();
      for(char c : s.nextLine().toCharArray()){
        if(c == ' ') sb.append(c);
        else sb.append(qwertyToDvorak(c));
      }
      System.out.println(sb.toString());
    }
  }

  static char[] QWERTY =       "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./".toCharArray();
  static char[] DVORAK =       "`123qjlmfp/[]456.orsuyb;=\\789aehtdck-0zx,inwvg'".toCharArray();
  static char[] QWERTY_SHIFT = "~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>?".toCharArray();
  static char[] DVORAK_SHIFT = "~!@#QJLMFP?{}$%^>ORSUYB:+|&*(AEHTDCK_)ZX<INWVG\"".toCharArray();

  static char qwertyToDvorak(char c){
    int index = -1;
    for(int i = 0; i < QWERTY.length; i++){
      if(QWERTY[i] == c){
        index = i;
        break;
      }
    }
    if(index != -1){
      return DVORAK[index];
    }else{
      for(int i = 0; i < QWERTY_SHIFT.length; i++){
        if(QWERTY_SHIFT[i] == c){
          index = i;
          break;
        }
      }
      return DVORAK_SHIFT[index];
    }
  }
}
