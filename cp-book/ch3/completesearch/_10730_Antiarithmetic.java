import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10730 Antiarithmetic?
Problem url: https://uva.onlinejudge.org/external/107/10730.pdf
Author: Andrey Yemelyanov
*/
public class _10730_Antiarithmetic {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String[] line = s.nextLine().split(":");
      int n = Integer.parseInt(line[0]);
      if(n == 0) break;
      int[] p = new int[n];
      String[] perm = line[1].split("\\s+");
      //System.out.println(Arrays.toString(perm));
      for(int i = 0; i < n; i++){
        p[i] = Integer.parseInt(perm[i + 1]);
      }

      if(isAntiarithmetic(p)) System.out.println("yes");
      else System.out.println("no");
    }
  }

  static boolean isAntiarithmetic(int[] p){
    for(int i = 0; i < p.length; i++){
      Set<Integer> s = new HashSet<>();
      for(int j = i + 1; j < p.length; j++){
        if(s.contains(p[j])) return false;
        if(p[j] > p[i]) s.add(2 * p[j] - p[i]);
      }
    }
    // repeat in reverse
    for(int i = p.length - 1; i >= 0; i--){
      Set<Integer> s = new HashSet<>();
      for(int j = i - 1; j >= 0; j--){
        if(s.contains(p[j])) return false;
        if(p[j] > p[i]) s.add(2 * p[j] - p[i]);
      }
    }
    return true;
  }
}
