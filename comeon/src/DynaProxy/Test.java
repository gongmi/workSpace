package DynaProxy;

public class Test {
	public static void main(String[] args) throws Exception {
		// ����һ��ԭʼ��GunDog������Ϊtarget
		Dog target = new GunDog();
		// ��ָ����target��������̬����
		Dog dog = (Dog) MyProxyFactory.getProxy(target);
		dog.info();
		dog.run();
		System.out.println(dog.getClass());
		System.out.println(target.getClass());
	}
}
