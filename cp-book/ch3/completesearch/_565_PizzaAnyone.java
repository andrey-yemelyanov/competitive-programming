import java.util.*;
import java.util.stream.*;

public class _565_PizzaAnyone{
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		List<int[]> constraints = new ArrayList<>();
		while(s.hasNext()){
			String line = s.nextLine().replace(";", "");
			if(!line.trim().equals(".")){
				int[] pair = new int[2];
				for(int i = 0; i < line.length(); i += 2){
					if(line.charAt(i) == '+'){
						pair[0] |= 1 << toCode(line.charAt(i + 1));
					}else{
						pair[1] |= 1 << toCode(line.charAt(i + 1));
					}
				}
				constraints.add(pair);
			}else{
				int toppings = getToppingCombination(constraints);
        if(toppings == NO_PIZZA) System.out.println("No pizza can satisfy these requests.");
        else System.out.printf("Toppings: %s\n", toString(toppings));
				constraints = new ArrayList<>();
			}
		}
	}

  static String toString(int toppingCombination){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < 16; i++){
      if((toppingCombination & (1 << i)) > 0){
        sb.append(getTopping(i));
      }
    }
    return sb.toString();
  }

	static final int NO_PIZZA = -1;
	static int getToppingCombination(List<int[]> constraints){
		for(int toppingCombination = 0; toppingCombination < (1 << 16); toppingCombination++){
			if(satisfiesAllConstraints(toppingCombination, constraints)){
				return toppingCombination;
			}
		}
		return NO_PIZZA;
	}

	static boolean satisfiesAllConstraints(int toppingCombination, List<int[]> constraints){
		for(int[] constraint : constraints){
      int includeToppings = constraint[0];
      int omitToppings = constraint[1];
			boolean satisfied = (toppingCombination & includeToppings) > 0;
      if(!satisfied) satisfied = (((1 << 16) - 1 - toppingCombination) & omitToppings) > 0;
			if(!satisfied) return false;
		}
		return true;
	}

	static int toCode(char topping){
		return topping - 'A';
	}

	static char getTopping(int code){
		return (char)('A' + code);
	}
}
