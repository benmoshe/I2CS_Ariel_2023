package exe.ex4.geo;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{
	public Segment_2D(Point_2D a, Point_2D b) {
		////// add your code here //////

		////////////////////////////////
	}
	public Segment_2D(Segment_2D t1) {
		////// add your code here //////

		////////////////////////////////
	}

	public Point_2D get_p1() {
		////// add your code here //////

		return null;
		////////////////////////////////
	}
	public Point_2D get_p2() {
		////// add your code here //////

		return null;
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