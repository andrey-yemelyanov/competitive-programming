import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10878 Decode the tape
Problem url: https://uva.onlinejudge.org/external/108/10878.pdf
Author: Andrey Yemelyanov
*/
public class _10878 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
	  s.nextLine();
    while(s.hasNext()){
      String line = s.nextLine().replace("|","").replace(".","");
      if(line.startsWith("_")) break;
      System.out.print(toChar(line));
    }
  }

  static char toChar(String line){
    int c = 0;
    for(int i = 0; i < line.length(); i++){
      c = (c << 1) | (line.charAt(i) == 'o' ? 1 : 0);
    }
    return (char)c;
  }
}
