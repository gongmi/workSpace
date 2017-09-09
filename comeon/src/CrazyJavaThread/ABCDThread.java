package CrazyJavaThread;

import java.util.concurrent.*;

public class ABCDThread {
	public static void main(String[] args) {

		Callable<Integer> a = new Callable<Integer>() {
			public Integer call() {
				System.out.println(1);// do something
				return 1;
			}
		};

		Callable<Integer> b = new Callable<Integer>() {
			public Integer call() {
				System.out.println(2); // do something
				return 2;
			}
		};

		Callable<Integer> c = new Callable<Integer>() {
			public Integer call() {
				System.out.println(3); // do something
				return 3;
			}
		};
		FutureTask<Integer> taskA = new FutureTask<Integer>(a);
		FutureTask<Integer> taskB = new FutureTask<Integer>(b);
		FutureTask<Integer> taskC = new FutureTask<Integer>(c);

		new Thread(taskA).start();
		new Thread(taskB).start();
		new Thread(taskC).start();

		int res;
		try {
			res = taskA.get() + taskB.get() + taskC.get();
			System.out.println(res);
		} catch (Exception e) {
		}
	}
}
