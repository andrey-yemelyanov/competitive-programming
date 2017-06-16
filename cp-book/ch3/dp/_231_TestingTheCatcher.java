import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 231 Testing the CATCHER
Problem url: https://uva.onlinejudge.org/external/2/231.pdf
Author: Andrey Yemelyanov
*/
public class _231_TestingTheCatcher {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Integer> missiles = new ArrayList<>();
    int t = 1;
    boolean firstCase = true;
    while(s.hasNext()){
      int height = s.nextInt();
      if(height == -1){
        if(missiles.size() == 0) break;
        int[] arr = new int[missiles.size()];
        for(int i = 0; i < arr.length; i++){
          arr[i] = missiles.get(i);
        }
        if(firstCase) firstCase = false;
        else System.out.println();
        System.out.printf("Test #%d:\n  maximum possible interceptions: %d\n", t++, lds(arr));
        missiles = new ArrayList<>();
      }else{
        missiles.add(height);
      }
    }
  }

  static int lds(int[] arr){
    int[] memo = new int[arr.length];
    int ldsLength = 0;
    for(int i = 0; i < arr.length; i++){
      ldsLength = max(ldsLength, lds(arr, i, memo));
    }
    return ldsLength;
  }

  static int lds(int[] arr, int i, int[] memo){
    if(i == 0) return 1;
    if(memo[i] != 0) return memo[i];
    int ldsLength = 1;
    for(int j = 0; j < i; j++){
      if(arr[j] >= arr[i]){
        ldsLength = max(ldsLength, 1 + lds(arr, j, memo));
      }
    }
    return memo[i] = ldsLength;
  }
}
