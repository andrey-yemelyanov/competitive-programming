package helvidios.cp.ch2.nonlineards.pq;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class _11995_ICanGuessDataStructure {
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	public static final String STACK = "stack";
	public static final String QUEUE = "queue";
	public static final String PRIORITY_QUEUE = "priority queue";
	public static final String IMPOSSIBLE = "impossible";
	public static final String NOT_SURE = "not sure";
	public static void main(String... args) throws IOException{
		String data = "6\r\n" + 
				"1 1\r\n" + 
				"1 2\r\n" + 
				"1 3\r\n" + 
				"2 1\r\n" + 
				"2 2\r\n" + 
				"2 3\r\n" + 
				"6\r\n" + 
				"1 1\r\n" + 
				"1 2\r\n" + 
				"1 3\r\n" + 
				"2 3\r\n" + 
				"2 2\r\n" + 
				"2 1\r\n" + 
				"2\r\n" + 
				"1 1\r\n" + 
				"2 2\r\n" + 
				"4\r\n" + 
				"1 2\r\n" + 
				"1 1\r\n" + 
				"2 1\r\n" + 
				"2 2\r\n" + 
				"7\r\n" + 
				"1 2\r\n" + 
				"1 5\r\n" + 
				"1 1\r\n" + 
				"1 3\r\n" + 
				"2 5\r\n" + 
				"1 4\r\n" + 
				"2 4";
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data.getBytes()));
		String line;
		StringBuilder sb = new StringBuilder();
		while((line = reader.readLine()) != null){
			int nCommands = Integer.parseInt(line);
			int[][] log = new int[nCommands][2];
			for(int i = 0; i < log.length; i++){
				String[] items = reader.readLine().split(" ");
				log[i][0] = Integer.parseInt(items[0]);
				log[i][1] = Integer.parseInt(items[1]);
			}
			sb.append(guessDataStructure(log));
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	public static String guessDataStructure(int[][] log){
		boolean isStack = isStack(log);
		boolean isQueue = isQueue(log);
		boolean isPriorityQueue = isPriorityQueue(log);
		if(!isStack && !isQueue && !isPriorityQueue) return IMPOSSIBLE;
		if(isStack && !isQueue && !isPriorityQueue) return STACK;
		if(!isStack && isQueue && !isPriorityQueue) return QUEUE;
		if(!isStack && !isQueue && isPriorityQueue) return PRIORITY_QUEUE;
		return NOT_SURE;
	}
	
	public static boolean isPriorityQueue(int[][] log){
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(100, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1);
			}
		});
		for(int[] item : log){
			if(item[0] == 1){
				pq.add(item[1]);
			}
			else if(item[0] == 2){
				if(pq.isEmpty()) return false;
				int max = pq.poll();
				if(max != item[1]) return false;
			}
		}
		return true;
	}
	
	public static boolean isQueue(int[][] log){
		Queue<Integer> q = new LinkedList<Integer>();
		for(int[] item : log){
			if(item[0] == 1){
				q.add(item[1]);
			}
			else if(item[0] == 2){
				if(q.isEmpty()) return false;
				int dequeue = q.poll();
				if(dequeue != item[1]) return false;
			}
		}
		return true;
	}
	
	public static boolean isStack(int[][] log){
		Stack<Integer> stack = new Stack<Integer>();
		for(int[] item : log){
			if(item[0] == 1){
				stack.push(item[1]);
			}
			else if(item[0] == 2){
				if(stack.isEmpty()) return false;
				int pop = stack.pop();
				if(pop != item[1]) return false;
			}
		}
		return true;
	}
}
