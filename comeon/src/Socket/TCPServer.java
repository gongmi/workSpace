package Socket;

import java.net.*;
import java.io.*;

//我想当然的添加了服务器的各种状态 但是似乎并不正确
public class TCPServer {
	public static void main(String[] args) throws Exception {
		int n = 1;
		ServerSocket ss = new ServerSocket(9999);
		System.out.println("服务器进入Listen状态");
		Thread.sleep(10000);
		while (true) {
			System.out.println("准备接受用户" + n + "的连接了");
			Socket socket = ss.accept(); // 一直等待着用户的连接 阻塞式
			System.out.println("服务器和用户" + n + "连接成功 服务器进入Established状态");
			Thread.sleep(20000);
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			System.out.println(dis.readUTF()); // 一直等待着用户的数据 阻塞式
			// 所以当有用户发数据过来很慢 程序一直卡在这里 别的用户无法运行accept
			System.out.println("接收客户端的信息 依然是Established状态");
			dis.close();
			socket.close();
			System.out.println("服务器进入close-wait状态");
			Thread.sleep(20000);
			n++;
		}
	}
}