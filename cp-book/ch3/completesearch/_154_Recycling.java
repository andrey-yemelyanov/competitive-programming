import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 154 Recycling
Problem url: https://uva.onlinejudge.org/external/1/154.pdf
Author: Andrey Yemelyanov
*/
public class _154_Recycling {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Map<String, String>> allocations = new ArrayList<>();
    while(s.hasNext()){
      String line = s.nextLine();
      if(line.contains("#")) break;
      if(!line.startsWith("e")){
        Map<String, String> allocation = new HashMap<>();
        Arrays.stream(line.split(","))
              .map(mapping -> mapping.split("/"))
              .forEach(i -> allocation.put(i[0], i[1]));
        allocations.add(allocation);
      }else{
        //System.out.println(allocations);
        System.out.println(findBestCity(allocations) + 1);
        allocations = new ArrayList<>();
      }
    }
  }

  static int findBestCity(List<Map<String, String>> allocations){
    int minCost = Integer.MAX_VALUE;
    int bestCity = 0;
    for(int city = 0; city < allocations.size(); city++){
      Map<String, String> candidateAllocation = allocations.get(city);
      int totalAdoptionCost = 0;
      for(int city2 = 0; city2 < allocations.size(); city2++){
        totalAdoptionCost += diff(candidateAllocation, allocations.get(city2));
      }
      if(totalAdoptionCost < minCost){
        minCost = totalAdoptionCost;
        bestCity = city;
      }
    }
    return bestCity;
  }

  static final String[] colors = new String[] {"r", "o", "y", "g", "b"};
  static int diff(Map<String, String> allocation1, Map<String, String> allocation2){
    int nDiffs = 0;
    for(String color : colors){
      if(!allocation1.get(color).equals(allocation2.get(color))) nDiffs++;
    }
    return nDiffs;
  }
}
