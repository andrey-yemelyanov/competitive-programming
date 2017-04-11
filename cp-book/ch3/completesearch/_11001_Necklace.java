import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11001 Necklace
Problem url: https://uva.onlinejudge.org/external/110/11001.pdf
Author: Andrey Yemelyanov
*/
public class _11001_Necklace {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int V = s.nextInt(); int V0 = s.nextInt();
      if(V == 0 && V0 == 0) break;
      System.out.println(maximizeNecklace(V, V0));
    }
  }

  static int maximizeNecklace(int V, int V0){
    double maxLen = 0; int maxNDisks = 0;
    for(int nDisks = 1; ((double)V / nDisks) > V0; nDisks++){
      double D = 0.3 * sqrt((double)V / nDisks - V0);
      double len = nDisks * D;
      if(abs(len - maxLen) < 1e-12) return 0;
      if(len > maxLen){
        maxLen = len;
        maxNDisks = nDisks;
      }
      //System.out.println(nDisks + " disks. Len = " + len + " Max len = " + maxLen + " D = " + D);
    }
    return maxNDisks;
  }
}
