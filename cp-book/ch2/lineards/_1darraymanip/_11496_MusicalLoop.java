import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11496 Musical Loop
Problem url: https://uva.onlinejudge.org/external/114/11496.pdf
Author: Andrey Yemelyanov
*/
public class _11496_MusicalLoop {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == 0) break;
      int[] inputArray = new int[N];
      for(int i = 0; i < inputArray.length; i++){
        inputArray[i] = s.nextInt();
      }
      System.out.println(countPeaks(inputArray));
    }
  }

  static int countPeaks(int[] arr){
    int nPeaks = 0;
    for(int i = 1; i < arr.length - 1; i++){
      if(arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) nPeaks++;
      else if(arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) nPeaks++;
    }
    if(arr[arr.length - 1] > arr[arr.length - 2] && arr[arr.length - 1] > arr[0]) nPeaks++;
    if(arr[arr.length - 1] < arr[arr.length - 2] && arr[arr.length - 1] < arr[0]) nPeaks++;
    if(arr[0] > arr[arr.length - 1] && arr[0] > arr[1]) nPeaks++;
    if(arr[0] < arr[arr.length - 1] && arr[0] < arr[1]) nPeaks++;
    return nPeaks;
  }
}
