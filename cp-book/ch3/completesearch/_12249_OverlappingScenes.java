import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12249 Overlapping Scenes
Problem url: https://uva.onlinejudge.org/external/122/12249.pdf
Author: Andrey Yemelyanov
*/
public class _12249_OverlappingScenes {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      List<String> scenes = new ArrayList<>();
      int n = s.nextInt();
      for(int i = 0; i < n; i++){
        scenes.add(s.next());
      }
      System.out.printf("Case %d: %d\n", c, getMinLength(scenes));
    }
  }

  static int getMinLength(List<String> scenes){
    int minLen = Integer.MAX_VALUE;
    for(List<String> sceneOrdering : permute(scenes)){
      minLen = min(minLen, merge(sceneOrdering).length());
    }
    return minLen;
  }

  static List<List<String>> permute(List<String> scenes){
    List<List<String>> permutations = new ArrayList<>();
    permute(scenes, 0, permutations);
    return permutations;
  }

  static void permute(List<String> scenes, int from, List<List<String>> permutations){
    if(from == scenes.size()){
      permutations.add(new ArrayList<String>(scenes));
    }
    for(int i = from; i < scenes.size(); i++){
      swap(scenes, i, from);
      permute(scenes, from + 1, permutations);
      swap(scenes, i, from);
    }
  }

  static void swap(List<String> scenes, int i, int j){
    String temp = scenes.get(i);
    scenes.set(i, scenes.get(j));
    scenes.set(j, temp);
  }

  static String merge(List<String> scenes){
    return merge(scenes, 1, scenes.get(0));
  }

  static String merge(List<String> scenes, int i, String merged){
    if(i == scenes.size()) return merged;
    return merge(scenes, i + 1, merge(merged, scenes.get(i)));
  }

  static String merge(String scene1, String scene2){
    for(int i = 0; i < scene1.length(); i++){
      String suffix = scene1.substring(i);
      if(scene2.startsWith(suffix)){
        return scene1 + scene2.substring(suffix.length());
      }
    }
    return scene1 + scene2;
  }
}
