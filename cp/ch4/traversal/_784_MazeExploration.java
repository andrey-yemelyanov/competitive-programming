package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _784_MazeExploration{
   public static void main(String[] args){
      String data = "2\r\nXXXXXXXXX\r\nX X X\r\nX * X\r\nX X X\r\nXXXXXXXXX\r\nX X\r\nX X\r\nX X\r\nXXXXX\r\n_____\r\nXXXXX\r\nX X\r\nX * X\r\nX X\r\nXXXXX\r\n_____";
      Scanner s = new Scanner(data);
      int nTests = s.nextInt();
      String separator = "";
      s.nextLine();
      while(nTests-- > 0){   
         List<String> lines = new ArrayList<>();
         while(s.hasNext()){
            String line = s.nextLine();
            if(line.contains("__")){
               separator = line;
               break;
            }
            lines.add(line);
         }
         char[][] maze = new char[lines.size()][lines.get(0).length()];
         for(int i = 0; i < maze.length; i++){
            maze[i] = lines.get(i).toCharArray();
         }
         int[] sp = findStartingPoint(maze);
         paintRooms(maze, sp[0], sp[1]);
         System.out.println(toString(maze));
         System.out.println(separator);
      }
   }
   
   static String toString(char[][] maze){
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < maze.length; i++){
         for(int j = 0; j < maze[i].length; j++){
            sb.append(maze[i][j]);
         }
         if(i < maze.length - 1) sb.append("\n");
      }
      return sb.toString();
   }
   
   static final char EMPTY = ' ';
   static final char PAINTED = '#';
   static final int[] dr = new int[] {-1, 0, 1, 0};
   static final int[] dc = new int[] {0, 1, 0, -1};
   static void paintRooms(char[][] maze, int row, int col){
      if(row < 0 || row >= maze.length || col < 0 || col >= maze[row].length) return;
      if(maze[row][col] != EMPTY && maze[row][col] != '*') return;
      maze[row][col] = PAINTED;
      for(int d = 0; d < 4; d++){
         paintRooms(maze, row + dr[d], col + dc[d]);
      }
   }
   
   static int[] findStartingPoint(char[][] maze){
      for(int i = 0; i < maze.length; i++){
         for(int j = 0; j < maze[i].length; j++){
            if(maze[i][j] == '*') return new int[] {i, j};
         }
      }
      return null;
   }
}