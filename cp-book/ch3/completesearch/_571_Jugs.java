import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 571 Jugs
Problem url: https://uva.onlinejudge.org/external/5/571.pdf
Author: Andrey Yemelyanov
*/
public class _571_Jugs {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int Ca = s.nextInt();
      int Cb = s.nextInt();
      int N = s.nextInt();
      List<String> solution = solve(Ca, Cb, N);
      solution.stream().forEach(System.out::println);
      System.out.println("success");
    }
  }

  static List<String> solve(int Ca, int Cb, int N){
    Stack<String> s = new Stack<>();
    solve(Ca, Cb, N, 0, 0, new boolean[Ca + 1][Cb + 1], s);
    List<String> list = new ArrayList<>();
    while(!s.isEmpty()) list.add(s.pop());
    return list;
  }

  static boolean solve(int Ca, int Cb, int N, int Wa, int Wb, boolean[][] explored, Stack<String> s){
    if(explored[Wa][Wb]) return false;
    if(Wa == N || Wb == N) return true;
    explored[Wa][Wb] = true;
    // fill A
    if(Wa < Ca && solve(Ca, Cb, N, Ca, Wb, explored, s)){
      s.push("fill A");
      return true;
    }

    // empty A
    if(Wa > 0 && solve(Ca, Cb, N, 0, Wb, explored, s)){
      s.push("empty A");
      return true;
    }
    // empty B
    if(Wb > 0 && solve(Ca, Cb, N, Wa, 0, explored, s)){
      s.push("empty B");
      return true;
    }
    // pour from A to B
    int[] w = pour(Wa, Wb, Ca, Cb);
    if(Wa > 0 && Wb < Cb && solve(Ca, Cb, N, w[0], w[1], explored, s)){
      s.push("pour A B");
      return true;
    }
    // pour from B to A
    w = pour(Wb, Wa, Cb, Ca);
    if(Wb > 0 && Wa < Ca && solve(Ca, Cb, N, w[1], w[0], explored, s)){
      s.push("pour B A");
      return true;
    }
    return false;
  }

  static int[] pour(int fromW, int toW, int fromC, int toC){
    int toCLeft = toC - toW;
    if(toCLeft >= fromW){
      return new int[] {0, toW + fromW};
    }else{
      return new int[] {fromW - toCLeft, toC};
    }
  }
}
