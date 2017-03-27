import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10698 Football Sort
Problem url: https://uva.onlinejudge.org/external/106/10698.pdf
Author: Andrey Yemelyanov
*/
public class _10698_FootballSort {
  static class Game{
    public String team1;
    public String team2;
    public int scoreTeam1;
    public int scoreTeam2;
    public Game(String team1, String team2, int scoreTeam1, int scoreTeam2){
      this.team1 = team1;
      this.team2 = team2;
      this.scoreTeam1 = scoreTeam1;
      this.scoreTeam2 = scoreTeam2;
    }
    public String toString(){
      return team1 + " " + scoreTeam1 + " - " + scoreTeam2 + " " + team2;
    }
  }
  static class TeamStats{
    public String team;
    public TeamStats(String team){
      this.team = team;
    }
    public int nPoints;
    public int nGamesPlayed;
    public int nScoredGoals;
    public int nSufferedGoals;
    public int goalDiff;
    public double percentPoints;

    public String getPercentagePoints(){
      return percentPoints == NA ? "N/A" : String.format(Locale.US, "%.2f", percentPoints);
    }

    public String toString(){
      return String.format("%16s%4d%4d%4d%4d%4d%7s", team, nPoints, nGamesPlayed,
        nScoredGoals, nSufferedGoals, goalDiff, getPercentagePoints());
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    boolean first = true;
    while(s.hasNext()){
      int nTeams = s.nextInt();
      int nGames = s.nextInt();
      if(nTeams == 0 && nGames == 0) break;
      Set<String> teams = new HashSet<>();
      for(int i = 0; i < nTeams; i++){
        teams.add(s.next());
      }
      List<Game> games = new ArrayList<>();
      for(int i = 0; i < nGames; i++){
        String team1 = s.next();
        int scoreTeam1 = s.nextInt();
        s.next();
        int scoreTeam2 = s.nextInt();
        String team2 = s.next();
        games.add(new Game(team1, team2, scoreTeam1, scoreTeam2));
      }
      if(first){
        first = false;
      }else{
        System.out.println();
      }
      System.out.println(toString(generateStats(teams, games)));
    }
  }

  static String toString(List<TeamStats> stats){
    StringBuilder sb = new StringBuilder();
    TeamStats prevTeam = null;
    for(int i = 0; i < stats.size(); i++){
      TeamStats team = stats.get(i);
      String pos = "";
      if(prevTeam == null || compareTeams(prevTeam, team) != 0){
        // print team position
        pos = "" + (i + 1) + ".";
      }
      sb.append(String.format("%3s%s", pos, team.toString()));
      if(i < stats.size() - 1) sb.append("\n");
      prevTeam = team;
    }
    return sb.toString();
  }

  static int compareTeams(TeamStats team1, TeamStats team2){
    if(team1.nPoints != team2.nPoints) return Integer.compare(team1.nPoints, team2.nPoints);
    if(team1.goalDiff != team2.goalDiff) return Integer.compare(team1.goalDiff, team2.goalDiff);
    if(team1.nScoredGoals != team2.nScoredGoals) return Integer.compare(team1.nScoredGoals, team2.nScoredGoals);
    return 0;
  }

  static List<TeamStats> generateStats(Set<String> teams, List<Game> games){
    List<TeamStats> stats = getStats(groupGames(teams, games));
    Collections.sort(stats, new Comparator<TeamStats>(){
      @Override
      public int compare(TeamStats team1, TeamStats team2){
        int compare = compareTeams(team2, team1);
        if(compare != 0) return compare;
        return team1.team.compareToIgnoreCase(team2.team);
      }
    });
    return stats;
  }

  static final double NA = -1;
  static List<TeamStats> getStats(Map<String, List<Game>> gamesPerTeam){
    List<TeamStats> stats = new ArrayList<>();
    for(String team : gamesPerTeam.keySet()){
      TeamStats teamStats = new TeamStats(team);
      for(Game game : gamesPerTeam.get(team)){
        teamStats.nGamesPlayed += 1;
        teamStats.nScoredGoals += game.scoreTeam1;
        teamStats.nSufferedGoals += game.scoreTeam2;
        teamStats.goalDiff = teamStats.nScoredGoals - teamStats.nSufferedGoals;
        teamStats.nPoints += getPoints(game.scoreTeam1, game.scoreTeam2);
      }
      stats.add(teamStats);
    }
    for(TeamStats teamStats : stats){
      teamStats.percentPoints = teamStats.nGamesPlayed != 0
        ? (double)teamStats.nPoints / (teamStats.nGamesPlayed * 3) * 100
        : NA;
    }
    return stats;
  }

  static int getPoints(int scoreTeam1, int scoreTeam2){
    if(scoreTeam1 > scoreTeam2) return 3;
    if(scoreTeam1 == scoreTeam2) return 1;
    return 0;
  }

  static Map<String, List<Game>> groupGames(Set<String> teams, List<Game> games){
    Map<String, List<Game>> m = new HashMap<>();
    for(String team : teams){
      m.put(team, new ArrayList<>());
    }
    for(Game game : games){
      m.get(game.team1).add(new Game(game.team1, game.team2, game.scoreTeam1, game.scoreTeam2));
      m.get(game.team2).add(new Game(game.team2, game.team1, game.scoreTeam2, game.scoreTeam1));
    }
    //System.out.println(m);
    return m;
  }
}
