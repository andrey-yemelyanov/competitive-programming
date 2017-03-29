import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11988 Broken Keyboard (a.k.a. Beiju Text)
Problem url: https://uva.onlinejudge.org/external/119/11988.pdf
Author: Andrey Yemelyanov
*/
public class _11988_BrokenKeyboard {
  static class TextEditor{
    private Node head;
    private Node tail;
    private Node cursor;
    class Node{
      public Node(char c){
        this.c = c;
      }
      public char c;
      public Node next;
    }
    public void moveCursorToStart(){
      cursor = null;
    }
    public void moveCursorToEnd(){
      cursor = tail;
    }
    public void insertChar(char c){
      Node newNode = new Node(c);
      if(head == null){
        head = newNode;
        tail = newNode;
        cursor = newNode;
      }else{
        if(cursor == null){
          newNode.next = head;
          head = newNode;
        }else{
          newNode.next = cursor.next;
          cursor.next = newNode;
          if(cursor == tail) tail = newNode;
        }
      }
      cursor = newNode;
    }
    public String getText(){
      StringBuilder sb = new StringBuilder();
      Node current = head;
      while(current != null){
        sb.append(current.c);
        current = current.next;
      }
      return sb.toString();
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String text = s.nextLine();
      TextEditor editor = new TextEditor();
      for(int i = 0; i < text.length(); i++){
        char c = text.charAt(i);
        if(c != ']' && c != '['){
          editor.insertChar(c);
        }else if(c == ']'){
          editor.moveCursorToEnd();
        }else{
          editor.moveCursorToStart();
        }
      }
      System.out.println(editor.getText());
    }
  }
}
