package CrazyJavaThread;

import java.util.concurrent.*;

public class ThreadPoolTest {
	public static void main(String[] args) throws Exception {
		// ����һ�����й̶��߳�����6�����̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(6);

		Runnable target = new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++)
					System.out.println(Thread.currentThread().getName() + "��i:" + i);
			}
		};
		// ���̳߳����ύ�����߳�
		pool.submit(target);
		pool.submit(target);
//		Future<String> f1 = pool.submit(new test());
//		Future<String> f2 = pool.submit(new test());
		// �ر��̳߳�
		pool.shutdown();
//		System.out.println(f1.get());
//		System.out.println(f2.get());
	}
}

class test implements Callable<String> {

	@Override
	public String call() throws Exception {
		for (int i = 0; i < 50; i++) {
			System.out.println(Thread.currentThread().getName() + "��i:" + i);

		}
		return Thread.currentThread().getName()+ "������";
	}

}
