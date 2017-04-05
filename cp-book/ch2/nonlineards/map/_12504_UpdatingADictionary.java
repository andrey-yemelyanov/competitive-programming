import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12504 Updating a Dictionary
Problem url: https://uva.onlinejudge.org/external/125/12504.pdf
Author: Andrey Yemelyanov
*/
public class _12504_UpdatingADictionary {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    s.nextLine();
    for(int t = 0; t < nTests; t++){
      Map<String, String> beforeDict = readDict(s);
      Map<String, String> afterDict = readDict(s);
      printChanges(beforeDict, afterDict);
    }
  }

  static Map<String, String> readDict(Scanner s){
    String[] line = s.nextLine().replace("{", "").replace("}", "").split(",");
    Map<String, String> dict = new HashMap<>();
    for(String token : line){
      if(!token.isEmpty()){
        token = token.trim();
        String[] pair = token.split(":");
        dict.put(pair[0], pair[1]);
      }
    }
    //System.out.println(dict);
    return dict;
  }

  static void printChanges(Map<String, String> beforeDict, Map<String, String> afterDict){
    SortedSet<String> newKeys = getNewKeys(beforeDict, afterDict);
    if(newKeys.size() > 0){
      System.out.println(
        "+" + getNewKeys(beforeDict, afterDict).stream()
                                               .collect(Collectors.joining(",")));
    }

    SortedSet<String> removedKeys = getRemovedKeys(beforeDict, afterDict);
    if(removedKeys.size() > 0){
      System.out.println(
        "-" + getRemovedKeys(beforeDict, afterDict).stream()
                                                   .collect(Collectors.joining(",")));
    }

    SortedSet<String> changedKeys = getChangedKeys(beforeDict, afterDict);
    if(changedKeys.size() > 0){
      System.out.println(
        "*" + getChangedKeys(beforeDict, afterDict).stream()
                                                   .collect(Collectors.joining(",")));
    }

    if(newKeys.size() == 0 && removedKeys.size() == 0 && changedKeys.size() == 0) System.out.println("No changes");

    System.out.println();
  }

  static SortedSet<String> getNewKeys(Map<String, String> beforeDict, Map<String, String> afterDict){
    SortedSet<String> newKeys = new TreeSet<>();
    for(String key : afterDict.keySet()){
      if(!beforeDict.containsKey(key)){
        newKeys.add(key);
      }
    }
    return newKeys;
  }

  static SortedSet<String> getRemovedKeys(Map<String, String> beforeDict, Map<String, String> afterDict){
    SortedSet<String> removedKeys = new TreeSet<>();
    for(String key : beforeDict.keySet()){
      if(!afterDict.containsKey(key)){
        removedKeys.add(key);
      }
    }
    return removedKeys;
  }

  static SortedSet<String> getChangedKeys(Map<String, String> beforeDict, Map<String, String> afterDict){
    SortedSet<String> changedKeys = new TreeSet<>();
    for(String key : afterDict.keySet()){
      if(beforeDict.containsKey(key) && !beforeDict.get(key).equals(afterDict.get(key))){
        changedKeys.add(key);
      }
    }
    return changedKeys;
  }
}
