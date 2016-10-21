package helvidios.cp.ch2.lineards.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _12100_PrinterQueue {
	public static class PrintJob{
		public int jobId;
		public int priority;
		public PrintJob(int jobId, int priority){
			this.jobId = jobId;
			this.priority = priority;
		}
		public String toString(){
			return jobId + " " + priority;
		}
	}
	public static void main(String... args){
		String data = "3\r\n" + 
				"1 0\r\n" + 
				"5\r\n" + 
				"4 2\r\n" + 
				"1 2 3 4\r\n" + 
				"6 0\r\n" + 
				"1 1 9 1 1 1";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while(nTestCases-- > 0){
			int nJobs = scanner.nextInt();
			int jobIndex = scanner.nextInt();
			Queue<PrintJob> jobs = new LinkedList<PrintJob>();
			for(int i = 0; i < nJobs; i++){
				jobs.add(new PrintJob(i, scanner.nextInt()));
			}
			System.out.println(getPrintTimeForJob(jobs, jobIndex));
		}
		scanner.close();
	}
	
	public static int getPrintTimeForJob(Queue<PrintJob> jobs, int jobIndex){
		int time = 0;
		while(true){
			PrintJob job = jobs.remove();
			while(moreImportantJobAvailable(jobs, job.priority)){
				jobs.add(job);
				job = jobs.remove();
			}

			// print job
			time++;
			
			if(job.jobId == jobIndex) break;
		}
		return time;
	}
	
	static boolean moreImportantJobAvailable(Queue<PrintJob> jobs, int priority){
		if(jobs.isEmpty()) return false;
		for(PrintJob job : jobs){
			if(job.priority > priority) 
				return true;
		}
		return false;
	}
}
