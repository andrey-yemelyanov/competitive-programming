import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10854 Number of Paths
Problem url: https://uva.onlinejudge.org/external/108/10854.pdf
Author: Andrey Yemelyanov
*/
public class _10854 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      List<String> code = new ArrayList<>();
      String line = s.next();
      while(!line.equals("ENDPROGRAM")){
        code.add(line);
        line = s.next();
      }
      System.out.println(countExecutionPaths(code, 0, code.size() - 1));
    }
  }

  static int countExecutionPaths(List<String> code, int from, int to){
    if(!containsConditional(code, from, to)) return 1;
    int[] split = splitAroundIfElse(code, from, to);
    int count = countExecutionPaths(code, split[0] + 1, split[1] - 1)
              + countExecutionPaths(code, split[1] + 1, split[2] - 1);
    if(containsConditional(code, split[2] + 1, to)){
      count *= countExecutionPaths(code, split[2] + 1, to);
    }
    return count;
  }

  static boolean containsConditional(List<String> code, int from, int to){
    for(int i = from; i <= to; i++) if(code.get(i).equals("IF")) return true;
    return false;
  }

  static int[] splitAroundIfElse(List<String> code, int from, int to){
    int i = from; // i points at top-level IF
    while(!code.get(i).equals("IF")) i++;
    Stack<String> s = new Stack<>();
    s.push(code.get(i));
    int j = i + 1;
    while(!s.isEmpty()){
      if(code.get(j).equals("IF")) s.push(code.get(j));
      else if(code.get(j).equals("ELSE")) s.pop();
      j++;
    }
    j--; // j points at corresponding ELSE
    int k = j + 1;
    s = new Stack<>();
    s.push("IF");
    while(!s.isEmpty()){
      if(code.get(k).equals("IF")) s.push(code.get(k));
      else if(code.get(k).equals("END_IF")) s.pop();
      k++;
    }
    k--; // k points at corresponding END_IF
    return new int[] {i, j, k};
  }
}
