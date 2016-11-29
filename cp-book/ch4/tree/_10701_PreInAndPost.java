import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10701 Pre, in and post
Problem url: https://uva.onlinejudge.org/external/107/10701.pdf
Author: Andrey Yemelyanov
*/
public class _10701_PreInAndPost {
  static class Node{
    public String value;
    public Node left;
    public Node right;
    public Node(String value){
      this.value = value;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int N = s.nextInt();
      String preorder = s.next(); String inorder = s.next();
      StringBuilder sb = new StringBuilder();
      postorder(buildTree(preorder, inorder), sb);
      System.out.println(sb.toString());
    }
  }

  static void postorder(Node root, StringBuilder traversal){
    if(root == null) return;
    postorder(root.left, traversal);
    postorder(root.right, traversal);
    traversal.append(root.value);
  }

  static Node buildTree(String preorder, String inorder){
    if(preorder.length() == 1) return new Node(preorder);
    String root = preorder.substring(0, 1);
    Node rootNode = new Node(root);
    String leftInorder = inorder.substring(0, inorder.indexOf(root));
    String rightInorder = inorder.substring(inorder.indexOf(root) + 1);
    if(!leftInorder.equals("")){
      String leftPreorder = preorder.substring(1, leftInorder.length() + 1);
      rootNode.left = buildTree(leftPreorder, leftInorder);
    }
    if(!rightInorder.equals("")){
      String rightPreorder = preorder.substring(leftInorder.length() + 1);
      rootNode.right = buildTree(rightPreorder, rightInorder);
    }
    return rootNode;
  }
}
