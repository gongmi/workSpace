package classload;

import java.util.*;
import java.net.*;
import java.io.*;

public class ClassLoaderPropTest {
	public static void main(String[] args) throws IOException {
		// ��ȡϵͳ�������
		ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
		System.out.println("ϵͳ���������" + systemLoader);
		/*
		 * ��ȡϵͳ��������ļ���·������ͨ����CLASSPATH��������ָ�� �������ϵͳû��ָ��CLASSPATH����������Ĭ���Ե�ǰ·����Ϊ
		 * ϵͳ��������ļ���·��
		 */
		Enumeration<URL> em1 = systemLoader.getResources("");
		while (em1.hasMoreElements()) {
			System.out.println(em1.nextElement());
		}
		// ��ȡϵͳ��������ĸ�����������õ���չ�������
		ClassLoader extensionLader = systemLoader.getParent();
		System.out.println("��չ���������" + extensionLader);
		System.out.println("��չ��������ļ���·����" + System.getProperty("java.ext.dirs"));
		System.out.println("��չ���������parent: " + extensionLader.getParent());
	}
}
