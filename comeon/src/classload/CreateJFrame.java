package classload;

import java.lang.reflect.*;
import java.util.*;

//指定构造器创建对象
public class CreateJFrame {
	public static void main(String[] args) throws Exception {
//		Class<?> jframeClazz = Class.forName("javax.swing.JFrame");
//		Constructor<?> ctor = jframeClazz.getConstructor(String.class);
//		Object obj = ctor.newInstance("测试窗口");
//		System.out.println(obj);
		Class<?> listClazz = Class.forName("java.util.LinkedList");
		Constructor<?> ctor = listClazz.getConstructor(Collection.class);
		String[] a=new String[]{"'2'","'3'","'4'"};
		LinkedList<String> obj = (LinkedList<String>) ctor.newInstance(Arrays.asList(a));
		System.out.println(obj);
	}
}
