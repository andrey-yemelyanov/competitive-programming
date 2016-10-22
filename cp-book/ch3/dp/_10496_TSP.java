package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _10496_TSP {
    static class Point{
        public int x;
        public int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public String toString(){
            return String.format("(%d,%d)", x, y);
        }
    }
    public static void main(String[] args) {
        String data = "2\r\n10 10\r\n1 1\r\n4\r\n2 3\r\n5 5\r\n9 4\r\n6 5\r\n10 10\r\n1 1\r\n4\r\n2 3\r\n5 5\r\n9 4\r\n6 5";
        Scanner scanner = new Scanner(data);
        int nTests = scanner.nextInt();
        while(nTests-- > 0){
            scanner.nextInt(); scanner.nextInt();
            Point origin = new Point(scanner.nextInt(), scanner.nextInt());
            int n = scanner.nextInt();
            Point[] coords = new Point[n + 1];
            coords[0] = origin;
            for(int i = 0; i < n; i++){
                coords[i + 1] = new Point(scanner.nextInt(), scanner.nextInt());
            }
            System.out.printf("The shortest path has length %d\n", tsp(coords));
        }
        scanner.close();
    }

    static int tsp(Point[] coords){
        memo = new int[coords.length][(1 << coords.length)];
        return tsp(0, 1, coords);
    }

    static int[][] memo;
    static int tsp(int pos, int mask, Point[] coords){
        if(mask == (1 << coords.length) - 1) return distance(coords[0], coords[pos]);
        if(memo[pos][mask] != 0) return memo[pos][mask];
        int minDist = 1000000;
        for(int next = 0; next < coords.length; next++){
            if((mask & (1 << next)) == 0){
                minDist = min(minDist, distance(coords[pos], coords[next])
                        + tsp(next, mask | (1 << next), coords));
            }
        }
        memo[pos][mask] = minDist;
        return minDist;
    }

    static int distance(Point p1, Point p2){
        return abs(p1.x - p2.x) + abs(p1.y - p2.y);
    }
}
