package TCP_MultiThread2;

import java.net.*;
import java.io.*;

public class Server {
	private static final int SERVER_PORT = 30000;
	// 使用CrazyitMap对象来保存每个 客户名字 和 对应输出流 之间的对应关系。
	public static CrazyitMap<String, PrintStream> clients = new CrazyitMap<>();

	public void init() {
		try (
		ServerSocket ss = new ServerSocket(SERVER_PORT)) {
			// 采用死循环来不断接受来自客户端的请求
			while (true) {
				Socket socket = ss.accept();
				new ServerThread(socket).start();
			}
		}
		catch (IOException ex) {
			System.out.println("服务器启动失败，是否端口" + SERVER_PORT + "已被占用？");
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.init();
	}
}
