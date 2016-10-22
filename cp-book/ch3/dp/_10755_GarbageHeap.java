package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _10755_GarbageHeap {
    public static void main(String[] args) {
        String data = "1\n" +
                "2 2 2\n" +
                "-1 2 0 -3 -2 -1 1 5";
        String data2 = "5\n" +
                "\n" +
                "2 2 2\n" +
                "-1 2 0 -3 -2 -1 1 5\n" +
                "\n" +
                "1 1 1 \n" +
                "-5 \n" +
                "\n" +
                "2 2 2\n" +
                "-1 -1 -1 -1 5 -1 -1 -1\n" +
                "\n" +
                "2 2 2\n" +
                "-1 -1 -1 -1 -1 -1 5 -1\n" +
                "\n" +
                "2 2 2\n" +
                "20 -1 -1 -1 -1 5 -1 -1";
        Scanner scanner = new Scanner(data2);
        int nTestCases = scanner.nextInt();
        while(nTestCases-- > 0){
            int x = scanner.nextInt(); int y = scanner.nextInt(); int z = scanner.nextInt();
            long[][][] heap = new long[x][y][z];
            for(int i = 0; i < x; i++){
                for(int j = 0; j < y; j++){
                    for(int k = 0; k < z; k++){
                        heap[i][j][k] = scanner.nextLong();
                    }
                }
            }
            System.out.println(maxHeapValue(heap));
            if(nTestCases > 0) System.out.println();
        }
        scanner.close();
    }

    static long maxSum1D(long[] values) {
        long sum = 0;
        long maxSum = Long.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
            maxSum = max(sum, maxSum);
            if (sum < 0) sum = 0;
        }
        return maxSum;
    }

    static long maxHeapValue(long[][][] heap){
        for(int k = 0; k < heap.length; k++){ // transform each 2d slice of the heap
            heap[k] = transform(heap[k]);
        }

        long maxValue = Long.MIN_VALUE;
        for(int i1 = 0; i1 < heap[0].length; i1++) for(int j1 = 0; j1 < heap[0][0].length; j1++) // start coordinade
            for(int i2 = i1; i2 < heap[0].length; i2++) for(int j2 = j1; j2 < heap[0][0].length; j2++) // end coordinate
            {
                long[] values = new long[heap.length];
                for(int k = 0; k < heap.length; k++){ // get sum for every 2d slice
                    values[k] = rectSum(heap[k], i1, j1, i2, j2);
                }
                maxValue = Math.max(maxValue, maxSum1D(values));
            }
        return maxValue;
    }

    static long rectSum(long[][] matrix, int i1, int j1, int i2, int j2){
        long subRect = matrix[i2][j2];
        if(i1 > 0) subRect -= matrix[i1 - 1][j2];
        if(j1 > 0) subRect -= matrix[i2][j1 - 1];
        if(i1 > 0 && j1 > 0) subRect += matrix[i1 - 1][j1 - 1];
        return subRect;

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
