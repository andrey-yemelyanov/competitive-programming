package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;

public class _10067_PlayingWithWheels{
	public static void main(String[] args){
		String data = "2\r\n8 0 5 6\r\n6 5 0 8\r\n5\r\n8 0 5 7\r\n8 0 4 7\r\n5 5 0 8\r\n7 5 0 8\r\n6 4 0 8\r\n0 0 0 0\r\n5 3 1 7\r\n8\r\n0 0 0 1\r\n0 0 0 9\r\n0 0 1 0\r\n0 0 9 0\r\n0 1 0 0\r\n0 9 0 0\r\n1 0 0 0\r\n9 0 0 0";
		Scanner s = new Scanner(data);
		int nTests = s.nextInt();
		while(nTests-- > 0){
			int initial = s.nextInt() * 1000 + s.nextInt() * 100 + s.nextInt() * 10 + s.nextInt();
			int target = s.nextInt() * 1000 + s.nextInt() * 100 + s.nextInt() * 10 + s.nextInt();
			int n = s.nextInt();
			Set<Integer> forbidden = new HashSet<>();
			while(n-- > 0){
				forbidden.add(s.nextInt() * 1000 + s.nextInt() * 100 + s.nextInt() * 10 + s.nextInt());
			}
			System.out.println(bfs(initial, target, forbidden));
		}
	}

	static final int INF = -1;
	static int bfs(int initial, int target, Set<Integer> forbidden){
		if(forbidden.contains(target)) return INF;
		Queue<Integer> q = new LinkedList<>();
		q.add(initial);
		int[][][][] dist = new int[10][10][10][10];
		for(int i = 0; i < dist.length; i++){
			for(int j = 0; j < dist[i].length; j++){
				for(int k = 0; k < dist[i][j].length; k++){
					for(int l = 0; l < dist[i][j][k].length; l++){
						dist[i][j][k][l] = INF;
					}
				}
			}
		}
		dist[d1(initial)][d2(initial)][d3(initial)][d4(initial)] = 0;
		while(!q.isEmpty()){
			int n = q.remove();
			if(n == target) return dist[d1(n)][d2(n)][d3(n)][d4(n)];
			for(int next : nextStates(n, forbidden)){
				if(dist[d1(next)][d2(next)][d3(next)][d4(next)] == INF){
					dist[d1(next)][d2(next)][d3(next)][d4(next)] = dist[d1(n)][d2(n)][d3(n)][d4(n)] + 1;
					q.add(next);
				}
			}
		}
		return INF;
	}

	static List<Integer> nextStates(int n, Set<Integer> forbidden){
		List<Integer> states = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			int next = incrementDigit(n, i);
			if(!forbidden.contains(next)) states.add(next);
			next = decrementDigit(n, i);
			if(!forbidden.contains(next)) states.add(next);
		}
		return states;
	}

	static int incrementDigit(int n, int i){
		int[] d = new int[]{d1(n), d2(n), d3(n), d4(n)};
		d[i] = (d[i] + 1) % 10;
		return toInt(d);
	}

	static int decrementDigit(int n, int i){
		int[] d = new int[]{d1(n), d2(n), d3(n), d4(n)};
		d[i] = (d[i] == 0 ? 9 : d[i] - 1);
		return toInt(d);	
	}

	static int toInt(int[] d){ return d[0] * 1000 + d[1] * 100 + d[2] * 10 + d[3];}
	static int d1(int n){ return n / 1000;}
	static int d2(int n){ return (n % 1000) / 100;}
	static int d3(int n){ return (n % 100) / 10;}
	static int d4(int n){ return n % 10;}
}