package helvidios.cp.ch2.nonlineards.map;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class _10226_HardwoodSpecies {
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	public static void main(String... args) throws NumberFormatException, IOException{
		String data = "2\n\n" + 
				"Red Alder\r\n" + 
				"Ash\r\n" + 
				"Aspen\r\n" + 
				"Basswood\r\n" + 
				"Ash\r\n" + 
				"Beech\r\n" + 
				"Yellow Birch\r\n" + 
				"Ash\r\n" + 
				"Cherry\r\n" + 
				"Cottonwood\r\n" + 
				"Ash\r\n" + 
				"Cypress\r\n" + 
				"Red Elm\r\n" + 
				"Gum\r\n" + 
				"Hackberry\r\n" + 
				"White Oak\r\n" + 
				"Hickory\r\n" + 
				"Pecan\r\n" + 
				"Hard Maple\r\n" + 
				"White Oak\r\n" + 
				"Soft Maple\r\n" + 
				"Red Oak\r\n" + 
				"Red Oak\r\n" + 
				"White Oak\r\n" + 
				"Poplan\r\n" + 
				"Sassafras\r\n" + 
				"Sycamore\r\n" + 
				"Black Walnut\r\n" + 
				"Willow\n\n"
				+ "Red Alder\r\n" + 
				"Ash\r\n" + 
				"Aspen\r\n" + 
				"Basswood\r\n" + 
				"Ash\r\n" + 
				"Beech\r\n" + 
				"Yellow Birch\r\n" + 
				"Ash\r\n" + 
				"Cherry\r\n" + 
				"Cottonwood\r\n" + 
				"Ash\r\n" + 
				"Cypress\r\n" + 
				"Red Elm\r\n" + 
				"Gum\r\n" + 
				"Hackberry\r\n" + 
				"White Oak\r\n" + 
				"Hickory\r\n" + 
				"Pecan\r\n" + 
				"Hard Maple\r\n" + 
				"White Oak\r\n" + 
				"Soft Maple\r\n" + 
				"Red Oak\r\n" + 
				"Red Oak\r\n" + 
				"White Oak\r\n" + 
				"Poplan\r\n" + 
				"Sassafras\r\n" + 
				"Sycamore\r\n" + 
				"Black Walnut\r\n" + 
				"Willow";
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data.getBytes()));
		int nTestCases = Integer.parseInt(reader.readLine());
		reader.readLine();
		StringBuilder sb = new StringBuilder();
		while(nTestCases-- > 0){
			List<String> trees = new ArrayList<String>();
			String line = null;
			while((line = reader.readLine()) != null && !line.isEmpty()){
				trees.add(line);
			}
			sb.append(printMap(collectStatistics(trees), trees.size()));
			if(nTestCases > 0) sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static String printMap(SortedMap<String, Integer> map, int nTrees){
		StringBuilder sb = new StringBuilder();
		for(Entry<String, Integer> entry : map.entrySet()){
			sb.append(entry.getKey() + " " + 
					String.format(Locale.US, "%1$.4f", (double)entry.getValue() / nTrees * 100));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static SortedMap<String, Integer> collectStatistics(List<String> trees){
		SortedMap<String, Integer> map = new TreeMap<String, Integer>();
		for(String tree : trees){
			if(!map.containsKey(tree)){
				map.put(tree, 0);
			}
			map.put(tree, map.get(tree) + 1);
		}
		return map;
	}
}
