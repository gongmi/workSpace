package Thread;

public class TestSync implements Runnable {
	Timer timer = new Timer();
	
	public void run() {
		timer.add(Thread.currentThread().getName());
	}
	public static void main(String[] args) {
		TestSync test = new TestSync();
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();
	}
}

class Timer {
	private static int num = 0;

	// public synchronized void add(String name){//ִ����δ���ʱ������ǰ����
	public void add(String name) {
//		synchronized (this) {
			 System.out.println("enter:"+name);
			 System.out.println("���ǵ�"+num+"��ʹ��timer���߳�");
			num++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			System.out.println("exit:" + name);
			System.out.println("���ǵ�" + num + "��ʹ��timer���߳�");
//		}
	}
}