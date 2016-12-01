import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12347 Binary Search Tree
Problem url: https://uva.onlinejudge.org/external/123/12347.pdf
Author: Andrey Yemelyanov
*/
public class _12347_BinarySearchTree {
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
    List<Integer> preorder = new ArrayList<>();
    while(s.hasNext()){
      preorder.add(s.nextInt());
    }
    List<Integer> postorder = new ArrayList<>();
    postorder(buildTree(preorder), postorder);
    System.out.println(
      postorder.stream()
               .map(Object::toString)
               .collect(Collectors.joining("\n"))
    );
  }

  static void postorder(Node node, List<Integer> traversal){
    if(node == null) return;
    postorder(node.left, traversal);
    postorder(node.right, traversal);
    traversal.add(node.value);
  }

  static Node buildTree(List<Integer> preorder){
    if(preorder.size() == 1) return new Node(preorder.get(0));
    int root = preorder.get(0);
    Node rootNode = new Node(root);
    int boundary = boundary(preorder, root);
    if(boundary == NOTFOUND){
      rootNode.left = buildTree(preorder.subList(1, preorder.size()));
    }else{
      List<Integer> leftPreorder = preorder.subList(1, boundary);
      List<Integer> rightPreorder = preorder.subList(boundary, preorder.size());
      if(leftPreorder.size() > 0){
        rootNode.left = buildTree(leftPreorder);
      }
      if(rightPreorder.size() > 0){
        rootNode.right = buildTree(rightPreorder);
      }
    }
    return rootNode;
  }

  static final int NOTFOUND = -1;
  static int boundary(List<Integer> preorder, int root){
    for(int i = 1; i < preorder.size(); i++){
      if(preorder.get(i) > root) return i;
    }
    return NOTFOUND;
  }
}
