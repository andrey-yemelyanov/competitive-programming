package helvidios.cp.ch1.adhoc.time;

import java.util.Scanner;

public class _11650_MirrorClock {
	public static void main(String... args){
		String data = "2\r\n" + 
				"12:00\r\n" + 
				"10:09";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while (nTestCases-- > 0) {
			String[] time = scanner.next().split(":");
			int hour = Integer.parseInt(time[0]);
			int minute = Integer.parseInt(time[1]);
			System.out.println(printTime(getRealTime(hour, minute)));
		}
		scanner.close();
	}
	
	public static String printTime(int totalMinutes){
		int hours = totalMinutes / 60;
		int minutes = totalMinutes % 60;
		return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes);
	}
	
	public static int getRealTime(int reflectedHour, int reflectedMinute){
		int hour = 0, minute = 0;
		if(reflectedHour == 12) hour = 12;
		else hour = 12 - reflectedHour;
		minute = 60 - reflectedMinute;
		if(minute == 60){
			minute = 0;
		}else{
			hour--;
		}
		if(hour == 0) hour = 12;
		return hour * 60 + minute;
	}
}
