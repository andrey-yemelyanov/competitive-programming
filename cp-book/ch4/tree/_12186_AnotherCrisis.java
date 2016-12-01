import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12186 Another Crisis
Problem url: https://uva.onlinejudge.org/external/121/12186.pdf
Author: Andrey Yemelyanov
*/
public class _12186_AnotherCrisis {
  static class Employee{
    public int id;
    public List<Employee> subordinates;
    public Employee(int id){
      this.id = id;
      subordinates = new ArrayList<>();
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int N = s.nextInt(); int T = s.nextInt();
      if(N == 0 && T == 0) break;
      Map<Integer, Employee> map = new HashMap<>();
      for(int i = 0; i <= N; i++){
        map.put(i, new Employee(i));
      }
      for(int i = 1; i <= N; i++){
        int boss = s.nextInt();
        map.get(boss).subordinates.add(map.get(i));
      }
      System.out.println(minWorkerPetitionsRequired(map.get(0), T / 100.0));
    }
  }

  static int minWorkerPetitionsRequired(Employee employee, double T){
    if(employee.subordinates.size() == 0) return 1; // this is a worker
    int nSubordinatePetitionsRequired = (int) ceil(employee.subordinates.size() * T);
    List<Integer> pressurePoints = new ArrayList<>();
    for(Employee subordinate : employee.subordinates){
      pressurePoints.add(minWorkerPetitionsRequired(subordinate, T));
    }
    Collections.sort(pressurePoints);
    int minWorkerPetitionsRequired = 0;
    for(int i = 0; i < nSubordinatePetitionsRequired; i++){
      minWorkerPetitionsRequired += pressurePoints.get(i);
    }
    return minWorkerPetitionsRequired;
  }
}
