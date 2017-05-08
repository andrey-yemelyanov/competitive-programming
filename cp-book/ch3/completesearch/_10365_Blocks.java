import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 10365 Blocks
Problem url: https://uva.onlinejudge.org/external/103/10365.pdf
Author: Andrey Yemelyanov
*/
public class _10365_Blocks {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt();
    while(nTests-- > 0){
    	System.out.println(getMinArea(s.nextInt()));
    }
  }

static int getArea(int x, int y, int z){
	return 2 * (x * y + x * z + y * z);
}

static int getMinArea(int N){
  	int minArea = Integer.MAX_VALUE;
  	for(int x = 1; x <= N; x++){
  		if(N % x != 0) continue;
  		for(int y = 1; y <= N / x; y++){
  			for(int z = 1; z <= N / (x * y); z++){
  				if(x * y * z == N){
  					minArea = min(minArea, getArea(x, y, z));
  				}
  			}
  		}
  	}
    return minArea;
  }
}
