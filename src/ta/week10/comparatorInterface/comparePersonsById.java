package comparatorInterface;

import java.util.Comparator;

import comparableInterface.Person;

//Define class Person that implements Comparable interface
public class comparePersonsById implements Comparator<Person>{  
   public int compare(Person p1, Person p2){	   
	   if(p1.getID() < p2.getID()) 
		   return -1;
	   else if (p1.getID() > p2.getID()) 
		   return 1;
	   else
		   return 0;		  
   }
}
