package CrazyJavaThread;

class Account3 {
	/*
	 * ����һ��ThreadLocal���͵ı������ñ�������һ���ֲ߳̾����� ÿ���̶߳��ᱣ���ñ�����һ������
	 */
	private ThreadLocal<String> name = new ThreadLocal<>();

	// ����һ����ʼ��name��Ա�����Ĺ�����
	public Account3(String str) {
		this.name.set(str);
		// ����������ڷ��ʵ�ǰ�̵߳�name������ֵ
		System.out.println("---" + this.name.get());
	}

	// name��setter��getter����
	public String getName() {
		return name.get();
	}

	public void setName(String str) {
		this.name.set(str);
	}
}

class MyTest extends Thread {
	private Account3 Account3;

	public MyTest(Account3 Account3, String name) {
		super(name);
		this.Account3 = Account3;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			// ��i == 6ʱ������˻����滻�ɵ�ǰ�߳���
			if (i == 6) {
				Account3.setName(getName());
			}
			// ���ͬһ���˻����˻�����ѭ������
			System.out.println(Account3.getName() + " �˻���iֵ��" + i);
		}
	}
}

public class ThreadLocalTest {
	public static void main(String[] args) {
		// ���������̣߳������̹߳���ͬһ��Account
		Account3 at = new Account3("��ʼ��");
		/*
		 * ��Ȼ�����̹߳���ͬһ���˻�����ֻ��һ���˻��� �������˻�����ThreadLocal���͵ģ�����ÿ���߳�
		 * ����ȫӵ�и��Ե��˻������������Դ�i == 6֮�󣬽��������� �̷߳���ͬһ���˻�ʱ������ͬ���˻�����
		 */
		new MyTest(at, "�̼߳�").start();
		new MyTest(at, "�߳���").start();
	}
}
