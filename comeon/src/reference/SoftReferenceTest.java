package reference;

import java.lang.ref.*;

/**
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class SoftReferenceTest {
	public static void main(String[] args) throws Exception {
		String str = new String("疯狂Java讲义");
		SoftReference<String> wr = new SoftReference<String>(str); // ①
		str = null; // ②
		// 取出软引用所引用的对象
		System.out.println(wr.get()); // ③
		// 强制垃圾回收
		System.gc();
		System.runFinalization();
		// 再次取出软引用所引用的对象 可以取到 因为现在内存不紧张 不会回收软引用对象
		System.out.println(wr.get()); // ④
	}
}
