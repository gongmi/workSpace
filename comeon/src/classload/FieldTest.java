package classload;

import java.lang.reflect.*;

class Person {
	private String name;
	private int age;

	public String toString() {
		return "Person[name:" + name + " , age:" + age + " ]";
	}
}

public class FieldTest {
	public static void main(String[] args) throws Exception {
		Person p = new Person();
		Class<Person> personClazz = Person.class;

		Field nameField = personClazz.getDeclaredField("name");
		nameField.setAccessible(true);
		nameField.set(p, "Yeeku.H.Lee");

		Field ageField = personClazz.getDeclaredField("age");
		ageField.setAccessible(true);
		ageField.setInt(p, 30);

		System.out.println(p);
	}
}
