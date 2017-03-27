package TCP_MultiThread;

import java.io.*;
import java.net.*;
public class MyClient
{
	public static void main(String[] args)throws Exception
	{
		Socket s = new Socket("127.0.0.1" , 30000);
		// 客户端启动ClientThread线程 不断读取来自服务器的数据
		new Thread(new ClientThread(s)).start();   // ①

	
		
		// 不断读取键盘输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Socket对应的输出流
		PrintStream ps = new PrintStream(s.getOutputStream());
		String line = null;
		while ((line = br.readLine()) != null)
		{
			// 将用户的键盘输入内容    写入Socket对应的输出流
			ps.println("端口为"+s.getLocalPort()+"的客户说:"+line);
		}
	}
}
