package Thread;

public class TestThread5 {
	public static void main(String args[]) {
		Runner5 r = new Runner5();
		Thread t = new Thread(r);
		t.start();

		for (int i = 0; i < 100; i++) {
			System.out.println("Ö÷Ïß³Ì:" + i);
			if (i == 20)
				try {
					t.join();
				} catch (InterruptedException e) {
				}
		}
	}
}

class Runner5 implements Runnable {
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("SubThread: " + i);
		}
	}
}