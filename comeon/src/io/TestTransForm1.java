package io;

import java.io.*;

//用两种不同的方式往文件里面写字符
public class TestTransForm1 {
	public static void main(String[] args) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(
					"d:\\GM\\char1.txt"));
			osw.write("mircosoftibmsunapplehp");
			System.out.println(osw.getEncoding());
			osw.close();
			
			osw = new OutputStreamWriter(new FileOutputStream("d:\\GM\\char2.txt", true),
					"ISO8859_1"); // latin-1
			osw.write("mircosoftibmsunapplehp");
			System.out.println(osw.getEncoding());
			osw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}