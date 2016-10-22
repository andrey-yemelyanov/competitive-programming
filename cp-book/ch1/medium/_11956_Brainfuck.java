package helvidios.cp.ch1.medium;

import java.util.Scanner;

public class _11956_Brainfuck {
	private static final int MEMORY_SIZE = 100;
	private static final int BYTE_SIZE = 256;
	
	public static void main(String... args){
		String data = "1\r\n" + 
				"..++<><<+++>>++++++++++++++++++++++++++>>>+++<+...++<><<+++>>++++++++++++++++++++++++++>>>+++<+...++<><<+++>>++++++++++++++++++++++++++>>>+++<+."; 
		Scanner scanner = new Scanner(data);
		int testCount = scanner.nextInt();
		for(int test = 1; test <= testCount; test++){
			int[] memory = execute(scanner.next());
			System.out.printf("Case %1$d: %2$s\n", test, getMemoryDump(memory));
		}
		scanner.close();
	}
	
	private static String getMemoryDump(int[] memory){
		StringBuilder dump = new StringBuilder();
		for(int memoryByte = 0; memoryByte < memory.length; memoryByte++){
			dump.append(String.format("%02X", memory[memoryByte]));
			if(memoryByte < memory.length - 1) dump.append(" ");
		}
			
		return dump.toString();
	}
	
	private static int[] execute(String program){
		int[] memory = new int[MEMORY_SIZE];
		int pointer = 0;
		for(int c = 0; c < program.length(); c++){
			char command = program.charAt(c);
			switch (command) {
				case '>':
					pointer = incrementPointer(memory, pointer);
					break;
				case '<':
					pointer = decrementPointer(memory, pointer);
					break;
				case '+':
					incrementByte(memory, pointer);
					break;
				case '-':
					decrementByte(memory, pointer);
					break;
				default:
					break;
			}
		}
		return memory;
	}
	
	private static int incrementPointer(int[] memory, int currentPointer){
		return (currentPointer + 1) % MEMORY_SIZE;
	}
	
	private static int decrementPointer(int[] memory, int currentPointer){
		currentPointer--;
		if(currentPointer < 0) currentPointer = MEMORY_SIZE - 1;
		return currentPointer;
	}
	
	private static void incrementByte(int[] memory, int currentPointer){
		memory[currentPointer]= (memory[currentPointer] + 1) % BYTE_SIZE;
	}
	
	private static void decrementByte(int[] memory, int currentPointer){
		memory[currentPointer]--;
		if(memory[currentPointer] < 0) memory[currentPointer] = BYTE_SIZE - 1;
	}
}
