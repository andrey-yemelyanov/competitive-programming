package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _10337_FlightPlanner {
    public static void main(String[] args) {
        String data = "2\n\n" +
                "400\n" +
                "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 9 9 1\n" +
                "1 -9 -9 1\n\n" +
                "1000\n" +
                "9 9 9 9 9 9 9 9 9 9\n" +
                "9 9 9 9 9 9 9 9 9 9\n" +
                "9 9 9 9 9 9 9 9 9 9\n" +
                "9 9 9 9 9 9 9 9 9 9\n" +
                "9 9 9 9 9 9 9 9 9 9\n" +
                "9 9 9 9 9 9 9 9 9 9\n" +
                "7 7 7 7 7 7 7 7 7 7\n" +
                "-5 -5 -5 -5 -5 -5 -5 -5 -5 -5\n" +
                "-7 -3 -7 -7 -7 -7 -7 -7 -7 -7\n" +
                "-9 -9 -9 -9 -9 -9 -9 -9 -9 -9\n";
        Scanner scanner = new Scanner(data);
        int nTests = scanner.nextInt();
        while(nTests-- > 0){
            int nWaypoints = scanner.nextInt() / 100;
            int[][] windSpeed = new int[maxAltitude + 1][nWaypoints];
            for(int i = 0; i < windSpeed.length; i++){
                for(int j = 0; j < windSpeed[0].length; j++){
                    windSpeed[i][j] = scanner.nextInt();
                }
            }
            System.out.println(minFuel(windSpeed, nWaypoints));
            System.out.println();
        }
        scanner.close();
    }

    static final int climbCost = 60;
    static final int holdAltitudeCost = 30;
    static final int sinkAltitudeCost = 20;
    static final int maxAltitude = 9;
    static final int minAltitude = 0;

    static int minFuel(int[][] windSpeed, int nWaypoints){
        memo = new int[1001][10];
        for(int i = 0; i < memo.length; i++){
            for(int j = 0; j < memo[0].length; j++){
                memo[i][j] = -1;
            }
        }
        return minFuel(0, 0, windSpeed, nWaypoints);
    }

    static int[][] memo;
    static final int POS_INF = 100000000;
    static int minFuel(int waypoint, int altitude, int[][] windSpeed, int nWaypoints){
        int savingsDueToWind = windSpeed[windSpeed.length - altitude - 1][waypoint];
        if(waypoint == nWaypoints - 1){
            if(altitude > 1) return POS_INF;
            else {
                return altitude == 1
                        ? sinkAltitudeCost - savingsDueToWind
                        : holdAltitudeCost - savingsDueToWind;
            }
        }

        if(memo[waypoint][altitude] != -1) return memo[waypoint][altitude];

        int minFuel = POS_INF;

        // try climbing up one mile
        if(altitude < maxAltitude){
            minFuel = climbCost - savingsDueToWind + minFuel(waypoint + 1, altitude + 1, windSpeed, nWaypoints);
        }

        // try holding current altitude
        minFuel = min(minFuel,
                holdAltitudeCost - savingsDueToWind + minFuel(waypoint + 1, altitude, windSpeed, nWaypoints));

        // try descending one mile
        if(altitude > minAltitude){
            minFuel = min(minFuel,
                    sinkAltitudeCost - savingsDueToWind + minFuel(waypoint + 1, altitude - 1, windSpeed, nWaypoints));
        }

        memo[waypoint][altitude] = minFuel;

        return minFuel;
    }
}
