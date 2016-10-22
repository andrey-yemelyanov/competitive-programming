package helvidios.cp.ch3.dp;

import java.util.stream.*;
import java.util.*;
import static java.lang.Math.*;
public class _497_StrategicDefenseInitiative {
    public static void main(String[] args){
        String data = "2\n\n1\r\n6\r\n2\r\n3\r\n5\n\n1\r\n6\r\n2\r\n3\r\n5";
        String data2 = "1\r\n\r\n935\r\n323\r\n314\r\n59\r\n484\r\n455\r\n939\r\n783\r\n567\r\n159\r\n176\r\n633\r\n327\r\n755\r\n236\r\n375\r\n743\r\n804\r\n916\r\n192\r\n771\r\n547\r\n311\r\n966\r\n370\r\n876\r\n100\r\n584\r\n337\r\n617\r\n571\r\n623\r\n940\r\n236\r\n33\r\n775\r\n43\r\n972\r\n557\r\n961\r\n130\r\n84\r\n593\r\n808\r\n838\r\n181\r\n535\r\n932\r\n984\r\n450\r\n124\r\n107\r\n996\r\n786\r\n424\r\n717\r\n661\r\n524\r\n300\r\n350\r\n492\r\n222\r\n972\r\n431\r\n810\r\n5\r\n557\r\n204\r\n328\r\n113\r\n164\r\n809\r\n548\r\n108\r\n968\r\n386\r\n288\r\n502\r\n317\r\n624\r\n951\r\n792\r\n82\r\n298\r\n577\r\n505\r\n15\r\n590\r\n380\r\n666\r\n939\r\n872\r\n240\r\n262\r\n654\r\n49\r\n266\r\n563\r\n252\r\n593\r\n675\r\n767\r\n753\r\n223\r\n874\r\n721\r\n960\r\n514\r\n222\r\n628\r\n489\r\n525\r\n420\r\n570\r\n822\r\n348\r\n74\r\n188\r\n289\r\n806\r\n854\r\n227\r\n29\r\n445\r\n489\r\n682\r\n493\r\n106\r\n244\r\n96\r\n51\r\n271\r\n214\r\n803\r\n845\r\n439\r\n523\r\n156\r\n952\r\n97\r\n783\r\n440\r\n621\r\n554\r\n9\r\n794\r\n254\r\n435\r\n334\r\n542\r\n592\r\n187\r\n121\r\n620\r\n983\r\n609\r\n653\r\n475\r\n66\r\n249\r\n922\r\n116\r\n871\r\n135\r\n271\r\n67\r\n573\r\n793\r\n222\r\n877\r\n241\r\n356\r\n316\r\n213\r\n910\r\n677\r\n7\r\n163\r\n463\r\n340\r\n56\r\n54\r\n878\r\n176\r\n25\r\n860\r\n136\r\n677";
        Scanner s = new Scanner(data);
        int nTestCases = s.nextInt();
        while(nTestCases-- > 0){
            List<Integer> list = new ArrayList<Integer>();
            String line = null;
            while(s.hasNext() && (line = s.nextLine()).isEmpty());
            list.add(Integer.parseInt(line));
            while(s.hasNext() && !(line = s.nextLine()).isEmpty()){
                list.add(Integer.parseInt(line));
            }

            int[] sequence = list.stream()
                    .mapToInt(i -> i)
                    .toArray();
            //System.out.println(toString(sequence));
            int[] solution = getSolution(sequence);
            System.out.printf("Max hits: %d\n", solution.length);
            System.out.println(toString(solution));
            if(nTestCases > 0) System.out.println();
        }
    }

    static int[] memo;
    static int lis(int[] sequence){
        memo = new int[sequence.length];
        memo[0] = 1;
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

    static int[] getSolution(int[] sequence){
        int lis = lis(sequence);
        int[] solution = new int[lis];
        int s = solution.length - 1;
        for(int i = sequence.length - 1; i >= 0; i--){
            if(memo[i] == lis){
                solution[s--] = sequence[i];
                lis--;
            }
            if(s < 0) break;
        }
        return solution;
    }

    static String toString(int[] sequence){
        return Arrays.stream(sequence)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining("\n"));
    }
}