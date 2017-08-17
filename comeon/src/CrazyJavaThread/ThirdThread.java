package CrazyJavaThread;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.*;

public class ThirdThread {
	public static void main(String[] args) {

		Callable<Integer> c = new Callable<Integer>() {
			public Integer call() {
				int i = 0;
				for (; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + " 的循环变量i的值：" + i);
				}
				// call()方法可以有返回值
				return i;
			}
		};

		FutureTask<Integer> task = new FutureTask<Integer>(c);

		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " 的循环变量i的值：" + i);
			if (i == 20) {
				// 实质还是以Callable对象来创建、并启动线程
				new Thread(task, "有返回值的线程").start();
			}
		}
		try {
			// 获取线程返回值
			System.out.println("子线程的返回值：" + task.get());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
