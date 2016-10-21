package helvidios.cp.ch1.adhoc.game.easier;

import java.util.Scanner;

enum Direction{
	TOP(0),
	NORTH(1),
	WEST(2),
	EAST(3),
	SOUTH(4),
	BOTTOM(5);
	
	private int value;
	private Direction(int value){
		this.value = value;
	}
	
	public int val(){
		return value;
	}
}

public class _10409_DieGame {
	public static void main(String... args){
		String data = "1\r\n" + 
				"north\r\n" + 
				"3\r\n" + 
				"north\r\n" + 
				"east\r\n" + 
				"south\r\n" + 
				"0\r\n" + 
				"\r\n" + 
				"";
		Scanner scanner = new Scanner(data);
		while(scanner.hasNext()){
			int nCommands = scanner.nextInt();
			if(nCommands == 0) break;
			String[] commands = new String[nCommands];
			for(int i = 0; i < commands.length; i++){
				commands[i] = scanner.next();
			}
			System.out.println(getTopFace(commands));
		}
		scanner.close();
	}
	
	static int getTopFace(String[] commands){
		int[] currentPosition = new int[]{1,2,3,4,5,6};
		for(String command : commands){
			currentPosition = tumble(command, currentPosition);
		}
		return currentPosition[Direction.TOP.val()];
	}
	
	static int[] tumble(String direction, int[] currentPosition){
		int[] newPosition = new int[6];
		switch (direction) {
			case "north":
				newPosition[Direction.WEST.val()] = currentPosition[Direction.WEST.val()];
				newPosition[Direction.EAST.val()] = currentPosition[Direction.EAST.val()];
				newPosition[Direction.NORTH.val()] = currentPosition[Direction.TOP.val()];
				newPosition[Direction.BOTTOM.val()] = currentPosition[Direction.NORTH.val()];
				newPosition[Direction.SOUTH.val()] = currentPosition[Direction.BOTTOM.val()];
				newPosition[Direction.TOP.val()] = currentPosition[Direction.SOUTH.val()];
				break;
			case "east":
				newPosition[Direction.SOUTH.val()] = currentPosition[Direction.SOUTH.val()];
				newPosition[Direction.NORTH.val()] = currentPosition[Direction.NORTH.val()];
				newPosition[Direction.TOP.val()] = currentPosition[Direction.WEST.val()];
				newPosition[Direction.BOTTOM.val()] = currentPosition[Direction.EAST.val()];
				newPosition[Direction.EAST.val()] = currentPosition[Direction.TOP.val()];
				newPosition[Direction.WEST.val()] = currentPosition[Direction.BOTTOM.val()];
				break;
			case "south":
				newPosition[Direction.WEST.val()] = currentPosition[Direction.WEST.val()];
				newPosition[Direction.EAST.val()] = currentPosition[Direction.EAST.val()];
				newPosition[Direction.NORTH.val()] = currentPosition[Direction.BOTTOM.val()];
				newPosition[Direction.BOTTOM.val()] = currentPosition[Direction.SOUTH.val()];
				newPosition[Direction.SOUTH.val()] = currentPosition[Direction.TOP.val()];
				newPosition[Direction.TOP.val()] = currentPosition[Direction.NORTH.val()];
				break;
			default: // west
				newPosition[Direction.SOUTH.val()] = currentPosition[Direction.SOUTH.val()];
				newPosition[Direction.NORTH.val()] = currentPosition[Direction.NORTH.val()];
				newPosition[Direction.TOP.val()] = currentPosition[Direction.EAST.val()];
				newPosition[Direction.BOTTOM.val()] = currentPosition[Direction.WEST.val()];
				newPosition[Direction.EAST.val()] = currentPosition[Direction.BOTTOM.val()];
				newPosition[Direction.WEST.val()] = currentPosition[Direction.TOP.val()];
				break;
			}
		return newPosition;
	}
}
