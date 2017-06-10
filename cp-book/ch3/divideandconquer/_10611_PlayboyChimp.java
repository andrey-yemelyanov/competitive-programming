import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10611 The Playboy Chimp
Problem url: https://uva.onlinejudge.org/external/106/10611.pdf
Author: Andrey Yemelyanov
*/
public class _10611_PlayboyChimp {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int N = s.nextInt();
    int[] arr = new int[N];
    for(int i = 0; i < N; i++){
      arr[i] = s.nextInt();
    }
    int Q = s.nextInt();
    while(Q-- > 0){
      int key = s.nextInt();
      int lower = lowerBound(arr, key - 1);
      int upper = upperBound(arr, key + 1);
      if(lower == NOT_FOUND) System.out.print("X ");
      else System.out.print(arr[lower] + " ");
      if(upper == NOT_FOUND) System.out.println("X");
      else System.out.println(arr[upper]);
    }
  }

  static final int NOT_FOUND = -1;
  static int lowerBound(int[] arr, int key){
    int from = 0;
    int to = arr.length - 1;
    while(to >= from){
      int mid = from + (to - from) / 2;
      if(arr[mid] == key){
        if(mid + 1 == arr.length || arr[mid + 1] > key) return mid;
        else from = mid + 1;
      }else if(arr[mid] < key){
        if(mid + 1 == arr.length || arr[mid + 1] > key) return mid;
        else from = mid + 1;
      }else{
        to = mid - 1;
      }
    }
    return NOT_FOUND;
  }

  static int upperBound(int[] arr, int key){
    int from = 0;
    int to = arr.length - 1;
    while(to >= from){
      int mid = from + (to - from) / 2;
      if(arr[mid] == key){
        if(mid + 1 == arr.length || arr[mid + 1] > key) return mid;
        else from = mid + 1;
      }else if(arr[mid] > key){
        if(mid - 1 < 0 || arr[mid - 1] < key) return mid;
        else to = mid - 1;
      }else from = mid + 1;
    }
    return NOT_FOUND;
  }
}
