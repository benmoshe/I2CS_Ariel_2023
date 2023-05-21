package iprintable;
//Define superclass Person
public class Person implements IPrintable{
	// Instance variables
	private int id;
	private String name;
	

	// Constructor
	public Person(int id, String name) {
		this.id = id;
		this.name = name;		
	}

	public void print() {
		System.out.println("Id: " + id 
				+ "\tName: " + name);
	}
	
	// Getters
	public String getName() {
		return name;
	}
	public int getID() {
		return id;
	}

	public String toString() {
		return name + "(" + id + ")";
	}	

}
