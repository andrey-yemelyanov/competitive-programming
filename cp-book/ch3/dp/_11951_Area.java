package helvidios.cp.ch3.dp;

import java.util.*;
import java.io.*;
public class _11951_Area {
    public static void main(String[] args) throws IOException{
        String data = "1\r\n5 6 15\r\n10 1 10 20 10 10\r\n30 1 1 5 1 1\r\n50 1 1 20 1 1\r\n10 5 5 10 5 1\r\n40 10 90 1 10 10";
        String data3 = "1\n2 3 25\n1 2 3\n4 5 6";
        String data4 = "1\n2 3 25\n0 0 0\n0 0 0";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));
        int nTestCases = Integer.parseInt(reader.readLine());
        for(int c = 1; c <= nTestCases; c++){
            String[] line1 = reader.readLine().split("\\s+");
            int nRows = Integer.parseInt(line1[0]);
            int nCols = Integer.parseInt(line1[1]);
            int moneyAvailable = Integer.parseInt(line1[2]);
            long[][] matrix = new long[nRows][nCols];
            for(int i = 0; i < matrix.length; i++){
                String[] line = reader.readLine().split("\\s+");
                for(int j = 0; j < matrix[0].length; j++){
                    matrix[i][j] = Long.parseLong(line[j]);
                }
            }
            long[] answer = buyOptimalPlot(transform(matrix), moneyAvailable);
            System.out.printf("Case #%d: %d %d\n", c, answer[0], answer[1]);
        }
    }

    static long[] buyOptimalPlot(long[][] matrix, int moneyAvailable){
        int largestArea = 0;
        long lowestCost = 0;
        int nRows = matrix.length;
        int nCols = matrix[0].length;
        for(int i = 0; i < nRows; i++){
            int maxHeight = nRows - i;
            if(maxHeight * nCols < largestArea) break;
            for(int j = 0; j < nCols; j++){ // start coordinate
                int maxWidth = nCols - j;
                if(maxWidth * maxHeight < largestArea) break;
                for(int k = i; k < nRows; k++){
                    for(int l = j; l < nCols; l++){ // end coordinate
                        int area = (k - i + 1) * (l - j + 1);
                        long cost = plotCost(matrix, i, j, k, l);
                        if(cost > moneyAvailable) break;
                        if(area > largestArea){
                            largestArea = area;
                            lowestCost = cost;
                        }else if(area == largestArea){
                            lowestCost = Math.min(cost, lowestCost);
                        }
                    }
                }
            }
        }
        return new long[]{largestArea, lowestCost};

    }

    static long plotCost(long[][] matrix, int i, int j, int k, int l){
        long cost = matrix[k][l];
        if(i > 0) cost -= matrix[i - 1][l];
        if(j > 0) cost -= matrix[k][j - 1];
        if(i > 0 && j > 0) cost += matrix[i - 1][j - 1];
        return cost;
    }

    static long[][] transform(long[][] matrix){
        long[][] transformed = new long[matrix.length][matrix[0].length];
        for(int i = 0; i < transformed.length; i++){
            for(int j = 0; j < transformed[0].length; j++){
                transformed[i][j] = matrix[i][j];
                if(i > 0) transformed[i][j] += transformed[i - 1][j];
                if(j > 0) transformed[i][j] += transformed[i][j - 1];
                if(i > 0 && j > 0) transformed[i][j] -= transformed[i - 1][j - 1];
            }
        }
        return transformed;
    }
}