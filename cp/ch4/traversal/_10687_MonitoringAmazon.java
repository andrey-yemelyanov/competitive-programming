package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _10687_MonitoringAmazon{
   static class Point{
      public int x;
      public int y;
      public Point(int x, int y){
         this.x = x;
         this.y = y;
      }
      @Override
      public boolean equals(Object other){
          Point otherPoint = (Point)other;
          return this.x == otherPoint.x && this.y == otherPoint.y;
      }
      public int hashCode() {
          int hash = 17;
          hash = hash * 31 + x;
          hash = hash * 31 + y;
          return hash;
      }
   }
   public static void main(String[] args){
      String data = "4\r\n1 0 0 1 -1 0 0 -1\r\n8\r\n1 0 1 1 0 1 -1 1 -1 0 -1 -1 0 -1 1 -1\r\n6\r\n0 3 0 4 1 3 -1 3 -1 -4 -2 -5\r\n0";
      Scanner s = new Scanner(data);
      while(s.hasNext()){
         int n = s.nextInt();
         if(n == 0) break;
         Point[] points = new Point[n];
         for(int i = 0; i < n; i++){
            points[i] = new Point(s.nextInt(), s.nextInt());
         }
         if(allStationsReachable(points[0], points)) System.out.println("All stations are reachable.");
         else System.out.println("There are stations that are unreachable.");
      }
   }  
   
   static void sendSignal(Point source, Point[] points, Set<Point> visited){
      Point[] closestPair = getClosestPair(source, points);
      for(Point nextPoint : closestPair){
         if(!visited.contains(nextPoint)){
            visited.add(nextPoint);
            sendSignal(nextPoint, points, visited);
         }
      }
   }
   
   static boolean allStationsReachable(Point dispatchStation, Point[] stations){
      Set<Point> reachableStations = new HashSet<>();
      reachableStations.add(dispatchStation);
      sendSignal(dispatchStation, stations, reachableStations);
      return reachableStations.size() == stations.length;
   }
   
   static Point[] getClosestPair(Point source, Point[] points){
      Set<Point> excludedPoints = new HashSet<>();
      Point min1 = getClosestPoint(source, points, excludedPoints);
      excludedPoints.add(min1);
      Point min2 = getClosestPoint(source, points, excludedPoints);
      return new Point[] {min1, min2};
   }
   
   static Point getClosestPoint(Point source, Point[] points, Set<Point> excludedPoints){
      int minDistance = Integer.MAX_VALUE;
      Point point = null;
      for(Point p : points){
         if(!excludedPoints.contains(p) && !source.equals(p)){
            int squaredDistance = (int)pow(source.x - p.x, 2) + (int)pow(source.y - p.y, 2);
            if(squaredDistance == minDistance){
               if((p.x < point.x) || (p.x == point.x && p.y < point.y)){
                  point = p;
               }
            }else if(squaredDistance < minDistance){
               minDistance = squaredDistance;
               point = p;
            }
         }
      }
      return point;
   }
}