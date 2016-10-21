package helvidios.cp.ch1.supereasy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class _272_TEX_Quotes {
	public static void main(String... args) throws IOException{
		String openingQuotation = "``";
		String closingQuotation = "''";
		
		String source = "\"To be or not to be,\" quoth the Bard, \"that\r\n" + 
				"is the question\".\r\n" + 
				"The programming contestant replied: \"I must disagree.\r\n" + 
				"To `C' or not to `C', that is The Question!\"";
		
		Reader reader = new InputStreamReader(new ByteArrayInputStream(source.getBytes()));
		//Reader reader = new InputStreamReader(System.in);
		int c;
		boolean quotationOpened = false;
		while((c = reader.read()) != -1){
			char inputChar = (char)c;
			if(inputChar == '"'){
				if(quotationOpened){
					System.out.print(closingQuotation);
					quotationOpened = false;
				}
				else{
					System.out.print(openingQuotation);
					quotationOpened = true;
				}
			}else System.out.print(inputChar);
		}
	}
}
