package helvidios.cp.ch2.lineards.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _12207_ThatIsYourQueue {
	public static void main(String... args){
		String data = "3 6\r\n" + 
				"N\n"
				+ "N\n"
				+ "E 1\r\n" + 
				"N\n"
				+ "N\r\n" + 
				"N\r\n" + 
				"10 2\r\n" + 
				"N\n"
				+ "N\n"
				+ "0 0\n";
		Scanner scanner = new Scanner(data);
		int caseNum = 0;
		while(true){
			int population = scanner.nextInt();
			int nCommands = scanner.nextInt();
			if(population == 0 && nCommands == 0) break;
			String[] commands = new String[nCommands];
			for(int i = 0; i < nCommands; i++){
				if(scanner.next().equals("E")){
					commands[i] = "E " + scanner.nextInt();
				}else {
					commands[i] = "N";
				}
			}
			System.out.println(output(getProcessingOrder(population, commands), ++caseNum));
		}
		scanner.close();
	}
	
	static String output(Queue<Integer> q, int caseNum){
		StringBuilder builder = new StringBuilder();
		builder.append("Case " + caseNum + ":\n");
		while(!q.isEmpty()){
			builder.append(q.remove());
			if(q.size() > 0) builder.append("\n");
		}
		return builder.toString();
	}
	
	public static Queue<Integer> getProcessingOrder(long population, String[] commands){
		Deque<Integer> dq = new LinkedList<Integer>();
		long maxItems = population <= 1000 ? population : 1000;
		for(int i = 1; i <= maxItems; i++){
			dq.addLast(i);
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 0; i < commands.length; i++){
			if(commands[i].equals("N")){
				int patient = dq.removeFirst();
				q.add(patient);
				dq.addLast(patient);
			}
			if(commands[i].contains("E")){
				int patient = Integer.parseInt(commands[i].split(" ")[1]);
				dq.remove(patient);
				dq.addFirst(patient);
			}
		}
		
		return q;
	}
}
