package helvidios.cp.ch3.dp;

import java.util.*;
import java.util.stream.*;

public class _10664_Luggage {
    public static void main(String[] args) {
        String data = "3\r\n1 2 1 2 1\r\n2 3 4 1 2 5 10 50 3 50\r\n3 5 2 7 1 7 5 2 8 9 1 25 15 8 3 1 38 45 8 1";
        Scanner scanner = new Scanner(data);
        int nTests = scanner.nextInt();
        scanner.nextLine();
        while(nTests-- > 0){
            String[] ints = scanner.nextLine().split("\\s+");
            List<Integer> luggage = Stream.of(ints)
                                          .map(Integer::parseInt)
                                          .collect(Collectors.toList());
            int totalWeight = luggage.stream()
                                     .mapToInt(i -> i)
                                     .sum();
            boolean result = false;
            if(totalWeight % 2 == 0){
                result = subsetSumPossible(0, luggage, totalWeight / 2);
            }
            if(result) System.out.println("YES");
            else System.out.println("NO");
        }
        scanner.close();
    }

    static boolean subsetSumPossible(int i, List<Integer> luggage, int targetSum){
        if(targetSum == 0) return true;
        if(i == luggage.size()) return false;
        if(luggage.get(i) <= targetSum &&
                subsetSumPossible(i + 1, luggage, targetSum - luggage.get(i)))
            return true;
        return subsetSumPossible(i + 1, luggage, targetSum);
    }
}
