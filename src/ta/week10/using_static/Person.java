package using_static;

//Define superclass Person
public class Person {
	// Class variables
	private static int licenseAge;

	// Instance variables
	private String name;
	private String ID;
	private int age;



	// Constructor
	public Person(String name, String ID, int age) {
		this.name = name;
		this.ID = ID;
		this.age = age;
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}

	public int getAge() {
		return age;
	}

	// Setters
	public void setLicenseAge(int age)  {
		licenseAge = age; 
	}

	public String toString() {
		String str = "";
		str += "Name (ID):  " + name + "(" + ID + ")";
		str += "\tAge:  " + age + " (";
		if (age < licenseAge)
			str += "can not drive";
		else
			str += "can drive";
		str += ")";
		return str;
	}


	public static void main(String[] args) {		
		Person p1 = new Person("Gogo", "111111111", 14);
		Person p2 = new Person("Momo", "222222222", 23);
		Person p3 = new Person("Yoyo", "333333333",19);		
		p1.setLicenseAge(18); 
		//same as: p2.setLicenseAge(18);		
		System.out.println("Changing adult age to be :" + Person.licenseAge);

		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());		
		p2.setLicenseAge(21); 
		//same as: p3.setLicenseAge(21);
		System.out.println("Changing adult age to be :" + Person.licenseAge);

		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());

	}
}
