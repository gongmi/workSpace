package Thread;

public class TestThread1 {
	public static void main(String args[]) {
		Runner1 r = new Runner1();

//		 r.run();
		Thread t = new Thread(r, "袁其祥线程");
		// t.setDaemon(true);
		// 把t设置为后台线程 前台线程结束后 后台线程就立即结束 但是有一定反应时间

		for (int i = 0; i < 100; i++) {
			if (i == 10)
				t.start();
			System.out.println(Thread.currentThread().getName() + i);
			 if(i ==50 && !t.isAlive()) t.start(); //只能对新建状态的线程调用start
		}
	}
}

class Runner1 implements Runnable {
//	 class Runner1 extends Thread {
	public void run() {
		for (int i = 0; i < 500; i++) {
			System.out.println(Thread.currentThread().getName() + "Runner1 :" + i);
		}
	}
}
