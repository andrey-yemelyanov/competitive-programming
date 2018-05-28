import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 622 Grammar Evaluation
Problem url: https://uva.onlinejudge.org/external/6/622.pdf
Author: Andrey Yemelyanov
*/
public class _622 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      String expr = s.next();
      try{
        System.out.println(eval(toPostfix(expr)));
      }catch(Exception ex){
        System.out.println("ERROR");
      }
    }
  }

  static int eval(List<String> postfix){
    Stack<Integer> s = new Stack<>();
    for(String token : postfix){
      if(isOperand(token)) s.push(Integer.parseInt(token));
      else s.push(applyOperator(s.pop(), s.pop(), token));
    }
    if(s.isEmpty() || s.size() > 1) throw new RuntimeException("Invalid expression");
    return s.pop();
  }

  static int applyOperator(int a, int b, String operator){
    if(operator.equals("*")) return a * b;
    else if(operator.equals("+")) return a + b;
    throw new RuntimeException("Unknown operator");
  }

  static List<String> toPostfix(String infix){
    Stack<String> s = new Stack<>();
    List<String> postfix = new ArrayList<>();
    for(String token : tokenize(infix)){
      if(isOperand(token)) postfix.add(token);
      else if(token.equals("(")) s.push(token);
      else if(token.equals(")")){
        while(!s.isEmpty() && !s.peek().equals("(")){
          postfix.add(s.pop());
        }
        if(!s.isEmpty() && !s.peek().equals("(")) throw new RuntimeException("Invalid expression");
        s.pop();
      }
      else if(isOperator(token)){
        while(!s.isEmpty() && precedence(token) <= precedence(s.peek())) postfix.add(s.pop());
        s.push(token);
      }
    }
    while(!s.isEmpty()) postfix.add(s.pop());
    return postfix;
  }

  static int precedence(String operator){
    if(operator.equals("*")) return 2;
    if(operator.equals("+")) return 1;
    return 0;
  }

  static boolean isOperator(String token){
    return token.equals("+") || token.equals("*");
  }

  static boolean isOperand(String token){
    try{
      Integer.parseInt(token);
      return true;
    }catch(Exception ex){ return false; }
  }

  static List<String> tokenize(String expr){
    List<String> tokens = new ArrayList<>();
    int i = 0;
    while(i < expr.length()){
      char c = expr.charAt(i);
      if(isOperator(new String(new char[]{c})) || c == ')' || c == '('){
        tokens.add(new String(new char[]{c}));
        i++;
      }else{
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        i++;
        while(i < expr.length() && Character.isDigit(expr.charAt(i))){
          sb.append(expr.charAt(i));
          i++;
        }
        tokens.add(sb.toString());
      }
    }
    return tokens;
  }
}
