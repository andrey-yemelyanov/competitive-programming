import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10393 The One-Handed Typist
Problem url: https://uva.onlinejudge.org/external/103/10393.pdf
Author: Andrey Yemelyanov
*/
public class _10393 {
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      int F = s.nextInt(); int N = s.nextInt();
      Set<Integer> missingFingers = new HashSet<>();
      while(F-- > 0) missingFingers.add(s.nextInt());
      List<String> words = new ArrayList<>();
      while(N-- > 0) words.add(s.next());
      Set<String> typableWords = getTypableWords(missingFingers, words);
      List<String> longestTypableWords = getWordsOfLength(getLongestWordLength(typableWords), typableWords);
      System.out.println(longestTypableWords.size());
      for(String word : longestTypableWords){
        System.out.println(word);
      }
    }
  }

  static List<String> getWordsOfLength(int len, Set<String> words){
    List<String> list = new ArrayList<>();
    for(String word : words){
      if(word.length() == len) list.add(word);
    }
    return list;
  }

  static int getLongestWordLength(Set<String> words){
    int maxLen = 0;
    for(String word : words){
      maxLen = max(maxLen, word.length());
    }
    return maxLen;
  }

  static SortedSet<String> getTypableWords(Set<Integer> missingFingers, List<String> words){
    SortedSet<String> typableWords = new TreeSet<>();
    for(String word : words){
      boolean wordIsTypable = true;
      for(char c : word.toCharArray()){
        if(missingFingers.contains(keyToFingerMap.get(c))){
          wordIsTypable = false;
          break;
        }
      }
      if(wordIsTypable) typableWords.add(word);
    }
    return typableWords;
  }

  static Map<Character, Integer> keyToFingerMap = new HashMap<>();
  static{
    char[][] fingerToKey = new char[11][];
    fingerToKey[1] = "qaz".toCharArray();
    fingerToKey[2] = "wsx".toCharArray();
    fingerToKey[3] = "edc".toCharArray();
    fingerToKey[4] = "rfvtgb".toCharArray();
    fingerToKey[5] = " ".toCharArray();
    fingerToKey[6] = " ".toCharArray();
    fingerToKey[7] = "yhnujm".toCharArray();
    fingerToKey[8] = "ik,".toCharArray();
    fingerToKey[9] = "ol.".toCharArray();
    fingerToKey[10] = "p;/".toCharArray();
    for(int i = 1; i < fingerToKey.length; i++){
      for(char c : fingerToKey[i]){
        keyToFingerMap.put(c, i);
      }
    }
  }
}
