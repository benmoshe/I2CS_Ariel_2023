
package exe.ex4.geo;


/**
 * This class represents a 2D point in the plane.
 * Do NOT change this class! It would be used as is for testing.
 * Ex4: you should NOT change this class!
 * @author boaz.benmoshe
 */


public class Point_2D {
    //public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
    public static final Point_2D ORIGIN = new Point_2D(0,0);
    private double _x,_y;
    public Point_2D(double x, double y) {
    	_x=x; _y=y;
    }
    public Point_2D(Point_2D p) {
       this(p.x(), p.y());
    }
    public Point_2D(String s) {
        try {
            String[] a = s.split(",");
            _x = Double.parseDouble(a[0]);
            _y = Double.parseDouble(a[1]);
        }
        catch(IllegalArgumentException e) {
            System.err.println("ERR: got wrong format string for Point2D init, got:"+s+"  should be of format: x,y");
            throw(e);
        }
    }
    public double x() {return _x;}
    public double y() {return _y;}
 
    public int ix() {return (int)_x;}
    public int iy() {return (int)_y;}
  
    public Point_2D add(Point_2D p) {
    	Point_2D a = new Point_2D(p.x()+x(),p.y()+y());
    	return a;
    }
    public String toString()
    {
        return _x+","+_y;
    }

    public double distance()
    {
        return this.distance(ORIGIN);
    }
    public double distance(Point_2D p2)
    {
        double dx = this.x() - p2.x();
        double dy = this.y() - p2.y();
        double t = (dx*dx+dy*dy);
        return Math.sqrt(t);
    }
    @Override
    public boolean equals(Object p)
    {
        if(p==null || !(p instanceof Point_2D)) {return false;}
        Point_2D p2 = (Point_2D)p;
        return ( (_x==p2._x) && (_y==p2._y));
    }
    public boolean close2equals(Point_2D p2, double eps)
    {
        return ( this.distance(p2) < eps );
    }

    /**
     * This method returns the vector between this point and the target point. The vector is represented as a Point2D.
     * @param target
     * @return
     */
    public Point_2D vector(Point_2D target) {
    	double dx = target.x() - this.x();
    	double dy = target.y() - this.y();
    	return new Point_2D(dx,dy);
    }
	
	public void move(Point_2D vec) {
		this._x += vec.x();
		this._y += vec.y();
	}
	
	/////////////////////// You should implement the methods below ///////////////////////////
	public void scale(Point_2D cen, double ratio) {
		double dx = x()-cen.x();
		double dy = y()-cen.y();
		dx *= ratio;
		dy *= ratio;
		_x = dx+cen.x();
		_y = dy+cen.y();
		/////////////////////////////////////
	}
	public void rotate(Point_2D cen, double angleDegrees) {
		double angRad = Math.toRadians(angleDegrees);
		double dx = x()-cen.x();
		double dy = y()-cen.y();
		double ang = Math.atan2(dy, dx);
		double d = Math.sqrt(dx*dx+dy*dy);
		ang += angRad;
		dx = d*Math.cos(ang);
		dy = d*Math.sin(ang);
		_x = cen.x() + dx;
		_y = cen.y() + dy;
	}
   
}
