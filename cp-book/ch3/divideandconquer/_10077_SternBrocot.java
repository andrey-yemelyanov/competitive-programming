import java.util.*;
import java.util.stream.*;

public class _10077_SternBrocot{
	public static void main(String... args){
		Scanner s = new Scanner(System.in);
		while(s.hasNext()){
			int m = s.nextInt();
			int n = s.nextInt();
			if(m == 1 && n == 1) break;
			System.out.println(getSternBrocotRepresentation(m, n));
		}
	}

	static String getSternBrocotRepresentation(int m, int n){
		StringBuilder sb = new StringBuilder();
		Fraction from = new Fraction(0, 1);
		Fraction to = new Fraction(1, 0);
		Fraction key = new Fraction(m, n);
		while(true){
			Fraction mid = new Fraction(
				from.numerator + to.numerator,
				from.denominator + to.denominator);
			if(key.compare(mid) == 0) break;
			if(key.compare(mid) > 0){
				from = mid;
				sb.append("R");
			}else{
				to = mid;
				sb.append("L");
			}
		}
		return sb.toString();
	}

	static class Fraction{
		public int numerator;
		public int denominator;
		public Fraction(int numerator, int denominator){
			this.numerator = numerator;
			this.denominator = denominator;
		}
		public int compare(Fraction other){
			return Integer.compare(
				this.numerator * other.denominator,
				this.denominator * other.numerator);
		}
	}
}
