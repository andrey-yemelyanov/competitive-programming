import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11878 Homework Checker
Problem url: https://uva.onlinejudge.org/external/118/11878.pdf
Author: Andrey Yemelyanov
*/
public class _11878 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int count = 0;
    while(s.hasNext()){
      if(isExpressionValid(s.nextLine())) count++;
    }
    System.out.println(count);
  }

  static boolean isExpressionValid(String exp){
    String[] split = exp.split("=");
    if(split[1].equals("?")) return false;
    int answer = Integer.parseInt(split[1]);
    return eval(split[0]) == answer;
  }

  static int eval(String exp){
    int i = 0;
    while(Character.isDigit(exp.charAt(i))) i++;
    int operand1 = Integer.parseInt(exp.substring(0, i));
    char operator = exp.charAt(i++);
    int operand2 = Integer.parseInt(exp.substring(i));
    if(operator == '+') return operand1 + operand2;
    return operand1 - operand2;
  }
}
