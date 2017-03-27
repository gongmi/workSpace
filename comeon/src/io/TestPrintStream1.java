package io;

import java.io.*;
//打印字符 显示在文件中 也就是往文件中写字符
public class TestPrintStream1 {
	public static void main(String[] args) {
		PrintStream ps = null;
		try {
			FileOutputStream fos = new FileOutputStream("d:\\GM\\log.txt");
			ps = new PrintStream(fos);//打印流
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ps != null) {
			System.setOut(ps);//把system的out设置为刚刚的打印流 重定向标准输出
		}
		int ln = 0;
		for (char c = 65; c <= 150; c++) {
			System.out.print(c + " ");
			if (ln++ >= 10) {
				System.out.println();//即ps.println()
				ln = 0;
			}
		}
	}
}
