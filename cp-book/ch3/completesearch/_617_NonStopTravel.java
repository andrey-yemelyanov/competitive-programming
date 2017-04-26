import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 617 Nonstop Travel
Problem url: https://uva.onlinejudge.org/external/6/617.pdf
Author: Andrey Yemelyanov
*/
public class _617_NonStopTravel {
  static class TrafficLight{
    public double L;
    public int G;
    public int Y;
    public int R;
    public TrafficLight(double L, int G, int Y, int R){
      this.L = L;
      this.G = G;
      this.Y = Y;
      this.R = R;
    }

    public boolean isRed(double time){
      double relativeTime = time % (G + Y + R);
      return relativeTime > (G + Y) && relativeTime < (G + Y + R);
    }
  }

  public static void main(String[] args){
    Scanner s = new Scanner(System.in).useLocale(Locale.US);
    int c = 1;
    while(s.hasNext()){
      int N = s.nextInt();
      if(N == -1) break;
      TrafficLight[] lights = new TrafficLight[N];
      for(int i = 0; i < N; i++){
        lights[i] = new TrafficLight(s.nextDouble(), s.nextInt(), s.nextInt(), s.nextInt());
      }
      List<Integer> speeds = travelNonStop(lights);
      if(speeds.size() == 0) System.out.printf("Case %d: No acceptable speeds.\n", c++);
      else System.out.printf("Case %d: %s\n", c++, format(speeds));
    }
  }

  static String format(List<Integer> speeds){
    StringBuilder sb = new StringBuilder();
    int rangeStart = 0; int rangeEnd = 0;
    for(int i = 1; i < speeds.size(); i++){
      if(speeds.get(i) == speeds.get(rangeEnd) + 1){
        rangeEnd++;
      }else{
        if(rangeStart == rangeEnd) sb.append(speeds.get(rangeStart));
        else sb.append(speeds.get(rangeStart) + "-" + speeds.get(rangeEnd));
        sb.append(", ");
        rangeStart = rangeEnd + 1;
        rangeEnd = rangeStart;
      }
    }
    if(rangeStart == rangeEnd) sb.append(speeds.get(rangeStart));
    else sb.append(speeds.get(rangeStart) + "-" + speeds.get(rangeEnd));
    return sb.toString();
  }

  static List<Integer> travelNonStop(TrafficLight[] lights){
    List<Integer> speeds = new ArrayList<>();
    final int MAX_SPEED = 60; final int MIN_SPEED = 30;
    for(int speed = MIN_SPEED; speed <= MAX_SPEED; speed++){
      if(nonStopTravelPossible(lights, speed)){
        speeds.add(speed);
      }
    }
    return speeds;
  }

  static boolean nonStopTravelPossible(TrafficLight[] lights, int speed){
    for(TrafficLight light : lights){
      double arrivalTime = light.L * 3600 / speed;
      if(light.isRed(arrivalTime)) return false;
    }
    return true;
  }
}
