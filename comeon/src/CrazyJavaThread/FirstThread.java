package CrazyJavaThread;

// ͨ���̳�Thread���������߳���
public class FirstThread extends Thread {
	private int i;

	public FirstThread(String string) {
		super(string);
	}

	// ��дrun������run�����ķ���������߳�ִ����
	public void run() {
		for (; i < 100; i++) {
			System.out.println(getName() + " " + i+"��ǰ��"+Thread.activeCount()+"���߳�");
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			// ����Thread��currentThread������ȡ��ǰ�߳�
			System.out.println(Thread.currentThread().getName() + " " + i+"��ǰ��"+Thread.activeCount()+"���߳�");
			if (i == 20) {
				new FirstThread("���").start();
				new FirstThread("Ԭ����").start();
			}
		}
	}
}
