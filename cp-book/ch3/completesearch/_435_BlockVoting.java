import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 435 Block Voting
Problem url: https://uva.onlinejudge.org/external/4/435.pdf
Author: Andrey Yemelyanov
*/
public class _435_BlockVoting {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    int nTests = s.nextInt();
    StringBuilder sb = new StringBuilder();
    while(nTests-- > 0){
      int P = s.nextInt();
      int[] votes = new int[P];
      for(int i = 0; i < P; i++){
        votes[i] = s.nextInt();
      }
      int[] pis = getPowerIndices(votes);
      IntStream.range(0, pis.length)
               .mapToObj(i -> String.format("party %d has power index %d", i + 1, pis[i]))
               .forEach(str -> sb.append(str + "\n"));
      sb.append("\n");
    }
    System.out.print(sb.toString());
  }

  static int[] getPowerIndices(int[] votes){
    int totalVotes = Arrays.stream(votes).sum();
    int majorityVotes = totalVotes / 2 + 1;
    int[] pis = new int[votes.length];
    for(int party = 0; party < votes.length; party++){
      pis[party] = getPartyPowerIndex(party, votes, majorityVotes);
    }
    return pis;
  }

  static int getPowerIndex(int party, int[] votes, int majorityVotes, int i, int totalVotes){
    if(i == votes.length){
      if(totalVotes < majorityVotes && votes[party] + totalVotes >= majorityVotes){
        return 1;
      }
      return 0;
    }
    if(i == party){
      return getPowerIndex(party, votes, majorityVotes, i + 1, totalVotes);
    }else{
      return getPowerIndex(party, votes, majorityVotes, i + 1, totalVotes + votes[i])
              + getPowerIndex(party, votes, majorityVotes, i + 1, totalVotes);
    }
  }

  static int getPartyPowerIndex(int party, int[] votes, int majorityVotes){
    return getPowerIndex(party, votes, majorityVotes, 0, 0);
  }
}
