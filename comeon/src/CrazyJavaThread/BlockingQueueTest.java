package CrazyJavaThread;

import java.util.concurrent.*;
public class BlockingQueueTest {
	public static void main(String[] args) throws Exception {
		// ����һ������Ϊ2����������
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
		bq.put("Java"); // ��bq.add()    bq.offer()��ͬ
		bq.put("Java");  
	//	bq.put("Java"); // �� �����̡߳�
		//bq.add("Java");
		System.out.println(bq.offer("Java"));
	}
}
