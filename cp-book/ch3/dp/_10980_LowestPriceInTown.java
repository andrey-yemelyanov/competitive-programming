import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10980 Lowest Price in Town
Problem url: https://uva.onlinejudge.org/external/109/10980.pdf
Author: Andrey Yemelyanov
*/
public class _10980_LowestPriceInTown {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    s.useLocale(Locale.US);
    int c = 1;
    while(s.hasNext()){
      int regPrice = (int)round(s.nextDouble() * 100);
      int M = s.nextInt();
      Offer[] offers = new Offer[M + 1];
      offers[0] = new Offer(regPrice, 1);
      for(int i = 1; i < offers.length; i++){
        int amount = s.nextInt();
        int price = (int)round(s.nextDouble() * 100);
        offers[i] = new Offer(price, amount);
      }
      int[][] memo = new int[offers.length][101];
      System.out.printf("Case %d:\n", c++);
      while(s.hasNextInt()){
        int nBottles = s.nextInt();
        int bestBuy = buy(offers, nBottles, memo);
        System.out.printf(Locale.US, "Buy %d for $%.2f\n", nBottles, bestBuy / 100.0);
      }
    }
  }

  static class Offer{
    public int price;
    public int amount;
    public Offer(int price, int amount){
      this.price = price;
      this.amount = amount;
    }
  }

  static int buy(Offer[] offers, int nBottles, int[][] memo){
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
    return buy(0, nBottles, offers, memo);
  }

  static final int INF = 100000000;
  static int buy(int i, int nBottles, Offer[] offers, int[][] memo){
    if(nBottles <= 0) return 0;
    if(i == offers.length && nBottles > 0) return INF;
    if(memo[i][nBottles] != -1) return memo[i][nBottles];
    return memo[i][nBottles] = min(
      buy(i + 1, nBottles, offers, memo),
      buy(i, nBottles - offers[i].amount, offers, memo) + offers[i].price
    );
  }
}
