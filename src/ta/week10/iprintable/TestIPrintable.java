package iprintable;


public class TestIPrintable {

	public static void doSomething(IPrintable i)
    {
        System.out.println("Type of i is " + i.getClass().getName());
        if (i instanceof Person)
            System.out.println(i.toString());

        if (i instanceof Rectangle)
        {
        	Rectangle r = (Rectangle)i;
        	System.out.println("Area of rectangle is " + r.getArea());
        }
    }
	public static void main(String[] args) {
		IPrintable i1 = new Person(111, "Haim");
		IPrintable i2 = new Rectangle(5,7);
		doSomething(i1);
		doSomething(i2);
	}

}
