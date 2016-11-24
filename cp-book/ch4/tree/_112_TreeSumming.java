import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;
import java.io.*;

/*
Problem name: 112 Tree Summing
Problem url: https://uva.onlinejudge.org/external/1/112.pdf
Author: Andrey Yemelyanov
*/
public class _112_TreeSumming {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while(true){
      String targetSumToken = readToken(reader);
      if(targetSumToken == null) break;
      int targetSum = Integer.parseInt(targetSumToken);
      //System.out.println(targetSum);
      stack = new Stack<>(); sumPossible = false; explore(reader, 0, targetSum);
      if(sumPossible) System.out.println("yes");
      else System.out.println("no");
    }
  }

  static boolean sumPossible = false;
  static Stack<String> stack;
  static void explore(BufferedReader reader, int sum, int targetSum) throws IOException {
    int nEmptySubtrees = 0;
    while(true){
      String token = readToken(reader);
      if(token.equals("(")){
        stack.push(token);
        String nextToken = readToken(reader);
        if(nextToken.equals(")")){
          stack.pop();
          nEmptySubtrees++;
        }else{ // must be numeric
          explore(reader, sum + Integer.parseInt(nextToken), targetSum);
        }
      }else if(token.equals(")")){
        stack.pop();
        if(nEmptySubtrees == 2) {
          //System.out.printf("Leave reached. Sum = %d\n", sum);
          if(!sumPossible) sumPossible = (sum == targetSum);
        }
        return;
      }
      if(stack.isEmpty()) break;
    }
  }

  static String readToken(BufferedReader reader) throws IOException {
    StringBuilder token = new StringBuilder();
    while(true){
      int i = reader.read();
      if(i == -1) return null;
      char c = (char) i;
      if(Character.isWhitespace(c)) continue;
      if(c == ')' || c == '('){
        token.append(c);
        break;
      }
      boolean numberFound = false;
      if(Character.isDigit(c) || c == '-'){
        token.append(c);
        while(true){
          c = peekNext(reader);
          if(Character.isDigit(c)){
            token.append(c);
            reader.read();
          }else{
            numberFound = true;
            break;
          }
        }
        if(numberFound) break;
      }
    }
    return token.toString();
  }

  static char peekNext(BufferedReader reader) throws IOException {
    reader.mark(1);
    char c = (char) reader.read();
    reader.reset();
    return c;
  }
}
