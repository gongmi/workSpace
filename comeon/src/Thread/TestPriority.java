package Thread;

public class TestPriority {
	public static void main(String[] args) {
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		Thread t1 = new Thread(new T1());
		Thread t2 = new Thread(new T2());
		t1.start();
		t2.start();

		for (int i = 0; i < 100; i++) {
			if (i == 50) {
				System.out.println("t1变高级了！！！！！！！");
				t1.setPriority(Thread.MAX_PRIORITY);
			}
			System.out.println("main:" + i + "优先级：" + Thread.currentThread().getPriority());
		}
	}
}

class T1 implements Runnable {
	public void run() {
		for (int i = 0; i < 100; i++) {

			System.out.println("T1: " + i + "优先级：" + Thread.currentThread().getPriority());
		}
	}
}

class T2 implements Runnable {
	public void run() {
		for (int i = 0; i < 100; i++) {

			System.out.println("------T2: " + i + "优先级：" + Thread.currentThread().getPriority());
		}
	}
}