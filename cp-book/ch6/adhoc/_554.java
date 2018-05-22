import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 554 Caesar Cypher
Problem url: https://uva.onlinejudge.org/external/5/554.pdf
Author: Andrey Yemelyanov
*/
public class _554 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    Set<String> dict = new HashSet<>();
    String word = s.next();
    while(!word.equals("#")) {
      dict.add(word);
      word = s.next();
    }
    s.nextLine();
    String message = s.nextLine();
    System.out.println(format(decrypt(message, dict), 60));
  }

  static String format(List<String> words, int maxLineLen){
    StringBuilder sb = new StringBuilder();
    StringJoiner sj = new StringJoiner(" ");
    for(String word : words){
      if(word.length() + sj.length() <= maxLineLen) sj.add(word);
      else{
        if(sb.length() > 0) sb.append("\n");
        sb.append(sj.toString());
        sj = new StringJoiner(" ");
        sj.add(word);
      }
    }
    if(sj.length() > 0)
    {
      if(sb.length() > 0) sb.append("\n");
      sb.append(sj.toString());
    }
    return sb.toString();
  }

  static final char[] ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  static List<String> decrypt(String message, Set<String> dict){
    message = message.toUpperCase();
    for(int shift = 1; shift < 27; shift++){
      List<String> words = shift(message, shift);
      if(mostWordsAreInDictionary(words, dict)) return words;
    }
    return null;
  }

  static boolean mostWordsAreInDictionary(List<String> words, Set<String> dict){
    int nWordsInDict = 0;
    for(String word : words) if(dict.contains(word)) nWordsInDict++;
    return nWordsInDict > (words.size() / 2);
  }

  static List<String> shift(String message, int shiftBy){
    List<String> words = new ArrayList<>();
    StringBuilder word = new StringBuilder();
    for(char c : message.toCharArray()){
      int i = c - 'A' + 1;
      if(c == ' ') i = 0;
      char shifted = ALPHABET[(i + shiftBy) % 27];
      if(shifted == ' '){
        words.add(word.toString());
        word = new StringBuilder();
      }else{
        word.append(shifted);
      }
    }
    if(word.length() > 0) words.add(word.toString());
    return words;
  }
}
