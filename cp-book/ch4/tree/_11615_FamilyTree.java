import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11615 Family Tree
Problem url: https://uva.onlinejudge.org/external/116/11615.pdf
Author: Andrey Yemelyanov
*/
public class _11615_FamilyTree {
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
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int N = s.nextInt(); int brother1 = s.nextInt(); int brother2 = s.nextInt();
      Map<Integer, Node> map = new HashMap<>();
      int nNodes = (1 << N) - 1;
      for(int i = 1; i <= nNodes; i++){
        map.put(i, new Node(i));
      }
      for(int i = 1; i <= nNodes; i++){
        Node descendant = map.get(i);
        Node father = null; Node mother = null;
        if(map.containsKey(descendant.value * 2)) father = map.get(descendant.value * 2);
        if(map.containsKey(descendant.value * 2 + 1)) mother = map.get(descendant.value * 2 + 1);
        descendant.left = father; descendant.right = mother;
      }
      System.out.println(nDistinctPeopleInFamilyTree(nNodes, map.get(brother1), map.get(brother2)));
    }
  }

  static int countNodes(Node node){
    if(node == null) return 0;
    if(node.left == null && node.right == null) return 1;
    return countNodes(node.left) + countNodes(node.right) + 1;
  }

  static int nCommonAncestors(Node brother1, Node brother2){
    return min(countNodes(brother1) - 1, countNodes(brother2) - 1);
  }

  static int nDistinctPeopleInFamilyTree(int nNodes, Node brother1, Node brother2){
    return nNodes - nCommonAncestors(brother1, brother2);
  }
}
