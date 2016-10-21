package helvidios.cp.ch2.lineards._1darraymanip;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _755_487_3279 {
	public static class Duplicate{
		public int number;
		public int nOccurrences;
	}
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	public static void main(String... args) throws NumberFormatException, IOException{
		String data = "2\r\n" + 
				"\r\n" + 
				"12\r\n" + 
				"4873279\r\n" + 
				"ITS-EASY\r\n" + 
				"888-4567\r\n" + 
				"3-10-10-10\r\n" + 
				"888-GLOP\r\n" + 
				"TUT-GLOP\r\n" + 
				"967-11-11\r\n" + 
				"310-GINO\r\n" + 
				"F101010\r\n" + 
				"888-1200\r\n" + 
				"-4-8-7-3-2-7-9-\r\n" + 
				"487-3279\r\n\n" + 
				"12\r\n" + 
				"4873279\r\n" + 
				"ITS-EASY\r\n" + 
				"888-4567\r\n" + 
				"3-10-10-10\r\n" + 
				"888-GLOP\r\n" + 
				"TUT-GLOP\r\n" +
				"967-11-11\r\n" + 
				"310-GINO\r\n" + 
				"F101010\r\n" + 
				"888-1200\r\n" + 
				"-4-8-7-3-2-7-9-\r\n" + 
				"487-3279\r\n" + 
				"";
		String data2 = "1\r\n" + 
				"\r\n" + 
				"40\r\n" + 
				"0U--4N712\r\n" + 
				"---X2-KN-U-75\r\n" + 
				"VR-J37G--3\r\n" + 
				"85W-0Y6-V\r\n" + 
				"85W-0Y6-V\r\n" + 
				"85W-0Y6-V\r\n" + 
				"--N-6AV4-NK\r\n" + 
				"--XL----F-PO--B-0\r\n" + 
				"-P-10167P\r\n" + 
				"7--R8YME-N\r\n" + 
				"P-U-1O6W-----1\r\n" + 
				"X-KFK87--L\r\n" + 
				"YFX63K-N\r\n" + 
				"-3-73G---ELH\r\n" + 
				"--2RD-6-IJ-Y\r\n" + 
				"P2--SJ9G9\r\n" + 
				"P2--SJ9G9\r\n" + 
				"O-1----2287-2\r\n" + 
				"-DC-C1L-3V\r\n" + 
				"7963V68\r\n" + 
				"U-66X52M\r\n" + 
				"U-66X52M\r\n" + 
				"-8-16F5TG\r\n" + 
				"---YN-32E5-K\r\n" + 
				"---YN-32E5-K\r\n" + 
				"4--KDJ-PV2\r\n" + 
				"4--KDJ-PV2\r\n" + 
				"P27-42L2\r\n" + 
				"P27-42L2\r\n" + 
				"-MIWO-W5D\r\n" + 
				"44M7675\r\n" + 
				"F---6-7R80M\r\n" + 
				"T5S6U1-P\r\n" + 
				"T5S6U1-P\r\n" + 
				"T5S6U1-P\r\n" + 
				"BUM85L--3\r\n" + 
				"BUM85L--3\r\n" + 
				"DB1E---046\r\n" + 
				"36O--7-1W2\r\n" + 
				"-R--52-P-Y7H";
		String data3 = "1\r\n" + 
				"\r\n" + 
				"4\r\n" + 
				"000-0123\r\n" + 
				"000-0123\n"+
				"999-9999\n"+
				"999-9999";
		
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data3.getBytes()));
		int nDatasets = Integer.parseInt(reader.readLine());
		reader.readLine();
		StringBuilder out = new StringBuilder();
		while(nDatasets-- > 0){
			int nNumbers = Integer.parseInt(reader.readLine());
			String[] numbers = new String[nNumbers];
			for(int i = 0; i < nNumbers; i++){
				numbers[i] = reader.readLine();
			}
			
			Duplicate[] dups = getDuplicateNumbers(standardFormNumbers(numbers));
			if(dups.length == 0) out.append("No duplicates.\n");
			else{
				for(Duplicate dup : dups){
					String standardNumber = String.format("%07d", dup.number);
					standardNumber = standardNumber.substring(0, 3) + "-" + standardNumber.substring(3);
					out.append(standardNumber + " " + dup.nOccurrences + "\n");
				}
			}
			if(nDatasets > 0) out.append("\n");
			reader.readLine();
		}
		System.out.print(out.toString());
	}
	
	public static int[] standardFormNumbers(String[] numbers){
		final int[] mappings = new int[]   {2,2,2,
											3,3,3,
											4,4,4,
											5,5,5,
											6,6,6,
											7,0,7,7,
											8,8,8,
											9,9,9,0};
		int[] digitalNumbers = new int[numbers.length];
		for(int i = 0; i < numbers.length; i++){
			String number = numbers[i];
			int standardForm = 0;
			for(int d = 0; d < number.length(); d++){
				if(Character.isDigit(number.charAt(d))){
					standardForm = standardForm * 10 + (number.charAt(d) - '0');
				}else if(Character.isLetter(number.charAt(d))){
					standardForm = standardForm * 10 + mappings[number.charAt(d) - 'A'];
				}
			}
			digitalNumbers[i] = standardForm;
		}
		
		return digitalNumbers;
	}
	
	public static Duplicate[] getDuplicateNumbers(int[] numbers){
		List<Duplicate> duplicates = new ArrayList<Duplicate>();
		Arrays.sort(numbers);
		for(int i = 0; i < numbers.length; i++){
			int duplicateCount = 0;
			int j = i + 1;
			for(; j < numbers.length; j++){
				if(numbers[j] == numbers[i]) duplicateCount++;
				else break;
			}

			if(duplicateCount > 0){
				Duplicate duplicate = new Duplicate();
				duplicate.number = numbers[i];
				duplicate.nOccurrences = duplicateCount + 1;
				duplicates.add(duplicate);
			}
			
			i = j - 1;
		}
		
		return duplicates.toArray(new Duplicate[0]);
	}
}
