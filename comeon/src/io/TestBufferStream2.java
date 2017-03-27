package io;

import java.io.*;

//先往文件中一行一行写10行小数  然后一行一行的读出来打印在控制台上 
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
			while ((s = br.readLine()) != null) {// readLine()以换行符为标志 阻塞式的
				System.out.println(s);
			}
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}