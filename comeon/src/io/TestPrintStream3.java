package io;

import java.util.*;
import java.io.*;

public class TestPrintStream3 {

	// ��TestTransForm2.java����
	
	//������������ܵ� 
	//InputStream->InputStreamReader->BufferedReader
	//�ӿ���̨��ȡ�ַ� BufferedReader����һ��һ�еĶ� ����
	//Ȼ��ѵõ��Ļ��ɴ�д�����console��logfile.log
	// Ȼ����"d:\\GM\\logfile.log"������Կ���д����־
	public static void main(String[] args) {
		String s = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			FileWriter fw = new FileWriter("d:\\GM\\logfile.log", true); // Log4J
			PrintWriter log = new PrintWriter(fw);
			while ((s = br.readLine()) != null) {
				// readLine()�Ի��з�Ϊ��־ ����ʽ�� �����ڿ���̨����ʱ ֻ�а��»س��� �Ż��ӡ��д
				if (s.equalsIgnoreCase("exit"))
					break;
				System.out.println(s.toUpperCase());
				log.println("-----");
				log.println(s.toUpperCase());
				log.flush();
			}
			log.println("===" + new Date() + "===");
			log.flush();
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}