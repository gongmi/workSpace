package chapter12;



public class VolatileTest {

	public static volatile int race = 0;

	public static void increase() {
		race++; //��������Ƿ�ԭ���Ե�
	}

	private static final int THREADS_COUNT = 20;

	public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < THREADS_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						increase();
					}
				}
			});
			threads[i].start();
		}

		while (Thread.activeCount() > 1)//�����ǰ�����̸߳�������1 ������main ����������߳�
			Thread.yield();//��ǰmain�̰߳��Լ���ʱ��Ƭyield������THREADS_COUNT���߳�  ���Ҫ��������߳�ִ�н���

		System.out.println(race);
	}
}
