import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11234 Expressions
Problem url: https://uva.onlinejudge.org/external/112/11234.pdf
Author: Andrey Yemelyanov
*/
public class _11234_Expressions {
  static class Node{
    public char value;
    public Node left;
    public Node right;
    public Node(char value){
      this.value = value;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int T = s.nextInt();
    while(T-- > 0){
      String postfixExpression = s.next();
      List<Node> traversal = levelOrderTraversal(buildTree(postfixExpression));
      Collections.reverse(traversal);
      System.out.println(
        traversal.stream()
                 .map(node -> Character.toString(node.value))
                 .collect(Collectors.joining(""))
      );
    }
  }

  static List<Node> levelOrderTraversal(Node tree){
    List<Node> traversal = new ArrayList<>();
    Queue<Node> q = new LinkedList<>();
    q.add(tree);
    while(!q.isEmpty()){
      Node node = q.remove();
      traversal.add(node);
      if(node.left != null) q.add(node.left);
      if(node.right != null) q.add(node.right);
    }
    return traversal;
  }

  static Node buildTree(String postfixExpression){
    Stack<Node> s = new Stack<>();
    for(char c : postfixExpression.toCharArray()){
      if(!isOperator(c)) s.push(new Node(c));
      else{
        Node root = new Node(c);
        root.right = s.pop();
        root.left = s.pop();
        s.push(root);
      }
    }
    return s.pop();
  }

  static boolean isOperator(char c){
    return Character.isUpperCase(c);
  }
}
