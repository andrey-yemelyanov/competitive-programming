package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _00657_TheDieIsCast{
   public static void main(String[] args){
      String data = "30 15\r\n..............................\r\n..............................\r\n...............*..............\r\n...*****......****............\r\n...*X***.....**X***...........\r\n...*****....***X**............\r\n...***X*.....****.............\r\n...*****.......*..............\r\n..............................\r\n........***........******.....\r\n.......**X****.....*X**X*.....\r\n......*******......******.....\r\n.....****X**.......*X**X*.....\r\n........***........******.....\r\n..............................\r\n0 0\r\n";
      String data2 = "3 2\r\nXX*\r\nX..\r\n0 0\n";
      String data3 = "10 6 \r\n.......... \r\n.XXX...... \r\n....XXX... \r\n.......XXX \r\n....XXX... \r\n*X*X...... \r\n6 6 \r\nXXXXX* \r\n.....X \r\n.....X \r\n.....X \r\n.....X \r\n.....X \r\n6 6 \r\nXXXXX. \r\n.....X \r\n.....X \r\n.....X \r\n.....X \r\n.....X \r\n6 6 \r\nXXXXX. \r\n....*X \r\n.....X \r\n.....X \r\n.....X \r\n.....X \r\n0 0 \r\n";
      String data4 = "5 5 \r\nXXX*X \r\nXXX*X \r\n..... \r\nX***X \r\nXX*** \r\n10 5 \r\n.......... \r\n..X**.*X.. \r\n.......... \r\n...*X*X... \r\n.......... \r\n10 5 \r\n.......... \r\n..X....X.. \r\n.......... \r\n...*X*X... \r\n.......... \r\n10 5 \r\n.......... \r\n..X....X.. \r\n..X....X.. \r\n..XXXXXX.. \r\n.......... \r\n10 5 \r\n.......... \r\n..X*X..... \r\n..*X*..... \r\n..X*X..... \r\n.......... \r\n10 5 \r\n.......... \r\n..X*X..... \r\n..*X**.... \r\n..X*X*.... \r\n.......... \r\n5 5 \r\nXXXXX \r\nXXXXX \r\nXXXXX \r\nXXXXX \r\nXXXXX \r\n0 0 ";
      String data5 = "10 5 \r\n.......... \r\n..X*X..... \r\n..*X**.... \r\n..X*X*.... \r\n.......... \r\n0 0\n";
      String data6 = "5 5\r\n*.***\r\n***..\r\n.....\r\n.....\r\n.....\r\n5 5\r\nXXX*X\r\nXXX*X\r\n.....\r\nX***X\r\nXX***\r\n10 5\r\n..........\r\n..X**.*X..\r\n..........\r\n...*X*X...\r\n..........\r\n10 5\r\n..........\r\n..X....X..\r\n..........\r\n...*X*X...\r\n..........\r\n10 5\r\n..........\r\n..X....X..\r\n..X....X..\r\n..XXXXXX..\r\n..........\r\n10 5\r\n..........\r\n..X*X.....\r\n..*X*.....\r\n..X*X.....\r\n..........\r\n10 5\r\n..........\r\n..X*X.....\r\n..*X**....\r\n..X*X*....\r\n..........\r\n5 5\r\nXXXXX\r\nXXXXX\r\nXXXXX\r\nXXXXX\r\nXXXXX\r\n30 15\r\n..............................\r\n..............................\r\n...............*..............\r\n...*****......****............\r\n...*X***.....**X***...........\r\n...*****....***X**............\r\n...***X*.....****.............\r\n...*****.......*..............\r\n..............................\r\n........***........******.....\r\n.......**X****.....*X**X*.....\r\n......*******......******.....\r\n.....****X**.......*X**X*.....\r\n........***........******.....\r\n..............................\r\n10 6\r\n..........\r\n.XXX......\r\n....XXX...\r\n.......XXX\r\n....XXX...\r\n*X*X......\r\n6 6\r\nXXXXX*\r\n.....X\r\n.....X\r\n.....X\r\n.....X\r\n.....X\r\n6 6\r\nXXXXX.\r\n.....X\r\n.....X\r\n.....X\r\n.....X\r\n.....X\r\n6 6\r\nXXXXX.\r\n....*X\r\n.....X\r\n.....X\r\n.....X\r\n.....X\r\n30 15\r\n.....X*X*X*X*X*X***...........\r\n.X......................X.....\r\n...............*.........X....\r\n...X****......****........X...\r\n...*X*.*.....**X***X..........\r\n...*.X......***X**.....XXX....\r\n...*.*X*.....****........X....\r\n...***.X.......*.........X....\r\n..............................\r\n.......X***.............*..***\r\n......******X****.....*X**X*..\r\n..***********......**.*.*.....\r\n.....****X**.......*X**X*.....\r\n........***........*....*.....\r\n........***.********..........\r\n0 0\n";
      Scanner s = new Scanner(data6);
      int throwNum = 1;
      while(s.hasNext()){
         int nCols = s.nextInt();
         int nRows = s.nextInt();
         s.nextLine();
         if(nCols == 0 && nRows == 0) break;
         char[][] grid = new char[nRows][nCols];
         for(int i = 0; i < nRows; i++){
            grid[i] = s.nextLine().toCharArray();
         }
         System.out.printf("Throw %d\n", throwNum++);
         List<Integer> nDots = countDots(grid);
         if(nDots.size() != 0){
            System.out.println(printList(nDots));   
         }
         System.out.println();
      }
   }
   
   static String printList(List<Integer> list){
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < list.size(); i++){
         sb.append(list.get(i));
         if(i < list.size() - 1) sb.append(" ");
      }
      return sb.toString();
   }
   
   static final char DOT = 'X';
   static final char VISITED = 'V';
   static final char BKG = '.';
   static final int[] dr = new int[]{-1, 0, 1, 0};
   static final int[] dc = new int[]{0, 1, 0, -1};
   
   static void floodfill(char[][] grid, int row, int col){
      if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
      if(grid[row][col] != DOT) return;
      grid[row][col] = VISITED;
      for(int d = 0; d < 4; d++){
         floodfill(grid, row + dr[d], col + dc[d]);
      }
   }
   
   static void floodfill(char[][] grid, int[][] dice, int row, int col, int color){
      if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
      if(grid[row][col] == BKG) return;
      if(dice[row][col] != 0) return;
      dice[row][col] = color;
      for(int d = 0; d < 4; d++){
         floodfill(grid, dice, row + dr[d], col + dc[d], color);
      }
   }
   
   static int[][] identifyDice(char[][] grid){
      int[][] dice = new int[grid.length][grid[0].length];
      int color = 1;
      for(int i = 0; i < grid.length; i++){
         for(int j = 0; j < grid[0].length; j++){
            if(grid[i][j] != BKG && dice[i][j] == 0){
               floodfill(grid, dice, i, j, color);
               color++;
            }
         }
      }
      return dice;
   }
      
   static List<Integer> countDots(char[][] grid){
      int[][] dice = identifyDice(grid);
      Map<Integer, Integer> m = new HashMap<>();
      for(int i = 0; i < grid.length; i++){
         for(int j = 0; j < grid[0].length; j++){
            if(grid[i][j] == DOT){
               floodfill(grid, i, j);
               if(!m.containsKey(dice[i][j])) m.put(dice[i][j], 0);
               m.put(dice[i][j], m.get(dice[i][j]) + 1);
            }
         }
      }
      List<Integer> list = new ArrayList<>(m.values());
      Collections.sort(list);
      return list;
   }
} 