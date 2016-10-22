package helvidios.cp.ch1.medium;

import java.util.Locale;
import java.util.Scanner;

public class _10141_RequestForProposal {
	private static class Proposal{
		public String name;
		public double price;
		public int[] metRequirements;
		public double getCompliance(int reqCount){
			return (double) metRequirements.length / reqCount;
		}
	}
	
	private static int getIndexOf(String[] requirements, String requirement){
		for(int i = 0; i < requirements.length; i++){
			if(requirements[i].equals(requirement)) return i;
		}
		return -1;
	}
	
	public static void main(String... args){
		String data = "6 4\r\n" + 
				"engine\r\n" + 
				"brakes\r\n" + 
				"tires\r\n" + 
				"ashtray\r\n" + 
				"vinyl roof\r\n" + 
				"trip computer\r\n" + 
				"Chevrolet\r\n" + 
				"20000.00 3\r\n" + 
				"engine\r\n" + 
				"tires\r\n" + 
				"brakes\r\n" + 
				"Cadillac\r\n" + 
				"70000.00 4\r\n" + 
				"ashtray\r\n" + 
				"vinyl roof\r\n" + 
				"trip computer\r\n" + 
				"engine\r\n" + 
				"Hyundai\r\n" + 
				"10000.00 3\r\n" + 
				"engine\r\n" + 
				"tires\r\n" + 
				"ashtray\r\n" + 
				"Lada\r\n" + 
				"6000.00 1\r\n" + 
				"tires\r\n" + 
				"1 1\r\n" + 
				"coffee\r\n" + 
				"Starbucks\r\n" + 
				"1.50 1\r\n" + 
				"coffee\r\n" + 
				"0 0\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		scanner.useLocale(Locale.US);
		int counter = 0;
		while(scanner.hasNext()){
			int reqCount = scanner.nextInt();
			int proposalCount = scanner.nextInt();
			if(reqCount == 0 && proposalCount == 0) break;
			
			String[] requirements = new String[reqCount];
			for(int requirement = 0; requirement < requirements.length; requirement++){
				requirements[requirement] = scanner.nextLine();
				if(requirements[requirement].isEmpty()) requirement--;
			}
			
			Proposal[] proposals = new Proposal[proposalCount];
			for(int i = 0; i < proposalCount; i++){
				Proposal proposal = new Proposal();
				proposal.name = scanner.nextLine();
				proposal.price = scanner.nextDouble();
				proposal.metRequirements = new int[scanner.nextInt()];
				for(int j = 0; j < proposal.metRequirements.length; j++){
					String req = scanner.nextLine();
					if(req.isEmpty()) j--;
					else proposal.metRequirements[j] = getIndexOf(requirements, req);
				}
				proposals[i] = proposal;
			}
			
			Proposal best = getBestProposal(proposals, requirements);
			if(++counter != 1) System.out.println();
			System.out.printf("RFP #%1$d\n%2$s\n", counter, best.name);
		}
		scanner.close();
	}
	
	private static Proposal getBestProposal(Proposal[] proposals, String[] requirements){
		int reqCount = requirements.length;
		Proposal best = proposals[0];
		for(int proposal = 1; proposal < proposals.length; proposal++){
			Proposal candidate = proposals[proposal];
			if(candidate.getCompliance(reqCount) > best.getCompliance(reqCount)){
				best = candidate;
			}
			else if(candidate.getCompliance(reqCount) == best.getCompliance(reqCount)){
				if(candidate.price < best.price) best = candidate;
			}
		}
		return best;
	}
}
