import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 699 The Falling Leaves
Problem url: https://uva.onlinejudge.org/external/6/699.pdf
Author: Andrey Yemelyanov
*/
public class _699_FallingLeaves {
  static class Node{
    public int value;
    public Node left;
    public Node right;
    public Node(int value){
      this.value = value;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    Node tree;
    int caseNum = 1;
    while((tree = buildTree(s)) != null){
      List<Integer> piles = countPiles(tree);
      System.out.printf(
        "Case %d:\n%s\n\n", caseNum++,
          piles.stream()
               .map(Object::toString)
               .collect(Collectors.joining(" "))
      );
    }
  }

  static Node buildTree(Scanner s){
    if(s.hasNext()){
      int root = s.nextInt();
      if(root == -1) return null;
      Node rootNode = new Node(root);
      rootNode.left = buildTree(s);
      rootNode.right = buildTree(s);
      return rootNode;
    }
    return null;
  }

  static List<Integer> countPiles(Node tree){
    SortedMap<Integer, Integer> map = new TreeMap<>();
    countPiles(tree, 0, map);
    return  new ArrayList<>(map.values());
  }

  static void countPiles(Node root, int verticalSlice, SortedMap<Integer, Integer> map){
    if(root == null) return;
    map.putIfAbsent(verticalSlice, 0);
    map.put(verticalSlice, map.get(verticalSlice) + root.value);
    countPiles(root.left, verticalSlice - 1, map);
    countPiles(root.right, verticalSlice + 1, map);
  }
}
