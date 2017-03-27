package io;

import java.io.*;

//�����ļ���һ��һ��д10��С��  Ȼ��һ��һ�еĶ�������ӡ�ڿ���̨�� 
public class TestBufferStream2 {
	public static void main(String[] args) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("d:\\GM\\dat2.txt"));
			BufferedReader br = new BufferedReader(new FileReader("d:\\GM\\dat2.txt"));
			String s = null;
			for (int i = 1; i <= 10; i++) {
				s = 10 * Math.random() + "";
				bw.write(s);
				bw.newLine();
			}
			bw.flush();
			while ((s = br.readLine()) != null) {// readLine()�Ի��з�Ϊ��־ ����ʽ��
				System.out.println(s);
			}
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}