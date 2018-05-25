import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10906 Strange Integration
Problem url: https://uva.onlinejudge.org/external/109/10906.pdf
Author: Andrey Yemelyanov
*/
public class _10906 {
  static class Expression{
    public String var;
    public String operand1;
    public String operand2;
    public String operator;
    public Expression(String var, String operand1, String operand2, String operator){
      this.var = var;
      this.operand1 = operand1;
      this.operand2 = operand2;
      this.operator = operator;
    }
    public static Expression parse(String expr){
      String[] tokens = expr.split("\\s+");
      return new Expression(tokens[0], tokens[2], tokens[4], tokens[3]);
    }
    public String toString(){
      return String.format("[%s=%s%s%s]", var, operand1, operator, operand2);
    }
  }

  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int t = 1; t <= nTests; t++){
      int n = s.nextInt(); s.nextLine();
      Map<String, Expression> map = new HashMap<>();
      String root = null;
      while(n-- > 0){
        Expression exp = Expression.parse(s.nextLine());
        map.put(exp.var, exp);
        if(n == 0) root = exp.var;
      }
      System.out.printf("Expression #%d: %s\n", t, integrate(map, root));
    }
  }

  static String integrate(Map<String, Expression> map, String root){
    Expression exp = map.get(root);
    StringBuilder sb = new StringBuilder();
    if(map.containsKey(exp.operand1)){
      Expression leftExp = map.get(exp.operand1);
      if(leftExp.operator.equals("+") && exp.operator.equals("*")){
        sb.append("(");
        sb.append(integrate(map, exp.operand1));
        sb.append(")");
      }else sb.append(integrate(map, exp.operand1));
    }else sb.append(exp.operand1);
    sb.append(exp.operator);
    if(map.containsKey(exp.operand2)){
      Expression rightExp = map.get(exp.operand2);
      if(rightExp.operator.equals(exp.operator) || (rightExp.operator.equals("+") && exp.operator.equals("*"))){
        sb.append("(");
        sb.append(integrate(map, exp.operand2));
        sb.append(")");
      }else sb.append(integrate(map, exp.operand2));
    }else sb.append(exp.operand2);
    return sb.toString();
  }
}
