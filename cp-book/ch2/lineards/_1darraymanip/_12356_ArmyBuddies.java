package helvidios.cp.ch2.lineards._1darraymanip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _12356_ArmyBuddies {
	public static final int NONE = -1;
	
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	
	public static void main(String... args) throws IOException{
		String data = "1 1\r\n" + 
				"1 1\r\n" + 
				"10 4\r\n" + 
				"2 5\r\n" + 
				"6 9\r\n" + 
				"1 1\r\n" + 
				"10 10\r\n" + 
				"5 1\r\n" + 
				"1 1\r\n" + 
				"0 0";
		
		BufferedWriter output = 
		           new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder builder = new StringBuilder(25000);
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data.getBytes()));
		while(true){
			String[] line = reader.readLine().split(" ");
			int nSoldiers = Integer.parseInt(line[0]);
			int nLossReports = Integer.parseInt(line[1]);
			if(nSoldiers == 0 && nLossReports == 0) break;
			int[][] lossReports = new int[nLossReports][];
			for(int i = 0; i < nLossReports; i++) {
				line = reader.readLine().split(" ");
				int from = Integer.parseInt(line[0]);
				int to = Integer.parseInt(line[1]);
				lossReports[i] = new int[]{from, to};
			}
			int[][] buddies = kill(nSoldiers, lossReports);
			for(int[] buddyReport : buddies){
				if(buddyReport[0] == NONE) builder.append("*");
				else builder.append(buddyReport[0] + 1);
				builder.append(" ");
				if(buddyReport[1] == NONE) builder.append("*");
				else builder.append(buddyReport[1] + 1);
				builder.append("\n");
			}
			builder.append("-\n");
			if (builder.length() >= 10000) {
                output.write(builder.toString());
                builder = new StringBuilder(25000);
            }
		}
		
		output.write(builder.toString());
        output.flush();
        output.close();
	}
	
	public static int[][] kill(int nSoldiers, int[][] lossReports){
		int[] left = new int[nSoldiers];
		int[] right = new int[nSoldiers];
		
		for(int i = 0; i < left.length; i++){
			left[i] = i == 0 ? NONE : i - 1;
			right[i] = i < right.length - 1 ? i + 1 : NONE;
		}
		
		int[][] buddies = new int[lossReports.length][];
		for(int i = 0; i < lossReports.length; i++){
			int from = lossReports[i][0] - 1;
			int to = lossReports[i][1] - 1;
			
			buddies[i] = new int[]{left[from], right[to]};
			if(left[from] != NONE) right[left[from]] = right[to];
			if(right[to] != NONE) left[right[to]] = left[from];
		}
		return buddies;
	}
}
