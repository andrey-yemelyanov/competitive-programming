import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11629 Ballot evaluation
Problem url: https://uva.onlinejudge.org/external/116/11629.pdf
Author: Andrey Yemelyanov
*/
public class _11629_BallotEvaluation {
  static class Guess{
    public List<String> parties;
    public String comparison;
    public int n;
    public Guess(List<String> parties, String comparison, int n){
      this.parties = parties;
      this.comparison = comparison;
      this.n = n;
    }
    public String toString(){
      return parties + " " + comparison + " " + n;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in).useLocale(Locale.US);;
    int p = s.nextInt();
    int g = s.nextInt();
    Map<String, Double> results = new HashMap<>();
    for(int i = 0; i < p; i++){
      results.put(s.next(), s.nextDouble());
    }
    s.nextLine();
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < g; i++){
      String[] line = s.nextLine().split("\\s+");
      List<String> parties = new ArrayList<>();
      int j = 0;
      for(; j < line.length; j++){
        String token = line[j];
        if(token.equals(">") || token.equals("<") || token.equals(">=") || token.equals("<=") || token.equals("=")) break;
        if(!token.equals("+")) parties.add(token);
      }
      String comparison = line[j];
      int n = Integer.parseInt(line[j + 1]);
      if(evalGuess(new Guess(parties, comparison, n), results)){
        sb.append("Guess #" + (i + 1) + " was correct.\n");
      }else{
        sb.append("Guess #" + (i + 1) + " was incorrect.\n");
      }
    }
    System.out.print(sb.toString());
  }

  static boolean evalGuess(Guess g, Map<String, Double> results){
    double leftSide = g.parties.stream()
                               .mapToDouble(party -> results.get(party))
                               .sum();
    double rightSide = (double)g.n;
    //System.out.println(g + " " + leftSide + g.comparison + rightSide + "?");
    switch(g.comparison){
      case "<":
        return leftSide < rightSide;
      case ">":
        return leftSide > rightSide;
      case "<=":
        return leftSide <= rightSide;
      case ">=":
        return leftSide >= rightSide;
      case "=":
        return leftSide == rightSide;
      default:
        throw new RuntimeException("Unknown operator " + g.comparison);
    }
  }
}
