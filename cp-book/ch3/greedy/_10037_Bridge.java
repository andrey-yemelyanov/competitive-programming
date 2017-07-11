import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10037 Bridge
Problem url: https://uva.onlinejudge.org/external/100/10037.pdf
Author: Andrey Yemelyanov
*/
public class _10037_Bridge {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    s.nextLine();
    while(nTests-- > 0){
      s.nextLine();
      int n = s.nextInt();
      int[] people = new int[n];
      for(int i = 0; i < n; i++) people[i] = s.nextInt();
      List<Integer[]> solution = new ArrayList<>();
      System.out.println(crossBridge(people, solution));
      solution.stream()
              .map(arr -> Arrays.stream(arr).map(Object::toString).collect(Collectors.joining(" ")))
              .forEach(i -> System.out.println(i));
      if(nTests > 0) System.out.println();
    }
  }

  static int crossBridge(int[] people, List<Integer[]> solution){
    return crossBridge(people, people.length - 1, solution);
  }

  static int crossBridge(int[] people, int i, List<Integer[]> solution){
    int nPeopleLeft = i + 1;
    if(nPeopleLeft == 1){
      solution.add(new Integer[] {people[i]});
      return people[i];
    }
    if(nPeopleLeft == 2){
      solution.add(new Integer[] {people[0], people[1]});
      return people[i];
    }
    if(nPeopleLeft == 3) {
      solution.add(new Integer[] {people[0], people[1]});
      solution.add(new Integer[] {people[0]});
      solution.add(new Integer[] {people[0], people[2]});
      return people[1] + people[0] + people[2];
    }
    int strategy1 = people[i - 1] + people[0] + people[i] + people[0];
    int strategy2 = people[1] + people[0] + people[i] + people[1];
    if(strategy1 < strategy2){
      solution.add(new Integer[] {people[0], people[i - 1]});
      solution.add(new Integer[] {people[0]});
      solution.add(new Integer[] {people[0], people[i]});
      solution.add(new Integer[] {people[0]});
      return strategy1 + crossBridge(people, i - 2, solution);
    }else{
      solution.add(new Integer[] {people[0], people[1]});
      solution.add(new Integer[] {people[0]});
      solution.add(new Integer[] {people[i - 1], people[i]});
      solution.add(new Integer[] {people[1]});
      return strategy2 + crossBridge(people, i - 2, solution);
    }
  }
}
