package helvidios.cp.ch2.nonlineards.map;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class _10686_SQFProblems {
	public static final String SQF_PROBLEM = "SQF Problem.";
	private static BufferedReader getBufferedReader(InputStream inputStream){
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	public static void main(String... args) throws NumberFormatException, IOException{
		String data = "1\n3\r\n" + 
				"NeverOne 0 1\r\n" + 
				"NeverTwo 1 2\r\n" + 
				"dummy\r\n" + 
				"NeverThree 2 2\r\n" + 
				"dummy\r\n" + 
				"dummy\r\n" + 
				"This is a text where the word dummy apears twice!\r\n" + 
				"Because I've added a dummy line. ";
		String data2 = "15\r\n" + 
				"4\r\n" + 
				"Biology 14 5\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"soil\r\n" + 
				"water\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"soil\r\n" + 
				"water\r\n" + 
				"Zoology 10 5\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"Numerology 5 0\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"Toxicology 4 7\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"plant\r\n" + 
				"animal life cell earth plant\r\n" + 
				"animal!\r\n" + 
				"life. \r\n" + 
				"cell;\r\n" + 
				"earth? plant animal life \r\n" + 
				"cell earth plant...animal life  cell earth\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"\r\n" + 
				"4\r\n" + 
				"Biology 14 1\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"soil\r\n" + 
				"water\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"soil\r\n" + 
				"water\r\n" + 
				"Zoology 10 5\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"Numerology 5 0\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"Toxicology 4 1\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"plant\r\n" + 
				"animal life cell earth plant\r\n" + 
				"animal!\r\n" + 
				"life. \r\n" + 
				"cell;\r\n" + 
				"earth? plant animal life \r\n" + 
				"cell earth plant...animal life  cell earth\r\n" + 
				"plant\r\n" + 
				"animal\r\n" + 
				"life \r\n" + 
				"cell\r\n" + 
				"earth\r\n" + 
				"\r\n" + 
				"1\r\n" + 
				"Geography 0 1\r\n" + 
				"Some rubbish text\r\n" + 
				"This should not be processed\r\n" + 
				"\r\n" + 
				"1\r\n" + 
				"Geography 1 1\r\n" + 
				"sudan\r\n" + 
				"\r\n" + 
				"3\r\n" + 
				"Imaginography 3 2\r\n" + 
				"imagine\r\n" + 
				"imagine\r\n" + 
				"real\r\n" + 
				"Geography 2 1\r\n" + 
				"chad\r\n" + 
				"sudan\r\n" + 
				"Countrygraphy 2 2\r\n" + 
				"chad\r\n" + 
				"sudan\r\n" + 
				"chad;imaginesudan is also in africa \r\n" + 
				"chad;imagine sudan is also in africa\r\n" + 
				"chad;sudan is also in africa\r\n" + 
				"chad;sudan is also in africa|real\r\n" + 
				"\r\n" + 
				"0\r\n" + 
				"\r\n" + 
				"0\r\n" + 
				"But still rubbish text and this is \r\n" + 
				"going to be processed????\r\n" + 
				"\r\n" + 
				"2\r\n" + 
				"Cryptology 2 2\r\n" + 
				"          cipher             \r\n" + 
				"                               decipher                       \r\n" + 
				"      Cryptography 1                0\r\n" + 
				"      key             \r\n" + 
				"cipher cipher cipher\r\n" + 
				"cipher[][230492-4923decipher<>>>>key \r\n" + 
				"\r\n" + 
				"2\r\n" + 
				"Cryptology 2 2\r\n" + 
				"          cipher             \r\n" + 
				"                               decipher                       \r\n" + 
				"      Cryptography 1                0\r\n" + 
				"      key             \r\n" + 
				"\r\n" + 
				"2\r\n" + 
				"category 3 2\r\n" + 
				"category\r\n" + 
				"category\r\n" + 
				"category \r\n" + 
				"Category 3 1\r\n" + 
				"category \r\n" + 
				"category \r\n" + 
				"category \r\n" + 
				"category Category\r\n" + 
				"\r\n" + 
				"2\r\n" + 
				"category 3 0\r\n" + 
				"category\r\n" + 
				"category\r\n" + 
				"category \r\n" + 
				"Category 3 0\r\n" + 
				"category \r\n" + 
				"category \r\n" + 
				"category \r\n" + 
				"\r\n" + 
				"1\r\n" + 
				"HipHop 0 10\r\n" + 
				"FloRida LilWayne\r\n" + 
				"\r\n" + 
				"3\r\n" + 
				"Math 3 2\r\n" + 
				"plus \r\n" + 
				"minus\r\n" + 
				"equals\r\n" + 
				"Physics 4 1\r\n" + 
				"mass\r\n" + 
				"plus\r\n" + 
				"Physics\r\n" + 
				"plus\r\n" + 
				"Chemistry 2 2\r\n" + 
				"atom\r\n" + 
				"reaction\r\n" + 
				"reaction plus plus plus not equals \r\n" + 
				"\r\n" + 
				"1\r\n" + 
				"Graph 2 2\r\n" + 
				"edge\r\n" + 
				"these\r\n" + 
				"We need an edge! And theseãáàâçéêíõóôúü are portuguese characters that are \r\n" + 
				"embedded so make sure you parse them\r\n" + 
				"\r\n" + 
				"3\r\n" + 
				"Cat 2 1\r\n" + 
				"batmouse\r\n" + 
				"supermouse\r\n" + 
				"Eats 2 1\r\n" + 
				"spidermouse\r\n" + 
				"CaptainAmericaMouse\r\n" + 
				"Dog 1 1\r\n" + 
				"hahahaitsavalidword\r\n" + 
				"I Can't hear you \"AYE AYE CAPTAIN\"\r\n" + 
				"Ohh...\r\n" + 
				"Who lives in a pineapple under the sea\r\n" + 
				"\"Spongebob squarepants\"\r\n" + 
				"Absorbant batmouse and yellow and porous is he\r\n" + 
				"\"Spongebob Squarepants\"\r\n" + 
				"If nautical nonsense be something you wish\r\n" + 
				"\"Spongebob *34*54545*CaptainAmericaMouse*5*5**5445* Squarepants\"\r\n" + 
				"Then @@hahahaitsavalidword. drop on the super.mouse deck and flop like a fish\r\n" + 
				"\"Spongebob Squarepants\"\r\n" + 
				"READY batmouse of course\r\n" + 
				"Spongebob squarepants\r\n" + 
				"Spongebob squarepants\r\n" + 
				"Spongebob squarepants";
		String data3 = "3\r\n" + 
				"2\r\n" + 
				"Graph 4 3\r\n" + 
				"node\r\n" + 
				"edge\r\n" + 
				"directed\r\n" + 
				"distance\r\n" + 
				"Geometrical 4 2\r\n" + 
				"point\r\n" + 
				"convex\r\n" + 
				"polygon\r\n" + 
				"boring\r\n" + 
				"This is neither a SQF problem nor a graph problem.\r\n" + 
				"This is a boring geometrical problem. In this problem\r\n" + 
				"you should calculate the convex area of a regular polygon.\r\n" + 
				"\r\n" + 
				"2\r\n" + 
				"Graph 4 3\r\n" + 
				"node\r\n" + 
				"edge\r\n" + 
				"directed\r\n" + 
				"distance\r\n" + 
				"Geometrical 4 2\r\n" + 
				"point\r\n" + 
				"convex\r\n" + 
				"polygon\r\n" + 
				"boring\r\n" + 
				"This is neither a SQF problem nor a graph problem node, edge directed.\r\n" + 
				"This is a boring geometrical problem. In this problem\r\n" + 
				"you should calculate the convex area of a regular polygon.\r\n" + 
				"\r\n" + 
				"2\r\n" + 
				"Graph 4 3\r\n" + 
				"node\r\n" + 
				"edge\r\n" + 
				"directed\r\n" + 
				"distance\r\n" + 
				"Geometrical 4 2\r\n" + 
				"point\r\n" + 
				"convex\r\n" + 
				"polygon\r\n" + 
				"boring\r\n" + 
				"This is neither a SQF problem nor a graph problem.\r\n" + 
				"This is a boringa geometrical problem. In this problem\r\n" + 
				"you should calculate the convexaaa area of a regular polygodsn.";
		String data4 = "1\n1\n"
				+ "categ1 1 1\n"
				+ "stop\n"
				+ "stop123";
		BufferedReader reader = getBufferedReader(new ByteArrayInputStream(data4.getBytes()));
		int nTestCases = Integer.parseInt(reader.readLine());
		while(nTestCases-- > 0){
			Map<String, Integer> categoryToRequiredNumKeywords = new TreeMap<String, Integer>();
			Map<String, List<String>> keywordToCategory = new TreeMap<String, List<String>>();
			int nCategories = Integer.parseInt(reader.readLine());
			List<String> allCategories = new ArrayList<String>();
			while(nCategories-- > 0){
				String[] categoryData = reader.readLine().trim().split("\\s+");
				String category = categoryData[0];
				allCategories.add(category);
				int nKeywords = Integer.parseInt(categoryData[1]);
				int nRequiredKeywords = Integer.parseInt(categoryData[2]);
				categoryToRequiredNumKeywords.put(category, nRequiredKeywords);
				while(nKeywords-- > 0){
					String keyword = reader.readLine().trim();
					if(!keywordToCategory.containsKey(keyword)){
						keywordToCategory.put(keyword, new ArrayList<String>());
					}
					if(!keywordToCategory.get(keyword).contains(category))
						keywordToCategory.get(keyword).add(category);
				}
			}
			String line = null;
			StringBuilder problemDescription = new StringBuilder();
			while((line = reader.readLine()) != null && !line.isEmpty()){
				problemDescription.append(line);
				problemDescription.append("\n");
			}
			List<String> categories = getProblemCategories(
					categoryToRequiredNumKeywords, 
					keywordToCategory, allCategories,
					problemDescription.toString());
			if(categories.size() == 0) System.out.println(SQF_PROBLEM);
			else System.out.println(printCategories(categories)); 
		}
	}
	
	static String printCategories(List<String> categories){
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < categories.size(); i++){
			s.append(categories.get(i));
			if(i < categories.size() - 1) s.append(",");
		}
		return s.toString();
	}
	
	public static String[] extractWords(String string){
		return string.split("[^a-zA-Z]");
	}
	
	public static List<String> getProblemCategories(
			Map<String, Integer> categoryToRequiredNumKeywords,
			Map<String, List<String>> keywordToCategory,
			List<String> allCategories,
			String problemDescription){
		Map<String, List<String>> categoryToProblemDescKeywords = new TreeMap<String, List<String>>();
		String[] words = extractWords(problemDescription);
		for(String word : words){
			if(keywordToCategory.containsKey(word)){
				List<String> categories = keywordToCategory.get(word);
				for(String category : categories){
					if(!categoryToProblemDescKeywords.containsKey(category)){
						categoryToProblemDescKeywords.put(category, new ArrayList<String>());
					}
					if(!categoryToProblemDescKeywords.get(category).contains(word)) 
						categoryToProblemDescKeywords.get(category).add(word);
				}
			}
		}
		
		List<String> categories = new ArrayList<String>();
		for(String category : allCategories){
			if(categoryToRequiredNumKeywords.get(category) == 0) categories.add(category);
			else if(categoryToProblemDescKeywords.containsKey(category)){
				if(categoryToProblemDescKeywords.get(category).size() >= 
						categoryToRequiredNumKeywords.get(category)){
					categories.add(category);
				}
			}
		}
		
		return categories;
	}
}
