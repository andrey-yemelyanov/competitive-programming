package helvidios.cp.ch3.dp;

import static java.lang.Math.*;
import java.util.*;

public class _10684_Jackpot {
    public static void main(String[] args) {
        String data = "5\r\n12 -4\r\n-10 4\r\n9\r\n3\r\n-2 -1 -2\r\n0";
        String data2 = "5\r\n12 -4 \r\n-3 4 \r\n9\r\n0";
        String data3 = "4\r\n0 0 0 0\r\n4\r\n0 0 0 -1\r\n6\r\n12 -4 -10 4 4 9\r\n6\r\n12 -4 -10 4 0 9\r\n5\r\n4 9 -10 -4 12\r\n0";
        Scanner scanner = new Scanner(data3);
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            if(n == 0) break;
            int[] bets = new int[n];
            for(int i = 0; i < n; i++){
                bets[i] = scanner.nextInt();
            }
            int maxSum = getMaxSum(bets);
            if(maxSum <= 0) System.out.println("Losing streak.");
            else System.out.printf("The maximum winning streak is %d.\n", maxSum);
        }
        scanner.close();
    }

    static int getMaxSum(int[] bets){
        int sum = 0; int maxSum = 0;
        for(int i = 0; i < bets.length; i++){
            sum += bets[i];
            maxSum = max(sum, maxSum);
            if(sum < 0) sum = 0;
        }
        return maxSum;
    }

}