package Thread;
 
public class TT implements Runnable {
	int b = 100;

	public synchronized void m1() throws Exception {

		System.out.println("m1��ʼʱ��b=" + b);
		b = 1000;
		System.out.println(Thread.currentThread().getName() + "���߳�Ҫ˯����");
		Thread.sleep(3000);
		System.out.println(Thread.currentThread().getName() + "���߳�����");
		System.out.println("m1����ʱ��b=" + b);
	}

	public  void m2() throws Exception {//���m2�����Ļ� ����m1 sync�˵�ǰ����tt 
		//m2һ������ִ��  ��Ϊ���Լ�û��sync ���Բ���Ҫ�õ����Ϳ��Է���b

		System.out.println("m2��ʼʱ��b=" + b);
		System.out.println(Thread.currentThread().getName() + "���߳�Ҫ˯����");

		Thread.sleep(3000);
		System.out.println(Thread.currentThread().getName() + "���߳�����");
		b = 2000;
		System.out.println("m2����ʱ��b=" + b);
	}

	public void run() {
		if (Thread.currentThread().getName().equals("t1"))
			try {
				m1();
			} catch (Exception e) {
				e.printStackTrace();
			}
		else
			try {
				m2();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static void main(String[] args) throws Exception {
		TT tt = new TT();
		Thread t1 = new Thread(tt);
		Thread t2 = new Thread(tt);
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();

		for (int i = 0; i < 100; i++) {
			Thread.sleep(100);
			System.out.println(i);
		}

	}
}