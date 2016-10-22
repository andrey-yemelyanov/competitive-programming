package helvidios.cp.ch3.dp;

import java.math.BigInteger;
import java.util.*;

public class _787_MaximumSubSequenceProduct {
    public static void main(String[] args) {
        String data = "1 2 3 -999999\n" +
                "-5 -2 2 -30 -999999\n" +
                "-8 -999999\n" +
                "-1 0 -2 -999999";
        Scanner scanner = new Scanner(data);
        while(scanner.hasNext()){
            List<Integer> list = new ArrayList<>();
            while(true){
                int number = scanner.nextInt();
                if(number == -999999) break;
                list.add(number);
            }
            System.out.println(getMaxSubSequenceProduct(list));
        }

        scanner.close();
    }

    public static BigInteger getMaxSubSequenceProduct(List<Integer> list){
        boolean allNonPositive = list.stream()
                                     .allMatch(i -> i <= 0);
        if(allNonPositive && !hasAdjacentNonZeroElements(list)) {
            return BigInteger.valueOf(list.stream()
                                          .max(Integer::compareTo)
                                          .get());
        }else {
            BigInteger maxProduct = BigInteger.ZERO;
            BigInteger positiveProduct = BigInteger.ONE;
            BigInteger negativeProduct = BigInteger.ONE;
            for (int i = 0; i < list.size(); i++) {
                int element = list.get(i);
                if (element > 0) {
                    positiveProduct = positiveProduct.multiply(BigInteger.valueOf(element));
                    negativeProduct = BigInteger.ONE.min(negativeProduct.multiply(
                            BigInteger.valueOf(element)));
                } else if (element == 0) {
                    positiveProduct = BigInteger.ONE;
                    negativeProduct = BigInteger.ONE;
                } else {
                    BigInteger prevPositiveProduct = positiveProduct;
                    positiveProduct = BigInteger.ONE.max(negativeProduct.multiply(
                            BigInteger.valueOf(element)));
                    negativeProduct = prevPositiveProduct.multiply(BigInteger.valueOf(element));
                }
                maxProduct = maxProduct.max(positiveProduct);
            }
            return maxProduct;
        }
    }

    private static boolean hasAdjacentNonZeroElements(List<Integer> list){
        for(int i = 0; i < list.size() - 1; i++){
            if(list.get(i) != 0 && list.get(i + 1) != 0){
                return true;
            }
        }
        return false;
    }
}
