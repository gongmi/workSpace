package CrazyJavaThread;

import java.util.concurrent.*;

public class ThreadPoolTest {
	public static void main(String[] args) throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(6);

		FutureTask<String> f1 = (FutureTask<String>) pool.submit(new test());
		Future<String> f2 = pool.submit(new test());
		pool.shutdown();
		System.out.println(f1.get());
		System.out.println(f2.get());
	}
}

class test implements Callable<String> {
	@Override
	public String call() throws Exception {
		for (int i = 0; i < 50; i++) {
			System.out.println(Thread.currentThread().getName() + "µÄi:" + i);

		}
		return Thread.currentThread().getName()+ "½áÊøÁË";
	}

}
