import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 957 Popes
Problem url: https://uva.onlinejudge.org/external/9/957.pdf
Author: Andrey Yemelyanov
*/
public class _957_Popes {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int Y = s.nextInt();
      int P = s.nextInt();
      int[] popes = new int[P];
      for(int i = 0; i < P; i++){
        popes[i] = s.nextInt();
      }
      s.nextLine();
      if(s.hasNext()) s.nextLine();
      System.out.println(
        Arrays.stream(mostPopesInPeriod(Y, popes))
              .mapToObj(Integer::toString)
              .collect(Collectors.joining(" ")));
      //System.out.println(lowerBound(popes, 20));
    }
  }

  static int[] mostPopesInPeriod(int Y, int[] popes){
    int[] bestInterval = null;
    int maxNPopes = 0;
    for(int i = 0; i < popes.length; i++){
      int fromYear = popes[i];
      int toYear = fromYear + Y - 1;
      int j = lowerBound(popes, toYear);
      int nPopes = j - i + 1;
      if(nPopes > maxNPopes){
        maxNPopes = nPopes;
        bestInterval = new int[]{i, j};
      }
    }
    return new int[]{maxNPopes, popes[bestInterval[0]], popes[bestInterval[1]]};
  }

  /*
    Returns index of the largest element less than or equal to key in arr
  */
  static int lowerBound(int[] arr, int key){
    int from = 0;
    int to = arr.length - 1;
    while(to >= from){
      int mid = from + (to - from) / 2;
      if(arr[mid] == key){
        if(mid + 1 == arr.length) return mid;
        if(arr[mid + 1] > key) return mid;
        else from = mid + 1;
      }else if(arr[mid] < key){
        if(mid + 1 == arr.length || arr[mid + 1] > key) return mid;
        else from = mid + 1;
      }else{
        to = mid - 1;
      }
    }
    return to;
  }
}
