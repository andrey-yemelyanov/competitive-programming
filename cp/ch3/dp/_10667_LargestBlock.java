package helvidios.cp.ch3.dp;

import java.util.*;
public class _10667_LargestBlock {
    public static void main(String []args){
        String data = "3\r\n10\r\n3\r\n2 2 5 3\r\n8 3 9 7\r\n3 6 3 8\r\n20\r\n1\r\n1 1 1 1\r\n10\r\n2\r\n5 1 5 10\r\n1 5 10 5";
        Scanner s = new Scanner(data);
        int nTestCases = s.nextInt();
        while(nTestCases-- >0){
            int boardSize = s.nextInt();
            int nBlocks = s.nextInt();
            int[][] board = new int[boardSize][boardSize];
            while(nBlocks-- > 0){
                int row1 = s.nextInt() - 1;
                int col1 = s.nextInt() - 1;
                int row2 = s.nextInt() - 1;
                int col2 = s.nextInt() - 1;
                for(int i = row1; i <= row2; i++){
                    for(int j = col1; j <= col2; j++){
                        board[i][j] = 1;
                    }
                }
            }
            System.out.println(largestEmptyArea(transform(board)));
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

    static int largestEmptyArea(int[][] matrix){
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