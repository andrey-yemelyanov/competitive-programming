import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10474 Where is the Marble?
Problem url: https://uva.onlinejudge.org/external/104/10474.pdf
Author: Andrey Yemelyanov
*/
public class _10474_WhereIsTheMarble {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int caseNum = 1;
    while(s.hasNext()){
      int N = s.nextInt(); int Q = s.nextInt();
      if(N == 0 && Q == 0) break;
      int[] arr = new int[N];
      for(int i = 0; i < N; i++){
        arr[i] = s.nextInt();
      }
      Arrays.sort(arr);
      System.out.printf("CASE# %d:\n", caseNum++);
      while(Q-- > 0){
        int key = s.nextInt();
        int index = findFirstOccurrence(arr, key);
        if(index == NOT_FOUND) System.out.printf("%d not found\n", key);
        else System.out.printf("%d found at %d\n", key, index + 1);
      }
    }
  }

  static int NOT_FOUND = -1;
  static int findFirstOccurrence(int[] arr, int key){
    int from = 0;
    int to = arr.length - 1;
    while(to >= from){
      int mid = from + (to - from) / 2;
      if(arr[mid] == key){
        if(mid == 0 || arr[mid - 1] < key) return mid;
        else to = mid - 1;
      }else if(arr[mid] < key) from = mid + 1;
      else to = mid - 1;
    }
    return NOT_FOUND;
  }
}
