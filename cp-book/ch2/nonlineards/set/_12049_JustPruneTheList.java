package helvidios.cp.ch2.nonlineards.set;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class _12049_JustPruneTheList {
	
	static class InputReader {
	      private BufferedReader reader;
	      private int idx;
	      private String next;
	      private int len;

	      public InputReader( InputStream stream ) {
	         reader = new BufferedReader( new InputStreamReader( stream ) );
	      }

	      public void next() throws IOException {
	         next = reader.readLine().trim();
	         idx = 0;
	         len = next.length();
	      }

	      public int nextInt() throws IOException {
	         if ( next == null || idx >= len ) {
	            next();
	         }

	         while ( idx < len && next.charAt( idx ) == ' ' ) {
	            ++idx;
	         }

	         int num = 0;
	         char ch;
	         boolean neg = false;
	         while ( idx < len && ( ch = next.charAt( idx ) ) != ' ' ) {
	            if ( ch == '-' ) {
	               neg = true;
	            } else {
	               num *= 10;
	               num += ch - 48;
	            }

	            ++idx;
	         }

	         return neg ? -num : num;
	      }
	   }

	public static void main(String... args) throws NumberFormatException, IOException{
		String data = "1\n"
				+ "5 5\r\n" + 
				"1 2 3 2 1\r\n" + 
				"1 2 5 2 3";
		InputReader reader = new InputReader(new ByteArrayInputStream(data.getBytes()));
		int nTestCases = reader.nextInt();
		while(nTestCases-- > 0){
			int[] list1 = new int[reader.nextInt()];
			int[] list2 = new int[reader.nextInt()];
			for(int i = 0; i < list1.length; i++){
				list1[i] = reader.nextInt();
			}
			for(int i = 0; i < list2.length; i++){
				list2[i] = reader.nextInt();
			}
			System.out.println(countElementsToRemove(list1, list2));
		}
	}
	
	public static int countElementsToRemove(int[] list1, int[] list2){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < list1.length; i++){
			if(!map.containsKey(list1[i])){
				map.put(list1[i], 1);
			}else{
				map.put(list1[i], map.get(list1[i]) + 1);
			}
		}
		
		for(int i = 0; i < list2.length; i++){
			if(!map.containsKey(list2[i])){
				map.put(list2[i], -1);
			}else{
				map.put(list2[i], map.get(list2[i]) - 1);
			}
		}

		int count = 0;
		for(Entry<Integer, Integer> entry : map.entrySet()){
			if(entry.getValue() != 0){
				count += Math.abs(entry.getValue());
			}
		}
		return count;
	}
}
