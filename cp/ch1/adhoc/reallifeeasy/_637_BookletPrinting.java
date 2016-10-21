package helvidios.cp.ch1.adhoc.reallifeeasy;

import java.util.Scanner;

public class _637_BookletPrinting {
	static final int BLANK = 0;
	static final String BLANK_PAGE = "Blank";
	private static class Sheet{
		public int front1;
		public int front2;
		public int back1;
		public int back2;
		public int sheet;
		
		public boolean frontBlank(){
			return front1 == BLANK && front2 == BLANK;
		}
		
		public boolean backBlank(){
			return back1 == BLANK && back2 == BLANK;
		}
		
		public String getPage(int side){
			return side == BLANK ? BLANK_PAGE : Integer.toString(side);
		}
		
		public String toString(){
			return Integer.toString(sheet);
		}
	}
	public static void main(String... args){
		String data = "1\r\n" + 
				"14\r\n" + 
				"4\r\n" + 
				"0\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while (scanner.hasNext()) {
			int nPages = scanner.nextInt();
			if(nPages == 0) break;
			System.out.println(String.format("Printing order for %1$d pages:", nPages));
			System.out.print(printLayout(generateLayout(nPages)));
		}
		scanner.close();
	}
	
	public static String printLayout(Sheet[] layout){
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < layout.length; i++){
			Sheet sheet = layout[i];
			if(!layout[i].frontBlank()){
				s.append(String.format("Sheet %1$d, front: %2$s, %3$s\n", 
						sheet.sheet, 
						sheet.getPage(sheet.front1),
						sheet.getPage(sheet.front2)));
			}
			if(!layout[i].backBlank()){
				s.append(String.format("Sheet %1$d, back : %2$s, %3$s\n", 
						sheet.sheet,
						sheet.getPage(sheet.back1),
						sheet.getPage(sheet.back2)));
			}
		}
		return s.toString();
	}
	
	public static Sheet[] generateLayout(int nPages){
		int nSheets = getNumSheets(nPages);
		Sheet[] layout = new Sheet[nSheets];
		
		int page = 0;
		for(int i = 0; i < nSheets; i++){
			Sheet sheet = new Sheet();
			sheet.sheet = i + 1;
			sheet.front2 = ++page;
			sheet.back1 = ++page;
			layout[i] = sheet;
		}
		
		for(int i = nSheets - 1; i >= 0; i--){
			layout[i].back2 = ++page;
			layout[i].front1 = ++page;
		}
		
		for(int i = 0; i < nSheets; i++){
			if(layout[i].front1 > nPages) layout[i].front1 = BLANK;
			if(layout[i].front2 > nPages) layout[i].front2 = BLANK;
			if(layout[i].back1 > nPages) layout[i].back1 = BLANK;
			if(layout[i].back2 > nPages) layout[i].back2 = BLANK;
		}
		
		return layout;
	}
	
	public static int getNumSheets(int nPages){
		return (int)Math.ceil(nPages / 4.0);
	}
}
