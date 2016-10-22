package helvidios.cp.ch3.completesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class _1064_Network {
	public static class Packet{
		public int messageId;
		public int bytesFrom;
		public int bytesTo;
		public Packet(int messageId, int bytesFrom, int bytesTo){
			this.messageId = messageId;
			this.bytesFrom = bytesFrom;
			this.bytesTo = bytesTo;
		}
		public int size(){
			return bytesTo - bytesFrom + 1;
		}
		public int getFirstByteForNextPacket(){
			return bytesTo + 1;
		}
		public String toString(){
			return messageId + " " + bytesFrom + " " + bytesTo;
		}
	}
	public static class PacketReader{
		private Stack<Packet> s;
		private Map<Integer, List<Packet>> inputBuffer = new HashMap<Integer, List<Packet>>();
		private int inputBufferSize = 0; 
		public PacketReader(Packet[] packets){
			s = toStack(packets);
		}
		
		private Stack<Packet> toStack(Packet[] packets){
			Stack<Packet> s = new Stack<Packet>();
			for(int i = packets.length - 1; i >= 0; i--){
				s.push(packets[i]);
			}
			return s;
		}
		
		public Packet readPacket(int messageId, int bytesFrom){
			Packet packet = getPacketFromBuffer(messageId, bytesFrom);
			if(packet != null){
				inputBufferSize -= packet.size();
				return packet;
			}
			
			while(!s.isEmpty()){
				if(s.peek().messageId == messageId && s.peek().bytesFrom == bytesFrom) return s.pop();
				inputBufferSize += bufferPacket(s.pop());
			}

			return null;
		}
		
		private int bufferPacket(Packet packet){
			if(!inputBuffer.containsKey(packet.messageId)) {
				inputBuffer.put(packet.messageId, new ArrayList<Packet>());
			}
			inputBuffer.get(packet.messageId).add(packet);
			return packet.size();
		}
		
		private Packet getPacketFromBuffer(int messageId, int bytesFrom){
			if(!inputBuffer.containsKey(messageId)) return null;
			for(Packet p : inputBuffer.get(messageId)){
				if(p.bytesFrom == bytesFrom) {
					return p;
				}
			}
			return null;
		}
		
		public int getInputBufferSize(){
			return inputBufferSize;
		}
	}
	public static void main(String... args){
		String data = "3 3\r\n" + 
				"5 5 5\r\n" + 
				"1 1 5\r\n" + 
				"2 1 5\r\n" + 
				"3 1 5\r\n" + 
				"3 5\r\n" + 
				"10 20 5\r\n" + 
				"2 16 20\r\n" + 
				"1 6 10\r\n" + 
				"3 1 5\r\n" + 
				"1 1 5\r\n" + 
				"2 1 15\r\n" + 
				"0 0";
		String data2 = "3 9\r\n" + 
				"5 5 5 \r\n" + 
				"3 5 5\r\n" + 
				"1 5 5\r\n" + 
				"1 1 2\r\n" + 
				"2 2 5\r\n" + 
				"3 4 4\r\n" + 
				"3 2 3\r\n" + 
				"3 1 1\r\n" + 
				"1 3 4\r\n" + 
				"2 1 1\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"1 1 1\r\n" + 
				"2 1 1\r\n" + 
				"1 2 5\r\n" + 
				"3 5 5\r\n" + 
				"3 1 4\r\n" + 
				"2 5 5\r\n" + 
				"2 2 4\r\n" + 
				"3 8\r\n" + 
				"5 5 5 \r\n" + 
				"3 3 5\r\n" + 
				"2 1 3\r\n" + 
				"3 2 2\r\n" + 
				"2 4 5\r\n" + 
				"1 1 2\r\n" + 
				"1 4 5\r\n" + 
				"3 1 1\r\n" + 
				"1 3 3\r\n" + 
				"3 8\r\n" + 
				"5 5 5 \r\n" + 
				"3 3 3\r\n" + 
				"1 1 4\r\n" + 
				"1 5 5\r\n" + 
				"2 5 5\r\n" + 
				"3 4 5\r\n" + 
				"2 1 1\r\n" + 
				"2 2 4\r\n" + 
				"3 1 2\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"2 1 3\r\n" + 
				"1 4 4\r\n" + 
				"1 1 3\r\n" + 
				"2 4 5\r\n" + 
				"1 5 5\r\n" + 
				"3 5 5\r\n" + 
				"3 1 4\r\n" + 
				"3 8\r\n" + 
				"5 5 5 \r\n" + 
				"3 1 1\r\n" + 
				"2 3 3\r\n" + 
				"1 3 5\r\n" + 
				"1 1 2\r\n" + 
				"3 2 4\r\n" + 
				"2 4 5\r\n" + 
				"2 1 2\r\n" + 
				"3 5 5\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"1 3 5\r\n" + 
				"3 1 2\r\n" + 
				"1 2 2\r\n" + 
				"3 3 5\r\n" + 
				"1 1 1\r\n" + 
				"2 1 2\r\n" + 
				"2 3 5\r\n" + 
				"3 6\r\n" + 
				"5 5 5 \r\n" + 
				"1 5 5\r\n" + 
				"3 2 5\r\n" + 
				"2 1 2\r\n" + 
				"3 1 1\r\n" + 
				"2 3 5\r\n" + 
				"1 1 4\r\n" + 
				"3 9\r\n" + 
				"5 5 5 \r\n" + 
				"2 2 2\r\n" + 
				"2 1 1\r\n" + 
				"2 3 4\r\n" + 
				"1 3 4\r\n" + 
				"3 4 5\r\n" + 
				"1 1 2\r\n" + 
				"3 1 3\r\n" + 
				"2 5 5\r\n" + 
				"1 5 5\r\n" + 
				"3 6\r\n" + 
				"5 5 5 \r\n" + 
				"3 4 5\r\n" + 
				"1 1 4\r\n" + 
				"2 1 2\r\n" + 
				"2 3 5\r\n" + 
				"1 5 5\r\n" + 
				"3 1 3\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"1 2 3\r\n" + 
				"2 4 5\r\n" + 
				"1 4 5\r\n" + 
				"3 3 5\r\n" + 
				"2 1 3\r\n" + 
				"3 1 2\r\n" + 
				"1 1 1\r\n" + 
				"3 8\r\n" + 
				"5 5 5 \r\n" + 
				"2 5 5\r\n" + 
				"3 1 1\r\n" + 
				"1 1 1\r\n" + 
				"2 1 1\r\n" + 
				"3 5 5\r\n" + 
				"3 2 4\r\n" + 
				"2 2 4\r\n" + 
				"1 2 5\r\n" + 
				"3 8\r\n" + 
				"5 5 5 \r\n" + 
				"1 3 3\r\n" + 
				"1 4 4\r\n" + 
				"1 5 5\r\n" + 
				"3 1 4\r\n" + 
				"2 1 2\r\n" + 
				"1 1 2\r\n" + 
				"3 5 5\r\n" + 
				"2 3 5\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"1 5 5\r\n" + 
				"1 2 4\r\n" + 
				"3 1 2\r\n" + 
				"2 1 4\r\n" + 
				"3 3 5\r\n" + 
				"2 5 5\r\n" + 
				"1 1 1\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"2 5 5\r\n" + 
				"1 1 3\r\n" + 
				"3 1 2\r\n" + 
				"2 1 4\r\n" + 
				"1 4 4\r\n" + 
				"3 3 5\r\n" + 
				"1 5 5\r\n" + 
				"3 6\r\n" + 
				"5 5 5 \r\n" + 
				"2 1 1\r\n" + 
				"3 1 3\r\n" + 
				"1 1 3\r\n" + 
				"3 4 5\r\n" + 
				"1 4 5\r\n" + 
				"2 2 5\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"2 5 5\r\n" + 
				"1 1 4\r\n" + 
				"3 1 3\r\n" + 
				"2 1 1\r\n" + 
				"3 4 5\r\n" + 
				"2 2 4\r\n" + 
				"1 5 5\r\n" + 
				"3 8\r\n" + 
				"5 5 5 \r\n" + 
				"1 3 4\r\n" + 
				"2 5 5\r\n" + 
				"2 2 4\r\n" + 
				"3 3 5\r\n" + 
				"1 1 2\r\n" + 
				"1 5 5\r\n" + 
				"2 1 1\r\n" + 
				"3 1 2\r\n" + 
				"3 9\r\n" + 
				"5 5 5 \r\n" + 
				"1 4 4\r\n" + 
				"2 3 4\r\n" + 
				"2 1 2\r\n" + 
				"3 1 1\r\n" + 
				"1 1 3\r\n" + 
				"2 5 5\r\n" + 
				"3 4 5\r\n" + 
				"3 2 3\r\n" + 
				"1 5 5\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"3 3 5\r\n" + 
				"1 1 2\r\n" + 
				"3 1 2\r\n" + 
				"1 3 5\r\n" + 
				"2 2 4\r\n" + 
				"2 5 5\r\n" + 
				"2 1 1\r\n" + 
				"3 8\r\n" + 
				"5 5 5 \r\n" + 
				"3 1 4\r\n" + 
				"2 3 3\r\n" + 
				"1 4 5\r\n" + 
				"3 5 5\r\n" + 
				"1 1 3\r\n" + 
				"2 4 4\r\n" + 
				"2 1 2\r\n" + 
				"2 5 5\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"3 1 4\r\n" + 
				"3 5 5\r\n" + 
				"2 1 3\r\n" + 
				"2 4 4\r\n" + 
				"2 5 5\r\n" + 
				"1 1 3\r\n" + 
				"1 4 5\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"1 5 5\r\n" + 
				"3 4 5\r\n" + 
				"2 5 5\r\n" + 
				"1 1 2\r\n" + 
				"1 3 4\r\n" + 
				"2 1 4\r\n" + 
				"3 1 3\r\n" + 
				"3 8\r\n" + 
				"5 5 5 \r\n" + 
				"3 5 5\r\n" + 
				"2 1 2\r\n" + 
				"2 5 5\r\n" + 
				"3 1 4\r\n" + 
				"1 1 3\r\n" + 
				"2 3 3\r\n" + 
				"2 4 4\r\n" + 
				"1 4 5\r\n" + 
				"3 7\r\n" + 
				"5 5 5 \r\n" + 
				"1 1 2\r\n" + 
				"2 1 2\r\n" + 
				"3 1 4\r\n" + 
				"3 5 5\r\n" + 
				"1 3 5\r\n" + 
				"2 3 4\r\n" + 
				"2 5 5\r\n" + 
				"0 0\r\n" + 
				"";
		String data3 = "3 7\r\n" + 
				"5 5 5 \r\n" + 
				"1 1 1\r\n" + 
				"2 1 1\r\n" + 
				"1 2 5\r\n" + 
				"3 5 5\r\n" + 
				"3 1 4\r\n" + 
				"2 5 5\r\n" + 
				"2 2 4"
				+ "\n0 0";
		Scanner scanner = new Scanner(data);
		int caseNum = 0;
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			if(n == 0 && m == 0) break;
			int[] messageSizes = new int[n];
			for(int i = 0; i < n; i++){
				messageSizes[i] = scanner.nextInt();
			}
			Packet[] packets = new Packet[m];
			for(int i = 0; i < m; i++){
				Packet p = new Packet(scanner.nextInt() - 1, scanner.nextInt(), scanner.nextInt());
				packets[i] = p;
			}
			int minBufferSize = minBufferSize(messageSizes, packets);
			System.out.printf("Case %1$d: %2$d\n\n", ++caseNum, minBufferSize);
		}
		scanner.close();
	}
	
	public static int minBufferSize(int[] messageSizes, Packet[] packets){
		Integer[] messageOrder = new Integer[messageSizes.length];
		for(int i = 0; i < messageOrder.length; i++){
			messageOrder[i] = i;
		}
		
		int minBufferSize = Integer.MAX_VALUE;
		// try re-forming packets into messages in all possible message permutations
		// keep track of max buffer size used for each of the permutations
		do{
			int maxBufferSize = 0;
			PacketReader packetReader = new PacketReader(packets);
			for(int messageId : messageOrder){
				int messageSize = 0;
				int startByte = 1;
				while(messageSize != messageSizes[messageId]){
					Packet packet = packetReader.readPacket(messageId, startByte);
					maxBufferSize = Math.max(maxBufferSize, packetReader.getInputBufferSize());
					messageSize += packet.size();
					startByte = packet.getFirstByteForNextPacket();
				}
			}
			minBufferSize = Math.min(minBufferSize, maxBufferSize);
		}while((messageOrder = nextPermutation(messageOrder)) != null);
		
		return minBufferSize;
	}
	
	public static <E extends Comparable<E>> E[] nextPermutation(E[] permutation){
		// Find the largest index k such that a[k] < a[k + 1].
		int k = permutation.length - 2;
		for(;k >= 0 && permutation[k].compareTo(permutation[k + 1]) >= 0; k--);
		if(k < 0) return null;
		
		// Find the largest index l greater than k such that a[k] < a[l].
		int l = permutation.length - 1;
		for(;l > k && permutation[k].compareTo(permutation[l]) >= 0;l--);
		
		// Swap the value of a[k] with that of a[l].
		swap(permutation, k, l);
		
		// Reverse the sequence from a[k + 1] up to and including the final element a[n].
		int left = k + 1;
		int right = permutation.length - 1;
		while(left < right){
			swap(permutation, left++, right--);
		}
		
		return permutation;
	}
	
	static <E> void swap(E[] array, int i, int j){
		E temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
