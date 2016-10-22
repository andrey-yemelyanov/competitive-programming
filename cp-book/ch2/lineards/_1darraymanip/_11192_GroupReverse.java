package helvidios.cp.ch2.lineards._1darraymanip;

import java.util.Scanner;

public class _11192_GroupReverse {
	public static void main(String... args){
		String data = "3 ABCEHSHSH\r\n" + 
				"\r\n" + 
				"5 FA0ETASINAHGRI0NATWON0QA0NARI0\r\n" + 
				"\r\n" + 
				"0\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(true){
			int nGroups = scanner.nextInt();
			if(nGroups == 0) break;
			String str = scanner.next();
			System.out.println(reverse(str, nGroups));
		}
		scanner.close();
	}
	
	public static String reverse(String str, int nGroups){
		char[] chars = str.toCharArray();
		int groupLength = chars.length / nGroups;
		for(int i = 0; i < chars.length; i += groupLength){
			reverseGroup(chars, i, i + groupLength - 1);
		}
		return new String(chars);
	}
	
	public static void reverseGroup(char[] chars, int from, int to){
		while(from < to){
			char temp = chars[from];
			chars[from] = chars[to];
			chars[to] = temp;
			from++; to--;
		}
	}
}	
