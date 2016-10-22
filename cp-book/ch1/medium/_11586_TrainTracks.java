package helvidios.cp.ch1.medium;

import java.util.Scanner;

public class _11586_TrainTracks {
	public static void main(String... args){
		String data = "4\r\n" + 
				"MF MF\r\n" + 
				"FM FF MF MM\r\n" + 
				"MM FF\r\n" + 
				"MF MF MF MF FF";
		Scanner scanner = new Scanner(data);
		int testCaseCount = Integer.parseInt(scanner.nextLine());
		while(testCaseCount-- > 0){
			String[] pieces = scanner.nextLine().split("\\s+");
			if(isLoopPossible(pieces))
				System.out.println("LOOP");
			else System.out.println("NO LOOP");
		}
		scanner.close();
	}
	
	private static boolean isLoopPossible(String[] pieces){
		if(pieces.length == 1) return false;
		int maleCount = 0;
		int femaleCount = 0;
		for(int i = 0; i < pieces.length; i++){
			if(pieces[i].charAt(0) == 'M') maleCount++;
			if(pieces[i].charAt(0) == 'F') femaleCount++;
			if(pieces[i].charAt(1) == 'M') maleCount++;
			if(pieces[i].charAt(1) == 'F') femaleCount++;
		}
		return maleCount == femaleCount;
	}
}
