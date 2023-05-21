package comparableInterface;


//Define class Person that implements Comparable interface
public class Person implements Comparable<Person>{
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
	   public int compareTo(Person o){		  
		   // compare by id
		   if(ID < o.ID) return -1;
		   else if (ID > o.ID) return 1;
		   return 0;
		   /*
		   // compare by name
		   return name.compareTo(o.name);
		   */
	   }
}
