import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11723 Numbering Roads
Problem url: https://uva.onlinejudge.org/external/117/11723.pdf
Author: Andrey Yemelyanov
*/
public class _11723 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int c = 1;
	  while(s.hasNext()){
      int nRoads = s.nextInt();
      int nInts = s.nextInt();
      if(nRoads == 0 && nInts == 0) break;
      int min = (int)ceil((double)nRoads / nInts) - 1;
      if(min > 26) System.out.printf("Case %d: impossible\n", c++);
      else System.out.printf("Case %d: %d\n", c++, min);
    }
  }
}
