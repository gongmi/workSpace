package Thread;

public class TestThread1 {
	public static void main(String args[]) {
		Runner1 r = new Runner1();

//		 r.run();
		Thread t = new Thread(r, "Ԭ�����߳�");
		// t.setDaemon(true);
		// ��t����Ϊ��̨�߳� ǰ̨�߳̽����� ��̨�߳̾��������� ������һ����Ӧʱ��

		for (int i = 0; i < 100; i++) {
			if (i == 10)
				t.start();
			System.out.println(Thread.currentThread().getName() + i);
			 if(i ==50 && !t.isAlive()) t.start(); //ֻ�ܶ��½�״̬���̵߳���start
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
