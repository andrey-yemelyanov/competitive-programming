import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10483 The Sum Equals the Product
Problem url: https://uva.onlinejudge.org/external/104/10483.pdf
Author: Andrey Yemelyanov
*/
public class _10483_SumEqualsProduct {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    s.useLocale(Locale.US);
    while(s.hasNext()){
      int from = (int)(s.nextDouble() * 100); int to = (int)(s.nextDouble() * 100);
      getTriplets(from, to).stream()
                           .map(t -> new Double[]{t[0] / 100.0, t[1] / 100.0, t[2] / 100.0})
                           .sorted((Double[] d1, Double[] d2) -> {
                                    int c1 = Double.compare(d1[0] + d1[1] + d1[2], d2[0] + d2[1] + d2[2]);
                                    if(c1 != 0) return c1;
                                    return Double.compare(d1[0], d2[0]);
                                  })
                           .map(t -> String.format(Locale.US,
                                      "%.2f = %.2f + %.2f + %.2f = %.2f * %.2f * %.2f",
                                      t[0] + t[1] + t[2], t[0], t[1], t[2], t[0], t[1], t[2]))
                           .forEach(triplet -> System.out.println(triplet));
    }
  }

  static List<Integer[]> getTriplets(int from, int to){
    List<Integer[]> triplets = new ArrayList<>();
    for(int a = 1; a * a * a < 256000000; a++){
      for(int b = a; a * b * b < 256000000; b++){
        if(a * b != 10000){
          int c = (10000 * (a + b)) / (a * b - 10000);
          if(c >= b){
            int sumUnscaled = a + b + c;
            if(10000 * sumUnscaled == (a * b * c)){
              double sum = sumUnscaled / 100.0;
              if(sumUnscaled >= from && sumUnscaled <= to){
                triplets.add(new Integer[] {a, b, c});
              }
            }
          }
        }
      }
    }
    return triplets;
  }
}
