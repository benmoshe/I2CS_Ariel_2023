package exe.ex4.geo;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect_2D implements GeoShape {
	public Rect_2D(Point_2D p1, Point_2D p2) {
		////// add your code here //////

		////////////////////////////////
	}
	public Rect_2D(Rect_2D t1) {
		////// add your code here //////

		////////////////////////////////
	}

	@Override
	public boolean contains(Point_2D ot) {
		return false;
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		return 0;
	}

	@Override
	public void translate(Point_2D vec) {

	}

	@Override
	public GeoShape copy() {
		return null;
	}

	@Override
	public void scale(Point_2D center, double ratio) {

	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {

	}
}
