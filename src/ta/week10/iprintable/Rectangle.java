package iprintable;

//Define Rectangle
public class Rectangle implements IPrintable{
	// Private member variables
	private int height, width;

	// Constructor
	public Rectangle(int height, int width) {		
		this.height = height;
		this.width = width;
	}

	public void print() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) 
				System.out.print('*');
			System.out.println();			
		}
	}
	
	@Override
	public String toString() {
		return "Rectangle of height=" + height + " and width=" + width;
	}

	public double getArea() {
		return height*width;
	}

}
