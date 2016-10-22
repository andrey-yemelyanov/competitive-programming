package helvidios.cp.ch2.nonlineards.set;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class _11136_HoaxOrWhat {
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	public static void main(String... args) throws NumberFormatException, IOException{
		String data = "5\r\n" + 
				"3 1 2 3\r\n" + 
				"2 1 1\r\n" + 
				"4 10 5 5 1\r\n" + 
				"0\r\n" + 
				"1 2\r\n" + 
				"2\r\n" + 
				"2 1 2\r\n" + 
				"2 1 2\r\n" + 
				"0";
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data.getBytes()));
		while(true){
			int nDays = Integer.parseInt(reader.readLine());
			if(nDays == 0) break;
			List<List<Integer>> bills = new ArrayList<List<Integer>>();
			for(int i = 0; i < nDays; i++){
				List<Integer> list = new ArrayList<Integer>();
				String[] items = reader.readLine().split(" ");
				for(int j = 1; j < items.length; j++){
					list.add(Integer.parseInt(items[j]));
				}
				bills.add(list);
			}
			System.out.println(getPromotionCost(nDays, bills));
		}
	}
	
	public static long getPromotionCost(int nDays, List<List<Integer>> bills){
		long cost = 0;
		SortedMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		for(int day = 0; day < nDays; day++){
			for(int i = 0; i < bills.get(day).size(); i++){
				int bill = bills.get(day).get(i);
				if(!map.containsKey(bill)){
					map.put(bill, 0);
				}
				map.put(bill, map.get(bill) + 1);
			}
			cost += getDailyPrize(map);
		}
		
		return cost;
	}
	
	static int getDailyPrize(SortedMap<Integer, Integer> map){
		int min = map.firstKey();
		int max = map.lastKey();
		
		if(map.get(min) == 1){
			map.remove(min);
		}else{
			map.put(min, map.get(min) - 1);
		}
		
		if(map.get(max) == 1){
			map.remove(max);
		}else{
			map.put(max, map.get(max) - 1);
		}
		
		return max - min;
	}
}
