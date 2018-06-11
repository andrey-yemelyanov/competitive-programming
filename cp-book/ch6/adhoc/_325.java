import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 325 Identifying Legal Pascal Real Constants
Problem url: https://uva.onlinejudge.org/external/3/325.pdf
Author: Andrey Yemelyanov
*/
public class _325 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      String n = s.next();
      if(n.equals("*")) break;
      if(n.matches("[-+]?\\d+(\\.\\d+([eE][-+]?\\d+)?|([eE][-+]?\\d+))")){
        System.out.printf("%s is legal.\n", n);
      }else{
        System.out.printf("%s is illegal.\n", n);
      }
    }
  }
}
