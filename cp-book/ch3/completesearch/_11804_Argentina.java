import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11804 Argentina
Problem url: https://uva.onlinejudge.org/external/118/11804.pdf
Author: Andrey Yemelyanov
*/
public class _11804_Argentina {
  static class Player{
    public String name;
    public int attackAbility;
    public int defendAbility;
    public Player(String name, int attackAbility, int defendAbility){
      this.name = name;
      this.attackAbility = attackAbility;
      this.defendAbility = defendAbility;
    }
    @Override
    public String toString(){
      return name + " " + attackAbility + " " + defendAbility;
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    for(int t = 1; t <= nTests; t++){
      List<Player> players = new ArrayList<>();
      for(int i = 0; i < 10; i++){
        players.add(new Player(s.next(), s.nextInt(), s.nextInt()));
      }
      System.out.printf("Case %d:\n%s\n", t, printFormation(players, getBestFormation(players)));
    }
  }

  static String printFormation(List<Player> players, boolean[] formation){
    return String.format("(%s)\n(%s)",
      IntStream.range(0, players.size())
               .filter(i -> formation[i])
               .mapToObj(i -> players.get(i).name)
               .collect(Collectors.joining(", ")),
      IntStream.range(0, players.size())
               .filter(i -> !formation[i])
               .mapToObj(i -> players.get(i).name)
               .collect(Collectors.joining(", ")));
  }

  static int attackAbility(List<Player> players, boolean[] formation){
    return IntStream.range(0, players.size())
                    .filter(i -> formation[i])
                    .map(i -> players.get(i).attackAbility)
                    .sum();
  }

  static int defendAbility(List<Player> players, boolean[] formation){
    return IntStream.range(0, players.size())
                    .filter(i -> !formation[i])
                    .map(i -> players.get(i).defendAbility)
                    .sum();
  }

  static boolean[] getBestFormation(List<Player> players){
    players.sort((p1, p2) -> p1.name.compareTo(p2.name));
    //System.out.println(players);
    int maxAttackAbility = Integer.MIN_VALUE;
    int maxDefendAbility = Integer.MIN_VALUE;
    boolean[] bestFormation = null;
    for(int p1 = 0; p1 < players.size(); p1++){
      for(int p2 = p1 + 1; p2 < players.size(); p2++){
        for(int p3 = p2 + 1; p3 < players.size(); p3++){
          for(int p4 = p3 + 1; p4 < players.size(); p4++){
            for(int p5 = p4 + 1; p5 < players.size(); p5++){
              boolean[] formation = new boolean[players.size()];
              formation[p1] = true;
              formation[p2] = true;
              formation[p3] = true;
              formation[p4] = true;
              formation[p5] = true;
              int totalAttackAbility = attackAbility(players, formation);
              int totalDefendAbility = defendAbility(players, formation);
              if(totalAttackAbility > maxAttackAbility
                || (totalAttackAbility == maxAttackAbility && totalDefendAbility > maxDefendAbility)){
                  maxAttackAbility = totalAttackAbility;
                  maxDefendAbility = totalDefendAbility;
                  bestFormation = formation;
                }
            }
          }
        }
      }
    }
    return bestFormation;
  }
}
