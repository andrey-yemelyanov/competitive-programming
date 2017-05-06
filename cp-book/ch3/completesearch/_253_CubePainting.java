import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 253 Cube painting
Problem url: https://uva.onlinejudge.org/external/2/253.pdf
Author: Andrey Yemelyanov
*/
public class _253_CubePainting {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      String cubes = s.next();
      if(cubesEqual(cubes.substring(0, 6), cubes.substring(6))){
        System.out.println("TRUE");
      }else System.out.println("FALSE");
    }
  }

  static boolean cubesEqual(String cube1, String cube2){
    String rotatedCube1 = cube1;
    for(int x = 0; x < 4; x++){
      rotatedCube1 = rotateAroundXAxis(rotatedCube1);
      for(int y = 0; y < 4; y++){
        rotatedCube1 = rotateAroundYAxis(rotatedCube1);
        for(int z = 0; z < 4; z++){
          rotatedCube1 = rotateAroundZAxis(rotatedCube1);
          if(rotatedCube1.equals(cube2)) return true;
        }
      }
    }
    return false;
  }

  static String rotateAroundZAxis(String cube){
    return rotate(cube, new int[] {4, 2, 1, 6, 5, 3});
  }

  static String rotateAroundXAxis(String cube){
    return rotate(cube, new int[] {5, 1, 3, 4, 6, 2});
  }

  static String rotateAroundYAxis(String cube){
    return rotate(cube, new int[] {1, 4, 2, 5, 3, 6});
  }

  static String rotate(String cube, int[] colorsAfterRotation){
    StringBuilder rotatedCube = new StringBuilder();
    for(int i = 0; i < colorsAfterRotation.length; i++){
      rotatedCube.append(cube.charAt(colorsAfterRotation[i] - 1));
    }
    return rotatedCube.toString();
  }
}
