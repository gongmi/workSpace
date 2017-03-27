package Socket;

import java.net.*;
import java.io.*;
public class TCPClient {
	public static void main(String[] args) throws Exception {
		int n = 1;
		while (true) {
			System.out.println("用户" + n + "准备连接了...");
			Socket socket = new Socket("127.0.0.1", 9999);
			System.out.println("用户" + n + " 连接完毕 用户进入Establish状态 ");
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF("hello 客户端，我是用户" + n);
			dos.flush();
			dos.close();
			n++;
			Thread.sleep(100000);
		}
	}
}
