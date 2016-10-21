package helvidios.cp.ch2.lineards.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class _12541_Birthdates {
	public static class Person{
		public GregorianCalendar birthdate;
		public String name;
	}
	public static void main(String... args){
		String data = "5\r\n" + 
				"Mickey 1 10 1991\r\n" + 
				"Alice 30 12 1990\r\n" + 
				"Tom 15 8 1993\r\n" + 
				"Jerry 18 9 1990\r\n" + 
				"Garfield 20 9 1990";
		Scanner scanner = new Scanner(data);
		int nPeople = scanner.nextInt();
		Person[] people = new Person[nPeople];
		for(int i = 0; i < nPeople; i++){
			String name = scanner.next();
			int day = scanner.nextInt();
			int month = scanner.nextInt();
			int year = scanner.nextInt();
			Person p = new Person();
			p.name = name;
			p.birthdate = new GregorianCalendar(year, month - 1, day);
			people[i] = p;
		}
		String[] result = getYoungestAndOldest(people);
		System.out.println(result[0] + "\n" + result[1]);
		scanner.close();
	}
	
	public static String[] getYoungestAndOldest(Person[] people){
		Arrays.sort(people, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o2.birthdate.compareTo(o1.birthdate);
			}
		});
		return new String[]{people[0].name, people[people.length - 1].name};
	}
}
