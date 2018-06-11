import java.util.*;
import static java.lang.Math.*;
import static java.lang.System.*;
import java.util.stream.*;

/*
Problem name: 10058 Jimmi's Riddles
Problem url: https://uva.onlinejudge.org/external/100/10058.pdf
Author: Andrey Yemelyanov
Big thanks to http://www.algorithmist.com/index.php/UVa_10058 for a neat solution
*/
public class _10058 {
  private static final String VERB = "(hate|love|know|like)s*";
  private static final String NOUN = "(tom|jerry|goofy|mickey|jimmy|dog|cat|mouse)";
  private static final String ARTICLE = "(a|the)";
  private static final String ACTOR = String.format("(%s|%s %s)", NOUN,  ARTICLE, NOUN);
  private static final String ACTIVE_LIST = String.format("(%s and )*%s", ACTOR, ACTOR);
  private static final String ACTION = String.format("%s %s %s", ACTIVE_LIST, VERB, ACTIVE_LIST);
  private static final String STATEMENT = String.format("(%s , )*%s", ACTION, ACTION);
  public static void main(String[] args){
    Scanner s = new Scanner(in);
    while(s.hasNext()){
      String riddle = s.nextLine();
      if(riddle.replaceAll(" +", " ").trim().matches(STATEMENT)){
        System.out.println("YES I WILL");
      }else{
        System.out.println("NO I WON'T");
      }
    }
  }
}
