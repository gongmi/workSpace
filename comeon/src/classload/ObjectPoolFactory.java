package classload;

import java.util.*;
import java.io.*;

//默认构造器创建对象

public class ObjectPoolFactory {
	// 定义一个对象池,前面是对象名，后面是实际对象
	private Map<String, Object> objectPool = new HashMap<>();

	private Object createObject(String clazzName) throws Exception {
		Class<?> clazz = Class.forName(clazzName);
		return clazz.newInstance();
	}

	// 该方法根据指定文件来初始化对象池，它会根据配置文件来创建对象
	public void initPool(String fileName) throws Exception {
		try (FileInputStream fis = new FileInputStream(fileName)) {
			Properties props = new Properties();
			props.load(fis);
			for (String name : props.stringPropertyNames()) {
				objectPool.put(name, createObject(props.getProperty(name)));
			}
		} catch (IOException ex) {
			System.out.println("读取" + fileName + "异常");
		}
	}

	public Object getObject(String name) {
		return objectPool.get(name);
	}

	public static void main(String[] args) throws Exception {
		ObjectPoolFactory pf = new ObjectPoolFactory();
		pf.initPool("d:\\GM\\obj.txt");
		System.out.println(pf.getObject("a")); // ①
		System.out.println(pf.getObject("b")); // ②
	}
}
