package io;

import java.io.*;
import java.util.Scanner;
//������������ܵ� 
//InputStream->InputStreamReader->BufferedReader
//�ӿ���̨��ȡ�ַ� BufferedReader����һ��һ�еĶ� ����
//Ȼ��ѵõ��Ļ��ɴ�д���������̨

public class TestTransForm2 {
	public static void main(String args[]) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		try {
			s = br.readLine();
			while (s != null) {
				if (s.equalsIgnoreCase("exit"))
					break;
				System.out.println(s.toUpperCase());
				s = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
} // ����