package reference;

import java.lang.ref.*;

/**
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class WeakReferenceTest {
	public static void main(String[] args) throws Exception {
		String str = new String("疯狂Java讲义");
		WeakReference<String> wr = new WeakReference<String>(str); // ①
		str = null; // ②
		// 取出弱引用所引用的对象
		System.out.println(wr.get()); // ③
		// 强制垃圾回收
		System.gc();
		System.runFinalization();
		// 再次取出弱引用所引用的对象 娶不到了  因为弱引用 无论内存是否足够 都会被回收
		System.out.println(wr.get()); // ④
	}
}
