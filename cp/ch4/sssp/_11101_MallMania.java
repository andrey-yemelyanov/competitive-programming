package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;

public class _11101_MallMania{
	public static void main(String[] args){
		String data = "4\r\n0 0 0 1 1 1 1 0\r\n6\r\n4 3 4 2 3 2\r\n2 2 2 3\r\n3 3\r\n0\r\n";
		String data2 = "4\r\n0 0\r\n0 1\r\n1 1\r\n1 0\n\n\r\n6\r\n4 3\r\n4 2\r\n3 2\r\n2 2\r\n2 3\r\n3 3\r\n4\r\n0 0\r\n0 1\r\n1 1\r\n1 0\r\n6\r\n3 2\r\n3 1\r\n2 1\r\n1 1\r\n1 2\r\n2 2\r\n4\r\n0 0\r\n0 1\r\n1 1\r\n1 0\r\n6\r\n2 2\r\n2 1\r\n1 1\r\n0 1\r\n0 2\r\n1 2\r\n0";
		Scanner s = new Scanner(data);
		while(s.hasNext()){
			int p1 = s.nextInt();
			if(p1 == 0) break;
			List<Integer> mall1 = new ArrayList<>();
			for(int i = 0; i < p1; i++){
				int avenue = s.nextInt(); int street = s.nextInt();
				mall1.add(toInt(street, avenue));
			}
			int p2 = s.nextInt();
			BitSet mall2 = new BitSet();
			for(int i = 0; i < p2; i++){
				int avenue = s.nextInt(); int street = s.nextInt();
				mall2.set(toInt(street, avenue));
			}
			System.out.println(minDistance(mall1, mall2));
		}
	}

	static int toInt(int street, int avenue){
		return (street << 11) + avenue;
	}

	static int street(int p){
		return p >> 11;
	}

	static int avenue(int p){
		return p & 2047;
	}

	static int minDistance(List<Integer> mall1, BitSet mall2){
		qTail = 0; qHead = 0;
		visited.clear();
		for(int p : mall1){
			q[qTail++] = p;			
			dist[street(p)][avenue(p)] = 0;
			visited.set(p);
		}
		return bfs(mall2);
	}

	static int qHead = 0, qTail = 0;
	static final int MAX = 2001;
	static final int INF = -1;
	static int[] DS = {-1, 0, +1, 0}, DA = {0, -1, 0, +1};
	static int[][] dist = new int[MAX][MAX];
	static int[] q = new int[(int)pow(2, 22)]; // 11 bits for storing street and 11 bits for avenue
	static BitSet visited = new BitSet();
	static int bfs(BitSet targetPoints){
		while(qHead != qTail){
			int p = q[qHead++];
			if(targetPoints.get(p)) return dist[street(p)][avenue(p)];
			for(int i = 0; i < DS.length; i++){
				int street = street(p) + DS[i];
				int avenue = avenue(p) + DA[i];
				if(street < 0 || street >= MAX || avenue < 0 || avenue >= MAX) continue;
				int next = toInt(street, avenue);
				if(!visited.get(next)){
					dist[street][avenue] = dist[street(p)][avenue(p)] + 1;
					q[qTail++] = next;
					visited.set(next);
				}
			}
		}
		return INF;
	}
}