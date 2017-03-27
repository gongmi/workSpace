package Socket;

import java.net.*;
//
//讲了TestSockServer +TestSockClient这两个程序 双向通信 不能交流 一个人说一句程序结束了
//必须要一个先说话 因为dis.readUTF()是阻塞式的 如果两边都不先说话 那么就会卡住
import java.io.*;

public class TestSockClient {
	public static void main(String[] args) {
		InputStream is = null;
		OutputStream os = null;
		try {
			Socket socket = new Socket("localhost", 5888);
			is = socket.getInputStream();
			os = socket.getOutputStream();
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);
//			dos.writeUTF("client:hey，服务器 我是客户");	//它先说 或者我先说
			String s = null;
			if ((s = dis.readUTF()) != null)
			System.out.println(s);
			dos.writeUTF("client:hey，服务器 我是客户");
			dos.close();
			dis.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
