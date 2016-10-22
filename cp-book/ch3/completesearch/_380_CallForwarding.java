package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class _380_CallForwarding {
	static class ForwardingRequest{
		public int source;
		public int time;
		public int duration;
		public int target;
		public ForwardingRequest(int source, int time, int duration, int target){
			this.source = source;
			this.time = time;
			this.duration = duration;
			this.target = target;
		}
		public boolean isSetFor(int extension, int time){
			return source == extension && time >= this.time && time <= this.time + duration;
		}
	}
	public static void main(String... args){
		String data = "2\r\n" + 
				"1111 0100 0200 2222\r\n" + 
				"1111 0301 0500 4444\r\n" + 
				"2222 0200 0200 3333\r\n" + 
				"3333 0250 1000 1111\r\n" + 
				"7777 1000 2000 7777\r\n" + 
				"0000\r\n" + 
				"0050 1111\r\n" + 
				"0150 1111\r\n" + 
				"0200 1111\r\n" + 
				"0225 2222\r\n" + 
				"0270 1111\r\n" + 
				"0320 1111\r\n" + 
				"0320 3333\r\n" + 
				"0900 3000\r\n" + 
				"1250 3333\r\n" + 
				"1250 7777\r\n" + 
				"9000\r\n" + 
				"0000\r\n" + 
				"3000 1111\r\n" + 
				"9000\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int nSystems = scanner.nextInt();
		StringBuilder out = new StringBuilder("CALL FORWARDING OUTPUT\n");
		for(int sys = 1; sys <= nSystems; sys++){
			out.append("SYSTEM " + sys + "\n");
			List<ForwardingRequest> requests = new ArrayList<ForwardingRequest>();
			int source = scanner.nextInt();
			while(source != 0){
				requests.add(new ForwardingRequest(source, scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
				source = scanner.nextInt();
			}
			while(true){
				int time = scanner.nextInt();
				if(time == 9000) break;
				int extension = scanner.nextInt();
				int number = call(extension, time, requests);
				out.append(String.format("AT %1$04d CALL TO %2$04d RINGS %3$04d\n", time, extension, number));
			}
		}
		out.append("END OF OUTPUT");
		System.out.println(out.toString());
		scanner.close();
	}
	
	static ForwardingRequest getForwarding(int extension, int time, List<ForwardingRequest> requests){
		for(ForwardingRequest request : requests){
			if(request.isSetFor(extension, time)) return request;
		}
		return null;
	}
	
	static int call(int extension, int time, List<ForwardingRequest> requests){
		Set<Integer> forwardingChain = new HashSet<Integer>();
		forwardingChain.add(extension);
		return call(extension, time, forwardingChain, requests);
	}
	
	static int call(int extension, int time, Set<Integer> forwardingChain, List<ForwardingRequest> requests){
		ForwardingRequest request = getForwarding(extension, time, requests);
		if(request == null) return extension;
		if(forwardingChain.contains(request.target)) return 9999; // cycle detected
		forwardingChain.add(request.target);
		return call(request.target, time, forwardingChain, requests);
	}
}
