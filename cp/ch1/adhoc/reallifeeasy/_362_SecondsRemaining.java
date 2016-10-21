package helvidios.cp.ch1.adhoc.reallifeeasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _362_SecondsRemaining {
	public static void main(String... args){
		String data = "100\r\n" + 
				"10\r\n" + 
				"20\r\n" + 
				"20\r\n" + 
				"0\r\n" + 
				"10\r\n" + 
				"0\r\n" + 
				"10\r\n" + 
				"0\r\n" + 
				"10\r\n" + 
				"0\r\n" + 
				"20\r\n" + 
				"200\r\n" + 
				"60\r\n" + 
				"30\r\n" + 
				"100\r\n" + 
				"10\r\n" + 
				"50\r\n" + 
				"5\r\n" + 
				"5\r\n" + 
				"5\r\n" + 
				"5\r\n" + 
				"25\r\n" + 
				"0\r\n" + 
				"0\r\n" + 
				"0\r\n" + 
				"0\r\n" + 
				"0\r\n" + 
				"0\r\n" + 
				"0\r\n" + 
				"0\r\n" + 
				"0\r\n" + 
				"0\r\n" + 
				"1\r\n" + 
				"1\r\n" + 
				"1\r\n" + 
				"1\r\n" + 
				"1\r\n" + 
				"0";
		String data4 = "500000 \r\n" + 
				"100000 \r\n" + 
				"100000 \r\n" + 
				"100000 \r\n" + 
				"100000 \r\n" + 
				"0 \r\n" + 
				"100000 \r\n" + 
				"5000000 \r\n" + 
				"1000000 \r\n" + 
				"1000000 \r\n" + 
				"1000000 \r\n" + 
				"1000000 \r\n" + 
				"0 \r\n" + 
				"1000000 \r\n" + 
				"500000000 \r\n" + 
				"100000000 \r\n" + 
				"100000000 \r\n" + 
				"100000000 \r\n" + 
				"100000000 \r\n" + 
				"0 \r\n" + 
				"100000000 \r\n" + 
				"5000000000 \r\n" + 
				"1000000000 \r\n" + 
				"1000000000 \r\n" + 
				"1000000000 \r\n" + 
				"1000000000 \r\n" + 
				"0\r\n" + 
				"1000000000 \r\n" + 
				"0";
		Scanner scanner = new Scanner(data4);
		int index = 0;
		StringBuilder stats = new StringBuilder();
		while(scanner.hasNext()){
			long fileSize = scanner.nextLong();
			if(fileSize == 0) break;
			List<Long> transfer = new ArrayList<Long>();
			long size = 0;
			while(size < fileSize){
				long bytesTransmitted = scanner.nextLong(); 
				transfer.add(bytesTransmitted);
				size += bytesTransmitted;
			}
			stats.append(outputStats(transfer, fileSize, ++index));
		}
		System.out.print(stats);
		scanner.close();
	}
	
	public static String outputStats(List<Long> transfer, long fileSize, int index){
		final int interval = 5;
		StringBuilder stats = new StringBuilder();
		stats.append(String.format("Output for data set %1$d, %2$d bytes:\n", index, fileSize));
		long totalTransferred = 0;
		for(int i = 0; i < transfer.size() / interval; i++){
			long bytesTransferred = bytesTransferred(transfer, interval * i, interval * (i + 1));
			if(bytesTransferred == 0){
				stats.append("   Time remaining: stalled\n");
			}else{
				totalTransferred += bytesTransferred;
				double transferRate = bytesTransferred * 0.2;
				double secondsRemaining = (fileSize - totalTransferred) / transferRate;
				stats.append("   Time remaining: " + (long)Math.ceil(secondsRemaining) + " seconds\n");
			}
		}
		stats.append(String.format("Total time: %1$d seconds\n", transfer.size()));
		stats.append("\n");
		return stats.toString();
	}
	
	static long bytesTransferred(List<Long> transfer, int from, int to){
		long totalTransferred = 0;
		for(int i = from; i < to && i < transfer.size(); i++){
			totalTransferred += transfer.get(i);
		}
		return totalTransferred;
	}
}
