package Thread;
 
public class TT implements Runnable {
	int b = 100;

	public synchronized void m1() throws Exception {

		System.out.println("m1开始时的b=" + b);
		b = 1000;
		System.out.println(Thread.currentThread().getName() + "的线程要睡眠了");
		Thread.sleep(3000);
		System.out.println(Thread.currentThread().getName() + "的线程醒了");
		System.out.println("m1结束时的b=" + b);
	}

	public  void m2() throws Exception {//如果m2不锁的话 就算m1 sync了当前对象tt 
		//m2一样可以执行  因为它自己没有sync 所以不需要得到锁就可以访问b

		System.out.println("m2开始时的b=" + b);
		System.out.println(Thread.currentThread().getName() + "的线程要睡眠了");

		Thread.sleep(3000);
		System.out.println(Thread.currentThread().getName() + "的线程醒了");
		b = 2000;
		System.out.println("m2结束时的b=" + b);
	}

	public void run() {
		if (Thread.currentThread().getName().equals("t1"))
			try {
				m1();
			} catch (Exception e) {
				e.printStackTrace();
			}
		else
			try {
				m2();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static void main(String[] args) throws Exception {
		TT tt = new TT();
		Thread t1 = new Thread(tt);
		Thread t2 = new Thread(tt);
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();

		for (int i = 0; i < 100; i++) {
			Thread.sleep(100);
			System.out.println(i);
		}

	}
}