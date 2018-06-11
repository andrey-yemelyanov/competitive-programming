import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 494 Kindergarten Counting Game
Problem url: https://uva.onlinejudge.org/external/4/494.pdf
Author: Andrey Yemelyanov
*/
public class _494 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      String line = s.nextLine();
      System.out.println(line.replaceAll("[^a-zA-Z]+", " ").trim().split(" ").length);
    }
  }
}
