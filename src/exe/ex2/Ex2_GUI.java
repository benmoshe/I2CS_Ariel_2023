package exe.ex2;

/**
 *  * Introduction to Computer Science 2023, Ariel University,
 *  * Ex2: arrays, static functions and JUnit
 *
 * This class is a very simple GUI (Graphic User Interface) main class, which 
 * uses the StdDraw (see: https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html) 
 * in order to draw two polynoms in a defined range.
 * Make sure you follow all the examples below, there is no need to change this class
 * @author boaz.benmoshe
 */
public class Ex2_GUI {
	public static void main(String[] a) {
		double min = -10, max=10;
		
		StdDraw.setScale(min, max);
		StdDraw.clear();
		double[] po4 = {2,1,-0.7, -0.02,0.02};
		double[] po3 = {6, 0.1, -0.2};
		double[] po2 = {1,-0.5};
		double[] po1 = {-2,0.25};
		
		//testGUI(po1,po2, min, max, 3);
		testGUI(po3,po4, min, max, 8);
	}
	
	public static void testGUI(double[] po1, double[] po2, double min, double max, int samples) {
		int n=1000;
		double x1 = Ex2_Sol.sameValue(po1,po2, -10,-5,Ex2_Sol.EPS);
		double x2 = Ex2_Sol.sameValue(po1,po2, 5,10,Ex2_Sol.EPS);
		double area1 = 0;
		StdDraw.setPenColor(StdDraw.GRAY);
		drawGrid(min,max);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		drawArea(po1,po2, x1, 6, samples);
		StdDraw.setPenColor(StdDraw.BLUE);
		drawPoly(po1, min, max,n);
		StdDraw.setPenColor(StdDraw.RED);
		drawPoly(po2, min, max,n);
		
		drawInfo(po1,0,-8);
		drawInfo(po2,0,7);
		
		double area = Ex2_Sol.area(po1, po2, x1, 6, samples);
		StdDraw.text(0,-9,"Area: "+samples+": "+area);
	}
	public static void drawPoly(double[] poly, double x1, double x2, int numberOfElements) {
		double x0=x1;
		double y0 = Ex2_Sol.f(poly, x0);
		double delta = (x2-x1)/numberOfElements;
		for(double x = x1+delta;x<=x2;x+=delta) {
			double y1 = Ex2_Sol.f(poly, x);
			StdDraw.line(x0, y0, x, y1);
			x0=x;
			y0 = y1;
		} 
	} 
	public static void drawArea(double[] p1, double[] p2, double x1, double x2, int numberOfElements) {
		double x0=x1;
		double y01 = Ex2_Sol.f(p1, x0);
		double y02 = Ex2_Sol.f(p2, x0);
		double delta = (x2-x1)/numberOfElements;
		for(double x = x1+delta;x<=x2;x+=delta) {
			double y11 = Ex2_Sol.f(p1, x);
			double y12 = Ex2_Sol.f(p2, x);
			double[] xx = {x0,x,x,x0};
			double[] yy = {y01,y11,y12,y02};
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.filledPolygon(xx, yy);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.polygon(xx, yy);
			x0=x;
			y01 = y11;
			y02=y12;
		} 
	} 
	public static void drawInfo(double[] poly, double x, double y) {
		String s = Ex2_Sol.poly(poly);
		StdDraw.text(x,y,s);
	}
	public static void drawGrid(double min, double max) {
		StdDraw.line(0,min,0,max);
		StdDraw.line(min,0,max,0);
	}
}
