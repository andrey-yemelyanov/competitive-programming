import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10851 2D Hieroglyphs decoder
Problem url: https://uva.onlinejudge.org/external/108/10851.pdf
Author: Andrey Yemelyanov
*/
public class _10851 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nMessages = s.nextInt(); s.nextLine();
    while(nMessages-- > 0){
      char[][] message = new char[10][];
      for(int i = 0; i < message.length; i++){
        message[i] = s.nextLine().toCharArray();
      }
      if(nMessages > 0) s.nextLine();
      System.out.println(decode(toBitMatrix(message)));
    }
  }

  static String decode(int[][] bitMatrix){
    StringBuilder msg = new StringBuilder();
    for(int j = 0; j < bitMatrix[0].length; j++){
      int c = 0;
      for(int i = bitMatrix.length - 1; i >= 0; i--){
        c = (c << 1) | bitMatrix[i][j];
      }
      msg.append((char)c);
    }
    return msg.toString();
  }

  static int[][] toBitMatrix(char[][] message){
    int[][] m = new int[8][message[0].length - 2];
    for(int i = 1; i < message.length - 1; i++){
      for(int j = 1; j < message[i].length - 1; j++){
        m[i - 1][j - 1] = (message[i][j] == '\\') ? 1 : 0;
      }
    }
    return m;
  }
}
