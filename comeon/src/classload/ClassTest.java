package classload;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;

// ������ظ�ע��
@Repeatable(Annos.class)
@interface Anno {
}

@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos {
	Anno[] value();
}

// ʹ��4��ע�����θ���
@SuppressWarnings(value = "unchecked")
@Deprecated


// ʹ���ظ�ע�����θ���  �ҵ�myeclipse����֧��
@Annos( {@Anno,@Anno})

public class ClassTest {
	// Ϊ���ඨ��һ��˽�еĹ�����
	private ClassTest() {
	}

	public ClassTest(String name) {
		System.out.println("ִ���в����Ĺ�����");
	}

	public void info() {
		System.out.println("ִ���޲�����info����");
	}

	public void info(String str) {
		System.out.println("ִ���в�����info����" + "����str����ֵ��" + str);
	}

	// ����һ�������õ��ڲ���
	class Inner {
	}

	public static void main(String[] args) throws Exception {
		Class<ClassTest> clazz = ClassTest.class;

		Constructor<?>[] ctors = clazz.getDeclaredConstructors();
		System.out.println("ClassTest��ȫ�����������£�");
		for (Constructor<?> c : ctors) {
			System.out.println(c);
		}
		
		Constructor<?>[] publicCtors = clazz.getConstructors();
		System.out.println("ClassTest��ȫ��public���������£�");
		for (Constructor<?> c : publicCtors) {
			System.out.println(c);
		}
		
		Method[] mtds = clazz.getMethods();
		System.out.println("ClassTest��ȫ��public�������£�");
		for (Method md : mtds) {
			System.out.println(md);
		}
		
		System.out.println("ClassTest���һ���ַ���������info()����Ϊ��" + clazz.getMethod("info", String.class));
		
		Annotation[] anns = clazz.getAnnotations();
		System.out.println("ClassTest��ȫ��Annotation���£�");
		for (Annotation an : anns) {
			System.out.println(an);
		}
		
		System.out.println("��ClassԪ���ϵ�@SuppressWarningsע��Ϊ��"
				+ Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)));
		System.out.println("��ClassԪ���ϵ�@Annoע��Ϊ��"
				+ Arrays.toString(clazz.getAnnotationsByType(Anno.class)));

		Class<?>[] inners = clazz.getDeclaredClasses();
		System.out.println("ClassTest��ȫ���ڲ������£�");
		for (Class<?> c : inners) {
			System.out.println(c);
		}

		Class<?> inClazz = Class.forName("classload.ClassTest$Inner");
		System.out.println("inClazz��Ӧ����ⲿ��Ϊ��" + inClazz.getDeclaringClass());
		
		System.out.println("ClassTest�İ�Ϊ��" + clazz.getPackage());
		System.out.println("ClassTest�ĸ���Ϊ��" + clazz.getSuperclass());
	}
}
