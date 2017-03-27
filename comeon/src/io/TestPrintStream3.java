package io;

import java.util.*;
import java.io.*;

public class TestPrintStream3 {

	// 与TestTransForm2.java类似
	
	//这里用了三层管道 
	//InputStream->InputStreamReader->BufferedReader
	//从控制台读取字符 BufferedReader可以一行一行的读 好用
	//然后把得到的换成大写输出到console与logfile.log
	// 然后在"d:\\GM\\logfile.log"里面可以看到写的日志
	public static void main(String[] args) {
		String s = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			FileWriter fw = new FileWriter("d:\\GM\\logfile.log", true); // Log4J
			PrintWriter log = new PrintWriter(fw);
			while ((s = br.readLine()) != null) {
				// readLine()以换行符为标志 阻塞式的 所以在控制台输入时 只有按下回车键 才会打印大写
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