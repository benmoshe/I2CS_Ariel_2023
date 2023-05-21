package composition;



/**
 * Exercise 1 on Classes and Instances, Composition
 * There are two ways to reuse existing classes, namely, composition and inheritance.
 * With composition (aka aggregation), you define a new class, which is composed of existing classes.
 * With inheritance, you derive a new class based on an existing class, with modifications or extensions.
 * This Circle class is a building block and is meant to be used in another program.
 * Define the Circle class
 * @author anna
 *
 */
public class Circle {

	/** Private variables
	 *  private instance variable, not accessible from outside this class
	 */
	private Point center;
	private double radius;

	//======= Constructors ==========

	/**
	 * This Constructor with given radius and center 
	 * @param radius is a radius of Circle
	 * @param center is a center of Circle
	 */
	public Circle(Point center, double radius) { 
		//this.center = center;
		this.center = new Point(center);
		this.radius = radius;
	}


	/**
	 * This is a copy constructor
	 * @param c is a object of Circle
	 */
	public Circle(Circle c) {
		this.center = c.center;
		this.radius = c.radius;
	}

	//======= Public getter and setter for private variables ==========
	/** A public method for retrieving the radius
	 *  @return the radius of Circle
	 */
	public double getRadius() {
		return radius; 
	}

	/** A public method for retrieving the center
	 *  @return the center of Circle
	 */
	public Point getCenter() {
		return center;
	}	

	/** A public method for setting the radius
	 *  @param r is a radius of Circle
	 */
	public void setRadius(double r) {
		radius = r;
	}

	//======= Public methods ==========
	/** A public method for computing the area of circle
	 *  @return the area of circle
	 */
	public double getArea() {
		return radius*radius*Math.PI;
	}

	/** A public method that provides a short description of this instance
	 * 
	 */
	public String toString() {
		return "Circle with radius = " + radius + " and center of " + center.toString();
	}

}
