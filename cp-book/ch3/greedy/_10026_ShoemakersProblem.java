import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10026 Shoemaker's Problem
Problem url: https://uva.onlinejudge.org/external/100/10026.pdf
Author: Andrey Yemelyanov
*/
public class _10026_ShoemakersProblem {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    while(nTests-- > 0){
      s.nextLine();
      int N = s.nextInt();
      List<Job> jobs = new ArrayList<>();
      int jobId = 1;
      for(int i = 0; i < N; i++) jobs.add(new Job(s.nextInt(), s.nextInt(), jobId++));
      System.out.println(
        orderJobs(jobs).stream()
                       .map(j -> j.id)
                       .map(Object::toString)
                       .collect(Collectors.joining(" "))
      );
      if(nTests > 0) System.out.println();
    }
  }

  static class Job{
    public int id;
    public int effort;
    public int fine;
    public Job(int effort, int fine, int id){
      this.effort = effort;
      this.fine = fine;
      this.id = id;
    }
    public double getFinePerDayRatio(){
      return fine / (double) effort;
    }
  }

  static List<Job> orderJobs(List<Job> jobs){
    return jobs.stream()
               .sorted((j1, j2) -> Double.compare(
                    j2.getFinePerDayRatio(), j1.getFinePerDayRatio()))
               .collect(Collectors.toList());
  }
}
