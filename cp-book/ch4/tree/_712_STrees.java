import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 712 S-Trees
Problem url: https://uva.onlinejudge.org/external/7/712.pdf
Author: Andrey Yemelyanov
*/
public class _712_STrees {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int tree = 1;
    while(s.hasNext()){
      int n = s.nextInt();
      if(n == 0) break;
      int[] varOrdering = new int[n];
      int[] terminalNodes = new int[1 << n];
      for(int i = 0; i < n; i++){
        //System.out.println(s.next());
        int var = Integer.parseInt(s.next().substring(1)) - 1;
        varOrdering[i] = var;
      }
      s.nextLine();
      char[] tn = s.nextLine().toCharArray();
      //System.out.println(Arrays.toString(tn));
      for(int i = 0; i < terminalNodes.length; i++){
        terminalNodes[i] = Character.getNumericValue(tn[i]);
      }
      int m = s.nextInt(); s.nextLine();
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("S-Tree #%d:\n", tree++));
      for(int i = 0; i < m; i++){
        int[] vva = new int[n];
        char[] vvaChar = s.nextLine().toCharArray();
        for(int j = 0; j < vvaChar.length; j++){
          vva[j] = Character.getNumericValue(vvaChar[j]);
        }
        sb.append(evalSTree(varOrdering, terminalNodes, vva));
      }
      System.out.println(sb.toString()); System.out.println();
    }
  }

  static int evalSTree(int[] varOrdering, int[] terminalNodes, int[] vva){
    return terminalNodes[terminalNodeIndex(varOrdering, vva, 0, terminalNodes.length - 1, 0)];
  }

  static int terminalNodeIndex(int[] varOrdering, int[] vva, int i, int j, int currentVar){
    if(currentVar == vva.length) return i; // i must be equal j at this level
    if(vva[varOrdering[currentVar]] == 1) { // go right
      return terminalNodeIndex(varOrdering, vva, i + (j - i) / 2 + 1, j, currentVar + 1);
    }else{ // go left
      return terminalNodeIndex(varOrdering, vva, i, i + (j - i) / 2, currentVar + 1);
    }
  }
}
