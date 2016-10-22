package helvidios.cp.ch1.adhoc.time;

import java.util.Scanner;

public class _10339_WatchingWatches {
	static final double cycle = 43200.0; // 12 hours in seconds
	
	public static void main(String... args){
		String data = "1 2\r\n" + 
				"0 7\n"+
				"11 19";
		Scanner scanner = new Scanner(data);
		while(true){
			if(!scanner.hasNextLine()) break;
			String line = scanner.nextLine().trim();
			String[] items = line.split(" ");
			int k = Integer.parseInt(items[0]);
			int m = Integer.parseInt(items[1]);
			int minutesToSync = nextSync(k, m);
			int hours = minutesToSync / 60;
			if(hours == 0) hours = 12;
			int minutes = minutesToSync % 60;
			System.out.println(k + " " + m + " " + (hours < 10 ? "0"+hours : hours) + ":" + (minutes < 10 ? "0"+minutes : minutes));
		}
		scanner.close();
	}
	
	/**
	 * The clocks are in sync when x(1 - k/86400) % 43200 = x(1 - m/86400) % 43200
	 * That is, the clocks are in sync when the difference between them is exactly 12 hrs (43200 seconds).
	 * x(1 - k/86400) = x(1 - m/86400) (mod 43200)
	 * or
	 * x(|m-k|/86400) = 0 (mod 43200)
	 * which is equivalent to
	 * x(|m-k|/86400) = 43200
	 * x = (43200*86400) / |m-k|, where x is real seconds elapsed before the clocks are in sync
	 * A clock goes slower by 1 - k/86400 every second.
	 * So the secondsElapsedOnSlowClock = (1 - k/86400)*x
	 */
	public static int nextSync(int k, int m){
		double secondsElapsedOnWatch = (((86400 - k) * 43200.0) / Math.abs(m - k));
		int minutesToSync = ((int) Math.round(secondsElapsedOnWatch / 60.0)) % 720;
		return minutesToSync;
	}
}
