import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 12515 Movie Police
Problem url: https://uva.onlinejudge.org/external/125/12515.pdf
Author: Andrey Yemelyanov
*/
public class _12515_MoviePolice {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int M = s.nextInt(); int Q = s.nextInt();
    String[] movies = new String[M];
    for(int i = 0; i < M; i++){
      movies[i] = s.next();
    }
    for(int q = 0; q < Q; q++){
      System.out.println(findMovieByClip(movies, s.next()) + 1);
    }
  }

  static int findMovieByClip(String[] movies, String clip){
    int bestMatch = 0;
    int minDiff = Integer.MAX_VALUE;
    for(int i = 0; i < movies.length; i++){
      String movie = movies[i];
      if(movie.length() < clip.length()) continue;
      int diff = diff(movie, clip);
      if(diff < minDiff){
        minDiff = diff;
        bestMatch = i;
      }
    }
    return bestMatch;
  }

  static int diff(String movie, String clip){
    int minHammingDist = Integer.MAX_VALUE;
    for(int i = 0; i <= movie.length() - clip.length(); i++){
      minHammingDist = min(minHammingDist, hammingDistance(movie.substring(i, i + clip.length()), clip));
    }
    return minHammingDist;
  }

  static int hammingDistance(String s1, String s2){
    int nDiffs = 0;
    for(int i = 0; i < s1.length(); i++){
      if(s1.charAt(i) != s2.charAt(i)) nDiffs++;
    }
    return nDiffs;
  }
}
