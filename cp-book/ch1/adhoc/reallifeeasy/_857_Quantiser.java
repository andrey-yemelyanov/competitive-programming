package helvidios.cp.ch1.adhoc.reallifeeasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class _857_Quantiser {
	public static class PerformanceEvent{
		public PerformanceEvent(){}
		public PerformanceEvent(int code, int note, int measure, int beat, int tick){
			this.code = code;
			this.note = note;
			this.measure = measure;
			this.beat = beat;
			this.tick = tick;
		}
		public int code;
		public int note;
		public int measure;
		public int beat;
		public int tick;
		public boolean exclude;
		
		public String toString(){
			return code + " " + note + " " + measure + " " + beat + " " + tick;
		}
		
		// needed for unit test object comparison
		@Override
		public boolean equals(Object obj){
			PerformanceEvent other = (PerformanceEvent)obj;
			return code == other.code &&
					note == other.note &&
					measure == other.measure && 
					beat == other.beat &&
					tick == other.tick;
		}
	}
	
	public static int[] CORRECT_TICKS = new int[]{0,60,120,180,240,300,360,420,480};
	public static int ON = 1;
	public static int OFF = 0;
	
	public static void main(String... args){
		String data = "10\r\n" + 
				"\r\n" + 
				"1 35 23 1 6\r\n" + 
				"\r\n" + 
				"1 52 23 1 17\r\n" + 
				"\r\n" + 
				"1 43 23 2 10\r\n" + 
				"\r\n" + 
				"0 52 23 3 15\r\n" + 
				"\r\n" + 
				"0 35 23 3 252\r\n" + 
				"\r\n" + 
				"1 35 23 4 473\r\n" + 
				"\r\n" + 
				"1 33 23 4 478\r\n" + 
				"\r\n" + 
				"0 43 24 1 11\r\n" + 
				"\r\n" + 
				"0 33 24 1 12\r\n" + 
				"\r\n" + 
				"0 35 24 2 3\r\n" + 
				"\r\n" + 
				"10\r\n" + 
				"\r\n" + 
				"1 42 14 1 55\r\n" + 
				"\r\n" + 
				"1 38 14 1 126\r\n" + 
				"\r\n" + 
				"0 42 14 1 177\r\n" + 
				"\r\n" + 
				"1 42 14 1 230\r\n" + 
				"\r\n" + 
				"1 51 14 1 241\r\n" + 
				"\r\n" + 
				"0 42 14 1 248\r\n" + 
				"\r\n" + 
				"1 42 14 1 352\r\n" + 
				"\r\n" + 
				"0 38 14 1 356\r\n" + 
				"\r\n" + 
				"0 51 14 1 472\r\n" + 
				"\r\n" + 
				"0 42 14 2 244\r\n" + 
				"\r\n" + 
				"-1";
		String data2 = "4\n"+ 
				"1 0 0 0 0\r\n" + 
				"0 0 0 0 58\r\n" + 
				"1 0 0 0 62\r\n" + 
				"0 0 0 0 118\n" +
				"-1";
		String data3 = "10\r\n" + 
				"1 35 23 1 6\r\n" + 
				"1 52 23 1 17\r\n" + 
				"1 43 23 2 10\r\n" + 
				"0 52 23 3 15\r\n" + 
				"0 35 23 3 252\r\n" + 
				"1 35 23 4 473\r\n" + 
				"1 33 23 4 478\r\n" + 
				"0 43 24 1 11\r\n" + 
				"0 33 24 1 12\r\n" + 
				"0 35 24 2 3\r\n" + 
				"10\r\n" + 
				"1 42 14 1 55\r\n" + 
				"1 38 14 1 126\r\n" + 
				"0 42 14 1 177\r\n" + 
				"1 42 14 1 230\r\n" + 
				"1 51 14 1 241\r\n" + 
				"0 42 14 1 248\r\n" + 
				"1 42 14 1 352\r\n" + 
				"0 38 14 1 356\r\n" + 
				"0 51 14 1 472\r\n" + 
				"0 42 14 2 244\r\n" + 
				"14\r\n" + 
				"0 33 13 4 478                   \r\n" + 
				"1 33 14 1 12\r\n" + 
				"1 33 23 4 478                   \r\n" + 
				"0 33 24 1 12 \r\n" + 
				"1 33 33 4 478                   \r\n" + 
				"0 33 34 1 12 \r\n" + 
				"1 42 14 1 15\r\n" + 
				"0 42 17 1 15\r\n" + 
				"1 42 19 4 450\r\n" + 
				"1 42 20 1 6\r\n" + 
				"1 43 19 4 450\r\n" + 
				"0 43 20 1 6\r\n" + 
				"0 44 19 4 450\r\n" + 
				"1 44 20 1 6                  \r\n" + 
				"-1";
		Scanner scanner = new Scanner(data3);
		while (scanner.hasNext()) {
			int nEvents = scanner.nextInt();
			if(nEvents == -1) break;
			List<PerformanceEvent> events = new ArrayList<PerformanceEvent>();
			while (nEvents-- > 0) {
				int code = scanner.nextInt();
				int note = scanner.nextInt();
				int measure = scanner.nextInt();
				int beat = scanner.nextInt();
				int tick = scanner.nextInt();
				events.add(new PerformanceEvent(code, note, measure, beat, tick));
			}
			List<PerformanceEvent> quantisedEvents = quantise(events);
			System.out.println(quantisedEvents.size());
			System.out.println(printEvents(quantisedEvents));
		}
		System.out.println("-1");
		scanner.close();
	}
	
	public static String printEvents(List<PerformanceEvent> events){
		StringBuilder printer = new StringBuilder();
		for(int i = 0; i < events.size(); i++){
			PerformanceEvent event = events.get(i);
			printer.append(event.toString());
			if(i < events.size() - 1) printer.append("\n");
		}
		return printer.toString();
	}
	
	public static List<PerformanceEvent> quantise(List<PerformanceEvent> events){
		for(PerformanceEvent event : events){
			quantiseEvent(event);
		}
		
		List<PerformanceEvent> filteredEvents = new ArrayList<PerformanceEvent>();
		for(int i = 0; i < events.size(); i++){
			PerformanceEvent event = events.get(i);
			if(event.code == ON){
				PerformanceEvent event2 = findEvent(events, OFF, event.note, i + 1);
				if(event2 != null){
					if(!zeroDuration(event, event2)){
						filteredEvents.add(event);
					}else{
						event2.exclude = true;
					}
				}else{
					filteredEvents.add(event);
				}
			}
			else if(!event.exclude){
				filteredEvents.add(event);
			}
		}
		
		return filteredEvents;
	}
	
	static PerformanceEvent findEvent(List<PerformanceEvent> events, int code, int note, int searchFrom){
		for(int i = searchFrom; i < events.size(); i++){
			PerformanceEvent event = events.get(i);
			if(event.code == code && event.note == note) return event;
		}
		return null;
	}
	
	static boolean zeroDuration(PerformanceEvent event1, PerformanceEvent event2){
		return  event1.measure == event2.measure &&
				event1.beat == event2.beat &&
				event1.tick == event2.tick;
	}
	
	public static void quantiseEvent(PerformanceEvent event){
		int correctTick = findClosestMatch(event.tick);
		setTick(event, correctTick);
	}
	
	static int findClosestMatch(int tick){
		for(int i = 0; i < CORRECT_TICKS.length; i++){
			if(tick >= CORRECT_TICKS[i] && tick <= CORRECT_TICKS[i + 1]){
				int left = i;
				int right = i + 1;
				if(Math.abs(tick - CORRECT_TICKS[left]) < Math.abs(tick - CORRECT_TICKS[right])){
					return CORRECT_TICKS[left];
				}else{
					return CORRECT_TICKS[right];
				}
			}
		}
		throw new RuntimeException("Unable to find closest tick match.");
	}
	
	static void setTick(PerformanceEvent event, int tick){
		if(tick == CORRECT_TICKS[CORRECT_TICKS.length - 1]){
			event.tick = 0;
			setBeat(event, event.beat + 1);
		}else{
			event.tick = tick;
		}
	}
	
	static void setBeat(PerformanceEvent event, int beat){ 
		if(beat > 4){
			event.beat = 1;
			event.measure++;
		}else{
			event.beat = beat;
		}
	}
}
