package exe.ex4.ex4;

import exe.ex4.geo.Circle_2D;
import exe.ex4.geo.Point_2D;
import exe.ex4.gui.GUIShape;
import exe.ex4.gui.GUI_Shape;
import java.awt.*;

/**
 * This class is a very simple main for starting with Ex4(2)/
 */
public class Ex4_main {

	public static void main(String[] args) {
		simpleShapes();
	//	loadDemo();
	}

	// Two simple GeoShapes
	public static void simpleShapes() {
		Ex4 ex4 = Ex4.getInstance();
		GUI_Shape_Collection shapes = ex4.getShape_Collection();
		Point_2D p1 = new Point_2D(3,4);
		Point_2D p2 = new Point_2D(6,8);
		Circle_2D c1 = new Circle_2D(p1,2);
		Circle_2D c2 = new Circle_2D(p2,3.4);
		GUI_Shape gs1 = new GUIShape(c1, true, Color.black, 1);
		GUI_Shape gs2 = new GUIShape(c2, true, Color.yellow, 2);
		shapes.add(gs1);
		shapes.add(gs2);
		ex4.init(shapes);
		ex4.show();
	}
	// Loads a file from file a0.txt (Circles only).
	public static void loadDemo() {
		Ex4 ex4 = Ex4.getInstance();
		GUI_Shape_Collection shapes = ex4.getShape_Collection();
		String file = "a0.txt"; //make sure the file is your working directory.
		shapes.load(file);
		ex4.init(shapes);
		ex4.show();
	}

}
