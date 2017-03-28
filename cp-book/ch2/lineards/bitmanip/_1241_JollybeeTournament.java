import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 1241 Jollybee Tournament
Problem url: https://uva.onlinejudge.org/external/12/1241.pdf
Author: Andrey Yemelyanov
*/
public class _1241_JollybeeTournament {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int T = s.nextInt();
    while(T-- > 0){
      int N = s.nextInt(); int M = s.nextInt();
      int nPlayers = (int)pow(2, N);
      BitSet initialRound = new BitSet(nPlayers);
      for(int i = 0; i < nPlayers; i++){
        initialRound.set(i);
      }
      for(int i = 0; i < M; i++){
        initialRound.set(s.nextInt() - 1, false);
      }
      System.out.println(playTournament(initialRound, nPlayers));
    }
  }

  static int playTournament(BitSet round, int nPlayers){
    if(nPlayers == 1) return 0;
    int nWalkoverMatches = 0;
    BitSet nextRound = new BitSet(nPlayers / 2);
    for(int i = 0; i < nPlayers; i += 2){
      boolean player1 = round.get(i);
      boolean player2 = round.get(i + 1);
      if(player1){
        if(!player2) nWalkoverMatches++;
        nextRound.set(i / 2);
      }else if(player2){
        if(!player1) nWalkoverMatches++;
        nextRound.set(i / 2);
      }else{
        nextRound.set(i / 2, false);
      }
    }
    nWalkoverMatches += playTournament(nextRound, nPlayers / 2);
    return nWalkoverMatches;
  }
}
