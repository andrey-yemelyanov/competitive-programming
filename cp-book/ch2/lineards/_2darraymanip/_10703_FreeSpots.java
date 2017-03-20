import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10703 Free spots
Problem url: https://uva.onlinejudge.org/external/107/10703.pdf
Author: Andrey Yemelyanov
*/
public class _10703_FreeSpots {
  public static class Subrect{
    public int row1;
    public int col1;
    public int row2;
    public int col2;
    public Subrect(int row1, int col1, int row2, int col2){
      this.row1 = min(row1, row2);
      this.row2 = max(row1, row2);
      this.col1 = min(col1, col2);
      this.col2 = max(col1, col2);
    }
    public String toString(){
      return row1 + " " + col1 + " " + row2 + " " + col2;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(true){
      int W = s.nextInt(); int H = s.nextInt(); int N = s.nextInt();
      if(W == 0 && H == 0 && N == 0) break;
      List<Subrect> subrects = new ArrayList<>();
      for(int i = 0; i < N; i++){
        int col1 = s.nextInt() - 1;
        int row1 = s.nextInt() - 1;
        int col2 = s.nextInt() - 1;
        int row2 = s.nextInt() - 1;
        subrects.add(new Subrect(row1, col1, row2, col2));
      }
      //System.out.println(subrects);
      int nFreeSpots = countFreeSpots(H, W, subrects);
      if(nFreeSpots == 0) System.out.println("There is no empty spots.");
      else if(nFreeSpots == 1) System.out.println("There is one empty spot.");
      else System.out.printf("There are %d empty spots.\n", nFreeSpots);
    }
  }

  static final int FREE = 0;
  static final int OCCUPIED = 1;
  static int countFreeSpots(int H, int W, List<Subrect> subrects){
    int[][] m = new int[H][W];
    for(Subrect subrect : subrects){
      for(int row = subrect.row1; row <= subrect.row2; row++){
        for(int col = subrect.col1; col <= subrect.col2; col++){
          m[row][col] = OCCUPIED;
        }
      }
    }
    int nFreeSpots = 0;
    for(int i = 0; i < m.length; i++){
      for(int j = 0; j < m[0].length; j++){
        if(m[i][j] == FREE) nFreeSpots++;
      }
    }
    return nFreeSpots;
  }
}
