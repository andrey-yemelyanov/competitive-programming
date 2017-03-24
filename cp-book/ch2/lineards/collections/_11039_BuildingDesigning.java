import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11039 Building designing
Problem url: https://uva.onlinejudge.org/external/110/11039.pdf
Author: Andrey Yemelyanov
*/
public class _11039_BuildingDesigning {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
      int N = s.nextInt();
      int[] floors = new int[N];
      for(int i = 0; i < N; i++){
        floors[i] = s.nextInt();
      }
      System.out.println(maxBuildingHeight(floors));
    }
  }

  static int maxBuildingHeight(int[] floors){
    int maxHeight = 0;
    if(floors.length == 0) return maxHeight;
    Arrays.sort(floors);
    int i = crossoverPoint(floors);
    if(i < 0) return 1;
    int pos = i;
    int neg = i - 1;
    int highestFloor = 0;
    if(floors[pos] <= abs(floors[neg])) highestFloor = floors[pos++];
    else highestFloor = floors[neg--];
    maxHeight++;
    while(pos < floors.length && neg >= 0){
      if(highestFloor < 0){
        pos = nextAbsLargerRight(highestFloor, pos, floors);
        if(pos < floors.length){
          highestFloor = floors[pos++];
          maxHeight++;
        }
      }else{
        neg = nextAbsLargerLeft(highestFloor, neg, floors);
        if(neg >= 0){
          highestFloor = floors[neg--];
          maxHeight++;
        }
      }
    }
    if(highestFloor < 0 && pos < floors.length){
      pos = nextAbsLargerRight(highestFloor, pos, floors);
      if(pos < floors.length){
        maxHeight++;
      }
    }
    if(highestFloor > 0 && neg >= 0){
      neg = nextAbsLargerLeft(highestFloor, neg, floors);
      if(neg >= 0){
        maxHeight++;
      }
    }
    return maxHeight;
  }

  static int crossoverPoint(int[] floors){
    for(int i = 0; i < floors.length - 1; i++){
      if(floors[i] < 0 && floors[i + 1] > 0) return i + 1;
    }
    return -1;
  }

  static int nextAbsLargerRight(int highestFloor, int pos, int[] floors){
    for(int i = pos; i < floors.length; i++){
      if(floors[i] > abs(highestFloor)) return i;
    }
    return floors.length;
  }

  static int nextAbsLargerLeft(int highestFloor, int neg, int[] floors){
    for(int i = neg; i >= 0; i--){
      if(abs(floors[i]) > highestFloor) return i;
    }
    return -1;
  }
}
