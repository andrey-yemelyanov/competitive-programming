package helvidios.cp.ch1.adhoc.reallifeeasy;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class _187_TransactionProcessing {
	private static class Transaction{
		public Transaction(int transactionId, int accountNumber, double amount){
			this.transactionId = transactionId;
			this.accountNumber = accountNumber;
			this.amount = amount;
		}
		public int transactionId;
		public int accountNumber;
		public double amount;
		
		public String toString(){
			return transactionId + " " + accountNumber + " " + amount;
		}
	}
	public static void main(String... args){
		String data = "111Cash \r\n" + 
				"121Accounts Receivable\r\n" + 
				"211Accounts Payable\r\n" + 
				"241Sales Tax Payable \r\n" + 
				"401Sales\r\n" + 
				"555Office Supplies\r\n" + 
				"000No such account\r\n" + 
				"100111    11795\r\n" + 
				"100121   -11795\r\n" + 
				"101121      105\r\n" + 
				"101241       -7 \r\n" + 
				"101401     -100\r\n" + 
				"102211   -70000\r\n" + 
				"102555    40000\r\n" + 
				"103111   -40000 \r\n" + 
				"103555    40000\r\n" + 
				"000000        0\r\n" + 
				"";
		Scanner scanner = new Scanner(data);

		String[] accounts = new String[999];
		// read account numbers
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			int accountNumber = Integer.parseInt(line.substring(0, 3));
			if(accountNumber == 0) break;
			accounts[accountNumber] = line.substring(3);
		}
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		// read transactions
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			int transactionId = Integer.parseInt(line.substring(0, 3));
			if(transactionId == 0) break;
			int accountNumber = Integer.parseInt(line.substring(3, 6));
			double amount = Integer.parseInt(line.substring(6).trim());
			transactions.add(new Transaction(transactionId, accountNumber, amount));
		}
		
		Integer[] transactionIds = getTransactionIds(transactions);
		for(int transactionId : transactionIds){
			int balance;
			if((balance = getBalance(transactions, transactionId)) != 0){
				System.out.println(printExceptionReport(
						transactions, accounts, transactionId, balance));
				System.out.println();
			}
		}
		
		scanner.close();
	}
	
	public static Integer[] getTransactionIds(List<Transaction> transactions){
		Set<Integer> ids = new LinkedHashSet<Integer>();
		for(Transaction transaction : transactions){
			ids.add(transaction.transactionId);
		}
		return ids.toArray(new Integer[0]);
	}
	
	public static int getBalance(List<Transaction> transactions, int transactionId){
		int balance = 0;
		for(Transaction transaction : transactions){
			if(transaction.transactionId == transactionId){
				balance += transaction.amount;
			}
		}
		return Math.abs(balance);
	}
	
	public static String printExceptionReport(
			List<Transaction> transactions, String[] accounts, int transactionId, double balance){
		StringBuilder report = new StringBuilder();
		report.append(String.format("*** Transaction %1$d is out of balance ***\n", transactionId));
		for(Transaction transaction : transactions){
			if(transaction.transactionId == transactionId){
				report.append(String.format(Locale.US, "%1$d %2$-30s %3$10.2f", 
						transaction.accountNumber, 
						accounts[transaction.accountNumber], 
						transaction.amount / 100));
				report.append("\n");
			}
		}
		
		report.append(String.format(Locale.US, "%1$d %2$-30s %3$10.2f", 
				999, "Out of Balance", balance / 100));
		
		return report.toString();
	}
}
