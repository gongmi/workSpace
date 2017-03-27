package classload;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;

public class ObjectPoolFactory2 {
	// ����һ�������,ǰ���Ƕ�������������ʵ�ʶ���
	private Map<String, Object> objectPool = new HashMap<>();
	private Properties config = new Properties();

	// ��ָ�������ļ��г�ʼ��Properties����
	public void init(String fileName) {
		try (FileInputStream fis = new FileInputStream(fileName)) {
			config.load(fis);
		} catch (IOException ex) {
			System.out.println("��ȡ" + fileName + "�쳣");
		}
	}

	private Object createObject(String clazzName) throws Exception {
		Class<?> clazz = Class.forName(clazzName);
		return clazz.newInstance();
	}

	// �÷�������ָ���ļ�����ʼ������أ�
	// ������������ļ�����������
	public void initPool() throws Exception {
		for (String name : config.stringPropertyNames()) {
			// ÿȡ��һ��key-value�ԣ����key�в������ٷֺţ�%��
			// ��ͱ����Ǹ���value������һ������
			// ����createObject�������󣬲���������ӵ��������
			if (!name.contains("%")) {
				objectPool.put(name, createObject(config.getProperty(name)));
			}
		}
	}

	// �÷���������������ļ�������ָ�������setter����
	public void initProperty() throws Exception {
		for (String name : config.stringPropertyNames()) {
			// ÿȡ��һ��key-value�ԣ����key�а����ٷֺţ�%��
			// ������Ϊ��key���ڿ��Ƶ��ö����setter��������ֵ��
			// %ǰ��Ϊ�������֣�������setter������
			if (name.contains("%")) {
				// �������ļ���key��%�ָ�
				String[] objAndProp = name.split("%");
				// ȡ������setter�����Ĳ���ֵ
				Object target = getObject(objAndProp[0]);
				// ��ȡsetter������:set + "����ĸ��д" + ʣ�²���
				String mtdName = "set" + objAndProp[1].substring(0, 1).toUpperCase()
						+ objAndProp[1].substring(1);
				// ͨ��target��getClass()��ȡ��ʵ��������Ӧ��Class����
				Class<?> targetClass = target.getClass();
				// ��ȡϣ�����õ�setter����
				Method mtd = targetClass.getMethod(mtdName, String.class);
				// ͨ��Method��invoke����ִ��setter������
				// ��config.getProperty(name)��ֵ��Ϊ����setter�ķ����Ĳ���
				mtd.invoke(target, config.getProperty(name));
			}
		}
	}

	public Object getObject(String name) {
		return objectPool.get(name);
	}

	public static void main(String[] args) throws Exception {
		ObjectPoolFactory2 epf = new ObjectPoolFactory2();
		epf.init("d:\\GM\\extObj.txt");
		epf.initPool();
		epf.initProperty();
		System.out.println(epf.getObject("a"));
	}
}
