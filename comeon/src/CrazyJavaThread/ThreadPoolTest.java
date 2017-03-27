package CrazyJavaThread;

import java.util.concurrent.*;

public class ThreadPoolTest {
	public static void main(String[] args) throws Exception {
		// 创建一个具有固定线程数（6）的线程池
		ExecutorService pool = Executors.newFixedThreadPool(6);

		Runnable target = new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++)
					System.out.println(Thread.currentThread().getName() + "的i:" + i);
			}
		};
		// 向线程池中提交两个线程
		pool.submit(target);
		pool.submit(target);
//		Future<String> f1 = pool.submit(new test());
//		Future<String> f2 = pool.submit(new test());
		// 关闭线程池
		pool.shutdown();
//		System.out.println(f1.get());
//		System.out.println(f2.get());
	}
}

class test implements Callable<String> {

	@Override
	public String call() throws Exception {
		for (int i = 0; i < 50; i++) {
			System.out.println(Thread.currentThread().getName() + "的i:" + i);

		}
		return Thread.currentThread().getName()+ "结束了";
	}

}
