package helvidios.cp.ch1.adhoc.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class _11947_CancerOrScorpio {
	public static void main(String... args) throws ParseException{
		String data = "3\r\n" + 
				"01232009\r\n" + 
				"01232008\n"+
				"01012004";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		int n = 0;
		while (nTestCases-- > 0) {
			String date = scanner.next();
			SimpleDateFormat df = new SimpleDateFormat("MMddyyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(df.parse(date));
			Calendar birthdate = getBirthdate(c);
			System.out.printf("%1$d %2$s %3$s\n", ++n, printDate(birthdate), getZodiac(birthdate));
		}
		scanner.close();
	}
	
	public static String getZodiac(Calendar date){
		if(isAfter(date, 20, 1) && isBefore(date, 20, 2)) return "aquarius";
		if(isAfter(date, 19, 2) && isBefore(date, 21, 3)) return "pisces";
		if(isAfter(date, 20, 3) && isBefore(date, 21, 4)) return "aries";
		if(isAfter(date, 20, 4) && isBefore(date, 22, 5)) return "taurus";
		if(isAfter(date, 21, 5) && isBefore(date, 22, 6)) return "gemini";
		if(isAfter(date, 21, 6) && isBefore(date, 23, 7)) return "cancer";
		if(isAfter(date, 22, 7) && isBefore(date, 22, 8)) return "leo";
		if(isAfter(date, 21, 8) && isBefore(date, 24, 9)) return "virgo";
		if(isAfter(date, 23, 9) && isBefore(date, 24, 10)) return "libra";
		if(isAfter(date, 23, 10) && isBefore(date, 23, 11)) return "scorpio";
		if(isAfter(date, 22, 11) && isBefore(date, 23, 12)) return "sagittarius";
		return "capricorn";
	}
	
	public static boolean isAfter(Calendar date, int day, int month){
		Calendar date2 = Calendar.getInstance();
		date2.set(date.get(Calendar.YEAR), month - 1, day);
		return date.compareTo(date2) > 0;
	}
	
	public static boolean isBefore(Calendar date, int day, int month){
		Calendar date2 = Calendar.getInstance();
		date2.set(date.get(Calendar.YEAR), month - 1, day);
		return date.compareTo(date2) < 0;
	}
	
	public static String printDate(Calendar date){
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		return dt.format(date.getTime());
	}
	
	public static Calendar getBirthdate(Calendar lastMenstrualCycle){
		Calendar birthdate = Calendar.getInstance();
		birthdate.setTime(lastMenstrualCycle.getTime());
		birthdate.add(Calendar.WEEK_OF_YEAR, 40);
		return birthdate;
	}
}
