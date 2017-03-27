package chapter7;

class SuperClass {

	static {
		System.out.println("SuperClass init!");
	}

	public static int value = 123;
}

class SubClass extends SuperClass {

	static {
		System.out.println("SubClass init!");
	}
}

class ConstClass {

	static {
		System.out.println("ConstClass init!");
	}

	public static final String HELLOWORLD = "hello world";
}

/**
 * ����ʹ�����ֶ���ʾһ�� ͨ���������ø���ľ�̬�ֶΣ����ᵼ�������ʼ��
 **/
// public class NotInitialization {
//
// public static void main(String[] args) {
// System.out.println(SubClass.value);
// }
//
// }

/**
 * ����ʹ�����ֶ���ʾ���� ͨ�����鶨���������࣬���ᴥ������ĳ�ʼ��
 **/
// public class NotInitialization {
//
// public static void main(String[] args) {
// SuperClass[] sca = new SuperClass[10];
// }
//
// }
/**
 * ����ʹ�����ֶ���ʾ���� �����ڱ���׶λ���������ĳ������У�������û��ֱ�����õ����峣�����࣬��˲��ᴥ�����峣������ĳ�ʼ����
 **/
public class NotInitialization {

	public static void main(String[] args) {
		System.out.println(ConstClass.HELLOWORLD);
	}
}