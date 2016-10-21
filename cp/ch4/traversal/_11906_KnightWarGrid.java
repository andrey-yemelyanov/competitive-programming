package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _11906_KnightWarGrid{
   static class Cell{
      public int row;
      public int col;
      public Cell(int row, int col){
         this.row = row;
         this.col = col;
      }
      public boolean equals(Object obj){
         Cell other = (Cell)obj;
         return other.row == this.row && other.col == this.col;
      }
      public int hashCode(){
         int hash = 17;
         hash = hash * 23 + row;
         hash = hash * 23 + col;
         return hash;
      }
   } 
   public static void main(String[] args){
      String data = "2\r\n3 3 2 1\r\n0\r\n4 4 1 2\r\n2\r\n3 3\r\n1 1";
      Scanner s = new Scanner(data);
      int nTests = s.nextInt();
      for(int c = 1; c <= nTests; c++){
         int R = s.nextInt(); int C = s.nextInt(); int M = s.nextInt(); int N = s.nextInt();
         int[][] grid = new int[C][R];
         int W = s.nextInt();
         for(int i = 0; i < W; i++){
            int col = s.nextInt(); int row = s.nextInt();
            grid[row][col] = WATER;
         }
         int[] result = exploreGrid(grid, M, N);
         System.out.printf("Case %d: %d %d\n", c, result[0], result[1]);
      }
   }
   
   static final int WATER = 1;
   static final int ENQ = 2;
   
   static int[] exploreGrid(int[][] grid, int M, int N){
      int nEven = 0; int nOdd = 0;
      Queue<Cell> q = new LinkedList<>();
      q.add(new Cell(0, 0));
      grid[0][0] = ENQ;
      while(!q.isEmpty()){
         Cell cell = q.remove();
         Set<Cell> jumps = getValidJumps(grid, cell, M, N);
         if(jumps.size() % 2 == 0) nEven++;
         else nOdd++;
         for(Cell jump : jumps){
            if(grid[jump.row][jump.col] != ENQ){
               q.add(jump);
               grid[jump.row][jump.col] = ENQ;
            }
         }   
      }
      return new int[] {nEven, nOdd};
   }
   
   static boolean cellValid(int[][] grid, Cell cell){
      if(cell.row < 0 || cell.row >= grid.length) return false;
      if(cell.col < 0 || cell.col >= grid[0].length) return false;
      return grid[cell.row][cell.col] != WATER;
   }
   
   static Set<Cell> getValidJumps(int[][] grid, Cell cell, int M, int N){
      Set<Cell> jumps = new HashSet<>();
      
      // upper left
      Cell nextCell = new Cell(cell.row - M, cell.col - N);
      if(cellValid(grid, nextCell)) jumps.add(nextCell);
      nextCell = new Cell(cell.row - N, cell.col - M);
      if(cellValid(grid, nextCell)) jumps.add(nextCell);
      
      // upper right
      nextCell = new Cell(cell.row - M, cell.col + N);
      if(cellValid(grid, nextCell)) jumps.add(nextCell);
      nextCell = new Cell(cell.row - N, cell.col + M);
      if(cellValid(grid, nextCell)) jumps.add(nextCell);
      
      // lower left
      nextCell = new Cell(cell.row + M, cell.col - N);
      if(cellValid(grid, nextCell)) jumps.add(nextCell);
      nextCell = new Cell(cell.row + N, cell.col - M);
      if(cellValid(grid, nextCell)) jumps.add(nextCell);
      
      // lower right
      nextCell = new Cell(cell.row + M, cell.col + N);
      if(cellValid(grid, nextCell)) jumps.add(nextCell);
      nextCell = new Cell(cell.row + N, cell.col + M);
      if(cellValid(grid, nextCell)) jumps.add(nextCell);
      
      return jumps;
   }
}