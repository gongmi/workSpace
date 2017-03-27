package CrazyJavaThread;

import java.util.concurrent.*;
public class BlockingQueueTest {
	public static void main(String[] args) throws Exception {
		// 定义一个长度为2的阻塞队列
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
		bq.put("Java"); // 与bq.add()    bq.offer()相同
		bq.put("Java");  
	//	bq.put("Java"); // ① 阻塞线程。
		//bq.add("Java");
		System.out.println(bq.offer("Java"));
	}
}
