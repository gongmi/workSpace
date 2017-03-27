
public class Test {
	public static void main(String[] args) {
		Driver d = new Driver();
		d.setName("老张");
	//	d.drive(new Broom());
		d.drive(new Car(),new Address("东北"));//父类引用指向子类对象
	}
}	
