package exe.ex4.ex4;

import exe.ex4.gui.GUI_Shape;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection{
	private ArrayList<GUI_Shape> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shape removeElementAt(int i) {
		//////////add your code below ///////////
		
		return null;
		//////////////////////////////////////////
	}

	@Override
	public void addAt(GUI_Shape s, int i) {
		//////////add your code below ///////////
		
		
		//////////////////////////////////////////
	}
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public GUI_Shape_Collection copy() {
		//////////add your code below ///////////

		return null;
		//////////////////////////////////////////
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		//////////add your code below ///////////
		
		
		//////////////////////////////////////////
	}

	@Override
	public void removeAll() {
		//////////add your code below ///////////
		
		
		//////////////////////////////////////////
	}

	@Override
	public void save(String file) {
		//////////add your code below ///////////
		
		
		//////////////////////////////////////////
	}

	@Override
	public void load(String file) {
		////////// add your code below ///////////
		
		
		//////////////////////////////////////////
	}

	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
	

}
