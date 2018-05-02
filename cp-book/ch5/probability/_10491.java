import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10491 Cows and Cars
Problem url: https://uva.onlinejudge.org/external/104/10491.pdf
Author: Andrey Yemelyanov
*/
public class _10491 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      System.out.printf("%.5f\n", P(s.nextInt(), s.nextInt(), s.nextInt()));
    }
  }

  static double P(int NCOWS, int NCARS, int NSHOW){
    return ((NCOWS / (double)(NCOWS + NCARS)) * (NCARS / (double)(NCARS + NCOWS - NSHOW - 1))) +
           ((NCARS / (double)(NCARS + NCOWS)) * ((NCARS - 1) / (double)(NCARS + NCOWS - NSHOW - 1)));
  }
}
