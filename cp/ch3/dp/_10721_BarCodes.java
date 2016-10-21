import java.util.*;
import static java.lang.Math.*;

public class _10721_BarCodes {
    public static void main(String[] args) {
        String data = "7 4 3\n7 4 2";
      	String data2 = "7 1 3";
        Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			int m = scanner.nextInt();
			memo = new long[n + 1][k + 1];
			for(int i = 0; i < memo.length; i++){
				for(int j = 0; j < memo[0].length; j++){
					memo[i][j] = -1;
				}
			}
			System.out.println(count(n, k, m));
		}
        scanner.close();
    }
	
	static long[][] memo;
	static long count(int n, int k, int m){
		if(k == 1 && n > 0 && n <= m) return 1;
		if(k == 1 && (n == 0 || n > m)) return 0;
		if(memo[n][k] != -1) return memo[n][k];
		long totalCount = 0;
		for(int i = 1; i <= m; i++){
			if(i <= n){
				totalCount += count(n - i, k - 1, m);
			}
		}
		return memo[n][k] = totalCount;
	}
}