import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 166 Making Change
Problem url: https://uva.onlinejudge.org/external/1/166.pdf
Author: Andrey Yemelyanov
*/
public class _166_MakingChange {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    s.useLocale(Locale.US);
    while(s.hasNext()){
      int[] coins = new int[6];
      int nCoins = 0;
      for(int i = 0; i < 6; i++){
        coins[i] = s.nextInt();
        nCoins += coins[i];
      }
      if(nCoins == 0) break;
      int amountToPay = (int)round(s.nextDouble() * 100);
      int[] coinsExpanded = new int[nCoins];
      int k = 0;
      for(int i = 0; i < denominations.length; i++){
        for(int j = 0; j < coins[i]; j++){
          coinsExpanded[k++] = denominations[i];
        }
      }
      //System.out.println(Arrays.toString(coinsExpanded));
      System.out.printf("%3d\n", pay(amountToPay, coinsExpanded));
    }
  }

  static int pay(int amountToPay, int[] coins){
    int totalMoney = Arrays.stream(coins).sum();
    int minCoinsExchanged = INF;
    for(int amount = amountToPay; amount <= totalMoney; amount += 5){
      int minCoinsToPay = minCoinsLimited(coins, amount);
      if(minCoinsToPay == INF) continue;
      int change = amount - amountToPay;
      int minCoinsInChange = minCoinsUnlimited(denominations, change);
      if(minCoinsInChange == INF) continue;
      minCoinsExchanged = min(minCoinsExchanged, minCoinsToPay + minCoinsInChange);
    }
    return minCoinsExchanged;
  }

  static final int[] denominations = new int[] {5, 10, 20, 50, 100, 200};
  static int minCoinsUnlimited(int[] denominations, int targetAmount){
    int[] memo = new int[targetAmount + 1];
    Arrays.fill(memo, -1);
    return minCoinsUnlimited(denominations, targetAmount, memo);
  }
  static int minCoinsUnlimited(int[] denominations, int targetAmount, int[] memo){
    if(targetAmount == 0) return 0;
    if(targetAmount < 0) return INF;
    if(memo[targetAmount] != -1) return memo[targetAmount];
    int nCoins = INF;
    for(int i = 0; i < denominations.length; i++){
      nCoins = min(nCoins, 1 + minCoinsUnlimited(denominations, targetAmount - denominations[i], memo));
    }
    return memo[targetAmount] = nCoins;
  }

  static int minCoinsLimited(int[] coins, int targetAmount){
    int[][] memo = new int[coins.length][targetAmount + 1];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
    return minCoinsLimited(coins, targetAmount, 0, memo);
  }
  static final int INF = 10000000;
  static int minCoinsLimited(int[] coins, int targetAmount, int i, int[][] memo){
    if(targetAmount == 0) return 0;
    if(targetAmount < 0) return INF;
    if(i == coins.length) return INF;
    if(memo[i][targetAmount] != -1) return memo[i][targetAmount];
    return memo[i][targetAmount] = min(1 + minCoinsLimited(coins, targetAmount - coins[i], i + 1, memo),
                                           minCoinsLimited(coins, targetAmount, i + 1, memo));
  }
}
