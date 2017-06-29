import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10910 Marks Distribution
Problem url: https://uva.onlinejudge.org/external/109/10910.pdf
Author: Andrey Yemelyanov
*/
public class _10910_MarksDistribution {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      System.out.println(count(s.nextInt(), s.nextInt(), s.nextInt()));
    }
  }

  static int count(int nSubjects, int totalMarks, int P){
    int[][] memo = new int[nSubjects + 1][totalMarks + 1];
    for(int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
    return count(nSubjects, totalMarks, P, memo);
  }

  static int count(int nSubjects, int totalMarks, int P, int[][] memo){
    if(nSubjects == 0){
      if(totalMarks == 0) return 1;
      return 0;
    }
    if(memo[nSubjects][totalMarks] != -1) return memo[nSubjects][totalMarks];
    int totalCount = 0;
    for(int i = P; i <= totalMarks; i++){
      totalCount += count(nSubjects - 1, totalMarks - i, P, memo);
    }
    return memo[nSubjects][totalMarks] = totalCount;
  }
}
