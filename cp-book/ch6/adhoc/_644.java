import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 644 Immediate Decodability
Problem url: https://uva.onlinejudge.org/external/6/644.pdf
Author: Andrey Yemelyanov
*/
public class _644 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int setNum = 1;
    List<String> codes = new ArrayList<>();
    while(s.hasNext()){
      String code = s.next();
      if(code.equals("9")){
        if(isDecodable(codes)){
          System.out.printf("Set %d is immediately decodable\n", setNum++);
        }else{
          System.out.printf("Set %d is not immediately decodable\n", setNum++);
        }
        codes.clear();
      }else{
        codes.add(code);
      }
    }
  }

  static boolean isDecodable(List<String> codes){
    for(int i = 0; i < codes.size(); i++){
      for(int j = i + 1; j < codes.size(); j++){
        String code1 = codes.get(i);
        String code2 = codes.get(j);
        if(code1.startsWith(code2) || code2.startsWith(code1)) return false;
      }
    }
    return true;
  }
}
