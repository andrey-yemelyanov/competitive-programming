import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10131 Is Bigger Smarter?
Problem url: https://uva.onlinejudge.org/external/101/10131.pdf
Author: Andrey Yemelyanov
*/
public class _10131_IsBiggerSmarter {
  static class Elephant{
    public int weight;
    public int iq;
    public int id;
    public Elephant(int id, int weight, int iq){
      this.id = id;
      this.weight = weight;
      this.iq = iq;
    }
    public String toString(){
      return String.format("(%d, %d)", weight, iq);
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    List<Elephant> elephants = new ArrayList<>();
    int id = 1;
    while(s.hasNext()){
      elephants.add(new Elephant(id, s.nextInt(), s.nextInt()));
      id++;
    }
    List<Integer> lds = lds(elephants);
    System.out.println(lds.size());
    for(int i : lds){
      System.out.println(elephants.get(i).id);
    }
  }

  static List<Integer> lds(List<Elephant> elephants){
    Collections.sort(elephants, new Comparator<Elephant>(){
      @Override
      public int compare(Elephant e1, Elephant e2){
        return Integer.compare(e1.weight, e2.weight);
      }
    });
    //System.out.println(elephants);
    int[] memo = new int[elephants.size()];
    int[] predecessor = new int[elephants.size()];
    for(int i = 0; i < predecessor.length; i++){
      predecessor[i] = i;
    }
    int maxLdsLength = 0;
    int k = 0;
    for(int i = 0; i < elephants.size(); i++){
      int ldsLength = lds(elephants, i, memo, predecessor);
      if(ldsLength > maxLdsLength){
        maxLdsLength = ldsLength;
        k = i;
      }
    }
    //System.out.println(Arrays.toString(memo));
    List<Integer> lds = new ArrayList<>();
    getLds(elephants, predecessor, k, lds);
    return lds;
  }

  static void getLds(List<Elephant> elephants, int[] predecessor, int i, List<Integer> lds){
    if(predecessor[i] == i){
      lds.add(i);
      return;
    }
    getLds(elephants, predecessor, predecessor[i], lds);
    lds.add(i);
  }

  static int lds(List<Elephant> elephants, int i, int[] memo, int[] predecessor){
    if(memo[i] != 0) return memo[i];
    int maxLdsLength = 1;
    for(int j = 0; j < i; j++){
      if(elephants.get(j).iq > elephants.get(i).iq
        && elephants.get(j).weight < elephants.get(i).weight){
        int ldsLength = 1 + lds(elephants, j, memo, predecessor);
        if(ldsLength > maxLdsLength){
          maxLdsLength = ldsLength;
          predecessor[i] = j;
        }
      }
    }
    return memo[i] = maxLdsLength;
  }
}
