package Socket;

import java.net.*;
import java.io.*;
public class TCPClient {
	public static void main(String[] args) throws Exception {
		int n = 1;
		while (true) {
			System.out.println("�û�" + n + "׼��������...");
			Socket socket = new Socket("127.0.0.1", 9999);
			System.out.println("�û�" + n + " ������� �û�����Establish״̬ ");
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF("hello �ͻ��ˣ������û�" + n);
			dos.flush();
			dos.close();
			n++;
			Thread.sleep(100000);
		}
	}
}
