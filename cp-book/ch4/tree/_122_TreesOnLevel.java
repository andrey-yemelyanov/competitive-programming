import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 122 Trees on the level
Problem url: https://uva.onlinejudge.org/external/1/122.pdf
Author: Andrey Yemelyanov
*/
public class _122_TreesOnLevel {
  static class Node{
    private int value = -1;
    private int nTimesValueAssigned;
    public void setValue(int value){
      this.value = value;
      nTimesValueAssigned++;
    }
    public int getValue(){return value;}
    public int nTimesValueAssigned(){return nTimesValueAssigned;}
    public Node left; public Node right;
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    Node root = null;
    while(s.hasNext()){
      String node = s.next();
      if(!node.equals("()")){
        String[] tokens = node.replace("(","").replace(")","").split(",");
        //System.out.println(Arrays.toString(tokens));
        int value = Integer.parseInt(tokens[0]);
        String path = tokens.length == 2 ? tokens[1] : "";
        if(root == null){
          root = new Node();
          map = new HashMap<>();
        }
        attachToTree(root, value, path);
      }else{
        if(!isCompletelySpecified(root)) System.out.println("not complete");
        else{
          List<Integer> traversal = levelOrderTraversal(root);
          System.out.println(
            traversal.stream()
                     .map(Object::toString)
                     .collect(Collectors.joining(" "))
          );
        }
        root = null;
      }
    }
  }

  static List<Integer> levelOrderTraversal(Node root){
    List<Integer> traversal = new ArrayList<>();
    Queue<Node> q = new LinkedList<>();
    q.add(root);
    while(!q.isEmpty()){
      Node node = q.remove();
      traversal.add(node.getValue());
      if(node.left != null) q.add(node.left);
      if(node.right != null) q.add(node.right);
    }
    return traversal;
  }

  static boolean isCompletelySpecified(Node root){
    if(root == null) return true;
    //System.out.println(root.getValue());
    if(root.nTimesValueAssigned() != 1) return false;
    return isCompletelySpecified(root.left) && isCompletelySpecified(root.right);
  }

  static Map<Integer, Integer> map;
  static void attachToTree(Node root, int value, String path){
    for(char p : path.toCharArray()){
      if(p == 'L'){
        if(root.left == null) root.left = new Node();
        root = root.left;
      }else if(p == 'R'){
        if(root.right == null) root.right = new Node();
        root = root.right;
      }
    }
    //System.out.println("Add " + value + " at " + path);
    map.putIfAbsent(value, 0);
    map.put(value, map.get(value) + 1);
    root.setValue(value);
  }
}
