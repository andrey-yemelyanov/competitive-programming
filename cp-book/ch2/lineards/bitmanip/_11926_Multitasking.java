package helvidios.cp.ch2.lineards.bitmanip;

import java.util.Scanner;

public class _11926_Multitasking {
	public static class Task{
		public int start;
		public int end;
		public int interval;
		public int getNumberOfOccurrences(int maxTime){
			if(interval == 0) return 1;
			return (maxTime - start) / interval + 1;
		}
		public int length(){
			return end - start;
		}
		public String toString(){
			return start + " " + end + " " + interval;
		}
	}
	public static void main(String... args){
		String data = "2 0\r\n" + 
				"10 20\r\n" + 
				"20 30\r\n" + 
				"2 0\r\n" + 
				"10 30\r\n" + 
				"20 21\r\n" + 
				"1 1\r\n" + 
				"1000 2000\r\n" + 
				"0 10 1000\r\n" + 
				"0 0";
		Scanner scanner = new Scanner(data);
		while(true){
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			if(n == 0 && m == 0) break;
			Task[] tasks = new Task[n + m];
			for(int i = 0; i < tasks.length; i++){
				int start = scanner.nextInt();
				int end = scanner.nextInt();
				int interval = 0;
				if(i >= n){
					interval = scanner.nextInt();
				}
				Task task = new Task();
				task.start = start;
				task.end = end;
				task.interval = interval;
				tasks[i] = task;
			}
			if(conflictFound(tasks)) System.out.println("CONFLICT");
			else System.out.println("NO CONFLICT");
		}
		scanner.close();
	}
	
	public static final int maxTime = 1000000;
	public static boolean conflictFound(Task[] tasks){
		boolean[] calendar = new boolean[maxTime + 1];  
		for(Task task : tasks){
			int nOccurrences = task.getNumberOfOccurrences(maxTime);
			for(int occurrence = 1; occurrence <= nOccurrences; occurrence++){
				if(inConflict(calendar, task, occurrence)) return true;
				bookTime(calendar, task, occurrence);
			}
		}
		return false;
	}
	
	public static int[] getRange(Task task, int occurrence){
		int from = task.start + task.interval * (occurrence - 1);
		int to = from + task.length();
		if(to > maxTime) to = maxTime;
		return new int[]{from, to};
	}
	
	public static void bookTime(boolean[] calendar, Task task, int occurrence){
		int[] range = getRange(task, occurrence);
		for(int i = range[0]; i <= range[1] - 1; i++){
			calendar[i] = true;
		}
	}
	
	public static boolean inConflict(boolean[] calendar, Task task, int occurrence){
		int[] range = getRange(task, occurrence);
		for(int i = range[0]; i <= range[1] - 1; i++){
			if(calendar[i]) return true;
		}
		return false;
	}
}
