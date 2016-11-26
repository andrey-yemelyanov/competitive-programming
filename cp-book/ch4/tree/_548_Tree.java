import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 548 Tree
Problem url: https://uva.onlinejudge.org/external/5/548.pdf
Author: Andrey Yemelyanov
*/
public class _548_Tree {
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
    while(s.hasNext()){
      List<Integer> inorder = Arrays.stream(s.nextLine().split("\\s+"))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());
      List<Integer> postorder = Arrays.stream(s.nextLine().split("\\s+"))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());
      Node tree = buildTree(inorder, postorder);
      minPathValue = Integer.MAX_VALUE;
      smallestLeaf = Integer.MAX_VALUE;
      cheapestLeaf(tree, 0);
      System.out.println(smallestLeaf);
      //preorder(tree, ""); System.out.println();
    }
  }

  static void preorder(Node tree, String tab){
    if(tree == null) return;
    System.out.println(tab + tree.value);
    preorder(tree.left, tab + " ");
    preorder(tree.right, tab + " ");
  }

  static int minPathValue;
  static int smallestLeaf;
  static void cheapestLeaf(Node root, int pathValue){
    if(root == null) return;
    if(root.left == null && root.right == null){
      if(pathValue + root.value < minPathValue){
        minPathValue = pathValue + root.value;
        smallestLeaf = root.value;
      }else if(pathValue + root.value == minPathValue && root.value < smallestLeaf){
        smallestLeaf = root.value;
      }
      return;
    }
    cheapestLeaf(root.left, pathValue + root.value);
    cheapestLeaf(root.right, pathValue + root.value);
  }

  static Node buildTree(List<Integer> inorder, List<Integer> postorder){
    if(inorder.size() == 1) return new Node(inorder.get(0));
    int root = postorder.get(postorder.size() - 1);
    Node rootNode = new Node(root);
    List<Integer> leftInorder = inorder.subList(0, inorder.indexOf(root));
    List<Integer> rightInorder = inorder.subList(inorder.indexOf(root) + 1, inorder.size());
    if(leftInorder.size() > 0){
      List<Integer> leftPostorder = postorder.subList(0, leftInorder.size());
      rootNode.left = buildTree(leftInorder, leftPostorder);
    }
    if(rightInorder.size() > 0){
      List<Integer> rightPostorder = postorder.subList(leftInorder.size(), postorder.size() - 1);
      rootNode.right = buildTree(rightInorder, rightPostorder);
    }
    return rootNode;
  }
}
