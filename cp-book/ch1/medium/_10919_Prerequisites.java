package helvidios.cp.ch1.medium;

import java.util.Scanner;

public class _10919_Prerequisites {
	public static void main(String... args){
		String data = "3 2\r\n" + 
				"0123 9876 2222\r\n" + 
				"2 1 8888 2222\r\n" + 
				"3 2 9876 2222 7654 \r\n" + 
				"3 2\r\n" + 
				"0123 9876 2222\r\n" + 
				"2 2 8888 2222\r\n" + 
				"3 2 7654 9876 2222\r\n" + 
				"0\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int takenCoursesCount = scanner.nextInt();
			if(takenCoursesCount == 0) break;
			
			int categoryCount = scanner.nextInt();
			int[] takenCourses = new int[takenCoursesCount];
			for(int course = 0; course < takenCoursesCount; course++){
				takenCourses[course] = scanner.nextInt();
			}
			
			boolean meetsRequirements = true;
			for(int category = 0; category < categoryCount; category++){
				int categoryCourseCount = scanner.nextInt();
				int categoryMinCourseCount = scanner.nextInt();
				int[] categoryCourses = new int[categoryCourseCount];
				for(int i = 0; i < categoryCourses.length; i++)
					categoryCourses[i] = scanner.nextInt();
				
				if(!meetsCategoryRequirements(takenCourses, categoryCourses, categoryMinCourseCount))
					meetsRequirements = false;
			}
			
			if(meetsRequirements) System.out.println("yes");
			else System.out.println("no");
		}
		scanner.close();
	}
	
	private static boolean meetsCategoryRequirements(
			int[] takenCourses, 
			int[] categoryCourses, 
			int mandatoryCourseCount){
		int count = 0;
		for(int course : takenCourses){
			if(contains(categoryCourses, course))
				count++;
		}
		
		return count >= mandatoryCourseCount;
	}
	
	private static boolean contains(int[] array, int element){
		for(int i = 0; i < array.length; i++)
			if(array[i] == element) return true;
		return false;
	}
}
