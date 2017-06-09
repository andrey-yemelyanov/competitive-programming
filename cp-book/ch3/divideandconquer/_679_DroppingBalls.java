import java.util.*;
import java.util.stream.*;

public class _679_DroppingBalls{
	public static void main(String... args){
		Scanner s = new Scanner(System.in);
		while(s.hasNext()){
			int nTests = s.nextInt();
			while(nTests-- > 0){
				int maxDepth = s.nextInt();
				if(maxDepth == -1) break;
				int i = s.nextInt();
				System.out.println(getStopPosition(i, maxDepth));
			}
		}
	}

	static int leftChild(int node){
		return node * 2;
	}

	static int rightChild(int node){
		return node * 2 + 1;
	}

	static boolean isTerminalNode(int node, int nNodes){
		return leftChild(node) > nNodes;
	}

	static int getStopPosition(int i, int maxDepth){
		int nNodes = (1 << maxDepth) - 1;
		int currentNode = 1;
		while(!isTerminalNode(currentNode, nNodes)){
			if(i % 2 == 0){
				currentNode = rightChild(currentNode);
				i /= 2;
			}else{
				currentNode = leftChild(currentNode);
				i = i / 2 + 1;
			}
		}
		return currentNode;
	}
}
