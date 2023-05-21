package comparatorInterface;
//Define class Person
public class Person {
	   // Instance variables
	   private String name;
	   private int ID;
	   // Constructor
	   public Person(String name, int ID) {
	      this.name = name;
	      this.ID = ID;
	   }	   
	   // Getters
	   public String getName() {
	      return name;
	   }	   
	   public int getID() {
	      return ID;
	   }	   
	   public String toString() {
	      return name + "(" + ID + ")";
	   }  
}
