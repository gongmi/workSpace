package CrazyJavaThread;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.*;

public class ThirdThread {
	public static void main(String[] args) {

		Callable<Integer> c = new Callable<Integer>() {
			public Integer call() {
				int i = 0;
				for (; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + " ��ѭ������i��ֵ��" + i);
				}
				// call()���������з���ֵ
				return i;
			}
		};

		FutureTask<Integer> task = new FutureTask<Integer>(c);

		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " ��ѭ������i��ֵ��" + i);
			if (i == 20) {
				// ʵ�ʻ�����Callable�������������������߳�
				new Thread(task, "�з���ֵ���߳�").start();
			}
		}
		try {
			// ��ȡ�̷߳���ֵ
			System.out.println("���̵߳ķ���ֵ��" + task.get());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
