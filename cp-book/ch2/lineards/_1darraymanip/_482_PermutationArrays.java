import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 482 Permutation Arrays
Problem url: https://uva.onlinejudge.org/external/4/482.pdf
Author: Andrey Yemelyanov
*/
public class _482_PermutationArrays {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = Integer.parseInt(s.nextLine());
    for(int i = 0; i < nTests; i++){
      s.nextLine();
      String[] line = s.nextLine().split("\\s+");
      int[] indexArray = new int[line.length];
      for(int k = 0; k < indexArray.length; k++){
        indexArray[k] = Integer.parseInt(line[k]);
      }
      String[] inputArray = s.nextLine().split("\\s+");
      System.out.println(printArray(permuteArray(inputArray, indexArray)));
      if(i < nTests - 1) System.out.println();
    }
  }

  static String printArray(String[] arr){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < arr.length; i++){
      sb.append(arr[i]);
      if(i < arr.length - 1) sb.append("\n");
    }
    return sb.toString();
  }

  static String[] permuteArray(String[] inputArray, int[] indexArray){
    String[] permutedArray = new String[inputArray.length];
    for(int i = 0; i < inputArray.length; i++){
      permutedArray[indexArray[i] - 1] = inputArray[i];
    }
    return permutedArray;
  }
}
