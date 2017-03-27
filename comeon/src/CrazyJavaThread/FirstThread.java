package CrazyJavaThread;

// 通过继承Thread类来创建线程类
public class FirstThread extends Thread {
	private int i;

	public FirstThread(String string) {
		super(string);
	}

	// 重写run方法，run方法的方法体就是线程执行体
	public void run() {
		for (; i < 100; i++) {
			System.out.println(getName() + " " + i+"当前有"+Thread.activeCount()+"个线程");
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			// 调用Thread的currentThread方法获取当前线程
			System.out.println(Thread.currentThread().getName() + " " + i+"当前有"+Thread.activeCount()+"个线程");
			if (i == 20) {
				new FirstThread("龚宓").start();
				new FirstThread("袁其祥").start();
			}
		}
	}
}
