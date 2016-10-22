package helvidios.cp.ch3.dp;

import java.util.*;
public class _10827_Torus {
    public static void main(String []args){
        String data = "2\r\n5\r\n1 -1 0 0 -4\r\n2 3 -2 -3 2\r\n4 1 -1 5 0\r\n3 -2 1 -3 2\r\n-3 2 4 1 -4\r\n3\r\n1 2 3\r\n4 5 6\r\n7 8 9\r\n";
        Scanner s = new Scanner(data);
        int nTestCases = s.nextInt();
        while(nTestCases-- > 0){
            int n = s.nextInt();
            int[][] matrix = new int[n][n];
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[0].length; j++){
                    matrix[i][j] = s.nextInt();
                }
            }
            //printMatrix(multiply(matrix));
            System.out.println(largestSum(transform(multiply(matrix))));
        }
    }

    static void printMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    static int[][] multiply(int[][] source){
        int[][] result = new int[source.length * 2][source[0].length * 2];
        copy(source, result, 0, 0);
        copy(source, result, 0, source[0].length);
        copy(source, result, source.length, 0);
        copy(source, result, source.length, source[0].length);
        return result;
    }

    static void copy(int[][] source, int[][] target, int rowOffset, int colOffset){
        for(int i = 0; i < source.length; i++){
            for(int j = 0; j < source[0].length; j++){
                target[i + rowOffset][j + colOffset] = source[i][j];
            }
        }
    }

    static int[][] transform(int[][] matrix){
        int[][] transformed = new int[matrix.length][matrix[0].length];
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

    static int largestSum(int[][] matrix){
        int largestSum = 0;
        for(int i = 0; i < matrix.length / 2; i++) for(int j = 0; j < matrix[0].length / 2; j++) // start coordinade
            for(int k = i; k < i + matrix.length / 2; k++) for(int l = j; l < j + matrix[0].length / 2; l++) // end coordinate
            {
                int subRect = matrix[k][l];
                if(i > 0) subRect -= matrix[i - 1][l];
                if(j > 0) subRect -= matrix[k][j - 1];
                if(i > 0 && j > 0) subRect += matrix[i - 1][j - 1];
                largestSum = Math.max(largestSum, subRect);
            }
        return largestSum;
    }
}
