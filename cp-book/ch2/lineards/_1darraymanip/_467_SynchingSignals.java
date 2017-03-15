import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 467 Synching Signals
Problem url: https://uva.onlinejudge.org/external/4/467.pdf
Author: Andrey Yemelyanov
*/
public class _467_SynchingSignals {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int set = 1;
    while(s.hasNext()){
      String[] tokens = s.nextLine().split("\\s+");
      int[] signals = new int[tokens.length];
      for(int i = 0; i < signals.length; i++){
        signals[i] = Integer.parseInt(tokens[i]);
      }
      int syncTime = getSyncTime(signals);
      if(syncTime == NO_SYNC){
        System.out.printf("Set %d is unable to synch after one hour.\n", set++);
      }else{
        System.out.printf("Set %d synchs again at %d minute(s) and %d second(s) after all turning green.\n", set++, syncTime / 60, syncTime % 60);
      }
    }
  }

  static int getSyncTime(int[] signals){
    boolean simulationIsRunning = false;
    int[] signalState = new int[signals.length]; // all signals are initially GREEN
    for(int time = 1; time <= 3600; time++){
      for(int signal = 0; signal < signals.length; signal++){
        int cycleTime = signals[signal];
        signalState[signal] = getState(time, cycleTime);
        if(signalState[signal] == YELLOW) simulationIsRunning = true;
      }
      if(simulationIsRunning && allGreen(signalState)) return time;
    }
    return NO_SYNC;
  }

  static boolean allGreen(int[] signalState){
    for(int state : signalState){
      if(state != GREEN) return false;
    }
    return true;
  }

  static int GREEN = 0;
  static int YELLOW = 1;
  static int RED = 2;
  static int NO_SYNC = -1;
  static int getState(int time, int cycleTime){
    int period = 2 * cycleTime;
    int modTime = time % period;
    if(modTime >= 0 && modTime < (cycleTime - 5)) return GREEN;
    if(modTime >= (cycleTime - 5) && modTime < cycleTime) return YELLOW;
    return RED;
  }
}
