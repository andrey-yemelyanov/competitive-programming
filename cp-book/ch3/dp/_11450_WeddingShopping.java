import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11450 Wedding shopping
Problem url: https://uva.onlinejudge.org/external/114/11450.pdf
Author: Andrey Yemelyanov
*/
public class _11450_WeddingShopping {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int M = s.nextInt();
      int nGarments = s.nextInt();
      List<List<Integer>> price = new ArrayList<>();
      for(int i = 0; i < nGarments; i++) price.add(new ArrayList<>());
      for(int i = 0; i < nGarments; i++){
        int nItems = s.nextInt();
        for(int j = 0; j < nItems; j++){
          price.get(i).add(s.nextInt());
        }
      }
      int bestBuy = bestBuy(price, M);
      if(bestBuy == -1) System.out.println("no solution");
      else System.out.println(bestBuy);
    }
  }

  static int bestBuy(List<List<Integer>> price, int M){
    int[][] memo = new int[price.size()][M + 1];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
    return bestBuy(0, M, M, price, memo);
  }

  static int bestBuy(int garment, int moneyAvailable, int M, List<List<Integer>> price, int[][] memo){
    if(moneyAvailable < 0) return -1;
    if(garment == price.size()) return M - moneyAvailable;
    if(memo[garment][moneyAvailable] != -1) return memo[garment][moneyAvailable];
    int bestBuy = -1;
    for(int itemPrice : price.get(garment)){
      bestBuy = max(bestBuy, bestBuy(garment + 1, moneyAvailable - itemPrice, M, price, memo));
    }
    return memo[garment][moneyAvailable] = bestBuy;
  }
}
