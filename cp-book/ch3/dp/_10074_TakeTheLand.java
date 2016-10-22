package helvidios.cp.ch3.dp;

import java.util.*;

public class _10074_TakeTheLand {
    public static void main(String []args){
        String data = "6 7\r\n0 1 1 0 1 1 0\r\n0 0 0 0 0 1 0\r\n1 0 0 0 0 0 1\r\n0 1 0 0 0 0 1\r\n1 1 0 0 0 1 0\r\n1 1 0 1 1 0 0\r\n0 0";
        String data2 = "3 4\n0 1 1 0\n0 0 0 1\n0 0 1 0\n0 0";
        Scanner s = new Scanner(data);
        while(s.hasNext()){
            int nRows = s.nextInt(); int nCols = s.nextInt();
            if(nRows == 0 && nCols == 0) break;
            int[][] matrix = new int[nRows][nCols];
            for(int i = 0; i < nRows; i++){
                for(int j = 0; j < nCols; j++){
                    matrix[i][j] = s.nextInt();
                }
            }
            System.out.println(largestTreelessArea(transform(matrix)));
        }
    }

    static void printMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
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

    static int largestTreelessArea(int[][] matrix){
        int largestArea = 0;
        for(int i = 0; i < matrix.length; i++) for(int j = 0; j < matrix[0].length; j++) // start coordinade
            for(int k = i; k < matrix.length; k++) for(int l = j; l < matrix[0].length; l++) // end coordinate
            {
                int subRect = matrix[k][l];
                if(i > 0) subRect -= matrix[i - 1][l];
                if(j > 0) subRect -= matrix[k][j - 1];
                if(i > 0 && j > 0) subRect += matrix[i - 1][j - 1];
                if(subRect == 0){
                    largestArea = Math.max(largestArea, ((l - j + 1) * (k - i + 1)));
                }
            }
        return largestArea;
    }
}
