import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11173 Grey Codes
Problem url: https://uva.onlinejudge.org/external/111/11173.pdf
Author: Andrey Yemelyanov
*/
public class _11173_GreyCodes {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int n = s.nextInt(); int k = s.nextInt();
      System.out.println(toDecimal(generateGreyCode(n, k)));
    }
  }

  static long toDecimal(List<Integer> code){
    long decimal = 0;
    for(int i = 0; i < code.size(); i++){
      if(code.get(i) == 1){
        decimal += pow(2, code.size() - 1 - i);
      }
    }
    return decimal;
  }

  static List<Integer> generateGreyCode(int n, int k){
    List<Integer> code = new ArrayList<>();
    generateGreyCode(n, k, code);
    return code;
  }

  static void generateGreyCode(int n, int k, List<Integer> code){
    if(n == 1){
      code.add(k);
      return;
    }
    if(k < (int)pow(2, n) / 2){
      code.add(0);
      generateGreyCode(n - 1, k, code);
    }else{
      code.add(1);
      generateGreyCode(n - 1, (int)pow(2, n) - 1 - k, code);
    }
  }
}
