package helvidios.cp.ch1.adhoc.time;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _10371_TimeZones {
	static final Map<String, Double> timeZones;
	static{
		timeZones = new HashMap<String, Double>();
		timeZones.put("UTC", 0d);
		timeZones.put("GMT", 0d);
		timeZones.put("BST", 1d);
		timeZones.put("IST", 1d);
		timeZones.put("WET", 0d);
		timeZones.put("WEST", 1d);
		timeZones.put("CET", 1d);
		timeZones.put("CEST", 2d);
		timeZones.put("EET", 2d);
		timeZones.put("EEST", 3d);
		timeZones.put("MSK", 3d);
		timeZones.put("MSD", 4d);
		timeZones.put("AST", -4d);
		timeZones.put("ADT", -3d);
		timeZones.put("NST", -3.5d);
		timeZones.put("NDT", -2.5d);
		timeZones.put("EST", -5d);
		timeZones.put("EDT", -4d);
		timeZones.put("CST", -6d);
		timeZones.put("CDT", -5d);
		timeZones.put("MST", -7d);
		timeZones.put("MDT", -6d);
		timeZones.put("PST", -8d);
		timeZones.put("PDT", -7d);
		timeZones.put("HST", -10d);
		timeZones.put("AKST", -9d);
		timeZones.put("AKDT", -8d);
		timeZones.put("AEST", 10d);
		timeZones.put("AEDT", 11d);
		timeZones.put("ACST", 9.5d);
		timeZones.put("ACDT", 10.5d);
		timeZones.put("AWST", 8d);
	}
	
	public static void main(String... args){
		String data = "6\r\n" + 
				"noon HST CEST\r\n" + 
				"11:29 a.m. EST GMT\r\n" + 
				"6:01 p.m. CST UTC\r\n" + 
				"12:40 p.m. ADT MSK\n" +
				"12:01 p.m. ADT ADT\n"+
				"12:01 a.m. ADT ADT";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		while (nTestCases-- > 0) {
			int hour = 0;
			int minute = 0;
			String fromTimeZone = null, toTimeZone = null;
			String time = scanner.next();
			if(time.equals("noon")){
				hour = 12;
				fromTimeZone = scanner.next();
				toTimeZone = scanner.next();
			}else if(time.equals("midnight")){
				fromTimeZone = scanner.next();
				toTimeZone = scanner.next();
			}else if(time.contains(":")){
				String[] t = time.split(":");
				hour = Integer.parseInt(t[0]);
				minute = Integer.parseInt(t[1]);
				boolean isAm = scanner.next().equals("a.m.");
				if(isAm && hour == 12) hour = 0; 
				if(!isAm && hour != 12) hour += 12;
				fromTimeZone = scanner.next();
				toTimeZone = scanner.next();
			}
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, hour);
			cal.set(Calendar.MINUTE, minute);
			System.out.println(printTime(convert(cal, fromTimeZone, toTimeZone)));
		}
		scanner.close();
	}
	
	static Calendar convert(Calendar time, String fromTimeZone, String toTimeZone){
		return fromUtc(toUtc(time, fromTimeZone), toTimeZone);
	}
	
	public static String printTime(Calendar time){
		if(time.get(Calendar.HOUR_OF_DAY) == 0 && time.get(Calendar.MINUTE) == 0){
			return "midnight";
		}
		
		if(time.get(Calendar.HOUR_OF_DAY) == 12 && time.get(Calendar.MINUTE) == 0){
			return "noon";
		}
		
		return (time.get(Calendar.HOUR) == 0 ? 12 : time.get(Calendar.HOUR)) + 
				":" + (time.get(Calendar.MINUTE) < 10 
						? "0"+time.get(Calendar.MINUTE):time.get(Calendar.MINUTE)) + 
				" " + (time.get(Calendar.AM_PM) == Calendar.AM ? "a.m." : "p.m.");
	}
	
	public static Calendar toUtc(Calendar time, String fromTimeZone){
		double diff = timeZones.get(fromTimeZone);
		Calendar utcTime = Calendar.getInstance();
		utcTime.setTime(time.getTime());
		int hours = (int) diff;
		int minutes = (int) ((diff - hours) * 60);
		utcTime.add(Calendar.HOUR_OF_DAY, -hours);
		utcTime.add(Calendar.MINUTE, -minutes);
		return utcTime;
	}
	
	public static Calendar fromUtc(Calendar utcTime, String toTimeZone){
		double diff = timeZones.get(toTimeZone);
		Calendar time = Calendar.getInstance();
		time.setTime(utcTime.getTime());
		int hours = (int) diff;
		int minutes = (int) ((diff - hours) * 60);
		time.add(Calendar.HOUR_OF_DAY, hours);
		time.add(Calendar.MINUTE, minutes);
		return time;
	}
}
