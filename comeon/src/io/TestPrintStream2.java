package io;

import java.io.*;

public class TestPrintStream2 {
	public static void main(String[] args) {

		String filename = args[0];
		// ������ʱ���ò��� ��������Ҫ�����ļ��ĵ�ַ
		// Ȼ���ӡ�ڿ���̨�� 
		System.out.println(filename);
		if (filename != null) {
			list(filename, System.out); 
		}
	}

	public static void list(String f, PrintStream fs) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String s = null;
			while ((s = br.readLine()) != null) {
				fs.println(s); //System.out.println
			}
			br.close();
		} catch (IOException e) {
			fs.println("�޷���ȡ�ļ�");
		}
	}
}