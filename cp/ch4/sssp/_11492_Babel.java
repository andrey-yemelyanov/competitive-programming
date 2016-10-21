package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;

public class _11492_Babel{
  public static void main(String[] args){
    String data = "4\r\nportugues frances\r\ningles espanhol red\r\nespanhol portugues amigo\r\nfrances ingles date\r\nespanhol ingles actual\r\n4\r\nportugues alemao\r\ningles espanhol red\r\nespanhol portugues amigo\r\nfrances ingles date\r\nespanhol ingles actual\r\n6\r\nportugues frances\r\ningles espanhol red\r\nespanhol portugues amigo\r\nfrances ingles date\r\nfrances espanhol la\r\nportugues ingles a\r\nespanhol ingles actual\r\n0";
    Scanner s = new Scanner(data);
    while(s.hasNext()){
      int M = s.nextInt(); if(M == 0) break;
      String startLang = s.next(); String finishLang = s.next();
      Map<String, Set<String>> graph = new HashMap<>();
      Map<String, List<String>> langMap = new HashMap<>();
      langMap.put(startLang, new ArrayList<String>());
      langMap.put(finishLang, new ArrayList<String>());
      for(int i = 0; i < M; i++){
        String lang1 = s.next(); String lang2 = s.next(); String word = s.next();
        if(!langMap.containsKey(lang1)) langMap.put(lang1, new ArrayList<String>());
        if(!langMap.containsKey(lang2)) langMap.put(lang2, new ArrayList<String>());
        langMap.get(lang1).add(word);
        langMap.get(lang2).add(word);
      }
      for(String lang : langMap.keySet()){
        List<String> words = new ArrayList<>(langMap.get(lang));
        for(int i = 0; i < words.size(); i++){
          for(int j = i + 1; j < words.size(); j++){
            String word1 = words.get(i); String word2 = words.get(j);
            if(!graph.containsKey(word1)) graph.put(word1, new HashSet<String>());
            if(!graph.containsKey(word2)) graph.put(word2, new HashSet<String>());
            if(word1.charAt(0) != word2.charAt(0)){
              graph.get(word1).add(word2);
              graph.get(word2).add(word1);
            }
          }
        }
      }
      //System.out.println(graph);
      // add source and sink vertices
      String source = "source";
      String sink = "sink";
      graph.put(source, new HashSet<String>());
      graph.put(sink, new HashSet<String>());
      for(String vertex : langMap.get(startLang)){
        graph.get(source).add(vertex);
        graph.get(vertex).add(source);
      }
      for(String vertex : langMap.get(finishLang)){
        graph.get(sink).add(vertex);
        graph.get(vertex).add(sink);
      }
      dijkstra(source, sink, graph);
      if(dist.get(sink) == INF) System.out.println("impossivel");
      else System.out.println(dist.get(sink));
    }
  }

  static class Pair{
    public String word;
    public int dist;
    public Pair(String word, int dist){ this.word = word; this.dist = dist; }
  }
  static final int INF = Integer.MAX_VALUE;
  static Map<String, Integer> dist;
  static void dijkstra(String source, String sink, Map<String, Set<String>> graph){
    dist = new HashMap<>();
    for(String word : graph.keySet()){
      dist.put(word, INF);
    }
    dist.put(source, 0);
    PriorityQueue<Pair> pq = new PriorityQueue<>(10, new Comparator<Pair>(){
      @Override
      public int compare(Pair p1, Pair p2){
        return Integer.compare(dist.get(p1.word), dist.get(p2.word));
      }
    });
    pq.add(new Pair(source, 0));
    while(!pq.isEmpty()){
      Pair p = pq.remove();
      if(p.dist > dist.get(p.word)) continue;
      for(String nextWord : graph.get(p.word)){
        int cost = !nextWord.equals(sink) ? nextWord.length() : 0;
        int altDist = dist.get(p.word) + cost;
        if(altDist < dist.get(nextWord)){
          dist.put(nextWord, altDist);
          pq.add(new Pair(nextWord, altDist));
        }
      }
    }
  }
}
