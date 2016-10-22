package helvidios.cp.ch2.nonlineards.pq;

import java.util.PriorityQueue;
import java.util.Scanner;

public class _10954_AddAll {
	public static void main(String... args){
		String data3 = "5\r\n" + 
				"1 2 3 4 5\r\n" + 
				"0";
		Scanner scanner = new Scanner(data3);
		while(true){
			int n = scanner.nextInt();
			if(n == 0) break;
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			while(n-- > 0){
				pq.add(scanner.nextInt());
			}
			int sum = 0;
			int cost = 0;
			while(pq.size() > 1){
				sum = pq.poll() + pq.poll();
				cost += sum;
				pq.add(sum);
			}
			System.out.println(cost);
		}
		scanner.close();
	}
}
