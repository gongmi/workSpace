package DynaProxy;

public class Test {
	public static void main(String[] args) throws Exception {
		// ����һ��ԭʼ��GunDog������Ϊtarget
		Dog target = new GunDog();
		MyInvocationHandler handler=new MyInvocationHandler(target);
		// ��ָ����target��������̬����
		Dog dog = (Dog) handler.getProxy();
		dog.info();
		dog.run();
		dog.equals(dog);
		dog.toString();
		System.out.println(dog.getClass());
		System.out.println(target.getClass());
	}
}
