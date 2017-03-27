package chapter13;

import java.util.Vector;

public class VectorTest {

	private static Vector<Integer> vector = new Vector<Integer>();

	public static void main(String[] args) {
		while (true) {
			for (int i = 0; i < 10; i++) {
				vector.add(i);
			}

			Thread removeThread = new Thread(new Runnable() {
				public void run() {
					synchronized (vector) {
						for (int i = 0; i < vector.size(); i++) {
							vector.remove(i);
						}
					}
				}
			});
			//如果不加synchronized 那么当下面的run方法进行到i已经赋值k的情况下
//			上面的run方法vector.remove(k)了 那么就会出错 因为vector.get(i)与vector.remove(i)
//			这两个方法是在源码里面加了synchronized的 只是表明 在这两个方法里面 不能被打断
//			但是这两个run方法做的事情却可以被打断
			Thread printThread = new Thread(new Runnable() {
				public void run() {
					synchronized (vector) {
						for (int i = 0; i < vector.size(); i++) {
							System.out.println((vector.get(i)));
						}
					}
				}
			});

			removeThread.start();
			printThread.start();

			// 不要同时产生过多的线程，否则会导致操作系统假死
			while (Thread.activeCount() > 20)
				;
		}
	}
}
