import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 123 Searching Quickly
Problem url: https://uva.onlinejudge.org/external/1/123.pdf
Author: Andrey Yemelyanov
*/
public class _123_SearchingQuickly {
  static class KeywordOccurrence{
   public String title;
   public int pos;
   public KeywordOccurrence(String title, int pos){
     this.title = title;
     this.pos = pos;
   }
   public String toString(){
     return title + "[" + pos + "]";
   }
 }
 static class Token{
   public String text;
   public int pos;
   public Token(String text, int pos){
     this.text = text;
     this.pos = pos;
   }
   public String toString(){
     return text + "[" + pos + "]";
   }
 }
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    Set<String> stopwords = new HashSet<>();
    List<String> titles = new ArrayList<>();
    boolean processingStopwords = true;
    while(s.hasNext()){
      String line = s.nextLine();
      if(line.equals("::")){
        processingStopwords = false;
      }else if(processingStopwords){
        stopwords.add(line);
      }else{
        titles.add(line);
      }
    }
    System.out.print(toString(buildKwicIndex(titles, stopwords)));
  }

  static SortedMap<String, List<KeywordOccurrence>> buildKwicIndex(List<String> titles, Set<String> stopwords){
    SortedMap<String, List<KeywordOccurrence>> index = new TreeMap<>();
    for(String title : titles){
      for(Token token : tokenize(title)){
        if(!stopwords.contains(token.text)){
          if(!index.containsKey(token.text)){
            index.put(token.text, new ArrayList<>());
          }
          index.get(token.text).add(new KeywordOccurrence(title.toLowerCase(), token.pos));
        }
      }
    }
    return index;
  }

  static List<Token> tokenize(String title){
     title = title.toLowerCase();
     List<Token> keywords = new ArrayList<>();
     int i = -1;
     String token = "";
     int tokenPos = 0;
     while(++i < title.length()){
       char c = title.charAt(i);
       if(c == ' '){
         if(token.length() > 0){
           keywords.add(new Token(token, tokenPos));
           token = "";
         }
       }else{
         if(token.length() == 0) tokenPos = i;
         token += c;
       }
     }
     if(token.length() > 0){
       keywords.add(new Token(token, tokenPos));
     }
     return keywords;
  }

  static String toString(SortedMap<String, List<KeywordOccurrence>> kwicIndex){
    StringBuilder sb = new StringBuilder();
    for(String keyword : kwicIndex.keySet()){
      for(KeywordOccurrence keywordOccurrence : kwicIndex.get(keyword)){
        sb.append(highlightKeyword(keywordOccurrence.title, keyword, keywordOccurrence.pos));
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  static String highlightKeyword(String title, String keyword, int pos){
    //System.out.println("Search '"+keyword+"' in '"+title+"'");
    return title.substring(0, pos)
            + title.substring(pos, pos + keyword.length()).toUpperCase()
            + title.substring(pos + keyword.length());
  }
}
