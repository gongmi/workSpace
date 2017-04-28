package chapter4;

public class DeadLockTest {
	static class SynAddRunalbe implements Runnable {
		int a, b;

		public SynAddRunalbe(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public void run() {
			synchronized (Integer.valueOf(a)) {
				synchronized (Integer.valueOf(b)) {
					System.out.println(a + b);
				}
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new SynAddRunalbe(1, 2)).start();
			new Thread(new SynAddRunalbe(2, 1)).start();
		}
	}
}