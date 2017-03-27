package Thread;

public class TestYield {
	public static void main(String[] args) {
		MyThread3 t1 = new MyThread3("t1");
		MyThread3 t2 = new MyThread3("t2");
		t1.start();
		t2.start();
	 if(Thread.activeCount()==2)
		System.out.println(	"��ǰ�������߳���+++++++++++++++++++++++++++++++++");
	}
}

class MyThread3 extends Thread {
	MyThread3(String s) {
		super(s);
	}

	public void run() {
		for (int i = 1; i <= 100; i++) {
			 if(Thread.activeCount()==2)
					System.out.println(	"��ǰ�������߳���+++++++++++++++++++++++++++++++++");
			System.out.println(getName() + ": " + i);
			if (i % 10 == 0) {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

			}
		}
	}
}
