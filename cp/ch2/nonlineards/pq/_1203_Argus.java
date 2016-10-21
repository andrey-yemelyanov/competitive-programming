package helvidios.cp.ch2.nonlineards.pq;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _1203_Argus {
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	static class QueryInstance{
		public QueryInstance(int id, int time){
			this.id = id;
			this.time = time;
		}
		public int id;
		public int time;
	}
	public static void main(String... args) throws IOException{
		String data = "Register 2004 200\r\n" + 
				"Register 2005 300\r\n" + 
				"#\r\n" + 
				"5\r\n" + 
				"";
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data.getBytes()));
		String line;
		List<Integer[]> list = new ArrayList<Integer[]>();
		while(!(line = reader.readLine()).equals("#")){
			String[] items = line.split(" ");
			list.add(new Integer[]{Integer.parseInt(items[1]), Integer.parseInt(items[2])});
		}
		int k = Integer.parseInt(reader.readLine());
		int[] q = firstKQueries(k, list.toArray(new Integer[0][0]));
		StringBuilder sb = new StringBuilder();
		for(int i : q){
			if(sb.length() != 0) sb.append("\n");
			sb.append(i);
		}
		System.out.println(sb.toString());
	}
	
	public static int[] firstKQueries(int k, Integer[][] queries){
		PriorityQueue<QueryInstance> pq = new PriorityQueue<QueryInstance>(1000, 
				new Comparator<QueryInstance>() {
					@Override
					public int compare(QueryInstance o1, QueryInstance o2) {
						if(o1.time != o2.time) return Integer.compare(o1.time, o2.time);
						return Integer.compare(o1.id, o2.id);
					}
		});
		
		int time = 0;
		while(pq.size() != k){
			time++;
			for(Integer[] query : queries){
				if(time % query[1] == 0){
					pq.add(new QueryInstance(query[0], time));
				}
			}
		}
		
		int[] q = new int[pq.size()];
		for(int i = 0; i < q.length; i++){
			q[i] = pq.poll().id;
		}
		return q;
	}
}
