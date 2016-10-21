package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _10113_ExchangeRates {
   static class ExchangeRate{
      public String product1;
      public int product1Rate;
      public String product2;
      public int product2Rate;
      public ExchangeRate(String product1, int product1Rate, 
         String product2, int product2Rate){
         this.product1 = product1;
         this.product1Rate = product1Rate;
         this.product2 = product2;
         this.product2Rate = product2Rate;
      }
   }
   public static void main(String[] args){
      String data = "! 6 shirt = 15 sock\r\n! 47 underwear = 9 pant\r\n? sock = shirt\r\n? shirt = pant\r\n! 2 sock = 1 underwear\r\n? pant = shirt\r\n.";
      Scanner s = new Scanner(data);   
      Map<String, List<ExchangeRate>> graph = new HashMap<>();
      while(s.hasNext()){
         String[] line = s.nextLine().split("\\s+");
         if(line[0].equals(".")) break;
         if(line[0].equals("!")){
            ExchangeRate er1 = new ExchangeRate(
               line[2], Integer.parseInt(line[1]),
               line[5], Integer.parseInt(line[4]));
            ExchangeRate er2 = new ExchangeRate(
               line[5], Integer.parseInt(line[4]),
               line[2], Integer.parseInt(line[1]));
            if(!graph.containsKey(er1.product1)){
               graph.put(er1.product1, new ArrayList<ExchangeRate>());
            }
            if(!graph.containsKey(er2.product1)){
               graph.put(er2.product1, new ArrayList<ExchangeRate>());
            }
            graph.get(er1.product1).add(er1);
            graph.get(er2.product1).add(er2);
         }else if(line[0].equals("?")){
            String fromProduct = line[1];
            String toProduct = line[3];
            int[] exchangeRate = exchangeRate(graph, fromProduct, toProduct);
            if(exchangeRate == null) System.out.printf("? %s = ? %s\n", fromProduct, toProduct);
            else System.out.printf("%d %s = %d %s\n", exchangeRate[0], fromProduct,
               exchangeRate[1], toProduct);
         }
      }
   }
   
   static int[] exchangeRate(Map<String, List<ExchangeRate>> graph,
      String fromProduct, String toProduct){
      Set<String> visited = new HashSet<>();
      visited.add(fromProduct);
      int[] er = exchangeRate(graph, fromProduct, toProduct, null, visited);
      if(er != null) return reduce(er);
      return er;
   }
   
   static int[] exchangeRate(Map<String, List<ExchangeRate>> graph,
      String fromProduct, String toProduct, int[] exchangeRate, Set<String> visited){
      for(ExchangeRate rate : graph.get(fromProduct)){
         int[] newExchangeRate = new int[] {rate.product1Rate, rate.product2Rate};
         if(exchangeRate != null){
            newExchangeRate = merge(exchangeRate, newExchangeRate);   
         }
         if(rate.product2.equals(toProduct)){
            return newExchangeRate;
         }else if(!visited.contains(rate.product2)){
            visited.add(rate.product2);
            int[] result = exchangeRate(graph, rate.product2, toProduct, 
               newExchangeRate, visited);
            if(result != null) return result;
         }
      }
      return null;
   }
   
   static int[] merge(int[] exchangeRate1, int[] exchangeRate2){
      int rate1 = exchangeRate1[0] * exchangeRate2[0];
      int rate2 = exchangeRate2[1] * exchangeRate1[1];
      return reduce(new int[] {rate1, rate2});
   }
   
   static int[] reduce(int[] exchangeRate){
      int gcd = gcd(exchangeRate[0], exchangeRate[1]);
      return new int[] {exchangeRate[0] / gcd, exchangeRate[1] / gcd};
   }
   
   static int gcd(int n1, int n2){
      while(n1 != n2){
         if(n1 > n2) n1 = n1 - n2;
         else n2 = n2 - n1;
      }
      return n1;
   }
}