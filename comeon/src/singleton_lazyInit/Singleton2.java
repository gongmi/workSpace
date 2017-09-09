package singleton_lazyInit;

//�̰߳�ȫ�� ������1�Ļ����ϼ�����synchronized ��������������
public class Singleton2 {
	private static Singleton2 instance;

	private Singleton2() {

	}

	public synchronized static Singleton2 getInstance() {
		if (instance == null)
			instance = new Singleton2();
		return instance;
	}
}