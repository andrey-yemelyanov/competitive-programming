package helvidios.cp.ch2.nonlineards.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class _939_Genes {
	public static final String DOMINANT = "dominant";
	public static final String RECESSIVE = "recessive";
	public static final String NON_EXISTENT = "non-existent";
	public static void main(String... args){
		String data = "7\r\n" + 
				"John dominant\r\n" + 
				"Mary recessive\r\n" + 
				"John Susan\r\n" + 
				"Mary Susan\r\n" + 
				"Peter non-existent\r\n" + 
				"Susan Marta\r\n" + 
				"Peter Marta";
		Scanner scanner = new Scanner(data);
		int nLines = scanner.nextInt();
		String[][] dataset = new String[nLines][2];
		for(int i = 0; i < nLines; i++){
			dataset[i][0] = scanner.next();
			dataset[i][1] = scanner.next();
		}
		System.out.print(printGenes(getGenes(dataset)));
		scanner.close();
	}
	
	static String printGenes(SortedMap<String, String> genes){
		StringBuilder sb = new StringBuilder();
		for(Entry<String, String> entry : genes.entrySet()){
			sb.append(entry.getKey() + " " + entry.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static SortedMap<String, String> getGenes(String[][] dataset){
		SortedMap<String, String> genes = new TreeMap<String, String>();
		Map<String, List<String>> childToParents = new TreeMap<String, List<String>>();
		for(String[] data : dataset){
			String name = data[0];
			if(data[1].equals(DOMINANT) || data[1].equals(RECESSIVE) || data[1].equals(NON_EXISTENT)){
				genes.put(name, data[1]);
			}else{
				String parent = data[0];
				String child = data[1];
				if(!childToParents.containsKey(child)){
					childToParents.put(child, new ArrayList<String>());
				}
				childToParents.get(child).add(parent);
			}
		}
		
		for(Entry<String, List<String>> entry : childToParents.entrySet()){
			genes.put(entry.getKey(), getGene(entry.getKey(), genes, childToParents));
		}
		
		return genes;
	}
	
	static String getInheritedGene(String parent1Gene, String parent2Gene){
		if(bothParentsHaveGene(parent1Gene, parent2Gene) || geneDominantInOneOfParents(parent1Gene, parent2Gene)){
			if(geneDominantInBothParents(parent1Gene, parent2Gene) || 
					geneDominantInOneParentAndRecessiveInAnother(parent1Gene, parent2Gene)){
				return DOMINANT;
			}else{
				return RECESSIVE;
			}
		}
		
		return NON_EXISTENT;
	}
	
	static boolean bothParentsHaveGene(String parent1Gene, String parent2Gene){
		return !parent1Gene.equals(NON_EXISTENT) && !parent2Gene.equals(NON_EXISTENT);
	}
	
	static boolean geneDominantInOneOfParents(String parent1Gene, String parent2Gene){
		return parent1Gene.equals(DOMINANT) || parent2Gene.equals(DOMINANT);
	}
	
	static boolean geneDominantInOneParentAndRecessiveInAnother(String parent1Gene, String parent2Gene){
		return (parent1Gene.equals(DOMINANT) && parent2Gene.equals(RECESSIVE)) || 
				(parent2Gene.equals(DOMINANT) && parent1Gene.equals(RECESSIVE));
	}
	
	static boolean geneDominantInBothParents(String parent1Gene, String parent2Gene){
		return parent1Gene.equals(DOMINANT) && parent2Gene.equals(DOMINANT);
	}
	
	static String getGene(
			String person, 
			SortedMap<String, String> genes, 
			Map<String, List<String>> childToParents){
		if(genes.containsKey(person)) return genes.get(person);
		String parent1Gene = getGene(childToParents.get(person).get(0), genes, childToParents);
		String parent2Gene = getGene(childToParents.get(person).get(1), genes, childToParents);
		return getInheritedGene(parent1Gene, parent2Gene);
	}
}
