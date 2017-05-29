import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10576 Y2K Accounting Bug
Problem url: https://uva.onlinejudge.org/external/105/10576.pdf
Author: Andrey Yemelyanov
*/
public class _10576_YTKAccountingBug {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    while(scanner.hasNext()){
      int s = scanner.nextInt(); int d = scanner.nextInt() * -1;
      int maxProfit = getMaxProfit(s, d);
      if(maxProfit < 0) System.out.println("Deficit");
      else System.out.println(maxProfit);
    }
  }

  static int getMaxProfit(int s, int d){
    int maxProfit = Integer.MIN_VALUE;
    for(List<Integer> solution : generateSolutions(s, d)){
      if(isSolutionValid(solution)){
        //System.out.println(solution);
        maxProfit = max(maxProfit, solution.stream().mapToInt(Integer::intValue).sum());
      }
    }
    return maxProfit;
  }

  static List<List<Integer>> generateSolutions(int s, int d){
    List<List<Integer>> solutions = new ArrayList<>();
    generateSolutions(solutions, new Stack<>(), s, d, 0);
    return solutions;
  }

  static void generateSolutions(List<List<Integer>> solutions, Stack<Integer> solution, int s, int d, int m){
    if(m == 12){
      solutions.add(new ArrayList<Integer>(solution));
      return;
    }
    solution.push(s);
    generateSolutions(solutions, solution, s, d, m + 1);
    solution.pop();
    solution.push(d);
    generateSolutions(solutions, solution, s, d, m + 1);
    solution.pop();
  }

  static boolean isSolutionValid(List<Integer> solution){
    int[] sums = new int[solution.size()];
    sums[0] = solution.get(0);
    for(int i = 1; i < sums.length; i++){
      sums[i] = solution.get(i) + sums[i - 1];
    }
    int M = 5;
    for(int i = M - 1; i < sums.length; i++){
      int sum = sums[i] - (i - M < 0 ? 0 : sums[i - M]);
      if(sum >= 0) return false;
    }
    return true;
  }
}
