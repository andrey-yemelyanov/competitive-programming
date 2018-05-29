import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11291 Smeech
Problem url: https://uva.onlinejudge.org/external/112/11291.pdf
Author: Andrey Yemelyanov
*/
public class _11291 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String expression = s.nextLine();
      if(expression.equals("()")) break;
      System.out.printf("%.2f\n", eval(expression));
    }
  }

  static double eval(String expression){
    Stack<Double> s = new Stack<>();
    for(String token : tokenize(expression)){
      if(isNumeric(token)) s.push(Double.parseDouble(token));
      else if(token.equals(")")){
        double e2 = s.pop();
        double e1 = s.pop();
        double prob = s.pop();
        s.push(prob * (e1 + e2) + (1 - prob) * (e1 - e2));
      }
    }
    return s.pop();
  }

  static boolean isNumeric(String token){
    return !token.equals(")") && !token.equals("(");
  }

  static List<String> tokenize(String expression){
    List<String> tokens = new ArrayList<>();
    int i = 0;
    while(i < expression.length()){
      char c = expression.charAt(i);
      if(c == '(' || c == ')'){
        tokens.add(new String(new char[]{c}));
        i++;
      }
      else if(Character.isDigit(c) || c == '-'){
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        i++;
        while(i < expression.length()
          && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')){
            sb.append(expression.charAt(i++));
          }
        tokens.add(sb.toString());
      }
      else i++;
    }
    return tokens;
  }
}
