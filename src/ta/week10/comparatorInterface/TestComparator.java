package comparatorInterface;

import java.util.Arrays;
import java.util.Comparator;

import comparableInterface.Person;

public class TestComparator {	
	public static void sortArray(Person[] arr, Comparator<Person> c) {
		for(int i=arr.length-1; i>0; i--){
			for(int j=0; j<i; j++){
				if(c.compare(arr[i], arr[j]) < 0){
					Person temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}			
	}
	public static void main(String[] args) {
		Person[] persons = new Person[3];
		persons[0] = new Person("Haim Cohen", 2222);
		persons[1] = new Person("Moshe Levi", 3333);
		persons[2] = new Person("Itzik Braun", 1111);
		System.out.println("Persons are:" + Arrays.toString(persons));
		
		Comparator<Person> pid = new comparePersonsById();
		sortArray(persons, pid);
		System.out.println("After sort by ID:" + Arrays.toString(persons));
		
		Comparator<Person> pname = new comparePersonsByName();
		sortArray(persons, pname);
		System.out.println("After sort by name:" + Arrays.toString(persons));
	}
}
