import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11048 Automatic Correction of Misspellings
Problem url: https://uva.onlinejudge.org/external/110/11048.pdf
Author: Andrey Yemelyanov
*/
public class _11048 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    Map<String, Integer> dict = new HashMap<>();
    int i = 0;
    while(N-- > 0){
      dict.put(s.next(), i++);
    }
    int Q = s.nextInt();
    while(Q-- > 0){
      String word = s.next();
      if(dict.containsKey(word)){
        System.out.printf("%s is correct\n", word);
      }else{
        String misspelling = getMisspelling(word, dict);
        if(misspelling == null) System.out.printf("%s is unknown\n", word);
        else System.out.printf("%s is a misspelling of %s\n", word, misspelling);
      }
    }
  }

  static String getMisspelling(String str, Map<String, Integer> dict){
    int earliestWord = Integer.MAX_VALUE;
    String misspelling = null;
    for(String word : getAllMisspellings(str)){
      if(dict.containsKey(word) && dict.get(word) < earliestWord){
        earliestWord = dict.get(word);
        misspelling = word;
      }
    }
    return misspelling;
  }

  static List<String> getAllMisspellings(String str){
    List<String> misspellings = new ArrayList<>();
    misspellings.addAll(oneLetterMissing(str));
    misspellings.addAll(oneLetterTooMuch(str));
    misspellings.addAll(oneLetterWrong(str));
    misspellings.addAll(twoAdjacentLettersWrong(str));
    return misspellings;
  }

  static List<String> oneLetterMissing(String str){
    // e.g. 'letter' is written as 'leter'
    List<String> words = new ArrayList<>();
    for(int i = 0; i < str.length(); i++){
      words.add(new StringBuilder(str).deleteCharAt(i).toString());
    }
    return words;
  }

  static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
  static List<String> oneLetterTooMuch(String str){
    // e.g. 'letter' is written as 'lettter'
    List<String> words = new ArrayList<>();
    for(int i = 0; i < str.length(); i++){
      for(int j = 0; j < alphabet.length; j++){
        words.add(str.substring(0, i) + alphabet[j] + str.substring(i));
      }
    }
    for(int j = 0; j < alphabet.length; j++){
      words.add(str + alphabet[j]);
    }
    return words;
  }

  static List<String> oneLetterWrong(String str){
    // e.g. 'letter' is written 'ketter'
    List<String> words = new ArrayList<>();
    for(int i = 0; i < str.length(); i++){
      for(int j = 0; j < alphabet.length; j++){
        char[] letters = str.toCharArray();
        letters[i] = alphabet[j];
        words.add(new String(letters));
      }
    }
    return words;
  }

  static List<String> twoAdjacentLettersWrong(String str){
    // The order of two adjacent letters is wrong (e.g., 'letter' is written 'lettre')
    List<String> words = new ArrayList<>();
    for(int i = 0; i < str.length() - 1; i++){
      char[] letters = str.toCharArray();
      swap(letters, i, i + 1);
      words.add(new String(letters));
    }
    return words;
  }

  static void swap(char[] letters, int i, int j){
    char temp = letters[i];
    letters[i] = letters[j];
    letters[j] = temp;
  }
}
