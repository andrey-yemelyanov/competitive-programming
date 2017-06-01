import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10475 Help the Leaders
Problem url: https://uva.onlinejudge.org/external/104/10475.pdf
Author: Andrey Yemelyanov
*/
public class _10475_HelpLeaders {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    int nTests = scanner.nextInt();
    for(int c = 1; c <= nTests; c++){
      int t = scanner.nextInt();
      int p = scanner.nextInt();
      int subsetSize = scanner.nextInt();
      String[] topics = new String[t];
      for(int i = 0; i < topics.length; i++){
        topics[i] = scanner.next().toUpperCase();
      }
      String[] prohibitedPairs = new String[p];
      for(int i = 0; i < prohibitedPairs.length; i++){
        prohibitedPairs[i] = (scanner.next() + " " + scanner.next()).toUpperCase();
      }
      System.out.println("Set " + c + ":");
      getValidTopicCombinations(topics, prohibitedPairs, subsetSize)
        .stream()
        .map(group -> {
              group.sort((t1,t2) -> {
                if(t1.length() == t2.length()) return t1.compareTo(t2);
                return Integer.compare(t2.length(), t1.length());
              });
              return group;
        })
        .sorted(new Comparator<List<String>>(){
          @Override
          public int compare(List<String> l1, List<String> l2){
            for(int i = 0; i < l1.size(); i++){
              String s1 = l1.get(i);
              String s2 = l2.get(i);
              if(s1.length() != s2.length()) return Integer.compare(s2.length(), s1.length());
              if(!s1.equals(s2)) return s1.compareTo(s2);
            }
            return 0;
          }
        })
        .map(group -> group.stream().collect(Collectors.joining(" ")))
        .forEach(System.out::println);
      System.out.println();
    }
  }

  static List<List<String>> getValidTopicCombinations(String[] topics, String[] prohibitedPairs, int subsetSize){
    Map<String, Integer> topicMap = new HashMap<>();
    for(int i = 0; i < topics.length; i++){
      topicMap.put(topics[i], i);
    }
    Set<Integer> prohibited = Arrays.stream(prohibitedPairs)
                                    .map(s -> s.split("\\s+"))
                                    .map(pair -> encodeTopicPair(pair[0], pair[1], topicMap))
                                    .collect(Collectors.toSet());

    return generateSubsets(topics, subsetSize).stream()
                                              .filter(c -> isValidCombination(c, prohibited, topicMap))
                                              .collect(Collectors.toList());
  }

  static boolean isValidCombination(List<String> topicCombination, Set<Integer> prohibitedPairs, Map<String, Integer> topicMap){
    for(int i = 0; i < topicCombination.size(); i++){
      for(int j = i + 1; j < topicCombination.size(); j++){
        int pairCode = encodeTopicPair(topicCombination.get(i), topicCombination.get(j), topicMap);
        if(prohibitedPairs.contains(pairCode)) return false;
      }
    }
    return true;
  }

  static int encodeTopicPair(String topic1, String topic2, Map<String, Integer> topicMap){
    int[] topicIds = new int[] {topicMap.get(topic1), topicMap.get(topic2)};
    Arrays.sort(topicIds);
    return (topicIds[0] << 4) | topicIds[1];
  }

  static List<List<String>> generateSubsets(String[] topics, int subsetSize){
    List<List<String>> subsets = new ArrayList<>();
    generateSubsets(topics, 0, subsetSize, new Stack<>(), subsets);
    return subsets;
  }

  static void generateSubsets(String[] topics, int i, int subsetSize, Stack<String> subset, List<List<String>> subsets){
    if(subset.size() == subsetSize){
      subsets.add(new ArrayList<>(subset));
      return;
    }
    if(i == topics.length) return;
    subset.push(topics[i]);
    generateSubsets(topics, i + 1, subsetSize, subset, subsets);
    subset.pop();
    generateSubsets(topics, i + 1, subsetSize, subset, subsets);
  }
}
