package helvidios.cp.ch3.greedy;

import java.util.*;

public class _12405_Scarecrow{
	static final char fertile = '.';
	static class Interval implements Comparable<Interval>{
		public int from;
		public int to;
		public Interval(List<Integer> coverage){
			this.from = coverage.get(0);
			this.to = coverage.get(coverage.size() - 1);
		}
		
		@Override
		public int compareTo(Interval i) {
			if(this.from == i.from) return Integer.compare(i.to, this.to);
			return Integer.compare(this.from, i.from);
		}
	}
	public static void main(String[] args){
		String data = "3\n3\r\n.#.\r\n11\r\n...##....##\r\n2\r\n##";
		String data2 = "2\r\n10\r\n..#..##...\r\n13\r\n..#..##....#.";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		for(int i = 1; i <= nTestCases; i++){
			int n = scanner.nextInt();
			char[] field = scanner.next().toCharArray();
			int ans = getMinScarecrows(field);
			System.out.printf("Case %1$d: %2$d\n", i, ans);
		}
		scanner.close();
	}
	
	static int getMinScarecrows(char[] field){
		List<Interval> intervals = buildIntervals(field);
		Collections.sort(intervals);
		int nScarecrows = 0; int coverageTo = -1;
		for(int i = 0; i < intervals.size(); i++){
			Interval interval = intervals.get(i);
			if((i == intervals.size() - 1 && interval.to > coverageTo) ||
				(interval.from > coverageTo && interval.to > coverageTo)){
				coverageTo = interval.to;
				nScarecrows++;
			}
		}
		return nScarecrows;
	}
	
	static List<Interval> buildIntervals(char[] field){
		List<Interval> intervals = new ArrayList<Interval>();
		for(int i = 0; i < field.length; i++){
			List<Integer> coverage = new ArrayList<Integer>();
			if(i > 0 && field[i - 1] == fertile) coverage.add(i - 1);
			if(field[i] == fertile) coverage.add(i);
			if(i < field.length - 1 && field[i + 1] == fertile) coverage.add(i + 1);
			if(!coverage.isEmpty()){
				intervals.add(new Interval(coverage));
			} 
		}
		return intervals;
	}
}
