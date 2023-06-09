package classes.week1;
/**
 * This is a simple documentation between programmers
 * This example demonstrates a simple integer (int) initialization, and basic mathematical 
 * operation over integers.
 * output:a=04, b=5, c=4, d=5, e=100, f=11, g=3

 * @author boaz.benmoshe
 *
 */
public class Integers {
	public static void main(String[] arg) {
		int a = 0;
		int b = 1;
		int c = 2+2;	// c=4
		int d = b+c; 	// d=5
		int e = c*d*d; 	// 3=100
		int f = e/(c+d);// f=100/9 = 11 (the result is an integer)
		int g = f%c;	// g=3: 11/4=2, 11-(4*2)=3
	
		System.out.println("a="+a+4+", b="+(b+4)+", c="+c+", d="+d+", e="+e+", f="+f+", g="+g);
	}
} 
