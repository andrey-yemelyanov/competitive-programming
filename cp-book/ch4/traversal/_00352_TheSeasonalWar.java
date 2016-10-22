package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _00352_TheSeasonalWar{
   static class Pixel{
      public int row; 
      public int col;
      public Pixel(int row, int col){
         this.row = row;
         this.col = col;
      }
   }
   public static void main(String[] args){
      String data = "6\r\n100100\r\n001010\r\n000000\r\n110000\r\n111000\r\n010100\r\n8\r\n01100101\r\n01000001\r\n00011000\r\n00000010\r\n11000011\r\n10100010\r\n10000001\r\n01100000";
      Scanner s = new Scanner(data);
      int img = 1;
      while(s.hasNext()){
         int n = s.nextInt();
         int[][] image = new int[n][n];
         for(int i = 0; i < image.length; i++){
            char[] line = s.next().toCharArray();
            for(int j = 0; j < image[0].length; j++){
               image[i][j] = Character.getNumericValue(line[j]);
            }
         }
         System.out.printf("Image number %d contains %d war eagles.\n", img++, countEagles(image));
      }
   }
   
   static boolean valid(int[][] image, Pixel p){
      return !(p.row < 0 || p.row >= image.length || p.col < 0 || p.col >= image[0].length);
   }
   
   static List<Pixel> validNeighbors(int[][] image, Pixel p){
      List<Pixel> list = new ArrayList<>();
      Pixel n = new Pixel(p.row - 1, p.col);
      if(valid(image, n)) list.add(n);
      n = new Pixel(p.row - 1, p.col + 1);
      if(valid(image, n)) list.add(n);  
      n = new Pixel(p.row, p.col + 1);
      if(valid(image, n)) list.add(n);
      n = new Pixel(p.row + 1, p.col + 1);
      if(valid(image, n)) list.add(n);  
      n = new Pixel(p.row + 1, p.col);
      if(valid(image, n)) list.add(n);  
      n = new Pixel(p.row + 1, p.col - 1);
      if(valid(image, n)) list.add(n);
      n = new Pixel(p.row, p.col - 1);
      if(valid(image, n)) list.add(n);
      n = new Pixel(p.row - 1, p.col - 1);
      if(valid(image, n)) list.add(n);
      return list;
   }
   
   static List<Pixel> unvisitedNeighbors(int[][] image, Pixel p){
      List<Pixel> list = new ArrayList<>();
      for(Pixel n : validNeighbors(image, p)){
         if(image[n.row][n.col] == 1){
            list.add(n);
         }
      }
      return list;
   }
   
   static final int VISITED = 5;
   static void dfs(int[][] image, Pixel p){
      image[p.row][p.col] = VISITED;
      for(Pixel neighbor : unvisitedNeighbors(image, p)){
         dfs(image, neighbor);
      }
   }
   
   static int countEagles(int[][] image){
      int nEagles = 0;
      for(int i = 0; i < image.length; i++){
         for(int j = 0; j < image[0].length; j++){
            if(image[i][j] == 1 && image[i][j] != VISITED){
               dfs(image, new Pixel(i, j));
               nEagles++;
            }
         }
      }
      return nEagles;
   }
   
   static void printImage(int[][] image){
      for(int i = 0; i < image.length; i++){
         System.out.println(Arrays.toString(image[i]));
      }
   }
}