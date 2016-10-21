package helvidios.cp.ch1.adhoc.reallifeharder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class _1061_ConsanguineCalculations {
	public static void main(String... args){
		String data = "\n     \n\nO+  O-  ? \r\n" + 
				"O+  ?       O-\r\n" + 
				"AB-  AB+  ? \r\n" + 
				"AB+  ?  O+ \r\n" + 
				"E N D\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		int caseNum = 0;
		while (true) {
			String line;
			while((line = scanner.nextLine().trim()).isEmpty());
			if(line.equals("E N D")) break;
			String[] types = line.split("\\s+");
			if(types[2].equals("?")){
				String parent1 = types[0];
				String parent2 = types[1];
				System.out.printf("Case %1$d: %2$s %3$s %4$s\n", ++caseNum, parent1, parent2, 
						toString(possibleInheritedBloodTypes(parent1, parent2)));
			}else{
				String parent1 = types[0].equals("?") ? types[1] : types[0];
				String child = types[2];
				List<String> bt = possibleParentBloodTypes(parent1, child);
				String bloodTypes = bt.size() == 0 ? "IMPOSSIBLE" : toString(bt);
				System.out.printf("Case %1$d: %2$s %3$s %4$s\n", ++caseNum, 
						types[0].equals("?")?bloodTypes:parent1,
						types[1].equals("?")?bloodTypes:parent1,
						child);
			}
		}
		scanner.close();
	}
	
	static String toString(List<String> bloodTypes){
		if(bloodTypes.size() == 1) return bloodTypes.get(0);
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(int i = 0; i < bloodTypes.size(); i++){
			sb.append(bloodTypes.get(i));
			if(i < bloodTypes.size() - 1){
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	public static List<String> possibleParentBloodTypes(
			String parent1BloodType, String childBloodType){
		List<String> types = new ArrayList<String>();
		String[] allBloodTypes = new String[]{"A+","A-","AB+","AB-","B+","B-","O+","O-"};
		for(String parent2BloodType : allBloodTypes){
			if(possibleInheritedBloodTypes(
					parent1BloodType, parent2BloodType).contains(childBloodType)){
				types.add(parent2BloodType);
			}
		}
		return types;
	}
	
	public static List<String> possibleInheritedBloodTypes(
			String parent1BloodType, String parent2BloodType){
		List<String> types = new ArrayList<String>();
		List<String> possibleAboBloodTypes = possibleInheritedAboBloodTypes(
				parent1BloodType.substring(0, parent1BloodType.length() - 1), 
				parent2BloodType.substring(0, parent2BloodType.length() - 1));
		List<String> possibleRhFactors = possibleInheritedRhFactors(
				parent1BloodType.charAt(parent1BloodType.length() - 1), 
				parent2BloodType.charAt(parent2BloodType.length() - 1));
		for(String bloodType : possibleAboBloodTypes){
			for(String rhFactor : possibleRhFactors){
				types.add(bloodType + rhFactor);
			}
		}
		return types;
	}
	
	static List<String> possibleInheritedRhFactors(char parent1RhFactor, char parent2RhFactor){ 
		Set<String> factors = new HashSet<String>();
		for(char rhAllele1 : rhAlleles(parent1RhFactor)){
			for(char rhAllele2 : rhAlleles(parent2RhFactor)){
				if(rhAllele1 == '-' && rhAllele2 == '-') factors.add("-");
				else factors.add("+");
			}
		}
		return new ArrayList<String>(factors);
	}
	
	static List<String> possibleInheritedAboBloodTypes(String parent1AboBloodType, String parent2AboBloodType){
		Set<String> types = new HashSet<String>();
		for(char allele1 : aboAlleles(parent1AboBloodType)){
			for(char allele2 : aboAlleles(parent2AboBloodType)){
				String bloodType = bloodType(new char[]{allele1, allele2});
				if(bloodType == null) bloodType = bloodType(new char[]{allele2, allele1});
				types.add(bloodType);
			}
		}
		return new ArrayList<String>(types);
	}
	
	static char[] rhAlleles(char rhFactor){
		if(rhFactor == '+') return new char[]{'+','-'};
		return new char[]{'-'};
	}
	
	static char[] aboAlleles(String aboBloodType){
		switch (aboBloodType) {
			case "A":
				return new char[]{'A','O'};
			case "AB":
				return new char[]{'A','B'};
			case "B":
				return new char[]{'B','O'};
			case "O":
				return new char[]{'O'};
		}
		throw new RuntimeException("Unknown blood type " + aboBloodType);
	}
	
	static String bloodType(char[] allelePair){
		if(allelePair[0] == 'A' && allelePair[1] == 'A') return "A";
		if(allelePair[0] == 'A' && allelePair[1] == 'B') return "AB";
		if(allelePair[0] == 'A' && allelePair[1] == 'O') return "A";
		if(allelePair[0] == 'B' && allelePair[1] == 'B') return "B";
		if(allelePair[0] == 'B' && allelePair[1] == 'O') return "B";
		if(allelePair[0] == 'O' && allelePair[1] == 'O') return "O";
		return null;
	}
}
