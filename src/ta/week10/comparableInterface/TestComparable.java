package comparableInterface;

import java.util.Arrays;

public class TestComparable {

	public static void main(String[] args) {
		Person[] persons = new Person[3];
		persons[0] = new Person("Haim Cohen", 2222);
		persons[1] = new Person("Moshe Levi", 3333);
		persons[2] = new Person("Itzik Braun", 1111);
		System.out.println("Persons are:" + Arrays.toString(persons));
		Arrays.sort(persons);
		System.out.println("After sort:" + Arrays.toString(persons));
		Arrays.sort(persons);
	}
}
