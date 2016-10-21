package helvidios.cp.ch2.lineards._1darraymanip;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class _11340_Newspaper {
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	public static void main(String... args) throws IOException{
		String data = "\n2\r\n\n\n" + 
				"7\r\n" + 
				"a 3\r\n" + 
				"W 10\r\n" + 
				"A 100\r\n" + 
				", 10\r\n" + 
				"k 7\r\n" + 
				". 3\r\n" + 
				"I 13\r\n" + 
				"7\r\n" + 
				"ACM International Collegiate Programming Contest (abbreviated\r\n" + 
				"as ACM-ICPC or just ICPC) is an annual multi-tiered competition\r\n" + 
				"among the universities of the world. The ICPC challenges students\r\n" + 
				"to set ever higheräåßß standards of excellence for themselves\r\n" + 
				"through competition that rewards team work, problem analysis,\r\n" + 
				"and rapid software development.\r\n" + 
				"From Wikipedia.\r\n" + 
				"\r\n" + 
				"7\r\n" + 
				"a 3\r\n" + 
				"W 10\r\n" + 
				"A 100\r\n" + 
				", 10\r\n" + 
				"k 7\r\n" + 
				". 3\r\n" + 
				"I 13\r\n" + 
				"7\r\n" + 
				"ACM International Collegiate Programming Contest (abbreviated\r\n" + 
				"as ACM-ICPC or just ICPC) is an annual multi-tiered competition\r\n" + 
				"among the universities of the world. The ICPC challenges students\r\n" + 
				"to set ever higher standards of excellence for themselves\r\n" + 
				"through competition that rewards team work, problem analysis,\r\n" + 
				"and rapid software development.\r\n" + 
				"From Wikipedia.\r\n" + 
				"";
		String data2 = "2\r\n" + 
				"2\r\n" + 
				"a 1\r\n" + 
				"b 2\r\n" + 
				"1\r\n" + 
				"abffbj ao we awea .w .e aneoi aw. awe. a w\r\n" + 
				"1\r\n" + 
				"b 11\r\n" + 
				"1\r\n" + 
				"bbbsdfanfwebawefamwepfmawpempe";
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data2.getBytes()));
		//BufferedReader reader = getBufferedReader(System.in);
		String line;
		line = nextToken(reader);
		int nTestCases = Integer.parseInt(line); 
		while(nTestCases-- > 0){
			long[] charCost = new long[65536];
			line = nextToken(reader);
			int nChars = Integer.parseInt(line);
			while(nChars-- > 0){
				line = nextToken(reader);
				String[] s = line.split("\\s+");
				char c = s[0].toCharArray()[0];
				charCost[c] = Integer.parseInt(s[1]);
			}
			
			int nLines = Integer.parseInt(nextToken(reader));
			long payment = 0;
			while(nLines-- > 0){
				line = reader.readLine();
				for(char c : line.toCharArray()){
					payment += charCost[c];
				}
			}
			
			System.out.printf(Locale.US, "%1$.2f$\n", payment / 100.0);
		}
	}
	
	static String nextToken(BufferedReader reader) throws IOException{
		String line;
		while((line = reader.readLine()).isEmpty()){}
		return line.trim();
	}
}
