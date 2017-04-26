import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 12488 Start Grid
Problem url: https://uva.onlinejudge.org/external/124/12488.pdf
Author: Andrey Yemelyanov
*/
public class _12488_StartGrid {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int nCars = s.nextInt();
      int[] startGrid = new int[nCars];
      int[] finishGrid = new int[nCars];
      for(int i = 0; i < nCars; i++){
        startGrid[i] = s.nextInt();
      }
      for(int i = 0; i < nCars; i++){
        finishGrid[i] = s.nextInt();
      }
      System.out.println(countOvertakes(startGrid, finishGrid));
    }
  }

  static int countOvertakes(int[] startGrid, int[] finishGrid){
    int nOvertakes = 0;
    int[] intermediateGrid = Arrays.copyOf(startGrid, startGrid.length);
    for(int i = 0; i < finishGrid.length; i++){
      int j = findCar(finishGrid[i], intermediateGrid);
      moveCar(j, i, intermediateGrid);
      nOvertakes += j - i;
    }
    return nOvertakes;
  }

  static int findCar(int car, int[] grid){
    for(int i = 0; i < grid.length; i++){
      if(grid[i] == car) return i;
    }
    throw new RuntimeException("Car " + car + " does not exist.");
  }

  static void moveCar(int from, int to, int[] grid){
    int car = grid[from];
    for(int i = from - 1; i >= to; i--){
      grid[i + 1] = grid[i];
    }
    grid[to] = car;
  }
}
