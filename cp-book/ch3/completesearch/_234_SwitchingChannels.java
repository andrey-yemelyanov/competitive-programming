import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 234 Switching Channels
Problem url: https://uva.onlinejudge.org/external/2/234.pdf
Author: Andrey Yemelyanov
*/
public class _234_SwitchingChannels {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int dataSet = 1;
    while(s.hasNext()){
      int p = s.nextInt();
      if(p == 0) break;
      int[] programs = new int[p];
      for(int i = 0; i < p; i++){
        programs[i] = s.nextInt();
      }
      SortedMap<Integer, List<Integer>> alignmentPoints = new TreeMap<>();
      int a = s.nextInt();
      for(int i = 0; i < a; i++){
        int importanceLevel = s.nextInt();
        int alignmentPoint = s.nextInt();
        alignmentPoints.putIfAbsent(importanceLevel, new ArrayList<>());
        alignmentPoints.get(importanceLevel).add(alignmentPoint);
      }
      int[] bestProgramOrdering = getBestProgramOrdering(programs, alignmentPoints);
      int totalMissTime = getTotalMissTime(bestProgramOrdering, alignmentPoints)
                          .values()
                          .stream()
                          .reduce(0, Integer::sum);
      System.out.printf("Data set %d\n", dataSet++);
      System.out.printf("Order: %s\n", Arrays.stream(bestProgramOrdering)
                                             .mapToObj(Integer::toString)
                                             .collect(Collectors.joining(" ")));
      System.out.printf("Error: %d\n", totalMissTime);
    }
  }

  static int[] getBestProgramOrdering(int[] programs, SortedMap<Integer, List<Integer>> alignmentPoints){
    List<int[]> programPermutations = getProgramPermutations(programs);
    int[] bestPermutation = programPermutations.get(0);
    Map<Integer, Integer> bestTotalMissTime = getTotalMissTime(bestPermutation, alignmentPoints);
    for(int i = 1; i < programPermutations.size(); i++){
      int[] permutation = programPermutations.get(i);
      Map<Integer, Integer> totalMissTime = getTotalMissTime(permutation, alignmentPoints);
      if(compare(totalMissTime, bestTotalMissTime) < 0){
        bestTotalMissTime = totalMissTime;
        bestPermutation = permutation;
      }
    }
    return bestPermutation;
  }

  static int compare(Map<Integer, Integer> totalMissTime1, Map<Integer, Integer> totalMissTime2){
    for(int importanceLevel : totalMissTime1.keySet()){
      int compare = Integer.compare(totalMissTime1.get(importanceLevel), totalMissTime2.get(importanceLevel));
      if(compare != 0) return compare;
    }
    return 0;
  }

  static Map<Integer, Integer> getTotalMissTime(int[] programs, SortedMap<Integer, List<Integer>> alignmentPoints){
    Map<Integer, Integer> m = new HashMap<>();
    for(int importanceLevel : alignmentPoints.keySet()){
      m.put(importanceLevel, getTotalMissTimeAtImportanceLevel(programs, alignmentPoints.get(importanceLevel)));
    }
    return m;
  }

  static int getTotalMissTimeAtImportanceLevel(int[] programs, List<Integer> alignmentPoints){
    int totalMissTime = 0;
    for(int alignmentPoint : alignmentPoints){
      totalMissTime += missTimeForAlignmentPoint(programs, alignmentPoint);
    }
    return totalMissTime;
  }

  static int missTimeForAlignmentPoint(int[] programs, int alignmentPoint){
    int[] sum = new int[programs.length];
    sum[0] = programs[0];
    for(int i = 1; i < programs.length; i++){
      sum[i] = sum[i - 1] + programs[i];
    }
    int diff = Integer.MAX_VALUE;
    for(int i = 0; i < sum.length; i++){
      diff = min(diff, abs(alignmentPoint - sum[i]));
    }
    return diff;
  }

  static List<int[]> getProgramPermutations(int[] programs){
    List<int[]> list = new ArrayList<>();
    permutePrograms(programs, 0, list);
    return list;
  }

  static void permutePrograms(int[] programs, int from, List<int[]> permutations){
    if(from == programs.length){
      permutations.add(Arrays.copyOf(programs, programs.length));
      return;
    }
    for(int i = from; i < programs.length; i++){
      swap(programs, i, from);
      permutePrograms(programs, from + 1, permutations);
      swap(programs, i, from);
    }
  }

  static void swap(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
