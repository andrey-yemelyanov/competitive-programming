package helvidios.cp.ch1.adhoc.reallifeharder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class _139_TelephoneTangles {
	public static class CallCost{
		public String code;
		public String name = "";
		public int centsPerMinute;
		
		public double getDollarsPerMinute(){
			return centsPerMinute / 100.0;
		}
	}
	public static class Call{
		public String number;
		public int duration;
		public String subscriberNumber;
		public String code = "";
		
		public Call(String number, int duration, HashMap<String, CallCost> costTable){
			this.number = number;
			this.duration = duration;
			this.subscriberNumber = number; 
			parse(costTable);
		}
		
		private void parse(HashMap<String, CallCost> costTable){
			if(!isLocal()){
				String code;
				for(int i = 0; i < number.length(); i++){
					code = number.substring(0, i + 1);
					if(costTable.containsKey(code)){
						this.code = code;
						this.subscriberNumber = number.substring(code.length()); 
						if(isValidSubscriberNumber()) break;
					}
				}
			}
		}
		
		public boolean isValidSubscriberNumber(){
			if(isLocal()) return true;
			if(isInternational() && subscriberNumber.length() >= 4 && subscriberNumber.length() <= 10) return true;
			if(isNational() && subscriberNumber.length() >= 4 && subscriberNumber.length() <= 7) return true;
			return false;
		}
		
		public boolean isLocal(){
			return !number.startsWith("0");
		}
		
		public boolean isInternational(){
			if(code.equals("")) return false;
			String countryCode = code.substring(2);
			return code.startsWith("00") 
					&& countryCode.length() >= 1 
					&& countryCode.length() <= 3;
		}
		
		public boolean isNational(){
			if(code.equals("")) return false;
			String areaCode = code.substring(1);
			return code.startsWith("0") && areaCode.length() >= 1 && areaCode.length() <= 5;
		}
	}
	public static void main(String... args){
		String data = "088925 Broadwood$81\r\n" + 
				"03 Arrow.town           $   38\r\n" + 
				"0061 Jago de la Vega $     140\r\n" +
				"00 Los Angeles$50\n"+
				"000000                       	   \r\n" + 
				"\n\n\n" + 
				"0315267777        22\r\n" + 
				"031526        22\r\n" +
				"0061853279  3\r\n" + 
				"0889256287213   122\r\n" + 
				"779760 1\r\n" + 
				"002832769 5\r\n" + 
				"0012345 10\n"+
				"#\r\n" + 
				"";
		String data2 = "0001 aa $1 \r\n" + 
				"01 bb $ 3 \r\n" + 
				"000000 \r\n" + 
				"00010000 3 \r\n" + 
				"# ";
		String data3 = "088925 Broadwood0000000baaaaaaad$81\r\n" + 
				"03  Arrowtown $38\r\n" + 
				"01 $24\r\n" + 
				"0061 Australia$140\r\n" + 
				"00852 Hong Kong.012345678901234$1111\r\n" + 
				"00 Los Angelos$10\r\n" + 
				"000000\r\n" + 
				"031526        22\r\n" + 
				"0889256287213   122\r\n" + 
				"008520123456789   64\r\n" + 
				"779760    1\r\n" + 
				"  002832769       5\r\n" + 
				"001234 1   \r\n" + 
				"0123456 3\r\n" + 
				"0123 4\r\n" + 
				"00134 5\r\n" + 
				"00123456789012 9\r\n" + 
				"0061234 600\r\n" + 
				"0061853279  300\r\n" + 
				"00611234567890 700\r\n" + 
				"006112345678901 800\r\n" + 
				"123456789 2\r\n" + 
				"123456789012345 4\r\n" + 
				"#";
		Scanner scanner = new Scanner(data3);
		HashMap<String, CallCost> costTable = new HashMap<String, CallCost>();
		while(true){
			String line = scanner.nextLine().replace("$", " ").trim();
			if(line.equals("000000")) break;
			String[] tokens = line.split("\\s+");			
			CallCost cost = new CallCost();
			cost.code = tokens[0];
			int i = 1;
			for(; i < tokens.length - 1; i++){
				cost.name += tokens[i] + " ";
			}
			cost.name = cost.name.trim();
			cost.centsPerMinute = Integer.parseInt(tokens[i]);
			costTable.put(cost.code, cost);
		}
		List<Call> callLog = new ArrayList<Call>();
		while (true) {
			String number = scanner.next();
			if(number.equals("#")) break;
			int duration = scanner.nextInt();
			callLog.add(new Call(number, duration, costTable));
		}
		
		String report = generateReport(costTable, callLog);
		System.out.print(report);
		
		scanner.close();
	}
	
	public static String generateReport(HashMap<String, CallCost> costTable, List<Call> callLog){
		StringBuilder sb = new StringBuilder();
		for(Call call : callLog){
			sb.append(generateReport(costTable, call));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	static CallCost getCallCost(HashMap<String, CallCost> costTable, Call call){
		if(call.isLocal()){
			CallCost cost = new CallCost();
			cost.code = "";
			cost.name = "Local";
			cost.centsPerMinute = 0;
			return cost;
		}
		
		if(!costTable.containsKey(call.code) || !call.isValidSubscriberNumber()) return null;
		return costTable.get(call.code);
	}
	
	static String generateReport(HashMap<String, CallCost> costTable, Call call){
		CallCost cost = getCallCost(costTable, call);
		String format = "%1$-16s %2$-25s %3$10s %4$5d %5$6s %6$7s";
		String costFormat = "%1$.2f";
		if(cost == null){
			return String.format(format, call.number, "Unknown", "", call.duration,"",String.format(Locale.US, costFormat, -1.0));
		}else{
			return String.format(
					format, 
					call.number, 
					cost.name, 
					call.subscriberNumber, 
					call.duration, 
					String.format(Locale.US, costFormat, cost.getDollarsPerMinute()), 
					String.format(Locale.US, costFormat, call.duration * cost.getDollarsPerMinute()));
		}
	}
}
