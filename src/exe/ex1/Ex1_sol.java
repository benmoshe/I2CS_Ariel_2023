package exe.ex1;
import java.util.Scanner;
/**
 * Department of Computer Science, Ariel University.
 * Introduction to Computer Science, Spring 2023
 *  
 * Ex1: Minimal Common Denominator - MCD(x,y,z) - suggested solution:
 * ===================================================================
 * Ex0+Ex1 - the goal is to find the minimal common denominator (aka MCD),
 * 
 * The algorithm has three stages: 
 * 1) input three natural numbers (x,y,z).
 * 2) computes the g=MCD(x,y). 	// MCD(x,y) = x*y / gcd(x,y);
 * 3) compute the MCD(g,z). 	// 
 * 
 * The GCD function is implemented using the following algorithm gcd(x,y) = gcd(x%y,y) (assuming x>y, y!=0).
 * The GPD function is using the representation of g as product of k prime numbers: p1*p2*..pk (p1<=p2<=...pk) 
 * While g%i==0 (i starts from 2), g is divided by i, else i is incremented.
 * 
 * In short: MCD(x,y) = x*y/(GCD(x,y));
 * 
 * Algorithm MCD(x,y,z) - Minimal Common Denominator:
 * ===================================================
 * 0) Input: three natural numbers (x,y,z), from the main's arguments or from the System input. 
 * 1) g = GCD(x,y): gcd(x,y)-->y (assumes x%y==0), gcd(x,y)-->gcd(x%y,y) (assumes x>y, else swaps(x,y)).
 * 1.1) if(x<y) return gcd(y,x); // swap;
 * 1.2) if(x%y==0) return y
 * 1.3) return gcd(x%y,y);
 * 
 * 2) MCD(x,y): x=an, y=bn (gcd(x,y)=n, gcd(a,b)=1) --> MCD(x,y)=MCD(an,bn)= abn = an*bn/n = x*y/GCD(x,y)
 * 2.1) n = gcd(x,y)
 * 2.2) t = x*y/n
 * 2.3) m = gcd(t,z)
 * 2.4) g = t*z/m
 * 3) 	return g;
 * 
 * Implementation remark: 
 * ======================
 * The MCD(x,y,z) function can be implemented in a elegant way by using the MCD(x,y) function
 * 
 * 0) MCD(x,y) = x*y/gcd(x,y)
 * 1) MCD(x,y,z) = MCD(MCD(x,y),z)
 * 
 * @author boaz.benmoshe
 * 
 */
public class Ex1_sol {
	/**
	 * Simple debug parameters - in case true additional printing is being done.
	 */
	public static final boolean DEBUG = false;
	/**
	 * This function computes the Grater Common Divider (GCD) of two natural numbers
	 * The function is implemented using recursion  - for simplicity.
	 * @param x
	 * @param y
	 * @return
	 */
	public static long gcd1(long x, long y) {
		if(DEBUG) {System.out.println("gcd1("+x+","+y+")");}
		if(x<y) {return gcd1(y,x);}
		if(x%y==0) {
			System.out.println("gcd1("+x+","+y+") = "+y);
			return y;}
		return gcd1(y,x%y);
	}
	/**
	 * This function computes the Grater Common Divider (GCD) of two natural numbers,
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static long gcd2(long x, long y) {
		if(DEBUG) {System.out.println("gcd2("+x+","+y+")");}
		if(x<y) {long t=x; x=y; y=t;}
		while(x%y!=0) {
			x=x%y;
			if(x<y) {long t=x; x=y; y=t;}
			if(DEBUG) {System.out.println("gcd2("+x+","+y+")");}
		}
		return y;
	}   

	/**
	 * Computes the maximum prime number which divides both x and y.
	 * @param x - a natural number 
	 * @param y - a natural number
	 * @return - the max prime number which divides both x and y.
	 */
	public static long minCommonDemodulator(long x, long y) {
		if(DEBUG) {System.out.println("MCD("+x+","+y+"): ");}
		long g1 = gcd1(x,y); // Recursive implementation
		long g2 = x * y / g1;
		if(DEBUG) {System.out.println("MCD("+x+","+y+") = "+g2);}
		return g2;
	}
	public static long minCommonDemodulator(long x, long y, long z) {
		if(DEBUG) {System.out.println("MCD("+x+","+y+","+z+"): ");}
		long g1 = minCommonDemodulator(x,y); // Recursive implementation
		long g2 = minCommonDemodulator(g1,z);
		return g2;
	}
	/**
	 * This main function simply allows input of two natural numbers x,y: via main(args) or System.in.
	 * and then computes the Greater Common Prime Divisor - GCPD(x,y):
	 * @param args
	 */
	//659675382240, 4485792599232
	public static void main(String[] args) {
		long x,y,z;

		if(args.length<3) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter the first number for min common demodulator: ");
			x = scanner.nextLong();
			System.out.print("Enter the second number for min common demodulator: ");
			y = scanner.nextLong();
			System.out.print("Enter the third number for min common demodulator: ");
			z = scanner.nextLong();
		}
		else {
			x = Long.parseLong(args[0]);
			y = Long.parseLong(args[1]);
			z = Long.parseLong(args[2]);
		}
		// 659675382240 , 4485792599232
		long start = System.nanoTime();
		long ans = minCommonDemodulator(x,y,z);
		long end = System.nanoTime();
		System.out.println("The MCD("+x+","+y+","+z+") = "+ans);
		double dt = (end - start)/(1000.0);
		if(DEBUG) {System.out.println("The runtime took: "+dt+" micro seconds."); }
	}
}
