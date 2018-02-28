import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 616 Coconuts, Revisited
Problem url: https://uva.onlinejudge.org/external/6/616.pdf
Author: Andrey Yemelyanov
*/
public class _616 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int nCoconuts = s.nextInt();
      if(nCoconuts < 0) break;
      int nPeople = howManyPersonsShipwrecked(nCoconuts);
      if(nPeople < 0) System.out.printf("%d coconuts, no solution\n", nCoconuts);
      else System.out.printf("%d coconuts, %d people and 1 monkey\n", nCoconuts, nPeople);
    }
  }

  static int howManyPersonsShipwrecked(int nCoconuts){
    for(int nPeople = (int)ceil(sqrt(nCoconuts)); nPeople > 0; nPeople--){
      if(simulate(nCoconuts, nPeople)) return nPeople;
    }
    return -1;
  }

  static boolean simulate(int nCoconuts, int nPeople){
    if(nCoconuts <= nPeople) return false;
    for(int i = 0; i < nPeople; i++){
      if(nCoconuts % nPeople != 1) return false;
      nCoconuts -= nCoconuts / nPeople + 1;
    }
    return nCoconuts % nPeople == 0;
  }
}
