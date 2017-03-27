package Socket;

//*		1. Java Socket编程步骤
//*		2. Socket/ServerSocket类用法
//*		3. 通过Socket对象可以获取通信对方Socket的信息

// 我包装成了PrintStream
import java.net.*;
import java.io.*;

//
public class TestServer {
	public static void main(String args[]) {
		try {
			ServerSocket s = new ServerSocket(8888);
			while (true) {
				Socket s1 = s.accept();
				OutputStream os = s1.getOutputStream();
//				DataOutputStream dos = new DataOutputStream(os);
//				dos.writeUTF("Hello," + s1.getInetAddress() + "port#" + s1.getPort() + "  bye-bye!");
				PrintStream ps=new PrintStream(os);
				ps.println("Hello," + s1.getInetAddress() + "port#" + s1.getPort() + "  bye-bye!");
				// 得到的是client端Socket构造函数写的ip即 127.0.0.1
				// 得到的是client端随机分配的port
//				dos.close();
				ps.close();
				s1.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("程序运行出错:" + e);
		}
	}
}