package singleton_lazyInit;

//��Ҳ���ӳٳ�ʼ��  
public class Singleton4 {
	private static class InstanceHolder {
		public static Singleton4 instance = new Singleton4();
	}

	private Singleton4() {

	}

	public static Singleton4 getInstance() {
		return InstanceHolder.instance; // ���ｫ����InstanceHolder�౻��ʼ�� ��������������ͬ��
										// ��Ϊ������InstanceHolder��static�ֶ�
	}
}
