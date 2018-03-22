import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10408 Farey sequences
Problem url: https://uva.onlinejudge.org/external/104/10408.pdf
Author: Andrey Yemelyanov
*/
public class _10408 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt(); int k = s.nextInt();
      List<Integer[]> sequence = generateFareySequence(n);
      System.out.printf("%d/%d\n", sequence.get(k - 1)[0], sequence.get(k - 1)[1]);
    }
  }

  static List<Integer[]> generateFareySequence(int n){
    List<Integer[]> sequence = new ArrayList<>();
    for(int i = 1; i <= n; i++){
      for(int j = 1; j <= n; j++){
        if(gcd(i, j) == 1 && i <= j){
          sequence.add(new Integer[]{i, j});
        }
      }
    }
    Collections.sort(sequence, new Comparator<Integer[]>(){
      @Override
      public int compare(Integer[] a1, Integer[] a2){
        return Double.compare((double)a1[0]/a1[1], (double)a2[0]/a2[1]);
      }
    });
    //System.out.println(sequence.size());
    return sequence;
  }

  static int gcd(int a, int b){
    if(b == 0) return a;
    return gcd(b, a % b);
  }
}
