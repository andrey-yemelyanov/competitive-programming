import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 394 Mapmaker
Problem url: https://uva.onlinejudge.org/external/3/394.pdf
Author: Andrey Yemelyanov
*/
public class _394_Mapmaker {
  static class Array{
    public int baseAddress;
    public int elementSize;
    public int[] lower;
    public int[] upper;
    public int nDims(){
      return lower.length;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int N = s.nextInt(); int R = s.nextInt();
    Map<String, Array> m = new HashMap<>();
    for(int i = 0; i < N; i++){
      String name = s.next();
      Array arr = new Array();
      arr.baseAddress = s.nextInt();
      arr.elementSize = s.nextInt();
      int nDims = s.nextInt();
      arr.lower = new int[nDims];
      arr.upper = new int[nDims];
      for(int j = 0; j < nDims; j++){
        arr.lower[j] = s.nextInt();
        arr.upper[j] = s.nextInt();
      }
      m.put(name, arr);
    }
    for(int i = 0; i < R; i++){
      String name = s.next();
      Array arr = m.get(name);
      int[] element = new int[arr.nDims()];
      for(int j = 0; j < element.length; j++){
        element[j] = s.nextInt();
      }
      int physAdd = getPhysAdd(arr, element);
      System.out.printf("%s%s = %d\n",
        name,
        Arrays.toString(element),
        physAdd);
    }
  }

  static int getPhysAdd(Array arr, int[] element){
    int D = arr.nDims();
    int[] c = new int[D + 1];
    c[c.length - 1] = arr.elementSize;
    for(int i = c.length - 2; i > 0; i--){
      c[i] = c[i + 1] * (arr.upper[i] - arr.lower[i] + 1);
    }

    int c_zero = arr.baseAddress;
    for(int i = 1; i < c.length; i++){
      c_zero -= c[i] * arr.lower[i - 1];
    }
    c[0] = c_zero;

    int physAdd = c[0];
    for(int i = 1; i < c.length; i++){
      physAdd += c[i] * element[i - 1];
    }
    return physAdd;
  }
}
