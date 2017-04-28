import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12583 Memory Overflow
Problem url: https://uva.onlinejudge.org/external/125/12583.pdf
Author: Andrey Yemelyanov
*/
public class _12583_MemoryOverflow {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int t = 1; t <= nTests; t++){
      int n = s.nextInt(); int k = s.nextInt(); String names = s.next();
      System.out.printf("Case %d: %d\n", t, countRecognitions(names.toCharArray(), k));
    }
  }

  static int countRecognitions(char[] people, int k){
    int n = 0;
    List<Character> list = new ArrayList<>();
    for(char person : people){
      if(list.contains(person)) n++;
      list.add(person);
      if(list.size() > k) list.remove(0);
    }
    return n;
  }
}
