import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11357 Ensuring Truth
Problem url: https://uva.onlinejudge.org/external/113/11357.pdf
Author: Andrey Yemelyanov
*/
public class _11357 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    while(nTests-- > 0){
      String exp = s.nextLine();
      if(evalExpression(exp)) System.out.println("YES");
      else System.out.println("NO");
    }
  }

  static boolean evalExpression(String exp){
    for(String clause : getClauses(exp)){
      if(evalClause(clause)) return true;
    }
    return false;
  }

  static String[] getClauses(String exp){
    String[] clauses = exp.split("\\|");
    for(int i = 0; i < clauses.length; i++){
      clauses[i] = clauses[i].replace("(","").replace(")","");
    }
    return clauses;
  }

  static boolean evalClause(String clause){
    String[] vars = clause.split("&");
    Set<String> set = new HashSet<String>();
    for(String var : vars){
      if(var.startsWith("~")){
        if(set.contains(var.substring(1))) return false;
      }else if(set.contains("~" + var)) return false;
      set.add(var);
    }
    return true;
  }
}
