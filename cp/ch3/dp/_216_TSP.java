package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _216_TSP {
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
        String data = "6\r\n5 19\r\n55 28\r\n38 101\r\n28 62\r\n111 84\r\n43 116\r\n5\r\n11 27\r\n84 99\r\n142 81\r\n88 30\r\n95 38\r\n3\r\n132 73\r\n49 86\r\n72 111\r\n0\r\n";
        String data2 = "7\r\n56 108\r\n127 56\r\n29 132\r\n43 105\r\n30 146\r\n54 135\r\n32 89\r\n0\r\n";
        Scanner scanner = new Scanner(data2);
        StringBuilder out = new StringBuilder();
        int counter = 0;
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            if(n == 0) break;
            Point[] computers = new Point[n];
            for(int i = 0; i < n; i++){
                computers[i] = new Point(scanner.nextInt(), scanner.nextInt());
            }
            int[] solution = getSolution(tsp(computers), computers);
            double total = 0.0;
            out.append("**********************************************************\n");
            out.append(String.format("Network #%d\n", ++counter));
            for(int i = 0; i < solution.length - 1; i++){
                double dist = distance(computers[solution[i]], computers[solution[i + 1]]);
                out.append(String.format("Cable requirement to connect %s to %s is %.2f feet.\n", 	computers[solution[i]], computers[solution[i + 1]], dist));
                total += dist;
            }
            out.append(String.format("Number of feet of cable required is %.2f.\n",
                    total));

        }
        System.out.print(out.toString());
        scanner.close();
    }

    static int[] getSolution(double minDist, Point[] computers){
        int[] solution = new int[computers.length];
        double dist = minDist; int s = 0; int mask = 0; int prev = -1;
        while(mask < ((1 << computers.length) - 1)){
            for(int i = 0; i < minDistMemo.length; i++){
                double nextDist = dist - ((prev == -1) ? 0
                        : distance(computers[prev], computers[i]));
                if(doubleEqual(minDistMemo[i][mask | (1 << i)], nextDist)){
                    solution[s++] = i;
                    prev = i;
                    mask = mask | (1 << i);
                    dist = nextDist;
                }
            }
        }
        return solution;
    }

    static boolean doubleEqual(double d1, double d2){
        final double eps = 1e-5;
        return abs(d1 - d2) <= eps;
    }

    static double tsp(Point[] computers){
        double minDist = Integer.MAX_VALUE;
        for(int i = 0; i < computers.length; i++){
            memo = new double[computers.length][1 << computers.length];
            double dist = tsp(i, 1 << i, computers);
            if(dist < minDist){
                minDist = dist;
                minDistMemo = memo;
            }
        }
        return minDist;
    }

    static double[][] memo;
    static double[][] minDistMemo;

    static double tsp(int pos, int mask, Point[] computers){
        if(mask == (1 << computers.length) - 1) return 0;
        if(memo[pos][mask] != 0) return memo[pos][mask];
        double minDist = Integer.MAX_VALUE;
        for(int next = 0; next < computers.length; next++){
            if((mask & (1 << next)) == 0){
                double dist = distance(computers[pos], computers[next])
                        + tsp(next, mask | (1 << next), computers);
                if(dist < minDist){
                    minDist = dist;
                }
            }
        }
        memo[pos][mask] = minDist;
        return minDist;
    }

    static double distance(Point p1, Point p2){
        final double slack = 16.0;
        return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2)) + slack;
    }
}
