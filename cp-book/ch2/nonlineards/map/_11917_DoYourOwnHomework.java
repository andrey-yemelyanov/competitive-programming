import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11917 Do Your Own Homework
Problem url: https://uva.onlinejudge.org/external/119/11917.pdf
Author: Andrey Yemelyanov
*/
public class _11917_DoYourOwnHomework {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    final int INF = Integer.MAX_VALUE;
    int nTests = s.nextInt();
    for(int c = 1; c <= nTests; c++){
      Map<String, Integer> subjects = new HashMap<>();
      int nSubjects = s.nextInt();
      for(int i = 0; i < nSubjects; i++){
        subjects.put(s.next(), s.nextInt());
      }
      int D = s.nextInt();
      String assignment = s.next();
      int completionTime = subjects.containsKey(assignment) ? subjects.get(assignment) : INF;
      if(completionTime == INF || completionTime > D + 5){
        System.out.println("Case " + c + ": Do your own homework!");
      }else if(completionTime <= D){
        System.out.println("Case " + c + ": Yesss");
      }else{
        System.out.println("Case " + c + ": Late");
      }
    }
  }
}
