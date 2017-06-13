import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11621 Small Factors
Problem url: https://uva.onlinejudge.org/external/116/11621.pdf
Author: Andrey Yemelyanov
*/
public class _11621_SmallFactors {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    List<Long> list = generateNumbers();
	  while(s.hasNext()){
      int m = s.nextInt();
      if(m == 0) break;
      System.out.println(list.get(upperBound(list, m)));
    }
  }

  static int upperBound(List<Long> arr, int key){
		int from = 0;
		int to = arr.size() - 1;
		while(to >= from){
			int mid = from + (to - from) / 2;
			if(arr.get(mid) == key) return mid;
			if(arr.get(mid) > key){
				if(mid - 1 < 0 || arr.get(mid - 1) < key) return mid;
				to = mid - 1;
			}else from = mid + 1;
		}
		return to;
	}

  static List<Long> generateNumbers(){
    List<Long> l = new ArrayList<>();
    for(int i = 0; i < 32; i++){
      for(int j = 0; j < 32; j++){
        long n = (long)pow(2, i) * (long)pow(3, j);
        if(n > Integer.MAX_VALUE) break;
        l.add(n);
      }
    }
    Collections.sort(l);
    return l;
  }
}
