package classload;

import java.lang.reflect.*;
import java.util.*;

class Test {
	public void replace(String str, List<String> list) {
	}
}

public class MethodParameterTest {
	public static void main(String[] args) throws Exception {
		Class<Test> clazz = Test.class;
		Method replace = clazz.getMethod("replace", String.class, List.class);
		System.out.println("replace方法参数个数：" + replace.getParameterCount());
		Parameter[] parameters = replace.getParameters();
		int index = 1;
		// 遍历所有参数
		for (Parameter p : parameters) {
			if (p.isNamePresent()) {
				System.out.println("---第" + index++ + "个参数信息---");
				System.out.println("参数名：" + p.getName());
				System.out.println("形参类型：" + p.getType());
				System.out.println("泛型类型：" + p.getParameterizedType());
			}
		}
	}
}
