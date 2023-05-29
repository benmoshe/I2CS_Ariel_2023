package exe.ex4.geo;

import exe.ex4.gui.GUI_Shape;

import java.util.Comparator;


/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shape>{
	
	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;
		
	}
	@Override
	public int compare(GUI_Shape o1, GUI_Shape o2) {
		////// add your code here //////

		return 0;
		////////////////////////////////
	}

}
