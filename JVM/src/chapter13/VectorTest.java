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
			//�������synchronized ��ô�������run�������е�i�Ѿ���ֵk�������
//			�����run����vector.remove(k)�� ��ô�ͻ���� ��Ϊvector.get(i)��vector.remove(i)
//			��������������Դ���������synchronized�� ֻ�Ǳ��� ���������������� ���ܱ����
//			����������run������������ȴ���Ա����
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

			// ��Ҫͬʱ����������̣߳�����ᵼ�²���ϵͳ����
			while (Thread.activeCount() > 20)
				;
		}
	}
}
