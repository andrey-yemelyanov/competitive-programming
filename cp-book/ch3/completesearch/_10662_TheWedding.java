import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10662 The Wedding
Problem url: https://uva.onlinejudge.org/external/106/10662.pdf
Author: Andrey Yemelyanov
*/
public class _10662_TheWedding {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int T = s.nextInt(); int R = s.nextInt(); int H = s.nextInt();

      int[] agencyPrices = new int[T];
      boolean[][] agencyToRestaurant = new boolean[T][R];
      for(int agency = 0; agency < T; agency++){
        agencyPrices[agency] = s.nextInt();
        for(int restaurant = 0; restaurant < R; restaurant++){
          agencyToRestaurant[agency][restaurant] = (s.nextInt() == 0);
        }
      }

      int[] restaurantPrices = new int[R];
      boolean[][] restaurantToHotel = new boolean[R][H];
      for(int restaurant = 0; restaurant < R; restaurant++){
        restaurantPrices[restaurant] = s.nextInt();
        for(int hotel = 0; hotel < H; hotel++){
          restaurantToHotel[restaurant][hotel] = (s.nextInt() == 0);
        }
      }

      int[] hotelPrices = new int[H];
      boolean[][] hotelToAgency = new boolean[H][T];
      for(int hotel = 0; hotel < H; hotel++){
        hotelPrices[hotel] = s.nextInt();
        for(int agency = 0; agency < T; agency++){
          hotelToAgency[hotel][agency] = (s.nextInt() == 0);
        }
      }

      int[] best = getBestCombination(agencyPrices, agencyToRestaurant,
                                      restaurantPrices, restaurantToHotel,
                                      hotelPrices, hotelToAgency);
      if(best[0] == -1){
        System.out.println("Don't get married!");
      }else{
          System.out.printf("%d %d %d:%d\n", best[0], best[1], best[2], best[3]);
      }
    }
  }

  static int[] getBestCombination(int[] agencyPrices, boolean[][] agencyToRestaurant,
                                  int[] restaurantPrices, boolean[][] restaurantToHotel,
                                  int[] hotelPrices, boolean[][] hotelToAgency){
    int bestAgency = -1;
    int bestRestaurant = -1;
    int bestHotel = -1;
    int bestPrice = Integer.MAX_VALUE;
    for(int agency = 0; agency < agencyPrices.length; agency++){
      for(int restaurant = 0; restaurant < restaurantPrices.length; restaurant++){
        if(agencyToRestaurant[agency][restaurant]){
          for(int hotel = 0; hotel < hotelPrices.length; hotel++){
            if(restaurantToHotel[restaurant][hotel] && hotelToAgency[hotel][agency]){
              int totalPrice = agencyPrices[agency] + restaurantPrices[restaurant] + hotelPrices[hotel];
              if(totalPrice < bestPrice){
                bestPrice = totalPrice;
                bestAgency = agency;
                bestRestaurant = restaurant;
                bestHotel = hotel;
              }
            }
          }
        }
      }
    }
    return new int[] {bestAgency, bestRestaurant, bestHotel, bestPrice};
  }
}
