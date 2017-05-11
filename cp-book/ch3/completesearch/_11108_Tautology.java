import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11108 Tautology
Problem url: https://uva.onlinejudge.org/external/111/11108.pdf
Author: Andrey Yemelyanov
*/
public class _11108_Tautology {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String wff = s.next();
      if(wff.equals("0")) break;
      if(isTautology(wff)) System.out.println("tautology");
      else System.out.println("not");
    }
  }

  static boolean isTautology(String wff){
    for(int p = 0; p <= 1; p++){
      for(int q = 0; q <= 1; q++){
        for(int r = 0; r <= 1; r++){
          for(int s = 0; s <= 1; s++){
            for(int t = 0; t <= 1; t++){
              Map<Character, Integer> vars = new HashMap<>();
              vars.put('p', p);
              vars.put('q', q);
              vars.put('r', r);
              vars.put('s', s);
              vars.put('t', t);
              if(evaluateWff(wff, vars) == 0) return false;
            }
          }
        }
      }
    }
    return true;
  }

  static boolean isOperator(char c){
    return Arrays.asList('K', 'A', 'N', 'C', 'E').contains(c);
  }

  // evaluation alg from https://f0rth3r3c0rd.wordpress.com/2012/07/05/uva-11108-tautology/
  static int evaluateWff(String wff, Map<Character, Integer> vars){
    Stack<Integer> s = new Stack<>();
    for(int i = wff.length() - 1; i >= 0; i--){
      char c = wff.charAt(i);
      if(isOperator(c)){
        if(c == 'N'){
          s.push(eval('N', s.pop()));
        }else{
          s.push(eval(c, s.pop(), s.pop()));
        }
      }else{
        s.push(vars.get(c));
      }
    }
    return s.pop();
  }

  /***
    Evaluates expression in the form: <operator><var1> or <operator><var1><var2>
  ***/
  static int eval(char operator, int... vars){
    switch(operator){
      case 'K':
        return vars[0] & vars[1];
      case 'A':
        return vars[0] | vars[1];
      case 'N':
        return vars[0] ^ 1;
      case 'C':
        return vars[0] == 1 && vars[1] == 0 ? 0 : 1;
      default:
        return vars[0] == vars[1] ? 1 : 0;
    }
  }
}
