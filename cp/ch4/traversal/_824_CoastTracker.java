package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _824_CoastTracker {
    static class Coord{
      public int row;
      public int col;
      public Coord(int row, int col){
         this.row = row;
         this.col = col;
      }
    }
	 
    public static	void main(String[] args) {
		String data	= "22 25 2\r\n22 26 0\r\n21 26 1\r\n21 25 1\r\n21 24 1\r\n22 24 1\r\n23 24 1\r\n23 25 1\r\n23 26 0\r\n21 26 1\r\n21 27 1\r\n20 27 1\r\n20 26 1\r\n20 25 0\r\n21 25 1\r\n22 25 1\r\n22 26 0\r\n22 27 0\r\n21 27 0\r\n21 28 0\r\n20 28 1\r\n20 27 1\r\n20 26 1\r\n21 26 1\r\n22 26 0\r\n22 27 0\r\n22 28 0\r\n-1";
		Scanner scanner =	new Scanner(data);
	   while(scanner.hasNext()){
         int x = scanner.nextInt();
         if(x == -1) break;
         int y = scanner.nextInt();
         int d = scanner.nextInt();
         int[][] area = new int[3][3];
         for(int i = 0; i < 8; i++){
            int col, row;
            
            int xShift = scanner.nextInt() - x;
            if(xShift > 0) col = 2;
            else if(xShift < 0) col = 0;
            else col = 1;
            
            int yShift = scanner.nextInt() - y;
            if(yShift > 0) row = 0;
            else if(yShift < 0) row = 2;
            else row = 1;
            
            area[row][col] = scanner.nextInt();   
         }
         System.out.println(turn(d, area));
      }
		scanner.close();
	 }
	  
    static Coord[] coords;
    static{
      coords = new Coord[8];
      coords[0] = new Coord(0, 1);
      coords[1] = new Coord(0, 0);
      coords[2] = new Coord(1, 0);
      coords[3] = new Coord(2, 0);
      coords[4] = new Coord(2, 1);
      coords[5] = new Coord(2, 2);
      coords[6] = new Coord(1, 2);
      coords[7] = new Coord(0, 2);
    }
    static int turn(int direction, int[][] area){
      for(int i = 0; i < 8; i++){
         int d = (direction + i + 6) % 8;
         if(area[coords[d].row][coords[d].col] == 1) return d;
      }
      throw new RuntimeException();
    }
}
