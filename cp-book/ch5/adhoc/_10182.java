import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10182 Bee Maja
Problem url: https://uva.onlinejudge.org/external/101/10182.pdf
Author: Andrey Yemelyanov
*/
public class _10182 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int williCoord = s.nextInt();
      int[] majaCoord = toMajaCoord(williCoord);
      System.out.printf("%d %d\n", majaCoord[0], majaCoord[1]);
    }
  }

  static int[] toMajaCoord(int williCoord){
    int layer = 0; int nCells = 1;
    while(williCoord > nCells){
      layer++;
      nCells += 6 * layer;
    }
    int x = layer; int y = 0;
    int cell = nCells;

    while(cell != williCoord && y != -x) {cell--; y--;}
    if(cell == williCoord) return new int[]{x, y};
    //System.out.printf("x=%d, y=%d, cell=%d\n", x, y, cell);

    while(cell != williCoord && x != 0) {cell--; x--;}
    if(cell == williCoord) return new int[]{x, y};
    //System.out.printf("x=%d, y=%d, cell=%d\n", x, y, cell);

    while(cell != williCoord && y != 0){cell--; x--; y++;}
    if(cell == williCoord) return new int[]{x, y};
    //System.out.printf("x=%d, y=%d, cell=%d\n", x, y, cell);

    while(cell != williCoord && y != -x) {cell--; y++;}
    if(cell == williCoord) return new int[]{x, y};
    //System.out.printf("x=%d, y=%d, cell=%d\n", x, y, cell);

    while(cell != williCoord && x != 0) {cell--; x++;}
    if(cell == williCoord) return new int[]{x, y};
    //System.out.printf("x=%d, y=%d, cell=%d\n", x, y, cell);

    while(cell != williCoord && y != 0){cell--; x++; y--;}
    return new int[]{x, y};
  }
}
