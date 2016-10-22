package helvidios.cp.ch1.adhoc.timewaster;

import java.util.Scanner;

public class _144_StudentGrants {
	public static void main(String... args){
		String data = "5 3\r\n" + 
				"0 0\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(true){
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			if(n == 0 && k == 0) break;
			System.out.println(print(dispenseGrants(n, k)));
		}
		scanner.close();
	}
	
	static String print(int[] students){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < students.length; i++){
			sb.append(String.format("%1$3d", students[i]));
		}
		return sb.toString();
	}
	
	public static int[] dispenseGrants(int nStudents, int machineLimit){
		final int grantAmount = 40;
		int currentCycle = 1;
		int outputStore = currentCycle;
		int[] students = new int[nStudents];
		int[] studentsLeavingQueue = new int[nStudents];
		int current = 0;
		while(!queueEmpty(students, grantAmount)){
			for(int i = 0; i < students.length; i++){
				if(students[i] < grantAmount){
					int amountLeftToPay = grantAmount - students[i];
					if(outputStore < amountLeftToPay){
						students[i] += outputStore;
						currentCycle = nextCycle(currentCycle, machineLimit);
						// move coins to the output store
						outputStore = currentCycle;
					}else{
						outputStore -= amountLeftToPay;
						students[i] += amountLeftToPay;
						studentsLeavingQueue[current++] = i + 1;
						if(outputStore == 0){
							currentCycle = nextCycle(currentCycle, machineLimit);
							// move coins to the output store
							outputStore = currentCycle;
						}
					}
				}
			}
		}
		
		return studentsLeavingQueue;
	}
	
	static int nextCycle(int currentCycle, int machineLimit){
		return (currentCycle % machineLimit) + 1; 
	}
	
	static boolean queueEmpty(int[] students, int grantAmount){
		for(int i = 0; i < students.length; i++){
			if(students[i] < grantAmount) return false;
		}
		return true;
	}
}
