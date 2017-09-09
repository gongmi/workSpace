package CrazyJavaThread;

import java.util.concurrent.*;
//���������������� ��blockingQueueʵ�� �� 
//Ҳ��������ʿ����notify��wait +stackʵ�� 
class Producer extends Thread {
	private BlockingQueue<String> bq;

	public Producer(BlockingQueue<String> bq) {
		this.bq = bq;
	}

	public void run() {
		for (int i = 0; i < 999999999; i++) {
			System.out.println(getName() + "---������׼��������");
			try {
				Thread.sleep(100);
				// ���Է���Ԫ�أ���������������̱߳�����
				bq.put(Thread.currentThread().getName()+"�İ���"+i);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println(getName() + "�������,��ǰʣ�µİ��ӣ�" + bq);
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
			System.out.println(getName() + "������׼�����ѣ�");
			try {
				Thread.sleep(200);
				// ����ȡ��Ԫ�أ���������ѿգ��̱߳�����
				bq.take();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println(getName() + "�������,��ǰʣ�µİ��ӣ�" + bq);
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
	prod1.setName("1��԰ʳ��");
	prod2.setName("2��԰ʳ��");
	prod3.setName("3÷԰ʳ��");
	eat1.setName("���");
	eat2.setName("Ԭ����");
	prod1.start();
	prod2.start();
	prod3.start();
	eat1.start();
	eat2.start();
	}
}