import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 540 Team Queue
Problem url: https://uva.onlinejudge.org/external/5/540.pdf
Author: Andrey Yemelyanov
*/
public class _540_TeamQueue {
  static class TeamQueue{
    class Node{
      public int player;
      public Node next;
      public Node(int player){
        this.player = player;
      }
      public String toString(){
        return Integer.toString(player);
      }
    }
    private Node head;
    private Node tail;
    private int size;
    private Map<Integer, Integer> playerToTeamMap;
    private Map<Integer, Node> teamTailMap;
    public TeamQueue(){
      playerToTeamMap = new HashMap<>();
      teamTailMap = new HashMap<>();
    }
    public void mapPlayerToTeam(int player, int team){
      playerToTeamMap.put(player, team);
      teamTailMap.put(team, null);
    }
    public void enqueue(int player){
      int team = playerToTeamMap.get(player);
      Node newPlayer = new Node(player);
      if(head == null){
        head = newPlayer;
        tail = newPlayer;
      }else{
        Node teamTail = teamTailMap.get(team);
        if(teamTail == null){
          tail.next = newPlayer;
          tail = newPlayer;
        }else{
          newPlayer.next = teamTail.next;
          teamTail.next = newPlayer;
          if(tail == teamTail) tail = newPlayer;
        }
      }
      teamTailMap.put(team, newPlayer);
      size++;
      //System.out.println(toString());
    }
    public int dequeue(){
      if(size == 0) throw new RuntimeException("Cannot dequeue an empty team queue.");
      int player = head.player;
      int team = playerToTeamMap.get(player);
      Node teamTail = teamTailMap.get(team);
      Node nodeToRemove = head;
      if(teamTail != null && teamTail == nodeToRemove){
        teamTailMap.remove(team);
      }
      head = head.next;
      nodeToRemove.next = null;
      size--;
      //System.out.println(toString());
      return player;
    }
    public int size(){
      return size;
    }
    public String toString(){
      Node current = head;
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      while(current != null){
        sb.append(current.player + " ");
        current = current.next;
      }
      sb.append("]");
      sb.append(" tail=" + tail + " head=" + head + " teamTailMap=" + teamTailMap.toString());
      return sb.toString();
    }
  }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int scenario = 1;
    while(true){
      int nTeams = s.nextInt();
      if(nTeams == 0) break;
      TeamQueue tq = new TeamQueue();
      for(int team = 0; team < nTeams; team++){
        int nPlayers = s.nextInt();
        for(int i = 0; i < nPlayers; i++){
          tq.mapPlayerToTeam(s.nextInt(), team);
        }
      }
      s.nextLine();
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("Scenario #%d\n", scenario++));
      while(true){
        String command = s.nextLine();
        if(command.equals("STOP")) break;
        if(command.equals("DEQUEUE") && tq.size() > 0) sb.append(tq.dequeue() + "\n");
        else{
          int player = Integer.parseInt(command.split("\\s+")[1]);
          tq.enqueue(player);
        }
      }
      System.out.println(sb.toString());
    }
  }
}
