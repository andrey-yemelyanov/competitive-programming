import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11239 Open Source
Problem url: https://uva.onlinejudge.org/external/112/11239.pdf
Author: Andrey Yemelyanov
*/
public class _11239_OpenSource {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    String project = null;
    Map<String, Set<String>> projectToUserMap = new HashMap<>();
    while(s.hasNext()){
      String line = s.nextLine();
      if(line.startsWith("0")) break;
      if(!line.startsWith("1")){
        if(isAllUpper(line)){
          project = line;
          projectToUserMap.put(project, new HashSet<>());
        }else{
          projectToUserMap.get(project).add(line);
        }
      }else{
        System.out.print(toString(sortSummary(summarize(projectToUserMap))));
        projectToUserMap = new HashMap<>();
      }
    }
  }

  static boolean isAllUpper(String s)
  {
    for(char c : s.toCharArray()){
      if(!Character.isUpperCase(c) && c != ' ')
        return false;
    }
    return true;
  }

  static Map<String, Integer> summarize(Map<String, Set<String>> projectToUserMap){
    Map<String, Set<String>> userToProjectMap = new HashMap<>();
    for(String project : projectToUserMap.keySet()){
      for(String userid : projectToUserMap.get(project)){
        userToProjectMap.putIfAbsent(userid, new HashSet<>());
        userToProjectMap.get(userid).add(project);
      }
    }
    Map<String, Integer> summary = new HashMap<>();
    for(String project : projectToUserMap.keySet()){
      summary.putIfAbsent(project, 0);
      for(String userid : projectToUserMap.get(project)){
        if(userToProjectMap.get(userid).size() == 1) summary.put(project, summary.get(project) + 1);
      }
    }
    return summary;
  }

  static List<Map.Entry<String, Integer>> sortSummary(Map<String, Integer> summary){
    List<Map.Entry<String, Integer>> list = new ArrayList<>(summary.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
      @Override
      public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2){
        if(e1.getValue() != e2.getValue()) return Integer.compare(e2.getValue(), e1.getValue());
        return e1.getKey().compareTo(e2.getKey());
      }
    });
    return list;
  }

  static String toString(List<Map.Entry<String, Integer>> list){
    StringBuilder sb = new StringBuilder();
    for(Map.Entry<String, Integer> entry : list){
      sb.append(entry.getKey() + " " + entry.getValue());
      sb.append("\n");
    }
    return sb.toString();
  }
}
