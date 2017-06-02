import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 129 Krypton Factor
Problem url: https://uva.onlinejudge.org/external/1/129.pdf
Author: Andrey Yemelyanov
*/
public class _129_KryptonFactor {
  static final int GROUP_SIZE = 4;
  static final int GROUPS_PER_LINE = 16;
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int n = s.nextInt();
      int L = s.nextInt();
      if(n == 0 && L == 0) break;
      String sequence = generateNthHardSequence(n, L);

      printGroups(breakIntoGroups(sequence, GROUP_SIZE), GROUPS_PER_LINE);
      System.out.println(sequence.length());
    }
  }

  static List<String> breakIntoGroups(String sequence, int groupSize){
    List<String> groups = new ArrayList<>();
    int from = 0;
    for(int i = 0; i < sequence.length(); i++){
      if(i - from + 1 == groupSize){
        groups.add(sequence.substring(from, i + 1));
        from = i + 1;
      }
    }
    if(from < sequence.length()){
      groups.add(sequence.substring(from));
    }
    return groups;
  }

  static void printGroups(List<String> groups, int nGroupsPerLine){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < groups.size(); i++){
      sb.append(groups.get(i));
      if((i + 1) % nGroupsPerLine == 0) sb.append("\n");
      else if(i < groups.size() - 1) sb.append(" ");
    }
    System.out.println(sb.toString());
  }

  static String generateNthHardSequence(int n, int L){
    StringBuilder sequence = new StringBuilder();
    generateNthHardSequence(n, L, 0, sequence);
    return sequence.toString();
  }

  static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  static int generateNthHardSequence(int n, int L, int current, StringBuilder sequence){
    if(current == n) return n;
    for(int i = 0; i < L; i++){
      char c = alphabet[i];
      sequence.append(c);
      if(isHardSequence(sequence)){
        current = generateNthHardSequence(n, L, current + 1, sequence);
      }
      if(current == n) break;
      sequence.deleteCharAt(sequence.length() - 1);
    }
    return current;
  }

  static boolean isHardSequence(StringBuilder sequence){
    for(int i = 0; i < sequence.length(); i++){
      for(int j = i + 1; j < sequence.length(); j++){
        if((j - i + 1) % 2 == 0){
          String subSeq1 = sequence.substring(i, i + (j - i) / 2 + 1);
          String subSeq2 = sequence.substring(i + (j - i) / 2 + 1, j + 1);
          if(subSeq1.equals(subSeq2)) return false;
        }
      }
    }
    return true;
  }
}
