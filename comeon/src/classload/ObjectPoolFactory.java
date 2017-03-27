package classload;

import java.util.*;
import java.io.*;

//Ĭ�Ϲ�������������

public class ObjectPoolFactory {
	// ����һ�������,ǰ���Ƕ�������������ʵ�ʶ���
	private Map<String, Object> objectPool = new HashMap<>();

	private Object createObject(String clazzName) throws Exception {
		Class<?> clazz = Class.forName(clazzName);
		return clazz.newInstance();
	}

	// �÷�������ָ���ļ�����ʼ������أ�������������ļ�����������
	public void initPool(String fileName) throws Exception {
		try (FileInputStream fis = new FileInputStream(fileName)) {
			Properties props = new Properties();
			props.load(fis);
			for (String name : props.stringPropertyNames()) {
				objectPool.put(name, createObject(props.getProperty(name)));
			}
		} catch (IOException ex) {
			System.out.println("��ȡ" + fileName + "�쳣");
		}
	}

	public Object getObject(String name) {
		return objectPool.get(name);
	}

	public static void main(String[] args) throws Exception {
		ObjectPoolFactory pf = new ObjectPoolFactory();
		pf.initPool("d:\\GM\\obj.txt");
		System.out.println(pf.getObject("a")); // ��
		System.out.println(pf.getObject("b")); // ��
	}
}
