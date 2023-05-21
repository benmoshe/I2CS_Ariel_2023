package comparatorInterface;

import java.util.Comparator;

import comparableInterface.Person;

public class comparePersonsByName implements Comparator<Person> {
   public int compare(Person p1, Person p2){		   
	   return (p1.getName().compareTo(p2.getName()));		  
   }
}
