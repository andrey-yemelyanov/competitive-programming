import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 488 Triangle Wave
Problem url: https://uva.onlinejudge.org/external/4/488.pdf
Author: Andrey Yemelyanov
*/
public class _488 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    StringBuilder sb = new StringBuilder();
    while(nTests-- > 0){
      int amp = s.nextInt(); int freq = s.nextInt();
      for(int i = 0; i < freq; i++){
        sb.append(printWaveform(amp));
        if(i < freq) {sb.append("\n"); sb.append("\n");}
      }
    }
    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb.toString());
  }

  static String printWaveform(int amp){
    StringBuilder sb = new StringBuilder();
    for(int j = 1; j <= amp; j++){
      for(int k = 0; k < j; k++){
        sb.append(j);
      }
      if(amp > 1) sb.append("\n");
    }
    for(int j = amp - 1; j > 0; j--){
      for(int k = 0; k < j; k++){
        sb.append(j);
      }
      if(j > 1) sb.append("\n");
    }
    return sb.toString();
  }
}
