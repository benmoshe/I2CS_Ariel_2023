package exe.ex4.gui;
/**
 * This interface represents GUI drawable Shape, 
 * with color, tag and fill values.
 * It contains a reference to a Geometric shape.
 *  Ex4: you should NOT change this interface!
 * @author boaz.benmoshe
 */

import exe.ex4.geo.GeoShape;

import java.awt.*;

/**
 * This interface represents a GUI shape with meta data of color, fill/empty and tag.
 * It also contains a geometric shape (GeoShape).
 *  Ex4: you should NOT change this interface!
 * @author boaz.benmoshe
 */
public interface GUI_Shape {
	public GeoShape getShape();
	public void setShape(GeoShape g);
	public boolean isFilled();
	public void setFilled(boolean filled);
	public Color getColor();
	public void setColor(Color cl);
	public int getTag();
	public void setTag(int tag);
	public GUI_Shape copy();
	public boolean isSelected();
	public void setSelected(boolean s);
	public String toString();
}
