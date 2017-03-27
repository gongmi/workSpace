package TCP_MultiThread;

import java.io.*;
import java.net.*;

public class ServerThread implements Runnable {
	Socket s = null;
	BufferedReader br = null;

	public ServerThread(Socket s) throws IOException {
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	public void run() {
		try {
			String content = null;
			// 采用循环不断从Socket中读取客户端发送过来的数据
			while ((content = readFromClient()) != null) {
				// 遍历socketList中的每个Socket， 将读到的内容向每个Socket发送一次
				for (Socket s : MyServer.socketList) {
					if (s != this.s) {
						PrintStream ps = new PrintStream(s.getOutputStream());
						ps.println(content);
						System.out.println(content);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 定义读取客户端数据的方法
	private String readFromClient() {
		try {
			return br.readLine();
		}
		// 如果捕捉到异常，表明该Socket对应的客户端已经关闭
		catch (IOException e) {
			// 删除该Socket。
			MyServer.socketList.remove(s); // ①
		}
		return null;
	}
}
