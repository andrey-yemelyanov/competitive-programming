package helvidios.cp.ch3.dp;

import java.util.*;
import java.util.stream.*;

public class _10261_FerryLoading {
    public static void main(String[] args) {
        // data = 6
        String data = "2\r\n\r\n50\r\n2500\r\n3000\r\n1000\r\n1000\r\n1500\r\n700\r\n800\r\n0\r\n\r\n50\r\n2500\r\n3000\r\n1000\r\n1000\r\n1500\r\n700\r\n800\r\n0";
        // data2 = 2
        String data2 = "1\r\n\r\n90\r\n4167\r\n6071\r\n5239\r\n2440\r\n1381\r\n2446\r\n2502\r\n3324\r\n7985\r\n2749\r\n1693\r\n8172\r\n3128\r\n1088\r\n1026\r\n5850\r\n408\r\n7692\r\n1130\r\n3087\r\n7157\r\n2922\r\n1599\r\n2992\r\n7558\r\n4696\r\n8821\r\n4818\r\n1558\r\n6123\r\n9833\r\n9273\r\n5841\r\n8719\r\n1712\r\n7122\r\n1165\r\n7762\r\n4094\r\n9050\r\n510\r\n9335\r\n969\r\n3539\r\n423\r\n1895\r\n3036\r\n4379\r\n3234\r\n4066\r\n7366\r\n391\r\n635\r\n8865\r\n6931\r\n8093\r\n3560\r\n9400\r\n6558\r\n8667\r\n9170\r\n6390\r\n7939\r\n8658\r\n5109\r\n9552\r\n9428\r\n9822\r\n7313\r\n3521\r\n8871\r\n1471\r\n2855\r\n3487\r\n8558\r\n3178\r\n8931\r\n5241\r\n7457\r\n2164\r\n2954\r\n8471\r\n6103\r\n3489\r\n7335\r\n3034\r\n1581\r\n4543\r\n6081\r\n1786\r\n3209\r\n8898\r\n8076\r\n4795\r\n7555\r\n6832\r\n7994\r\n6982\r\n6653\r\n5307\r\n4150\r\n9172\r\n425\r\n6906\r\n2658\r\n8883\r\n3731\r\n1588\r\n7771\r\n4836\r\n7301\r\n724\r\n3306\r\n3403\r\n4113\r\n4288\r\n9985\r\n9242\r\n8731\r\n6065\r\n1027\r\n5587\r\n8610\r\n2750\r\n382\r\n9813\r\n3230\r\n2023\r\n6794\r\n9783\r\n7230\r\n944\r\n2701\r\n1302\r\n1497\r\n8908\r\n3832\r\n5128\r\n4143\r\n1602\r\n9864\r\n1443\r\n2226\r\n6817\r\n4747\r\n9887\r\n1105\r\n4731\r\n9128\r\n3483\r\n4444\r\n3802\r\n2718\r\n3053\r\n6453\r\n3000\r\n6513\r\n9583\r\n8571\r\n3307\r\n3112\r\n5801\r\n7799\r\n9362\r\n750\r\n9196\r\n8269\r\n4483\r\n7971\r\n6059\r\n5985\r\n7835\r\n7403\r\n1859\r\n8299\r\n2149\r\n1745\r\n9304\r\n527\r\n4521\r\n2787\r\n4871\r\n1970\r\n5405\r\n1572\r\n0";
        String data3 = "15\r\n\r\n20\r\n101\r\n207\r\n155\r\n143\r\n512\r\n335\r\n108\r\n977\r\n567\r\n201\r\n188\r\n311\r\n112\r\n0\r\n\r\n6 \r\n200 \r\n200 \r\n400 \r\n400 \r\n0\r\n\r\n1\r\n200\r\n100\r\n0\r\n\r\n1\r\n100\r\n200\r\n0\r\n\r\n1\r\n100\r\n100\r\n0\r\n\r\n1\r\n10\r\n20\r\n30\r\n40\r\n50\r\n0\r\n\r\n50\r\n2500\r\n3000\r\n1000\r\n1000\r\n1500\r\n700\r\n800\r\n0\r\n\r\n1\r\n10\r\n20\r\n30\r\n40\r\n50\r\n60\r\n0\r\n\r\n1\r\n10\r\n20\r\n30\r\n40\r\n40\r\n60\r\n0\r\n\r\n15\r\n100\r\n100\r\n200\r\n200\r\n300\r\n300\r\n400\r\n400\r\n500\r\n500\r\n0\r\n\r\n1\r\n200\r\n0\r\n\r\n22\r\n1200\r\n500\r\n2000\r\n450\r\n200\r\n0\r\n\r\n1\r\n10\r\n7\r\n2\r\n11\r\n13\r\n34\r\n22\r\n45\r\n1\r\n2\r\n10\r\n4\r\n50\r\n0\r\n\r\n1\r\n10\r\n7\r\n2\r\n11\r\n13\r\n34\r\n22\r\n45\r\n1\r\n2\r\n10\r\n4\r\n39\r\n0\r\n\r\n100\r\n\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n100\r\n0";
        String data4 = "1\n50\r\n2500\r\n3000\r\n1000\r\n1000\r\n1500\r\n700\r\n800\r\n0";
        Scanner scanner = new Scanner(data3);
        int nTestCases = scanner.nextInt();
        while(nTestCases-- > 0){
            int ferryLength = scanner.nextInt() * 100;
            List<Integer> queue = new ArrayList<>();
            int car;
            while((car = scanner.nextInt()) != 0){
                queue.add(car);
            }
            List<String> arrangement = loadFerry(queue, ferryLength);
            System.out.println(arrangement.size());
            System.out.println(toString(arrangement));
            if(nTestCases > 0 && arrangement.size() > 0) System.out.println();
        }
        scanner.close();
    }

