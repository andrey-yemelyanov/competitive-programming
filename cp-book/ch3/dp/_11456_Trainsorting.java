package helvidios.cp.ch3.dp;

import java.util.*;
import static java.lang.Math.*;

public class _11456_Trainsorting {
    public static void main(String[] args) {
        String data = "2\n3\n1\n2\n3\n6\n5\n7\n2\n1\n3\n8";
        Scanner scanner = new Scanner(data);
        int nTestCases = scanner.nextInt();
        while(nTestCases-- > 0){
            int[] sequence = new int[scanner.nextInt()];
            for(int i = sequence.length - 1; i >= 0; i--){
                sequence[i] = scanner.nextInt();
            }
            System.out.println(maxTrainLength(sequence));
        }
        scanner.close();
    }

    static int[] memoLis;
    static int[] memoLds;
    static int maxTrainLength(int[] sequence){
        memoLis = new int[sequence.length];
        memoLds = new int[sequence.length];
        int maxTrainLength = 0;
        for(int i = sequence.length - 1; i >= 0; i--){
            maxTrainLength = max(maxTrainLength,
                    (lis(sequence, i, sequence[i]) + lds(sequence, i, sequence[i]) - 1));
        }
        return maxTrainLength;
    }

    static int lis(int[] sequence, int i, int prev){
        if(memoLis[i] != 0) return memoLis[i];
        int maxLisLength = 0;
        for(int j = i - 1; j >= 0; j--){
            if(sequence[j] < prev){
                maxLisLength = max(maxLisLength, lis(sequence, j, sequence[j]));
            }
        }
        maxLisLength++;
        memoLis[i] = maxLisLength;
        return maxLisLength;
    }

    static int lds(int[] sequence, int i, int prev){
        if(memoLds[i] != 0) return memoLds[i];
        int maxLdsLength = 0;
        for(int j = i - 1; j >= 0; j--){
            if(sequence[j] > prev){
                maxLdsLength = max(maxLdsLength, lds(sequence, j, sequence[j]));
            }
        }
        maxLdsLength++;
        memoLds[i] = maxLdsLength;
        return maxLdsLength;
    }
}