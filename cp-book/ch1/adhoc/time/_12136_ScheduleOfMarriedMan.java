package helvidios.cp.ch1.adhoc.time;

import java.util.Calendar;
import java.util.Scanner;

public class _12136_ScheduleOfMarriedMan {
	public static class Appointment{
		public Calendar from;
		public Calendar to;
	}
	public static void main(String... args){
		String data = "3\r\n" + 
				"\r\n" + 
				"17:47 22:40\r\n" + 
				"\r\n" + 
				"06:18 17:04\r\n" + 
				"\r\n" + 
				"10:44 17:05\r\n" + 
				"\r\n" + 
				"01:11 01:27\r\n" + 
				"\r\n" + 
				"03:36 19:02\r\n" + 
				"\r\n" + 
				"14:33 15:24\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nTestCases = scanner.nextInt();
		int n = 0;
		while(nTestCases-- > 0){
			String[] wifeStart = scanner.next().split(":");
			String[] wifeEnd = scanner.next().split(":");
			String[] meetingStart = scanner.next().split(":");
			String[] meetingEnd = scanner.next().split(":");
			
			Appointment wife = new Appointment();
			Calendar fromWife = Calendar.getInstance();
			fromWife.set(Calendar.HOUR_OF_DAY, Integer.parseInt(wifeStart[0]));
			fromWife.set(Calendar.MINUTE, Integer.parseInt(wifeStart[1]));
			Calendar toWife = Calendar.getInstance();
			toWife.set(Calendar.HOUR_OF_DAY, Integer.parseInt(wifeEnd[0]));
			toWife.set(Calendar.MINUTE, Integer.parseInt(wifeEnd[1]));
			wife.from = fromWife;
			wife.to = toWife;
			
			Appointment meeting = new Appointment();
			Calendar fromMeeting = Calendar.getInstance();
			fromMeeting.set(Calendar.HOUR_OF_DAY, Integer.parseInt(meetingStart[0]));
			fromMeeting.set(Calendar.MINUTE, Integer.parseInt(meetingStart[1]));
			Calendar toMeeting = Calendar.getInstance();
			toMeeting.set(Calendar.HOUR_OF_DAY, Integer.parseInt(meetingEnd[0]));
			toMeeting.set(Calendar.MINUTE, Integer.parseInt(meetingEnd[1]));
			meeting.from = fromMeeting;
			meeting.to = toMeeting;
			
			if(inConflict(wife, meeting)){
				System.out.printf("Case %1$d: Mrs Meeting\n", ++n);
			}else{
				System.out.printf("Case %1$d: Hits Meeting\n", ++n);
			}
		}
		scanner.close();
	}
	
	public static boolean inConflict(Appointment wife, Appointment meeting){
		if(wife.to.compareTo(meeting.from) >= 0 && wife.from.compareTo(meeting.from) <= 0) return true;
		if(wife.from.compareTo(meeting.to) <= 0 && wife.to.compareTo(meeting.to) >= 0) return true;
		if(wife.from.compareTo(meeting.from) >= 0 && wife.to.compareTo(meeting.to) <= 0) return true;
		if(wife.from.compareTo(meeting.from) <= 0 && wife.to.compareTo(meeting.to) >= 0) return true;
		return false;
	}
}
