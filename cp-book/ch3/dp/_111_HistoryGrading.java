package helvidios.cp.ch3.dp;

import java.util.*;
import static java.lang.Math.*;

public class _111_HistoryGrading {
    public static void main(String[] args) {
        String data = "4\n" +
                "4 2 3 1\n" +
                "1 3 2 4\n" +
                "3 2 1 4\n" +
                "2 3 4 1";
        String data2 = "10\n" +
                "3 1 2 4 9 5 10 6 8 7\n" +
                "1 2 3 4 5 6 7 8 9 10\n" +
                "4 7 2 3 10 6 9 1 5 8\n" +
                "3 1 2 4 9 5 10 6 8 7\n" +
                "2 10 1 3 8 4 9 5 7 6";
        Scanner scanner = new Scanner(data2);
        int n = scanner.nextInt();
        int[] events = new int[n];
        for(int i = 0; i < n; i++){
            events[i] = scanner.nextInt();
        }
        while(scanner.hasNext()){
            int[] response = new int[n];
            for(int i = 0; i < n; i++){
                response[i] = scanner.nextInt();
            }
            System.out.println(lis(transform(events, response)));
        }
        scanner.close();
    }

    static int[] transform(int[] events, int[] response){
        Map<Integer, Integer> orderMap = orderMap(toChronOrder(events));
        int[] chronResponse = toChronOrder(response);
        int[] transformedResponse = new int[chronResponse.length];
        for(int i = 0; i < chronResponse.length; i++){
            transformedResponse[i] = orderMap.get(chronResponse[i]);
        }
        return transformedResponse;
    }

    static int[] toChronOrder(int[] events){
        int[] e = new int[events.length];
        for(int i = 0; i < events.length; i++){
            e[events[i] - 1] = i + 1;
        }
        return e;
    }

    static Map<Integer, Integer> orderMap(int[] events){
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i < events.length; i++){
            m.put(events[i], i + 1);
        }
        return m;

    }

    static int[] memo;
    static int lis(int[] sequence){
        memo = new int[sequence.length];
        int maxLisLength = 0;
        for(int i = sequence.length - 1; i >= 0; i--){
            maxLisLength = max(maxLisLength, lis(sequence, i, sequence[i]));
        }
        return maxLisLength;
    }

    static int lis(int[] sequence, int i, int prev){
        if(memo[i] != 0) return memo[i];
        int maxLisLength = 0;
        for(int j = i - 1; j >= 0; j--){
            if(sequence[j] < prev){
                maxLisLength = max(maxLisLength, lis(sequence, j, sequence[j]));
            }
        }
        maxLisLength++;
        memo[i] = maxLisLength;
        return maxLisLength;
    }
}