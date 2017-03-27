package io;

import java.io.*;
import java.util.Scanner;
//这里用了三层管道 
//InputStream->InputStreamReader->BufferedReader
//从控制台读取字符 BufferedReader可以一行一行的读 好用
//然后把得到的换成大写输出到控制台

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
} // 阻塞