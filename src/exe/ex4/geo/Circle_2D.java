package exe.ex4.geo;

/** 
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;
	
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}
	public double getRadius() {return this._radius;}
	public Point_2D getCenter(){ return _center;}
	 @Override
	    public String toString()
	    {
			////// add your code here //////

			return null;
			////////////////////////////////
		}
	@Override
	public boolean contains(Point_2D ot) {
		////// add your code here //////

		return false;
		////////////////////////////////
	}
	
	@Override
	public double area() {
		////// add your code here //////

		return -1;
		////////////////////////////////
	}
	@Override
	public double perimeter() {
		////// add your code here //////

		return -1;
		////////////////////////////////
	}
	@Override
	public void translate(Point_2D vec) {
		////// add your code here //////

		////////////////////////////////
	}
	@Override
	public GeoShape copy() {
		////// add your code here //////

		return null;
		////////////////////////////////
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		////// add your code here //////

		////////////////////////////////
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		////// add your code here //////

		////////////////////////////////
	}

}
