package DynaProxy;

public class Test {
	public static void main(String[] args) throws Exception {
		// 创建一个原始的GunDog对象，作为target
		Dog target = new GunDog();
		MyInvocationHandler handler=new MyInvocationHandler(target);
		// 以指定的target来创建动态代理
		Dog dog = (Dog) handler.getProxy();
		dog.info();
		dog.run();
		dog.equals(dog);
		dog.toString();
		System.out.println(dog.getClass());
		System.out.println(target.getClass());
	}
}
