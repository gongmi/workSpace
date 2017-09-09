package CrazyJavaThread;

import java.util.concurrent.*;
//生产者消费者问题 用blockingQueue实现 的 
//也可以用马士兵的notify与wait +stack实现 
class Producer extends Thread {
	private BlockingQueue<String> bq;

	public Producer(BlockingQueue<String> bq) {
		this.bq = bq;
	}

	public void run() {
		for (int i = 0; i < 999999999; i++) {
			System.out.println(getName() + "---生产者准备生产！");
			try {
				Thread.sleep(100);
				// 尝试放入元素，如果队列已满，线程被阻塞
				bq.put(Thread.currentThread().getName()+"的包子"+i);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println(getName() + "生产完成,当前剩下的包子：" + bq);
		}
	}
}



class Consumer extends Thread {
	private BlockingQueue<String> bq;

	public Consumer(BlockingQueue<String> bq) {
		this.bq = bq;
	}

	public void run() {
		while (true) {
			System.out.println(getName() + "消费者准备消费！");
			try {
				Thread.sleep(200);
				// 尝试取出元素，如果队列已空，线程被阻塞
				bq.take();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println(getName() + "消费完成,当前剩下的包子：" + bq);
		}
	}
}

public class BlockingQueueTest2 {
	public static void main(String[] args) {
		BlockingQueue<String> bq = new LinkedBlockingQueue<>(2);
	Thread prod1=new Producer(bq);
	Thread prod2=new Producer(bq);
	Thread prod3=new Producer(bq);
	Thread eat1=new Consumer(bq);
	Thread eat2=new Consumer(bq);
	prod1.setName("1桃园食堂");
	prod2.setName("2橘园食堂");
	prod3.setName("3梅园食堂");
	eat1.setName("龚宓");
	eat2.setName("袁其祥");
	prod1.start();
	prod2.start();
	prod3.start();
	eat1.start();
	eat2.start();
	}
}