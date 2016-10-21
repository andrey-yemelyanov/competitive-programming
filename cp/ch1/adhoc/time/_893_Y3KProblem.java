package helvidios.cp.ch1.adhoc.time;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class _893_Y3KProblem {
	public static void main(String... args){
		String data = "1 31 12 2999\r\n" + 
				"40 1 2 2004\r\n" + 
				"60 31 12 1999\r\n" + 
				"60 31 12 2999\r\n" + 
				"146097 31 12 1999\r\n" + 
				"999999 1 1 2000\r\n" + 
				"0 0 0 0\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(true){
			int nDays = scanner.nextInt();
			int day = scanner.nextInt();
			int month = scanner.nextInt();
			int year = scanner.nextInt();
			if(nDays == 0 && day == 0 && month == 0 && year == 0) break;
			GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
			calendar.add(GregorianCalendar.DAY_OF_MONTH, nDays);
			System.out.printf("%1$d %2$d %3$d\n", 
					calendar.get(GregorianCalendar.DAY_OF_MONTH),
					calendar.get(GregorianCalendar.MONTH) + 1,
					calendar.get(GregorianCalendar.YEAR));
		}
		scanner.close();
	}
}
