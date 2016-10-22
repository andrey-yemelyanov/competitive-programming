package helvidios.cp.ch1.adhoc.time;

import java.util.Locale;
import java.util.Scanner;

public class _579_ClockHands {
	public static void main(String... args){
		String data = "12:00\r\n" + 
				"9:00\r\n" + 
				"8:10\r\n" + 
				"9:27\n"+
				"1:05\n"+
				"1:36\n"+
				"0:00\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(true){
			String line = scanner.next();
			if(line.equals("0:00")) break;
			String[] time = line.split(":");
			int hour = Integer.parseInt(time[0]);
			int minute = Integer.parseInt(time[1]);
			double angle = getAngleBetween(hour, minute);
			System.out.printf(Locale.US, "%1$.3f\n", angle);
		}
		scanner.close();
	}
	
	public static double getAngleBetween(int hourHand, int minuteHand){
		int oneMinuteAngle = 360 / 60;
		if(hourHand == 12) hourHand = 0;
		double hourHandPosition = hourHand * 5 + ((minuteHand / 60.0) * 5);
		int hourHandClosestMinute = (int) Math.floor(hourHandPosition);
		double diffAngle = (hourHandPosition - hourHandClosestMinute) * oneMinuteAngle;
		double angle = 0;
		while (hourHandClosestMinute != minuteHand) {
			hourHandClosestMinute++;
			if(hourHandClosestMinute > 59) hourHandClosestMinute = 0;
			angle += oneMinuteAngle;
		}
		if(angle == 0) return diffAngle;
		angle -= diffAngle;
		return Math.min(angle, 360 - angle);
	}
}
