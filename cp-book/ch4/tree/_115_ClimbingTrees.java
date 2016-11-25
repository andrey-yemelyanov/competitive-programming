import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 115 Climbing Trees
Problem url: https://uva.onlinejudge.org/external/1/115.pdf
Author: Andrey Yemelyanov
*/
public class _115_ClimbingTrees {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    Map<String, String> tree = new HashMap<>();
    boolean readingTree = true;
    while(s.hasNext()){
      String[] pair = s.nextLine().split("\\s+");
      String p1 = pair[0]; String p2 = pair[1];
      if(p1.equals("no.child") && readingTree) {
        readingTree = false;
        continue;
      }
      if(readingTree){
        tree.put(p1, p2);
        tree.putIfAbsent(p2, null);
      }else{
        answerQuery(tree, p1, p2);
      }
    }
  }

  static void answerQuery(Map<String, String> tree, String p1, String p2){
    String lca = lca(tree, p1, p2);

    if(lca == null){
      System.out.println("no relation");
      return;
    }

    if(lca.equals(p1)){ // p1 is ancestor
      int dist = dist(tree, p2, lca);
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < dist; i++){
        if(i == dist - 1) sb.append("grand ");
        else sb.append("great ");
      }
      sb.append("parent");
      System.out.println(sb.toString());
      return;
    }

    if(lca.equals(p2)){ // p1 is descendant
      int dist = dist(tree, p1, lca);
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < dist; i++){
        if(i == dist - 1) sb.append("grand ");
        else sb.append("great ");
      }
      sb.append("child");
      System.out.println(sb.toString());
      return;
    }

    int dist1 = dist(tree, p1, lca);
    int dist2 = dist(tree, p2, lca);
    int k = min(dist1, dist2);
    int j = abs(dist1 - dist2);
    if(k == 0 && j == 0) System.out.println("sibling");
    else if(j == 0) System.out.println(k + " cousin");
    else System.out.println(k + " cousin removed " + j);
  }

  static int dist(Map<String, String> tree, String from, String to){
    List<String> path = pathToRoot(tree, from);
    return abs(path.indexOf(from) - path.indexOf(to)) - 1;
  }

  static String findCommonPerson(List<String> path1, List<String> path2){
    Set<String> set = new TreeSet<>(path1);
    for(String p : path2){
      if(set.contains(p)){
        return p;
      }
    }
    return null;
  }

  static String lca(Map<String, String> tree, String p1, String p2){
    return findCommonPerson(pathToRoot(tree, p1), pathToRoot(tree, p2));
  }

  static List<String> pathToRoot(Map<String, String> tree, String from){
    List<String> path = new ArrayList<>();
    if(!tree.containsKey(from)) return path;
    pathToRoot(tree, from, path);
    return path;
  }

  static void pathToRoot(Map<String, String> tree, String from, List<String> path){
    path.add(from);
    if(tree.get(from) == null) return;
    pathToRoot(tree, tree.get(from), path);
  }
}
