import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 386 Perfect Cubes
Problem url: https://uva.onlinejudge.org/external/3/386.pdf
Author: Andrey Yemelyanov
*/
public class _386_PerfectCubes {
  public static class Set{
    public int a;
    public int b;
    public int c;
    public int d;
    public Set(int a, int b, int c, int d){
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
    }
    public String toString(){
      return String.format("Cube = %d, Triple = (%d,%d,%d)", a, b, c, d);
    }
  }

  static final int MAX_A = 200;
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    System.out.println(
      findSets(cubes(MAX_A))
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n")));
  }

  static int[] cubes(int max){
    int[] cubes = new int[max + 1];
    for(int i = 0; i <= max; i++){
      cubes[i] = (int)pow(i, 3);
    }
    return cubes;
  }

  static List<Set> findSets(int[] cubes){
    List<Set> sets = new ArrayList<>();
    for(int a = 2; a <= MAX_A; a++){
      int aCube = cubes[a];
      for(int b = 2; b < a; b++){
        int bCube = cubes[b];
        for(int c = b + 1; c < a; c++){
          int cCube = cubes[c];
          if(bCube + cCube >= aCube) break;
          for(int d = c + 1; d < a; d++){
            int dCube = cubes[d];
            if(bCube + cCube + dCube > aCube) break;
            if(bCube + cCube + dCube == aCube) sets.add(new Set(a, b, c, d));
          }
        }
      }
    }
    return sets;
  }
}
