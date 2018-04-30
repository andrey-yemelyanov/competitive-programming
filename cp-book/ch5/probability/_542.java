import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 542 France '98
Problem url: https://uva.onlinejudge.org/external/5/542.pdf
Author: Andrey Yemelyanov

P(Brazil wins World Cup) = P(Brazil in final)
  * (P(Brazil beats Italy | Italy in final) + ... + P(Brazil beats Croatia | Croatia in final))
*/
public class _542 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    String[] countries = new String[16];
    for(int i = 0; i < countries.length; i++) countries[i] = s.next();
    double[][] probs = new double[16][16];
    for(int i = 0; i < probs.length; i++){
      for(int j = 0; j < probs.length; j++){
        probs[i][j] = s.nextInt() / 100.0;
      }
    }
    for(int i = 0; i < countries.length; i++){
      double probability = getProbabilityOfWinningWorldCup(i, probs);
      System.out.printf("%-10s p=%.2f%%\n", countries[i], probability * 100);
    }
  }

  static double getProbabilityOfWinningWorldCup(int country, double[][] probs){
    return getProbability(country, WINS_WORLD_CUP, probs);
  }

  static final int SEMI_FINALS = 2;
  static final int FINAL = 3;
  static final int WINS_WORLD_CUP = 4;

  static double getProbability(int country, int stage, double[][] probs){
    double probability = 0.0;
    if(stage == WINS_WORLD_CUP){
      int oppositeHalf = 0;
      if(country < 8) oppositeHalf = 8;
      double probOfReachingFinal = getProbability(country, stage - 1, probs);
      for(int opponent = oppositeHalf; opponent < oppositeHalf + 8; opponent++){
        probability += probOfReachingFinal * probs[country][opponent] * getProbability(opponent, stage - 1, probs);
      }
    }
    else if(stage == FINAL){
      int oppositeQuarter = 0;
      if(country < 4) oppositeQuarter = 4;
      else if(country < 8) oppositeQuarter = 0;
      else if(country < 12) oppositeQuarter = 12;
      else if(country < 16) oppositeQuarter = 8;
      double probOfReachingSemis = getProbability(country, stage - 1, probs);
      for(int opponent = oppositeQuarter; opponent < oppositeQuarter + 4; opponent++){
        probability += probOfReachingSemis * probs[country][opponent] * getProbability(opponent, stage - 1, probs);
      }
    }
    else if(stage == SEMI_FINALS){
      double probOfReachingQuarterFinals = probs[country][getEighthFinalOpponentFor(country)];
      int oppositePair = getOppositePairFor(country);
      for(int opponent = oppositePair * 2; opponent < oppositePair * 2 + 2; opponent++){
        double probOfOpponentReachingQuarterFinals = probs[opponent][getEighthFinalOpponentFor(opponent)];
        probability += probOfReachingQuarterFinals * probs[country][opponent] * probOfOpponentReachingQuarterFinals;
      }
    }
    return probability;
  }

  static int getOppositePairFor(int country){
    int pair = country / 2;
    if(pair % 2 == 0) return pair + 1;
    return pair - 1;
  }

  static int getEighthFinalOpponentFor(int country){
    if(country % 2 == 0) return country + 1;
    return country - 1;
  }
}
