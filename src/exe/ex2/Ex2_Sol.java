package exe.ex2;
/** 
 * Introduction to Computer Science 2023, Ariel University,
 * Ex2: arrays, static functions and JUnit
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * The array {0.1, 0, -3, 0.2} represents the following polynom: 0.2x^3-3x^2+0.1
 * This is the main Class you should implement (see "add your code here").

 * @author boaz.benmoshe
 *
 */
public class Ex2_Sol {
	/** Epsilon value for numerical computation, it serves as a "close enough" threshold. */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/** The zero polynom is represented as an array with a single (0) entry. */
	public static final double[] ZERO = {0};
	/**
	 * This function computes a polynomial representation from a set of 2D points on the polynom.
	 * Note: this function only works for a set of points containing two or less points, else returns null.
	 * @param xx
	 * @param yy
	 * @return an array of doubles representing the coefficients of the polynom.
	 */
	public static double[] PolynomFromPoints(double[] xx, double[] yy) {
		double [] ans = null;
		if(xx!=null && yy!=null && xx.length==yy.length) { 
			if(xx.length==3) {
				double x1 = xx[0], x2 = xx[1], x3 = xx[2];
				double y1 = yy[0], y2 = yy[1], y3 = yy[2];
				//	http://stackoverflow.com/questions/717762/how-to-calculate-the-vertex-of-a-parabola-given-three-points
				double dd = (x1-x2) * (x1-x3) * (x2-x3);
				double a     = (x3 * (y2-y1) + x2 * (y1-y3) + x1 * (y3-y2)) / dd;
				double b     = (x3*x3 * (y1-y2) + x2*x2 * (y3-y1) + x1*x1 * (y2-y3)) / dd;
				double c     = (x2 * x3 * (x2-x3) * y1+x3 * x1 * (x3-x1) * y2+x1 * x2 * (x1-x2) * y3) / dd;
				ans = new double[3];
				ans[0] = c; ans[1] = b; ans[2] = a;			
			}
			if(xx.length==2) {
				double x1 = xx[0], x2 = xx[1];
				double y1 = yy[0], y2 = yy[1];
				/** f(x) = a*x+b, y1 = a*x1 + b, y2 = a*x2 + b, b = y2-a*x2
				 *  y1 = a*x1 + y2-a*x2, y1-y2 = a(x1-x2)
				 *  a = (y1-y2) / (x1-x2)
				 *  b = y1-x1*a; */
				double a =  (y1-y2) / (x1-x2);
				double b = y1-(x1*a);
				ans = new double[2];
				ans[0] = b; ans[1] = a;
			}
		}
		return ans;
	}
	/** Two polynoms are equal if and only if the have the same coefficients - up to an epsilon (aka EPS) value.
	 * @param p1 first polynom
	 * @param p2 second polynom
	 * @return true iff p1 represents the same polynom as p2.
	 */
	public static boolean equals(double[] p1, double[] p2) {
		boolean ans = true;
		if(p1==null && p2 == null ) {return true;}
		if(p1 ==null || p2 == null) {ans = false;}
		else {
			int len = Math.max(p1.length, p2.length);
			for(int i=0;i<len && ans;i++) {
				double f1 = f(p1,i);
				double f2 = f(p2,i);
				double d = Math.abs(f1-f2);
				if(d>=Ex2.EPS) {ans = false;}
			}
		}
		return ans;
	}
	/**
	 * Computes the f(x) value of the polynom at x.
	 * @param poly
	 * @param x
	 * @return f(x) - the polynom value at x.
	 */
	public static double f(double[] poly, double x) {
		double ans = 0;
		for(int i=0;i<poly.length;i++) {
			double c = Math.pow(x, i);
			ans +=c*poly[i];
		}
		return ans;
	}
	/** 
	 * Computes a String representing the polynom.
	 * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	 * @param poly the polynom represented as an array of doubles
	 * @return String representing the polynom: 
	 */
	public static String poly(double[] poly) {
		String ans = "";
		for(int i=poly.length-1; i>=0;i--) {
			if(poly[i]!=0) {
				String c = " ";
				if(poly[i]>0) {c=" +";}
				c+=poly[i];
				if(i>0) {c+="x";}
				if(i>1) {c+="^"+i;}
				ans +=c;
			}
		}
		if(ans.length()==0) {ans="0";}
		return ans;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	 * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p1(x) -p2(x)| < eps.
	 */
	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
		double f1_1 = f(p1,x1);
		double f1_2 = f(p1,x2);
		double f2_1 = f(p2,x1);
		double f2_2 = f(p2,x2);
		double x12 = (x1+x2)/2;
		double f1_12 = f(p1,x12);
		double f2_12 = f(p2,x12);
		double d1 = f1_1 - f2_1;
		double d2 = f1_2 - f2_2;
		double delta = f1_12 - f2_12;
		while (d1*d2<=0 && Math.abs(delta)>=eps) {
			if(delta*d1<=0) {x2=x12;f1_2= f(p1,x2);f2_2= f(p2,x2);}
			else {x1=x12;f1_1 = f(p1,x1);f2_1 = f(p2,x1);}
			x12 = (x1+x2)/2;
			d1 = f1_1 - f2_1;
			d2 = f1_2 - f2_2;
			f1_12 = f(p1,x12);
			f2_12 = f(p2,x12);
			delta = f1_12 - f2_12;
		}
		return x12;
	}
	/**
	 * Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x1) <= 0. 
	 * This function should be implemented iteratively (none recursive).
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root(double[] p, double x1, double x2, double eps) {
		double f1 = f(p,x1);
		double f2 = f(p,x2);
		double x12 = (x1+x2)/2;
		double f12 = f(p,x12);
		while (f1*f2<=0 && Math.abs(f12)>=eps) {
			if(f12*f1<=0) {x2=x12;f2= f(p,x2);}
			else {x1=x12;f1 = f(p,x1);}
			x12 = (x1+x2)/2;
			f12 = f(p,x12);
		}
		return x12;
	}
	/** Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x1) <= 0. 
	 * This function should be implemented recursivly.
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root_rec(double[] p, double x1, double x2, double eps) {
		double f1 = f(p,x1);
		double f2 = f(p,x2);
		double x12 = (x1+x2)/2;
		double f12 = f(p,x12);
		if (f1*f2<=0 && Math.abs(f12)<eps) {return x12;}
		if(f12*f1<=0) {return root_rec(p, x1, x12, eps);}
		else {return root_rec(p, x12, x2, eps);}
	}
	public static double length(double[] poly, double x1, double x2, int numOfSamples) {
		double ans = 0;
		double x0=x1;
		double y0 = Ex2.f(poly, x0);
		double delta = (x2-x1)/numOfSamples;
		for(double x = x1+delta;x<=x2;x+=delta) {
			double y1 = Ex2.f(poly, x);
			double dx = x-x0;
			double dy = y1-y0;
			double dist = Math.sqrt(dx*dx + dy*dy);
			ans += dist;
			x0=x;
			y0 = y1;
		} 
		return ans;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an integer representing the number of "boxes". 
	 * This function computes an approximation of the area between the polynoms within the x-range.
	 * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numOfTrapezoides - a natural number representing the number of boxes between xq and x2.
	 * @return the approximated area between the two polynoms within the [x1,x2] range.
	 */
	public static double area(double[] p1,double[]p2, double x1, double x2, int numOfTrapezoides) {
		double ans = 0;
		double x = x1;
		double delta = (x2-x1)/numOfTrapezoides;
		for(int i=0; i< numOfTrapezoides;i++) {
			double xx = x+delta;
			double dp1p2x = f(p1, x) - f(p2,x);
			double dp1p2xx = f(p1, xx) - f(p2,xx);
			if(dp1p2x*dp1p2xx>=0) {
				double da = delta*(dp1p2x+dp1p2xx)/2;
				ans += Math.abs(da);
			}
			else {
				double xMid = sameValue(p1,p2,x,xx,Ex2_Sol.EPS);
				double s1 = Math.abs(dp1p2x);
				s1 = s1*(xMid-x);
				double s2 = Math.abs(dp1p2xx);
				s2 = s2*(xx-xMid);
				double s12 = s1/(s1+s2);
				double da = (s1+s2)/2;
				ans += da; 
			}
			
			x+=delta;
		}
		return ans;
	}
	/**
	 * This function computes the array representation of a polynom from a String
	 * representation. Note:given a polynom represented as a double array,  
	 * getPolynomFromString(poly(p)) should return an array equals to p.
	 * 
	 * @param p - a String representing polynom.
	 * @return
	 */
	public static double[] getPolynomFromString(String p) {
		String[] pp = p.split(" "); //  -1.0x^2 +3.0x +2.0
		int size = 0;
		double[] ans = new double[0];
		for(int i=0;i<pp.length;i++) {
			if(pp[i].length()>1) {
				int pwr = getPower(pp[i]);
				double coef = getCoef(pp[i]);
				if(size==0) {size = pwr; ans=new double[size+1];}
				ans[pwr] = coef;
			}
		}
		return ans;
	}
	/**
	 * This function computes the polynom which is the sum of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] add(double[] p1, double[] p2) {
		int s1 = p1.length, s2 = p2.length, mp=Math.max(s1, s2);
		double[] ans = new double[mp];
		for(int i=0;i<mp;i++) {
			double v1=0, v2=0;
			if(i<s1) {v1 = p1[i];}
			if(i<s2) {v2 = p2[i];}
			ans[i] = v1+v2;
		}
		ans = copy(ans);
		return ans;
	}
	/**
	 * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] mul(double[] p1, double[] p2) {
		double[] ans = new double[1];
		for(int i=0;i<p1.length;i++) {
			double[] cp1 = mul(p1[i], i, p2);
			ans = add(ans, cp1);
		}
		ans = copy(ans);
		return ans;
	}
	/**
	 * This function computes the derivative polynom.
	 * @param po
	 * @return
	 */
	public static double[] derivative (double[] po) {
		int s = po.length-1;
		double[] ans = null;
		if(s==0) {ans = ZERO;}
		else {
			ans =new double[s];
			for(int i=0;i<s;i++) {
				ans[i] = (i+1)*po[i+1];
			}
		}
		return ans;
	}
	///////////////////// Private /////////////////////
	private static double[] mul(double coef, int pwr, double[] p) {
		double[] ans = new double[p.length+pwr];
		for(int i=0;i<p.length;i++) {
			ans[i+pwr] = p[i]*coef;
		}
		return ans;
	}
	private static double[] copy(double [] p) {
		double[] ans = p;
		int pow = p.length-1;
		if(p!=null) {
			while(pow>0 && p[pow]==0) {pow--;}
			ans = new double[pow+1];
			for(int i=0;i<=pow;i++) {
				ans[i] = p[i];
			}
		}
		return ans;
	}
	
	private static double getCoef(String s) {
		int t = s.indexOf("x");
		if(t==0) {return 1;}
		if(t>0) {s = s.substring(0,t);}
		double ans = Double.parseDouble(s);
		return ans;
	}
	private static int getPower(String s) {
		int t = s.indexOf("x");
		if(t==-1) {return 0;}
		t = s.indexOf("^");
		if(t==-1) {return 1;}
		String pr = s.substring(t+1);
		int ans = Integer.parseInt(pr);
		return ans;
	}
}
