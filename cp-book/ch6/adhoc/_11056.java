import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11056 Formula 1
Problem url: https://uva.onlinejudge.org/external/110/11056.pdf
Author: Andrey Yemelyanov
*/
public class _11056 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    while(s.hasNext()){
      int nRacers = s.nextInt();
      SortedMap<Integer, List<String>> map = new TreeMap<>();
      for(int i = 0; i < nRacers; i++){
        String racerName = s.next();
        s.next();
        int minutes = s.nextInt(); s.next();
        int seconds = s.nextInt(); s.next();
        int ms = s.nextInt(); s.next();
        int time = minutes * 60 * 1000 + seconds * 1000 + ms;
        map.putIfAbsent(time, new ArrayList<>());
        map.get(time).add(racerName);
      }
      String[] startingGrid = new String[nRacers];
      int i = 0;
      for(int key : map.keySet()){
        List<String> racers = map.get(key);
        Collections.sort(racers, new Comparator<String>(){
          @Override
          public int compare(String s1, String s2){
            return s1.toLowerCase().compareTo(s2.toLowerCase());
          }
        });
        for(String racer : racers){
          startingGrid[i++] = racer;
        }
      }
      int row = 1;
      for(int k = 0; k < startingGrid.length; k += 2){
        sb.append(String.format("Row %d\n", row++));
        sb.append(startingGrid[k]);
        if(k + 1 < startingGrid.length){
          sb.append("\n");
          sb.append(startingGrid[k + 1]);
        }
        if(k < startingGrid.length) sb.append("\n");
      }
      sb.append("\n");
    }
    System.out.print(sb.toString());
  }
}
