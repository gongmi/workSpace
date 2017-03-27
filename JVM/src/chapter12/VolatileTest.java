package chapter12;



public class VolatileTest {

	public static volatile int race = 0;

	public static void increase() {
		race++; //这条语句是非原子性的
	}

	private static final int THREADS_COUNT = 20;

	public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < THREADS_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						increase();
					}
				}
			});
			threads[i].start();
		}

		while (Thread.activeCount() > 1)//如果当前存活的线程个数大于1 即除了main 还有另外的线程
			Thread.yield();//当前main线程把自己的时间片yield给另外THREADS_COUNT个线程  务必要等另外的线程执行结束

		System.out.println(race);
	}
}
