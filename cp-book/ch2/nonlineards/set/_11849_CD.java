package helvidios.cp.ch2.nonlineards.set;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class _11849_CD {
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	public static void main(String... args) throws NumberFormatException, IOException{
		String data = "3 3\r\n" + 
				"1\n2\n3\n1\n2\n4\n"
				+ "0 0\n";
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data.getBytes()));
		
		while(true){
			String[] items = reader.readLine().split(" ");
			int n = Integer.parseInt(items[0]);
			int m = Integer.parseInt(items[1]);
			if(n == 0 && m == 0) break;
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			int total = n + m;
			while(total-- > 0){
				int cd = Integer.parseInt(reader.readLine());
				if(!map.containsKey(cd)){
					map.put(cd, 0);
				}
				map.put(cd, map.get(cd) + 1);
			}
			int count = 0;
			for(Entry<Integer, Integer> entry : map.entrySet()){
				if(entry.getValue() > 1) count++;
			}
			System.out.println(count);
		}
	}
}