    static String toString(List<String> arrangement){
        return arrangement.stream()
                .collect(Collectors.joining("\n"));
    }

    static final String port = "port";
    static final String starboard = "starboard";
    static int[][] memo;
    static int loadFerry(int i, List<Integer> queue, int left, int right){
        if(i == queue.size()) return 0;
        int carLength = queue.get(i);
        if(memo[i][left] != 0) return memo[i][left];

        int maxLeft = 0;
        int maxRight = 0;
        if(carLength <= left){
            maxLeft = 1 + loadFerry(i + 1, queue, left - carLength, right);
        }
        if(carLength <= right){
            maxRight = 1 + loadFerry(i + 1, queue, left, right - carLength);
        }
        int max = Math.max(maxLeft, maxRight);
        memo[i][left] = max;
        //System.out.printf("Memoized state i=%d, left=%d, right=%d, value=%d\n",
        //		i, left, right, memo[i][left]);

        return max;
    }

    static List<String> loadFerry(List<Integer> queue, int ferryLength){
        List<String> arrangement = new ArrayList<>();
        memo = new int[queue.size() + 1][ferryLength + 1];
        int nCars = loadFerry(0, queue, ferryLength, ferryLength);
        //System.out.println(nCars);
        int prevLeft = ferryLength; int prevRight = ferryLength;
        int totalCarLength = 0;
        for(int i = 1; i <= nCars; i++){
            totalCarLength += queue.get(i - 1);
            int left = 0; int right = 0;
            for(int j = 0; j < memo[i].length; j++){
                right = 2 * ferryLength - (totalCarLength + j);
                if(memo[i][j] == nCars - i && (j == prevLeft || right == prevRight)){
                    left = j;
                    break;
                }
            }

            //System.out.printf("Found state i=%d, left=%d, right=%d, value=%d\n",
            //	i, left, right, memo[i][left]);

            if(left < prevLeft) arrangement.add(port);
            else if(right < prevRight) arrangement.add(starboard);
            prevLeft = left;
            prevRight = right;
        }
        return arrangement;
    }
}