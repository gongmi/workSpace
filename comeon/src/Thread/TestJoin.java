package Thread;

public class TestJoin {
	public static void main(String[] args) {
		MyThread2 t1 = new MyThread2("join的线程");
		MyThread2 t2 = new MyThread2("新线程");
		t1.start();
		t2.start();
		for (int i = 1; i <= 30; i++) {
			System.out.println("i am main thread" + i);

			if (i == 20)
				try {
					t1.join();
				} catch (InterruptedException e) {
				}

		}
	}
}

class MyThread2 extends Thread {
	MyThread2(String s) {
		super(s);
	}

	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("i am " + getName() + i);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
