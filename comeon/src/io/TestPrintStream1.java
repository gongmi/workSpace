package io;

import java.io.*;
//��ӡ�ַ� ��ʾ���ļ��� Ҳ�������ļ���д�ַ�
public class TestPrintStream1 {
	public static void main(String[] args) {
		PrintStream ps = null;
		try {
			FileOutputStream fos = new FileOutputStream("d:\\GM\\log.txt");
			ps = new PrintStream(fos);//��ӡ��
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ps != null) {
			System.setOut(ps);//��system��out����Ϊ�ոյĴ�ӡ�� �ض����׼���
		}
		int ln = 0;
		for (char c = 65; c <= 150; c++) {
			System.out.print(c + " ");
			if (ln++ >= 10) {
				System.out.println();//��ps.println()
				ln = 0;
			}
		}
	}
}
