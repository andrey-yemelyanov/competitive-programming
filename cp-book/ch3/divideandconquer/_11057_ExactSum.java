package helvidios.cp.ch3.divideandconquer;

import java.util.*;

public class _11057_ExactSum{
	public static void main(String[] args){
		String data = "2\r\n40 40\r\n80\r\n5\r\n10 2 6 8 4\r\n10";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int nBooks = scanner.nextInt();
			int[] bookPrices = new int[nBooks];	
			for(int i = 0; i < nBooks; i++){
				bookPrices[i] = scanner.nextInt();
			}
			int moneyAvailable = scanner.nextInt();
			int[] match = solve(bookPrices, moneyAvailable);
			System.out.printf("Peter should buy books whose prices are %1$d and %2$d.\n\n", match[0], match[1]);
		}
	}

	static int[] solve(int[] bookPrices, int moneyAvailable){
		Arrays.sort(bookPrices);
		int minDiff = Integer.MAX_VALUE;
		int price1 = 0; int price2 = 0;
		for(int i = 0; i < bookPrices.length; i++){
			int book1Price = bookPrices[i];
			int book2Price = moneyAvailable - book1Price;
			if(book1Price <= book2Price){
				int matchCount = countBookPrice(bookPrices, book2Price);
				if(matchCount > 0){
					if(book1Price == book2Price && matchCount >= 2){
						return new int[]{book1Price, book2Price};
					}
					else{
						int diff = book2Price - book1Price;
						if(diff < minDiff){
							minDiff = diff;
							price1 = book1Price;
							price2 = book2Price;
						}
					}
				}
			}
		}
		return new int[]{price1, price2};
	}

	static int countBookPrice(int[] bookPrices, int price){
		int i = Arrays.binarySearch(bookPrices, price);
		if(i < 0) return 0;
		
		int count = 1;
		// count left
		for(int j = i - 1; j >= 0; j--){
			if(bookPrices[j] == price) count++;
		}
		// count right
		for(int j = i + 1; j < bookPrices.length; j++){
			if(bookPrices[j] == price) count++;
		}

		return count;
	}
}